package SocketTest

import java.net.{Socket, DatagramPacket, DatagramSocket, InetAddress}

/**
  * Created by hadoop on 3/15/16.
  */
object UDPSocket {
  def main(args: Array[String]) {
    val socket = new DatagramSocket()
    val tcp = new Socket("localhost", 9998)
    val address = InetAddress.getByName("localhost")
    while (true) {
      val str = readLine("client:")
      val message = new DatagramPacket(str.getBytes(), str.length, address, 9999)
      socket.send(message)
    }
    socket.close()
  }
}

object UDPServer {
  def main(args: Array[String]) {
    val socket = new DatagramSocket(9999)
    val bys = new Array[Byte](100)
    val packet = new DatagramPacket(bys, bys.length)
    while (true) {
      socket.receive(packet)
      println("ip: " + packet.getAddress.getHostAddress + "hostname:" + packet.getAddress.getHostName)
      println("message is: " + new String(bys))
    }
    socket.close()
  }
}