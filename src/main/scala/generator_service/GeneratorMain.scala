package generator_service

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.scala.DefaultScalaModule
import common.Configs

object GeneratorMain extends App {
  implicit val mapper = new ObjectMapper().registerModule(DefaultScalaModule)
  val dataGenerator = new DataGenerator(Configs.InputFileName)
  val producer = new Producer(Configs.KafkaBrokerAddress)

  try {
    dataGenerator.processLines(data => producer.sendMessageToTopic(data.id, data, Configs.TopicName))
  }
  finally {
    producer.close
  }
}
