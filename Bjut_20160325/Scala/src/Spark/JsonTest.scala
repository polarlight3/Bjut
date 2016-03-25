package Spark

import net.sf.json._
import scala.io.Source


object testCase {
  def main(args: Array[String]): Unit = {
    val br = Source.fromFile("/home/hadoop/Desktop/Student.json")
    val str = br.mkString
    val json = JSONArray.fromObject(str)
    val tt = json.getJSONObject(0)
    println(tt.getString("Name"))
  }
}

class Person{
  var name:String = null
  var num:String = null
  var balance:String = null
}