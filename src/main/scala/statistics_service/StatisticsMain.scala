package statistics_service

import akka.actor.typed.ActorSystem
import akka.actor.typed.scaladsl.Behaviors
import akka.http.scaladsl.Http
import akka.stream.scaladsl.Source
import common.Configs
import akka.http.scaladsl.model.{ContentTypes, HttpEntity, HttpResponse, StatusCodes}
import akka.http.scaladsl.server.Directives._
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.scala.DefaultScalaModule

import scala.concurrent.{ExecutionContext, Future}

object StatisticsMain extends App {
  implicit val system = ActorSystem(Behaviors.empty, "kafka-throughput-investigation")
  implicit val executionContext = ExecutionContext.global

  val dataReader = new MongoDataReader(Configs.MongoUri, Configs.MongoDbName, Configs.MongoCollectionName)
  val mapper: ObjectMapper = new ObjectMapper().registerModule(DefaultScalaModule)
  val statisticsAggregator = new StatisticsAggregator(dataReader)

  val serverSource: Source[Http.IncomingConnection, Future[Http.ServerBinding]] =
    Http().newServerAt(Configs.StatisticsHost, Configs.StatisticsPort).connectionSource()

  try {
    serverSource.runForeach { connection =>
      println("Accepted new connection from " + connection.remoteAddress)
      connection.handleWith(
        get {
          concat(
            path("health") {
              handleSync(_ => HttpResponse(StatusCodes.OK))
            },
            path("analytics") {
              handle(_ => statisticsAggregator.collectStatistics()
                  .map(data => mapper.writeValueAsString(data))
                  .map(json => HttpResponse(StatusCodes.OK, entity = HttpEntity(ContentTypes.`application/json`, json))))
            }
          )
        }
      )
    }
  } catch {
    case error: Throwable => println(error)
  } finally {
    // todo: find where to close
    //dataReader.close
  }
}
