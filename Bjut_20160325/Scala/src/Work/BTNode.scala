package Work

/**
  * Created by hadoop on 3/22/16.
  */
class BTNode(M: Int) {
  //M :the min number of children
  var keyNum: Int = 0
  //the number of key
  var isLeaf: Boolean = true
  var ids = new Array[Int](2 * M - 1)
  var values = new Array[String](2 * M - 1)
  var children = new Array[BTNode](2 * M)
}

