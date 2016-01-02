package Class

class Test(ages: Int){
  private var age = ages
}

object Test{

  def apply(num: Int){//return compain object
    new Test(num)
  }

  def main(args: Array[String]): Unit = {
    var act = Test(24)
   
    //act.isInstanceOf[Test]
  }
  
}

