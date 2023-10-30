object app extends App {

    // 1

    println("Task 1: ")

    def lastElement[T](myList: List[T]): Option[T] = {
        if(myList.isEmpty) {
            None
        }
        else {
            if(myList.length == 1) {
                Some(myList.head)
            }
            else {
                lastElement(myList.tail)
            }
        }
    }

    val myList = List(1, 2, 3, 4, 3, 5)

    val result = lastElement(myList)

    println(result)

    // 2 

    println("Task 2: ")

    def twoLastElements[T](myList: List[T]): Option[List[T]] = {
        if(myList.isEmpty || myList.length == 1) {None}
        else if(myList.length == 2) {
            Some(myList)
        } 
        else {
            twoLastElements(myList.tail)
        }
    }

    val myList2 = List(1, 2, 3, 4, 3, 5)

    val result2 = twoLastElements(myList2)

    result2.foreach(println)

    // 3

    println("Task 3: ")

    def listLength[T](myList: List[T]): Int = {
        if (myList.isEmpty) {
            0 
        }
        else {
            def listLengthHelp[T](myList: List[T], value: Int): Int = {
                if (myList.isEmpty) {
                    value
                } 
                else {
                    listLengthHelp(myList.tail, value + 1)
                }
            }
            listLengthHelp(myList, 0) 
       }
    }


    val myList3 = List(1,2,3,4,5)

    val result3 = listLength(myList3)

    println(result3)

    // 4

    println("Task 4: ")

    def reverseList[T](myList: List[T]): Option[List[T]] = {
        def reverseListHelp(inputList: List[T], result: List[T]): List[T] = {
            inputList match {
                case Nil => result 
                case head :: tail => reverseListHelp(tail, head :: result)
            }
        }

        if (myList.isEmpty) {
            None 
        } 
        else {
            val reversedList = reverseListHelp(myList, List.empty)
            Some(reversedList) 
        }
    }

    val myList4 = List(1,2,3,4,5)

    val result4 = reverseList(myList4)

    result4.foreach(println)

    // 5

    println("Task 5: ")

    def isPalindrome[T](myList: List[T]): Option[Boolean] = {
        if (myList.isEmpty) {
            Some(true)
        }
        else {
            def isPalindromeHelp(inputList: List[T], revList: List[T]): Boolean = {
                inputList match {
                    case Nil => revList == myList
                    case head :: tail => isPalindromeHelp(tail, head :: revList)
                }
            }

            val result = isPalindromeHelp(myList, List.empty)

            Some(result) 
        }
    }

    val myList5 = List("a","b","c")

    val result5 = isPalindrome(myList5)

    println(result5)


    // 6

    println("Task 6: ")

    def deleteDuplicates[T](myList: List[T]): Option[List[T]] = {
        def deleteDuplicatesHelper(inputList: List[T], seen: List[T], result: List[T]): List[T] = {
            inputList match {
                case Nil => result.reverse
                case head :: tail if seen.contains(head) => deleteDuplicatesHelper(tail, seen, result)
                case head :: tail => deleteDuplicatesHelper(tail, head :: seen, head :: result)
            }
        }

        if (myList.isEmpty) {
            Some(myList)
        } 
        else {
            Some(deleteDuplicatesHelper(myList, List.empty, List.empty))
        }
    }

    val myList6 = List(1,2,1,3,4,5,1,2,1,2,1,6,7,8,9)

    val result6 = deleteDuplicates(myList6)

    result6.foreach(println)

    // 7

    println("Task 7: ")

    def removeEvenIndexes[T](myList: List[T]): List[T] = {
        def removeEvenIndexesHelper(myList: List[T], index: Int, result: List[T]): List[T] = {
            myList match {
                case Nil => result.reverse
                case head :: tail if index % 2 == 0 => removeEvenIndexesHelper(tail, index + 1, result)
                case head :: tail => removeEvenIndexesHelper(tail, index + 1, head :: result)
            }
        }

        removeEvenIndexesHelper(myList, 0, List.empty)
    }

    val myList7 = List(1,2,3,4,5,6,7,8,9)

    val result7 = removeEvenIndexes(myList7)

    result7.foreach(println)

    // 8

    println("Task 8: ")

    def isPrime(value: Int): Boolean = {
        if(value <= 1) {
            false
        }
        else if(value <= 3) {
            true
        }
        else if(value % 2 == 0 || value % 3 == 0) {
            false
        }
        else {
            def isPrimeHelp(dividor: Int): Boolean = {
                if(dividor*dividor > value) {
                    true
                }
                else if(value % dividor == 0){
                    false
                }
                else {
                    isPrimeHelp(dividor + 2)
                }
            }
            isPrimeHelp(5)
        }


    }

    val randomNumber = 12

    val result8 = isPrime(randomNumber)

    println(result8)

}