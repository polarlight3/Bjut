package Work

/**
  * Created by hadoop on 3/18/16.
  */
class BTree {
  var M: Int = 0
  var tree: BTNode = null
  var keyMax: Int = 0
  var keyMin: Int = 0

  def createBTree(m: Int): Unit = {
    tree = new BTNode(m)
    tree.isLeaf = true
    keyMax = 2 * m - 1
    keyMin = m - 1
    M = m
  }

  def addNode(tn: Json): Boolean = {
    if (tree == null) {
      println("BTree hasn't been created")
      false
    }
    if (isExit(tn.id)) {
      println("Already Exit")
      false
    }
    else {
      if (tree.keyNum == keyMax) {
        val pNode = new BTNode(M)
        pNode.isLeaf = false
        pNode.children(0) = tree
        splitChild(pNode, 0, tree)
        tree = pNode //Update the root node
      }
      insertNotFull(tree, tn)
      true
    }
  }

  def splitChild(btp: BTNode, st: Int, btc: BTNode): Unit = {
    var i: Int = 0
    val pRightBNode = new BTNode(M)
    pRightBNode.isLeaf = btc.isLeaf
    pRightBNode.keyNum = keyMin
    for (i <- 0 until keyMin){
      pRightBNode.values(i) = btc.values(i + keyMin + 1)
      pRightBNode.ids(i) = btc.ids(i + keyMin + 1)
    }
    if(!btc.isLeaf){
      for(i <- 0 to keyMin)
        pRightBNode.children(i) = btc.children(i + keyMin + 1)
    }
    btc.keyNum = keyMin
    i = btp.keyNum
    while (i > st) {
      btp.children(i + 1) = btp.children(i)
      btp.values(i) = btp.values(i - 1)
      i -= 1
    }
    btp.keyNum += 1
    btp.children(st + 1) = pRightBNode
    btp.ids(st) = btc.ids(keyMin)
    btp.values(st) = btc.values(keyMin)
  }

  def insertNotFull(bt: BTNode, js: Json): Unit = {
    var i: Int = bt.keyNum
    val gap = findGap(bt, js.id)
    if (bt.isLeaf) {
      while (i > gap) {
        bt.ids(i) = bt.ids(i - 1)
        bt.values(i) = bt.values(i - 1)
        i -= 1
      }
      bt.ids(i) = js.id
      bt.values(i) = js.name
      bt.keyNum += 1
    }
    else {
      var pChild = bt.children(gap)
      if (pChild.keyNum == keyMax) {
        splitChild(bt, gap, pChild)
        if (js.id > bt.ids(gap))
          pChild = bt.children(gap + 1)
      }
      insertNotFull(pChild, js)
    }
  }

  def search(num: Int, tr: BTNode = tree): String = {
    if (tr.ids.contains(num))
      tr.values(tr.ids.indexOf(num))
    else if (tr.isLeaf)
      "Not Exist"
    else
      search(num, tr.children(findGap(tr, num)))
  }

  def isExit(num: Int, tr: BTNode = tree): Boolean = {
    if (tr.ids.contains(num))
      true
    else if (tr.isLeaf)
      false
    else
      isExit(num, tr.children(findGap(tr, num)))
  }

  def findGap(bt:BTNode, num: Int): Int = {
    var j = 0
    for (i <- 0 until bt.keyNum if bt.ids(i) < num)
      j += 1
    j
  }
}

