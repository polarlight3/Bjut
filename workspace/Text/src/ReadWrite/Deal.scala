package ReadWrite
import scala.io.Source

object Deal {
  def main(args: Array[String]): Unit = {
      val source = Source.fromFile("/home/hadoop/Desktop/haha", "UTF-8")
      val contents = source.mkString
      print(contents)
      for(c <- contents){
        if(c >= 97 && c <= 123)
          print((c - 32).asInstanceOf[Char])
        else
          print(c.asInstanceOf[Char])
      }
      source.close()
  }
}