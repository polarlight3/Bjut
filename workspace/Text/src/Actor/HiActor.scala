package Actor

import scala.actors.Actor._
import scala.actors.Actor

class HiActor extends Actor{
  def act(){
    while(true){
      receive{
        case "Hi" => println("hello")
      }
    }
  }
}

object Lf{
	def main(args: Array[String]): Unit = {
	  var str = "Hi"
			var actor1 = actor{
			  while(true)
			  receive{
			    case "Hi" => println("What's wrong with you !\n")
			     sender ! "hehe"
			  }
			}
			actor1.start()
			val reply = actor1 !? str
			
			println(reply)
	}
}