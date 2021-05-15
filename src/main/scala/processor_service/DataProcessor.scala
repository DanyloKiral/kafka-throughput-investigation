package processor_service

import java.time.LocalDateTime
import common.{Configs, RedditComment}
import org.json4s._
import org.json4s.jackson.JsonMethods._

class DataProcessor {
  implicit val formats = org.json4s.DefaultFormats
  private val dataWriter = new DataWriter(Configs.OutputFileName)
  dataWriter.initOutputFile()

  def processMessage(message: String): Unit = {
    // todo: wrap everything to docker
    val redditComment = parse(message).extract[RedditComment]
    Thread.sleep(1000)
    dataWriter.writeRecord(
      redditComment.id, redditComment.author, redditComment.comment,
      redditComment.createdAt, redditComment.processingStartedAt, LocalDateTime.now, 0) // todo: message size???
  }
}
