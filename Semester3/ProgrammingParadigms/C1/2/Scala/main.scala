object app2 extends App {
    def count[T](value : T, list : List[T]):Int = {

    if (list != null && list.nonEmpty) {
      val isEqual = if (value == list.head) 1 else 0
      isEqual + count(value, list.tail)
    } else 0
  }


    var myList = List(1,2,1,2,3,4,2,7,8,3,6,2)

    var result = count(2, myList)

    println(result)
}