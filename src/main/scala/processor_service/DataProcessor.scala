package processor_service

import java.time.LocalDateTime
import java.time.temporal.ChronoUnit

import common.{Configs, LocalDateSerializer, RedditComment}
import org.json4s._
import org.json4s.jackson.JsonMethods._

class DataProcessor (private val dataWriter: MongoDataWriter) {
  implicit val formats = org.json4s.DefaultFormats + LocalDateSerializer

  def processMessage(message: String): Unit = {
    val redditComment = parse(message).extract[RedditComment]

    Thread.sleep(1000)
    val messageSizeBytes = message.getBytes.length

    dataWriter.write(redditComment.id, redditComment.processingStartedAt, LocalDateTime.now, messageSizeBytes)
  }
}
