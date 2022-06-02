package com.test.scala.kafka.config

import java.util.Properties

class KafkaConfig(props: Properties) {

  def getBootStrapServer() = {
    props.getProperty("bootstrap-servers")
  }

  def getProducerTopicName() = {
    props.getProperty("producer-topic-name")
  }
}
