import scala.languageFeature.reflectiveCalls
object app extends App {


    // 0

    println("Task 0: ")

    def Log(prefix: String)(datetime: String)(text: String): String = {
        "[" + prefix + "] " + datetime + " " + text
    }

    val logMessage = Log("WARN")("2023-10-28")("Hello, world!")
    println(logMessage)


    // 1

    println("Task 1: ")

    def Map(myList: List[Int], unaryFunction: Int => Int): List[Int] = {
        myList match {
            case Nil => Nil 
            case head :: tail => unaryFunction(head) :: Map(tail, unaryFunction) 
        }
    }

    val myIntegers = List(1, 2, 3, 4, 5)

    val doubledList = Map(myIntegers, (x: Int) => x * x)

    println(doubledList)


    // 2

    println("Task 2: ")

    def Filter(myList: List[Int], pred: Int => Boolean): List[Int] = {
        myList match {
            case Nil => Nil
            case head :: tail if pred(head) => head :: Filter(tail, pred)
            case head :: tail => Filter(tail, pred)
        }
    }

    val myIntegers2 = List(1,2,3,4,5,6,7,8,9,10)

    val result2 = Filter(myIntegers2, (x: Int) => x % 2 == 0)

    println(result2)

    // 3

    println("Task 3: ")

    def Reduce(myList: List[Int], op: (Int, Int) => Int, acc: Int): Int = {
        myList match {
            case Nil => acc
            case head :: tail => Reduce(tail, op, op(head, acc))
        }
    }

    val myIntegers3 = List(1,2,3)

    val result3 = Reduce(myIntegers3, (x,y) => x+y, 0)

    print(result3)

    // 4

    println("Task 4: ")

    def Average(myList: List[Int]): Float = {
        Reduce(myList, (x,y) => x+y, 0) / myList.length
    }

    val myIntegers4 = List(1,2,3,4,5,6,7,8,9,10)

    val result4 = Average(myIntegers4)

    print("Average of this list is: " + result4)


    // 5

    println("Task 5: ")


    def generateAcronym(myString: String): String = {
        val words = myString.split(" ")  
        val acronym = words.map(_.head.toUpper).mkString  
        acronym
    }

    val inputString = "Zakład Ubezpieczeń Społecznych"
    val acronym = generateAcronym(inputString)

    println(acronym)  

    // 6

    def reduceList(myList: List[Int]): List[Int] = {
        val sum = myList.sum
        def cubeValue(value: Int): Int = value * value * value
        myList match {
            case Nil => Nil
            case head :: tail if cubeValue(head) <= sum => head :: reduceList(tail)
            case _ :: tail => reduceList(tail)
        }
    }

    val myIntegers6 = List(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
    val result6 = reduceList(myIntegers6)
    println(result6)






}