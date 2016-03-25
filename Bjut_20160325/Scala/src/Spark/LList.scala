package Spark


object LList {
  def main(args: Array[String]): Unit = {
    var li = List("kobe","curry","jamse")
    var ll = "a1"::"a2"::"a3"::Nil
    li = li ::: li
    li.foreach { println }
  }
}