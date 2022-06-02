package com.test.scala.kafka

import com.test.scala.kafka.consumer.TestConsumer
import com.test.scala.kafka.producer.TestProducer

object ConsumerApp extends App {

//  val topicName = "test-topic"
//  val consumerGroupId = "test-group-id"
//  val kafkaBrokers = "localhost:9092"
//
//  val kafkaConsumer = new TestConsumer(topicName, consumerGroupId, kafkaBrokers)
//
//  kafkaConsumer.processRecords()

    val kafkaProducer = new TestProducer("test_topic")

}
