package ReadWrite

import scala.io.Source

object Te{
  def main(args: Array[String]): Unit = {
    val source = Source.fromFile("/home/hadoop/Desktop/haha", "UTF-8")
    val lineIterator = source.getLines()
    var num = 1;
    for(i <- lineIterator){
      println("the " + num + " is :" + i)
      num = num + 1;
    }
    source.close()
  }
}