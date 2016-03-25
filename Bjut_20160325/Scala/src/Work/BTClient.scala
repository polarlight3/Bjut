package Work


import java.io._
import java.net.{ServerSocket, Socket}

import org.json.JSONObject

import scala.io.Source

/**
  * Created by hadoop on 3/19/16.
  */
object BTClient {
  def main(args: Array[String]) {
    val socket = new Socket("localhost", 9999)
    val dataObj = new ObjectOutputStream(new DataOutputStream(socket.getOutputStream))
    val br = Source.fromFile("/home/hadoop/Desktop/Student1.json")
    val str:String = br.mkString
    val jsobj = new JSONObject(str)
    val stuList = jsobj.getJSONArray("Student")
    for(i <- 0 until stuList.length()){
      val stuObj = stuList.getJSONObject(i)
      val stu = new Student(stuObj.getString("Number"), stuObj.getString("Name"), stuObj.getInt("Balance"))
      dataObj.writeObject(stu)
      dataObj.flush()
    }
    dataObj.writeObject("EOF")
    dataObj.flush()
    dataObj.close()
    socket.close()
  }
}







