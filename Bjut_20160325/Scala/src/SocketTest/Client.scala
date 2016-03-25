package SocketTest

import java.io._
import java.net.Socket

object Client{
  def main(args: Array[String]) {
    val socket = new Socket("localhost", 9999)
    val out = new ObjectOutputStream(
      new DataOutputStream(socket.getOutputStream)
    )
    val in = new ObjectInputStream(
      new DataInputStream(socket.getInputStream)
    )
    var re = readLine("me: ")
    while (re != "byebye"){
      out.writeObject(re)
      out.flush()
      val res = in.readObject()
      println("client : "+ res)
      re = readLine("me:")
    }
    out.writeObject(re)
    out.flush()
    println("client : "+ in.readObject())
    out.close()
    in.close()
    socket.close()
  }
}