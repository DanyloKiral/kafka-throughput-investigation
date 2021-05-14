package generator_service

import common.Configs

object GeneratorMain extends App {
  val dataGenerator = new DataGenerator(Configs.InputFileName)
  val producer = new Producer(Configs.KafkaBrokerAddress)

  try {
    dataGenerator.processLines(data => producer.sendMessageToTopic(data.id, data.description, Configs.TopicName))
  }
  finally {
    producer.close
  }
}
