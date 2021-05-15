package processor_service

import common.Configs
import org.json4s._
import org.json4s.jackson.JsonMethods._

class DataProcessor {
  implicit val formats = org.json4s.DefaultFormats
  private val dataWriter = new DataWriter(Configs.OutputFileName)
  dataWriter.initOutputFile()

  def processMessage(message: String): Unit = {
    // todo: access consume performance logs
    // todo: wrap everything to docker
    Thread.sleep(1000)
    val dict = parse(message).extract[Map[String, Any]]
    dataWriter.writeRecord(dict)
  }
}
