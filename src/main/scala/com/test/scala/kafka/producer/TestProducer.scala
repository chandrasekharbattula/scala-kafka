package com.test.scala.kafka.producer

import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.test.scala.kafka.config.KafkaConfig
import com.test.scala.kafka.model.Message
import org.apache.kafka.clients.producer.{Callback, KafkaProducer, ProducerRecord, RecordMetadata}

import java.util
import java.util.Properties
import scala.collection.mutable
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

    val kafkaConfig: KafkaConfig = KafkaConfig(new util.HashMap[String,Object](config.asInstanceOf[util.Map[String, Object]]))

    val kafkaProducer: KafkaProducer[String, Message] = {
      val properties = new Properties()
      properties.put("bootstrap.servers", kafkaConfig.getBootStrapServer)
      properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer")
      properties.put("value.serializer", "com.test.scala.kafka.serializer.MessageSerializer")
      new KafkaProducer[String, Message](properties)
    }


  val message = new Message("testMessage")
  kafkaProducer.send(new ProducerRecord[String, Message](kafkaConfig.getProducerTopicName,"testKey4", message), new producerCallback)
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
