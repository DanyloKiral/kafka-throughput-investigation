package processor_service

import java.util.Properties
import java.util
import scala.jdk.CollectionConverters._
import org.apache.kafka.clients.consumer.{ConsumerConfig, KafkaConsumer}
import java.time.Duration

class Consumer (kafkaBrokerAddress: String) {
  private val props = new Properties()
  props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaBrokerAddress)
  props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer")
  props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer")
  props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest")
  props.put(ConsumerConfig.GROUP_ID_CONFIG, "consumer-group")
  private val consumer = new KafkaConsumer[String, String](props)

  def processMessages(processFunc: String => Unit, topic: String): Unit = {
    consumer.subscribe(util.Arrays.asList(topic))

    while (true) {
      consumer.poll(Duration.ofSeconds(10)).asScala
        .foreach(r => processFunc(r.value))
    }
  }

  def close(): Unit =
    consumer.close
}
