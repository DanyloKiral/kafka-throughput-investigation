package processor_service

import java.time.LocalDateTime

import common.CommentStatistics
import org.mongodb.scala.{Completed, MongoClient}
import org.mongodb.scala.bson.codecs.Macros._
import org.mongodb.scala.MongoClient.DEFAULT_CODEC_REGISTRY
import org.bson.codecs.configuration.CodecRegistries.{fromRegistries, fromProviders}

class MongoDataWriter (mongoUri: String, mongoDbName: String, mongoCollectionName: String) {
  private val codecRegistry = fromRegistries(fromProviders(classOf[CommentStatistics]), DEFAULT_CODEC_REGISTRY)
  private val mongoClient: MongoClient = MongoClient(mongoUri)
  private val mongoDb = mongoClient.getDatabase(mongoDbName).withCodecRegistry(codecRegistry)
  private val collection = mongoDb.getCollection[CommentStatistics](mongoCollectionName)

  def write(id: String, processingStart: LocalDateTime, processingEnd: LocalDateTime, messageSizeBytes: Int): Unit =
    collection.insertOne(CommentStatistics(id, processingStart, processingEnd, messageSizeBytes))
      .subscribe((_: Completed) => {})

  def close(): Unit =
    mongoClient.close
}
