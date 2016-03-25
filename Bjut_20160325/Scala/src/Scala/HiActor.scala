package Scala

import scala.actors.{TIMEOUT, Channel, OutputChannel, Actor}
import util.control.Breaks._

/**
  * Created by hadoop on 3/21/16.
  */

object HiActor {

  case class Speak(line: String)

  case class Gesture(bodyPark: String, action: String)

  case class Negotiate()

  def main(args: Array[String]) {
    /*    def ct = "Thread " + Thread.currentThread().getName() + ":"
        var flag = true
        val actor1 = actor{
          while(flag){
            receive{
              case Negotiate => println(ct + "Ni Mei")
              case Speak(line) => println(ct + line)
              case Gesture(bp, ac) => println(ct + "bodyPart: " + bp + " actor: " + ac )
              case "End" => flag = false
            }
          }
        }
        println(ct)
        actor1 ! Negotiate
        actor1 ! Speak("what's up")
        actor1 ! Gesture("facebook", "Alibaba")
        actor1 ! "End"*/
    val actor1 = new CActor
    val actor2 = new CActor
    actor1.start()
    val reply = actor1 !? "hi"
    println(reply)
  }
}

class CActor extends Actor {
  def act(): Unit = {
    link()
    loop {
      react {
        case "hi" => reply("Hello World")
        case "end" => "This's over"
        case _ => "What do you mean?"
      }
    }
  }
}
