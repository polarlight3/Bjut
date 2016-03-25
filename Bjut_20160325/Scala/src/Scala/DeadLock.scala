package Scala

/**
  * Created by hadoop on 3/21/16.
  */
object DeadLock {
  def main(args: Array[String]) {
    val la = new Lock(false)
    val lb = new Lock(true)
    la.start()
    lb.start()
  }
}

class Lock extends Thread {
  var flag = true
  def this(boolean: Boolean){
    this()
    flag = boolean
  }
  override def run(){
    if(flag == true){
      MyLock.locka.synchronized{
        println("lockA");
        Thread.sleep(1000)
        MyLock.lockb.synchronized{
          println("lockB")
        }
      }
    }
    else{
      MyLock.lockb.synchronized{
        println("lockB");
        Thread.sleep(1000)

        MyLock.locka.synchronized{
          println("lockA")
        }
      }
    }
  }
}

object MyLock{
  var locka = new Object
  var lockb = new Object
}