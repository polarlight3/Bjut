package Scala

/**
  * Created by hadoop on 3/21/16.
  */
object ThreadDemo {
  def main(args: Array[String]) {
    val t1 = new Tr
    val t2 = new Tr
    t1.start()
    t2.start()
  }
}

class Tr extends Thread{
  override def run: Unit ={
    for (i <- 0 to 100)
      println(i + " ")
  }
}
