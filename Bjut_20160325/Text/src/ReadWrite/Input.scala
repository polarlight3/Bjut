package ReadWrite
import scala.io.Source
import java.io.PrintWriter

object Input {
  def main(args: Array[String]): Unit = {
//    var source1 = Source.fromURI("")
    var source2 = Source.fromString("Hello world~")
//    var sour = Console.readLine()
//    println(sour)
		var sour1 = Source.stdin
		
		val out = new PrintWriter("/home/hadoop/Desktop/haha")
    for( i <- 1 to 100)
      out.print(i + " ")
    out.close()
  }
}