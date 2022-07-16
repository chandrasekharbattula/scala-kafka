package test

import org.apache.spark.sql.SparkSession

object Utils {

  def createSparkSession(): SparkSession = {
    SparkSession.builder()
      .appName("SparkTest")
      .master("local[*]")
      .getOrCreate()
  }

}
