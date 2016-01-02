package Reset

//协变类型只能作为方法的返回类型，而逆变类型只能作为方法的参数类型

class T1[+T]{
  //def showIn : T
 // def showOut(b:R){println(b.getClass())}
}

class A1 extends T1[String]{}

object Pair {
  def t2(s1:T1[String], s2:T1[Any]){
    println("successful")
  }
  
  def main(args: Array[String]): Unit = {
    var e1 = new T1[String]
    var e2 = new A1
    t2(e1, e1)
    
  }
}