package statistics_service

import common.CommentStatistics
import org.mongodb.scala.MongoClient

import scala.concurrent.{ExecutionContext, Future}
import org.mongodb.scala.bson.codecs.Macros._
import org.mongodb.scala.MongoClient.DEFAULT_CODEC_REGISTRY
import org.bson.codecs.configuration.CodecRegistries.{fromProviders, fromRegistries}

class MongoDataReader (mongoUri: String, mongoDbName: String, mongoCollectionName: String)
                      (implicit val executionContext: ExecutionContext) {
  private val codecRegistry = fromRegistries(fromProviders(classOf[CommentStatistics]), DEFAULT_CODEC_REGISTRY)
  private val mongoClient: MongoClient = MongoClient(mongoUri)
  private val mongoDb = mongoClient.getDatabase(mongoDbName).withCodecRegistry(codecRegistry)
  private val collection = mongoDb.getCollection[CommentStatistics](mongoCollectionName)

  def read(): Future[List[CommentStatistics]] =
    collection.find.toFuture.map(_.toList)

  def close(): Unit =
    mongoClient.close
}
