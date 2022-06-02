package com.test.scala.kafka.producer

import com.test.scala.kafka.config.KafkaConfig
import com.test.scala.kafka.model.Message
import org.apache.kafka.clients.producer.{Callback, KafkaProducer, ProducerRecord, RecordMetadata}

import java.util.Properties
import scala.io.{BufferedSource, Source}

class TestProducer(topicName: String) {

  println(s"in Test Producer $topicName")

//  val kafkaConf: Config = ConfigFactory.load()
//  kafkaConf.
//
//  println(kafkaConf.getString("bootstrap-server"))

    val config = new Properties
    val source: BufferedSource = Source.fromInputStream(
      getClass.getClassLoader.getResourceAsStream("application.conf")
    )

    config.load(source.bufferedReader())

    val kafkaConfig: KafkaConfig = KafkaConfig(config)

    val kafkaProducer: KafkaProducer[String, String] = {
      val properties = new Properties()
      properties.put("bootstrap.servers", kafkaConfig.getBootStrapServer())
      properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer")
      properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer")
      new KafkaProducer[String, String](properties)
    }

  val record = new ProducerRecord[String, String](kafkaConfig.getProducerTopicName(),"testKey4", "testValue")
  kafkaProducer.send(record, new producerCallback)
  kafkaProducer.flush()

}

private class producerCallback extends Callback {

  override def onCompletion(metadata: RecordMetadata, exception: Exception): Unit = {
    if (exception != null) {
      exception.printStackTrace()
    } else {
      println(s" $metadata.topic() : + $metadata.offset() : + ${metadata.partition()}")
    }

  }
}
