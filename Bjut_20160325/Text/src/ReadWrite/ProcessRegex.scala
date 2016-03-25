package ReadWrite
import sys.process._
import java.io.File
import scala.util.matching.Regex

object Process {
  def main(args: Array[String]): Unit = {
    "ls -al .." #> new File("/home/hadoop/Desktop/haha")! //redirect the upper level's directory to the file
   // "ls -al .." #| "grep sec"!
    var numPattern = "[0-9]+".r
    for(matcha <- numPattern.findAllIn("jasdofij55 52 5weo6"))
      println(matcha)
    var ll = numPattern.findFirstIn("kljlj5456sdfsdf545d")
    
    println(numPattern.getClass)
    println(ll.getClass)
    
    val itemPattern = "([0-9]+) ([a-z]+)".r
    for(pattern <- itemPattern.findAllIn("53 asdf,446 asdf"))
      println(pattern.getClass)
  }
}