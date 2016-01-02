package Class

trait Lf{
  val max: Int;
  def sho(message: String)
  def warn(msg: String){
    sho("WARN:" + msg)
  }
}

trait TimeLf extends Lf{
  def ww(message: String){
    warn(message)
  } 
  override def sho(msg: String){
    println(msg)
  }
}

class lf extends TimeLf{
  val max = 10 //the variable that hasn't been initialized needs to be initialized
}

class lf1{}

object Trait2{
  def main(args: Array[String]): Unit = {
    val l = new lf1{
      val max = 12
    }
    l.max
  }
}