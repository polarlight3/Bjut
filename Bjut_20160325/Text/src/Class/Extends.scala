package Class

class Person(_name: String, _age: Int){
  private var name = _name
  private var age = _age
  def show(){
    println("name : " + name +  "\n age : " + age)
  }
}

class Student(_name: String, _age: Int, _num: Int) extends Person(_name, _age){
  private var name = _name
  private var age = _age
  private var num = _num
 
  override def show(){
    println("name : " + name +  "\n age : " + age + "\n num : " + num)
  }
}

object Tea {
  def main(args: Array[String]): Unit = {
    var lf = new Person("kobe", 24)
    var ll = new Student("james", 23, 23)
    var me = new Person("liufan", 23){
      var pri = println("hellow liufan")      
    }
    me.pri
    
  }
}