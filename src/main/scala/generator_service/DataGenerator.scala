package generator_service

import common.RedditData

import scala.io.Source
import org.json4s._
import org.json4s.jackson.JsonMethods._

class DataGenerator (fileName: String) {
  def processLines(processFunc: RedditData => Unit): Unit = {
    implicit val formats = org.json4s.DefaultFormats

    Source.fromFile(fileName).getLines
      .map(parse(_).extract[Map[String, Any]])
      .map(dict => RedditData(dict("id").toString, dict("description").toString))
      .foreach(processFunc(_))
  }
}
