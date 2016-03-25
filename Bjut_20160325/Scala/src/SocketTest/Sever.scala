package SocketTest

import java.io._
import java.net.ServerSocket


/**
  * Created by hadoop on 3/14/16.
  */
object Sever extends Thread{
  def main(args: Array[String]) {
   try{
     val listener = new ServerSocket(9999)
     new ServerThread(listener.accept()).start()
     listener.close()
   }
    catch {
      case e: IOException =>
        System.err.println("Could not listen on port : 9999.")
        System.exit(-1)
    }

  }
}

