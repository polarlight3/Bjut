package Work

//import org.json4s.jackson.Json

/**
  * Created by hadoop on 3/18/16.
  */
object BTreeTest {
  def main(args: Array[String]) {
    val bt = new BTree()
    val ar = Array[Int](1,2,4,5,6)
    val d1 = new Json(11,"aa")
    val d2 = new Json(12,"ba")
    val d3 = new Json(13,"ca")
    val d4 = new Json(41,"da")
    val d5 = new Json(111,"aa")
    val d6 = new Json(112,"ba")
    val d7 = new Json(113,"ca")
    val d8 = new Json(411,"da")
    val data:Array[Json] = Array(new Json(11,"aa"),new Json(32,"bb"))
    val data1 = new Array[Json](10);
//    data1 = ((11,"aa"),(32,"bb"),(43,"cc"),(24,"dd"),(27,"ee"),(52,"ff"))

    bt.createBTree(2)
    bt.addNode(d1)

    println(bt.search(111))

  }
}


