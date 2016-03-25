package Scala

import java.io.PrintWriter

import org.apache.spark.SparkConf

import scala.io.Source

/**
  * Created by hadoop on 1/15/16.
  */
object Chapter1 {
  def main(args: Array[String]) {
    Source.fromFile("/home/hadoop/Desktop/haha").mkString.split("\\s+").filter(_.length() < 5).foreach(println)

  }

}
