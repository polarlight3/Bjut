package Actor

import scala.actors.Actor
import scala.actors.Actor._
import scala.actors.Channel

class tt{
  private val l = 2;
}

class Computer extends Actor{
  def act(){
    while(true)
      receive{
        case x => println("hahahaha");exit()
      }
  }
}


object Comput {
  def main(args: Array[String]): Unit = {
		  val computeActor: Computer = new Computer
//      val channel = new Channel[String]
//      channel.receive{
//        case "h" => println("Successful!")
//        case _ => println("sadfsadf")
//      }
       computeActor.start()
       computeActor ! 1
       
  }
}