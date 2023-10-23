object app1 extends App{
    def flatten1[T](list: List[List[T]]): List[T] = {
        if(list!=null && list.nonEmpty) {
            list.head ++ flatten1(list.tail)
        } 
        else {
            Nil
        }
    }

    val list = List(List(1,2,3,4), List(5,6,5), List(1,2,3,4))

    val newList = flatten1(list)

    newList.foreach(element => println(element))

}