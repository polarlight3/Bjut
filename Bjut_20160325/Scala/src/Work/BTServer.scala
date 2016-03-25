package Work

import java.io.{DataInputStream, ObjectInputStream}
import java.net._


/**
  * Created by hadoop on 3/22/16.
  */

object BTServer{
  def main(args: Array[String]) {
    val lisener = new ServerSocket(9999)
    val socket = lisener.accept()
    val br = new ObjectInputStream(new DataInputStream(socket.getInputStream))
    var flag = true

    val slaveSocket = new DatagramSocket()
    val address = InetAddress.getByName("localhost")

    val bt = new BTree()

    bt.createBTree(2)

    while (flag){
      val res = br.readObject()
      if(res != "EOF"){
        val temp = res.asInstanceOf[Student]
        val d1 = new Json(temp.num.substring(1).toInt,temp.name)
        bt.addNode(d1)

        val data = temp.num.substring(1) + ":" +temp.name
        val slaveMessage = new DatagramPacket(data.getBytes(), data.length, address, 10000)
        slaveSocket.send(slaveMessage)
      }
      else
        flag = false
    }


    println(bt.search(201507005))
//    lisener.close()


  }
}