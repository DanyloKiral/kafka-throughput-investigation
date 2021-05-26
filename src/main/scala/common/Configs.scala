package common

import scala.util.Properties

object Configs {
  val InputFileName = Properties.envOrElse("KFTI_INPUT_FILE_NAME", "data/RC_2010-12")
  val TopicName = Properties.envOrElse("KFTI_KAFKA_TOPIC_NAME", "reddit-messages")
  val KafkaBrokerAddress = Properties.envOrElse("KFTI_KAFKA_BROKER_ADDRESS", "localhost:9092")

  val MongoUri = Properties.envOrElse("KFTI_MONGO_URI", "mongodb://localhost:27001")
  val MongoDbName = Properties.envOrElse("KFTI_MONGO_DB_NAME", "KafkaThroughputInvestigation")
  val MongoCollectionName = Properties.envOrElse("KFTI_MONGO_COLLECTION_NAME", "ProcessedRedditComments")

  val StatisticsHost = Properties.envOrElse("KFTI_STATISTICS_HOST", "localhost")
  val StatisticsPort = Properties.envOrElse("KFTI_STATISTICS_PORT", "1234").toInt
}
