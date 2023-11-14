object app extends App {
    def listLength[T](myList: List[T]): Int = {
        def listLengthHelp[T](myList: List[T], count: Int): Int = {
            myList match{
                case head :: tail => listLengthHelp(tail, count+1)
                case Nil => count
            }
        }
        listLengthHelp(myList, 0)
    }

    val myList = List(1,2,3,4,5,6,7,8,9)

    println(listLength(myList))
}