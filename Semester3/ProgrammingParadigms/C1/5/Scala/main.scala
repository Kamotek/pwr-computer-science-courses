object app5 extends App {
    def palindrome[T](list : List[T]):Boolean = {
        list == list.reverse
    }

    val myList = List("a","d","a")

    println(palindrome(myList))
}
