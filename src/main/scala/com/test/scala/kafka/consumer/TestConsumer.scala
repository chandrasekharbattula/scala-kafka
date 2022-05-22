package com.test.scala.kafka.consumer

import org.apache.kafka.clients.consumer.{ConsumerConfig, KafkaConsumer}
import org.apache.kafka.common.serialization.StringDeserializer
import org.slf4j.{Logger, LoggerFactory}

import java.time.Duration
import java.util
import java.util.concurrent.{ExecutorService, Executors}
import java.util.{Collections, Properties}

class TestConsumer(topicName: String, consumerGroupId: String, kafkaBrokers: String) {

  val logger: Logger = LoggerFactory.getLogger(getClass.getSimpleName)
  val props: Properties = createConsumerConfig(kafkaBrokers, consumerGroupId)
  val kafkaConsumer = new KafkaConsumer[String, String](props)
  val executor : ExecutorService = Executors.newSingleThreadExecutor()

  def createConsumerConfig(kafkaBrokers: String, consumerGroupId: String) : Properties = {
    val props = new Properties()
    props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaBrokers)
    props.put(ConsumerConfig.GROUP_ID_CONFIG, consumerGroupId)
    props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer")
    props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer")
    props
  }

  def processRecords(): Unit = {
    logger.info("Kafka consumer started")
    kafkaConsumer.subscribe(Collections.singleton(topicName))
    val consumerRecords: Thread = new Thread():
      override def run(): Unit = {
        while (true) {
          kafkaConsumer.poll(Duration.ofSeconds(1000L))
            .forEach(record => logger.info(s"Key : ${record.key()} , Value: ${record.value()}, Offset: ${record.offset()}"))
        }
      }
    executor.submit(consumerRecords)
  }

  Runtime.getRuntime.addShutdownHook(new Thread() :
    override def run() : Unit = {
      logger.info("Shutting down gracefully")
      kafkaConsumer.close()
      executor.shutdown()
    }
  )
}

