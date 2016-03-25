package Work

import java.io.Serializable

/**
  * Created by hadoop on 3/22/16.
  */
class Student extends Serializable{
  var num:String = null
  var name:String = null
  var balance:Int = 0
  def this(nu:String, na:String, ba:Int){
    this()
    this.num = nu
    this.name = na
    this.balance = ba
  }
  override def toString(): String ={
    "Name: " + name + "\tNumber: " + num +"\tBalance: " + balance
  }
}