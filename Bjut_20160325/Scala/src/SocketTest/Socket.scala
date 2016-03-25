package SocketTest

import java.io._
import java.net.{ServerSocket, Socket}


/**
  * Created by hadoop on 3/14/16.
  */
object Socket {
  def main(args: Array[String]) {
    val socket = new Socket("localhost", 9999)
    val out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream))
    val in = new BufferedReader(new InputStreamReader(socket.getInputStream))
    val str = "aaabbb"
    out.write(str)
    out.newLine()
    out.flush()
    println("client: "+ in.readLine())
    in.close()
    out.close()
    socket.close()
  }
}

object SocketServer{
  def main(args: Array[String]) {
    val listener = new ServerSocket(9999)
    val socket = listener.accept()
    val br = new BufferedReader(new InputStreamReader(socket.getInputStream))
    val pw = new PrintWriter(socket.getOutputStream, true)
    println("server: " + br.readLine())
    pw.println("bbaa")
    socket.close()
  }
}
