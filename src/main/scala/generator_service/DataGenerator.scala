package generator_service

import common.RedditData

import scala.io.Source
import org.json4s._
import org.json4s.jackson.JsonMethods._

class DataGenerator (fileName: String) {
  implicit val formats = org.json4s.DefaultFormats

  def processLines(processFunc: RedditData => Unit): Unit = {
    Source.fromFile(fileName).getLines
      .map(json => (json, parse(json).extract[Map[String, Any]]))
      .map(data => RedditData(data._2("id").toString, data._1))
      .foreach(processFunc(_))
  }
}
