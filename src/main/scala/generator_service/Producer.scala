package generator_service

import java.util.Properties

import org.apache.kafka.clients.producer._

class Producer (kafkaBrokerAddress: String) {
  val props = new Properties()
  props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaBrokerAddress)
  props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer")
  props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer")
  val producer = new KafkaProducer[String, String](props)

  def sendMessageToTopic(key: String, message: String, topic: String): Unit = {
    val record = new ProducerRecord[String, String](topic, key, message)
    producer.send(record).get()
    producer.flush
  }

  def close(): Unit = {
    producer.close()
  }
}
