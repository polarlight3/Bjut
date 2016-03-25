package Spark

import org.apache.spark.{SparkContext, SparkConf}

/**
  * Created by hadoop on 1/11/16.
  */
object WordCount {
  def main(args: Array[String]): Unit = {

    val str = "spark://localhost:7077"
    var path = "hdfs://localhost:9000/result"
    val conf = new SparkConf().setAppName("WCount").setMaster(str)
    val sc = new SparkContext(conf)
    val file = sc.textFile("hdfs://localhost:9000/test")

    //    file.flatMap(_.split("\\s+")).map((_, 1)).reduceByKey(_+_).collect().foreach(println)
    file.flatMap(_.split("\\s+")).map((_, 1)).reduceByKey(_+_).saveAsTextFile(path)

    sc.stop()
  }
}
