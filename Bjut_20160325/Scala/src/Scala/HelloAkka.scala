package Scala

import akka.actor.{Props, ActorSystem}

/**
  * Created by hadoop on 3/22/16.
  */
object HelloAkka {
  def main(args: Array[String]) {
    var system = ActorSystem.create("HelloAkka")

  }
}
