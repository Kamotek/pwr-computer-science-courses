file://<WORKSPACE>/main.scala
### java.lang.AssertionError: NoDenotation.owner

occurred in the presentation compiler.

action parameters:
offset: 2886
uri: file://<WORKSPACE>/main.scala
text:
```scala
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
        if(myList.isEmpty) {None}
        else if(myList.length <= 2) {
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
            0 // Return 0 for an empty list
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
            listLengthHelp(myList, 0) // Call the inner function with an initial value of 0
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

    def deleteRep[T](myList: Listp[@@])
}
```



#### Error stacktrace:

```
dotty.tools.dotc.core.SymDenotations$NoDenotation$.owner(SymDenotations.scala:2576)
	scala.meta.internal.pc.SignatureHelpProvider$.isValid(SignatureHelpProvider.scala:83)
	scala.meta.internal.pc.SignatureHelpProvider$.notCurrentApply(SignatureHelpProvider.scala:94)
	scala.meta.internal.pc.SignatureHelpProvider$.$anonfun$1(SignatureHelpProvider.scala:48)
	scala.collection.StrictOptimizedLinearSeqOps.loop$3(LinearSeq.scala:280)
	scala.collection.StrictOptimizedLinearSeqOps.dropWhile(LinearSeq.scala:282)
	scala.collection.StrictOptimizedLinearSeqOps.dropWhile$(LinearSeq.scala:278)
	scala.collection.immutable.List.dropWhile(List.scala:79)
	scala.meta.internal.pc.SignatureHelpProvider$.signatureHelp(SignatureHelpProvider.scala:48)
	scala.meta.internal.pc.ScalaPresentationCompiler.signatureHelp$$anonfun$1(ScalaPresentationCompiler.scala:375)
```
#### Short summary: 

java.lang.AssertionError: NoDenotation.owner