package common

object Configs {
  val InputFileName = "data/RC_2010-12" //"data/reddit_subreddits.ndjson"
  val TopicName = "reddit-messages"
  val KafkaBrokerAddress = "localhost:9092"

  val MongoUri = "mongodb://localhost:27001"
  val MongoDbName = "KafkaThroughputInvestigation"
  val MongoCollectionName = "ProcessedRedditComments"

  val StatisticsHost = "localhost"
  val StatisticsPort = 1234
}
