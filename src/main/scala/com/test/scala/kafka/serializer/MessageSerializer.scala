package com.test.scala.kafka.serializer

import com.google.gson.Gson
import com.test.scala.kafka.model.Message
import org.apache.kafka.common.header.Headers
import org.apache.kafka.common.serialization.Serializer

import java.nio.charset.Charset

class MessageSerializer extends Serializer[Message] {

  private val gson: Gson = new Gson()

  override def serialize(topic: String, data: Message): Array[Byte] = {
    gson.toJson(data).getBytes(Charset.forName("UTF-8"))
  }

}
