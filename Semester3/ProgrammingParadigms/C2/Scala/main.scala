import scala.annotation.tailrec
object app extends App {
    // Zad1 - myślę, że 1

    // 2

    // a

    def fib(n: Int): Int = {
        if(n <= 1) {
            n
        }
        else {
            fib(n-1) + fib(n-2)
        }
    }

   // val result2a = fib(15)

   // println(result2a)

    for (i <- 0 until 20) {
        val result = fib(i)
        println(s"Fibonacci($i) = $result")
    }


    // b
        def fibTail(n: Int): Int = {
            @tailrec
            def fibHelper(n: Int, a: Int, b: Int): Int = {
                if (n == 0) {
                    a
                }
                else if (n == 1) {
                    b
                }
                else fibHelper(n - 1, b, a + b)
            }

            if (n <= 1) n
            else fibHelper(n, 0, 1)
        }
        
  //  val result2b = fibTail(15)

  //  println(result2b)

    for (i <- 0 until 20) {
        val result = fibTail(i)
        println(s"FibonacciTail($i) = $result")
    }

    // 3

    def root3a(a: Double): Double = {
        def root3aHelper(x: Double, epsilon: Double): Double = {
            val nextX = x + (a / (x * x) - x) / 3.0
            if (Math.abs(nextX * nextX * nextX - a) <= epsilon * Math.abs(a)) {
                nextX
            } 
            else {
                root3aHelper(nextX, epsilon)
            }
        }
        val initialX = if (a > 1) a / 3.0 else a
        val epsilon = 1e-15
        root3aHelper(initialX, epsilon)
    }

    val result3a = root3a(16.0) // Example: finding the cube root of 27
    println(result3a)


// 3b

    def root3b: Double => Double = (a: Double) => {
        def root3bHelper(x: Double, epsilon: Double): Double = {
            val nextX = x + (a / (x * x) - x) / 3.0
            if (Math.abs(nextX * nextX * nextX - a) <= epsilon * Math.abs(a)) {
                nextX
            } 
            else {
                root3bHelper(nextX, epsilon)
            }
        }
        val initialX = if (a > 1) a / 3.0 else a
        val epsilon = 1e-15
        root3bHelper(initialX, epsilon)
    }

    val result3b = root3b(16.0) 
    println(result3b)

    // 4

    val pattern = List(-2, -1, 0, 1, 2)

    pattern match {
        case _ :: _ :: x :: _ :: _ => println(s"x = $x")
        case _ => println("Brak dopasowania")
    }   

    val pattern2 = List((1, 2), (0, 1))




    pattern2 match {
        case List(_, (x, _)) => println(s"x = $x")
        case _ => println("Brak dopasowania")
    }

    // 5
    def initSegment[A](xs: List[A], ys: List[A]): Boolean = {
        @annotation.tailrec
        def checkSegment(xstail: List[A], ystail: List[A]): Boolean = (xstail, ystail) match {
            case (Nil, _) => true          // Pusta lista jest początkowym segmentem każdej listy.
            case (x :: xsRest, y :: ysRest) if x == y => checkSegment(xsRest, ysRest)
            case _ => false
        }

        checkSegment(xs, ys)
    }

    val myList5a = List(1,2,3,4,5,6)
    val myList5b = List(1,2,3,4,5)
    val myList5c = List(3,2,4,8,1)

    println(initSegment(myList5a, myList5c))


    // 6

    def replaceNth[A](xs: List[A], n: Int, x: A): List[A] = {
        def replaceNthHelper(lst: List[A], index: Int, value: A): List[A] = (lst, index) match {
            case (Nil, _) => Nil
            case (head :: tail, 0) => value :: tail
            case (head :: tail, i) if i > 0 => head :: replaceNthHelper(tail, i - 1, value)
            case _ => lst
         }

        if (n >= 0) {
            replaceNthHelper(xs, n, x)
        } 
        else {
            xs // Return the original list if n is negative
        }
    }

    val originalList = List('o', 'l', 'a', 'm', 'a', 'k', 'o', 't', 'a')    
    val modifiedList = replaceNth(originalList, 1, 's')

    println(modifiedList) // ['o', 's', 'a', 'm', 'a', 'k', 'o', 't', 'a']


}   
