package Spark

import org.apache.spark.mllib.linalg.DenseVector

import scala.collection.mutable.{HashMap, HashSet}
import scala.io.Source
import breeze.linalg.Vector

object Collec {
  
  def main(args: Array[String]): Unit = {
//    val file = Source.fromFile("/home/hadoop/Desktop/haha", "UTF-8")
//    file.collect
//    val t =  Array("a","b","c","d")
//    println(file.getClass)
    val points = new HashSet[Vector[Double]]
    val kPoints = new HashMap[Int, Vector[Double]]
    val ar = (for (i <- 0 to 10) yield i.toDouble).toArray
    val tt = new DenseVector(ar)
    points.add(Vector(1,2))
    points.add(Vector(2,3))
    kPoints.put(1, Vector(2))
    points.map(a => println(a.apply(1)))
    println(points.head)
    println(kPoints)
    println(kPoints(1))

  }
}