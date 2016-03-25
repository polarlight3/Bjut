package SocketTest

import java.io._
import java.net.{Socket, SocketException}
import java.util.Date

/**
  * Created by hadoop on 3/14/16.
  */
case class ServerThread (socket: Socket) extends Thread("ServerThread"){

  override  def run(): Unit = {
    try {
      val writer = new PrintWriter(new File("/home/hadoop/Desktop/chatlog"))

      val out = new ObjectOutputStream(
        new DataOutputStream(socket.getOutputStream)
      )
      val in = new ObjectInputStream(
        new DataInputStream(socket.getInputStream)
      )
      var result = in.readObject()
      while (result != "byebye"){
        writer.write("client: "  + result + "\t"+ new Date(System.currentTimeMillis()) + "\n")
        out.writeObject(result)
        writer.write("server: " + result + "\t" + new Date(System.currentTimeMillis()) + "\n")
        result = in.readObject()
      }
      writer.write("client: "  + result + "\t"+ new Date(System.currentTimeMillis()) + "\n")
      out.writeObject(result)
      writer.write("server: "  + result + "\t"+ new Date(System.currentTimeMillis()) + "\n")

      writer.close()
      out.close();
      in.close();
    }
    catch {
      case e : SocketException => ()
      case e : IOException => println("IO Error")
    }
  }
}
