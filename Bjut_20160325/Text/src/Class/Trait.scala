package Class

trait Logger{
  def log(message: String){
    println(message)
  } 
}

trait TimeLogger extends Logger{
  override def log(message: String){
    super.log(new java.util.Date() + " " + message)
  } 
}

trait Short extends Logger{
  val len = 13
  override def log(message: String){
    super.log(
        if(message.length() < len) message
        else message.substring(0,len - 3) + "..."
    )
  } 
}

class Tes{
  private val tt = 13
}

object Trait extends Logger with TimeLogger{
  def show(me: String){
    println("the massage is:")
    log(me)
  }
  def main(args: Array[String]): Unit = {
    var l1 = new Tes with Short
    var l2 = new Tes with TimeLogger
    var lf = new Tes with Short with TimeLogger //IN THE LIST'S LAST,THE FIRST INVOKING 
    var ll = new Tes with TimeLogger with Short
    print("1   :");l1.log("hello world! and you")
    print("2   :");l2.log("hello world! and you")
    print("2 1 :");lf.log("hello world! and you")
    print("1 2 :");ll.log("hello world! and you")
  }
}