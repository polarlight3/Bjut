package Spark

import scala.collection.mutable.ArrayBuffer

/**
  * Created by hadoop on 2/25/16.
  */
object Comput {
  def sum(pa:Int *) = {
    var result = 0
    for(i <- pa) result += i
    result
  }

  def main(args: Array[String]): Unit = {
   //ln(sum(1,2,3,6,5,4,7))
    lazy val file = scala.io.Source.fromFile("/asdf")
    val b = ArrayBuffer[Int]()

    b += 1
    println(b)
    b += (2,13,4)
    println(b)
    b ++= Array(4,5,6)
    println(b)
    b.trimEnd(2)
    println(b)
    println(b.sum)
    println(b.min)
    println(b.max)
    var b1 = b.sorted
    println(b1.mkString(" and "))
    scala.util.Sorting.quickSort(b1.toArray)
    val a = b.filter { _ % 2 == 1 }.map { 2 * _ }
    println(a.mkString("< ", " , ", " >"))
//    for(i <- 0 to (10, 2))     printf("%d ", i);println()
//    for(i <- (0 to 10).reverse)    printf("%d ", i);println()
//    for(i <- 10 to 1)      printf("%d ", i)
  }

}
