package processor_service

import common.Configs

object ProcessorMain extends App {
  val consumer = new Consumer(Configs.KafkaBrokerAddress)
  val dataWriter = new MongoDataWriter(Configs.MongoUri, Configs.MongoDbName, Configs.MongoCollectionName)
  val processor = new DataProcessor(dataWriter)

  try {
    consumer.processMessages(processor.processMessage, Configs.TopicName)
  } catch {
    case error: Throwable => println(error)
  } finally {
    consumer.close
    dataWriter.close
  }
}

