package Scala

/**
  * Created by hadoop on 2/26/16.
  */
object iterators {
  def printArray(ar: Array[Double]): Unit ={
    ar.iterator foreach {x => println(x)}
    Iterator.from(0)
  }

  def main(args: Array[String]) {
    val ar = Array[Double](6, 2, 8, 5, 1)
    printArray(ar)
  }
}
