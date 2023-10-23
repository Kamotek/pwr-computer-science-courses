object app4 extends App{
    def sqrList(list : List[Int]):List[Int] = {
        if (list != null && list.nonEmpty) {
            List(list.head*list.head) ++ sqrList(list.tail)
        } else Nil
     }

    val myList = List(1,2,3,4,5,6,7,8,9,10)

    val newList = sqrList(myList)

    newList.foreach(element => println(element))
}