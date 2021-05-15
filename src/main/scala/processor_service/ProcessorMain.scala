package processor_service

import common.Configs

object ProcessorMain extends App {
  val consumer = new Consumer(Configs.KafkaBrokerAddress)
  val processor = new DataProcessor()

  try {
    consumer.processMessages(processor.processMessage, Configs.TopicName)
  }
  finally {
    consumer.close
  }
}
