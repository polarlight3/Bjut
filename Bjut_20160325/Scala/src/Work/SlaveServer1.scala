package Work

import java.net.{DatagramPacket, DatagramSocket}

import scala.collection.mutable.ArrayBuffer

/**
  * Created by hadoop on 3/23/16.
  */
object SlaveServer1 {
  def main(args: Array[String]) {
    val data = ArrayBuffer[Json]()
    val socket = new DatagramSocket(10000)
    val bys = new Array[Byte](100)
    while (true){
      val packet = new DatagramPacket(bys, bys.length)
      socket.receive(packet)
      val str = new String(bys)
      val ar = str.split(":")
      data += new Json(ar(0).toInt, ar(1))
      println("ID: "+ data.last.id + "\nName: " + data.last.name)
    }
//    socket.close()
  }
}
