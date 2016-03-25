package Spark

import Work.BTNode

/**
  * Created by hadoop on 3/19/16.
  */
object Test {
  def main(args: Array[String]) {
    var a = 1
    var b = 2
    val c:Byte = 127
    println(sum(a,b))
    println(c)
    println(c)
  }

  def sum(a:Int, b:Int): Unit ={
    a + b
  }
}
