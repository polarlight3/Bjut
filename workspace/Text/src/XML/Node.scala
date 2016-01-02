package XML

import scala.xml._

object Node {
  def main(args: Array[String]): Unit = {
   val list = <ul><li>Kobe</li><li>James</li></ul>
   val list2 = list.copy(label = "lo")
   println(list2)
   println(list.label)
  }
}