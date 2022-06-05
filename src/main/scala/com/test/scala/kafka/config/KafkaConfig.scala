package com.test.scala.kafka.config

import java.util
import java.util.Properties
import java.util.Map

class KafkaConfig(props : util.Map[String, Object]) {


  def getBootStrapServer: String = {
    props.get("bootstrap-servers").toString
  }

  def getProducerTopicName: String = {
    props.get("producer-topic-name").toString
  }
}
