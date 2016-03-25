
package Class

abstract class person{
  def id: Unit //abstract method
  val num: Int //abstract parameter
  var name: String
  def show(){
    println("I'm kobe")
  }
}

class student(val num: Int) extends person{
  //val num = 23  --type needs to conrrespond to the super argument val 
  var name = ""
  def id = println("where amazing happen")
  override def show(){
    println("I'm lebron")
  }
}

object Override extends App {
  var lf = new student(23)
  println(lf.num)
}