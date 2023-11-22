import scala.collection.mutable.HashMap
import scala.annotation.tailrec


object app extends App {

    
    //1
    def stirling(n: Int, m: Int): Long = {
        (n, m) match {
            case (_, 1) => 1
            case (n, m) if n == m => 1
            case (n, m) => stirling(n-1, m-1) + (m * stirling(n-1, m))
        }
    }

    println(stirling(10, 3))
    println(stirling(10, 3))


   
    val memo: HashMap[(Int, Int), Int] = HashMap()


    def memorized_stirling(n: Int, m: Int): Int = {
        memo.getOrElseUpdate((n, m), (n, m) match {
            case (_, 1) => 1
            case (n, m) if n == m => 1
            case (n, m) => memorized_stirling(n-1, m-1) + (m * memorized_stirling(n-1, m))
        })
    }

    println(memorized_stirling(10, 3))
    println(memorized_stirling(10, 3))

//2

    
    def fun(n: Int): Long = {
        @annotation.tailrec
        def innerMostLoopCount(i: Int, j: Int, k: Int, count: Long): Long = {
            if (i < 3 * n) {
                if (j < 2 * n) {
                    if (k < n) {
                        innerMostLoopCount(i, j, k + 1, count + 1)
                    } 
                    else {
                        innerMostLoopCount(i, j + 1, 0, count)
                    }   
                } 
                else {
                    innerMostLoopCount(i + 1, 0, 0, count)
                }
            } 
            else {
                count
            }
        }

        innerMostLoopCount(0, 0, 0, 0)
    }

    def make_memoize(fun: Int => Long): Int => Long = {
        var memoMap = Map.empty[Int, Long]
        (arg: Int) => {
            if (memoMap.contains(arg)) {
            memoMap(arg)
            } 
            else {
                val result = fun(arg)
                memoMap += (arg -> result)
                result
            }
        }
    }

    val memoized_fun = make_memoize(fun)

    println(memoized_fun(3000))
    println(memoized_fun(3000))


    // 3

    
    var timeBefore = System.currentTimeMillis()
    lazy val lazy_stirling = stirling(30, 10)
    var timeAfter = System.currentTimeMillis()

    var timeExec = timeAfter - timeBefore

    println(s"Time of declaration: $timeExec")


    timeBefore = System.currentTimeMillis()
    println(s"Lazy stirling: $lazy_stirling")
    timeAfter = System.currentTimeMillis()

    timeExec = timeAfter - timeBefore

    println(s"Time of print: $timeExec")
    

    // 4


    lazy val bellStream: Stream[Long] = {
        def loop(n: Int): Stream[Long] = {
            val bellNumber = (1 to n).map(k => stirling(n, k)).sum
            bellNumber #:: loop(n + 1)
        }

        loop(0)
    }

// Example: Take the first 10 Bell numbers from the stream and convert to a List
    val firstTenBellNumbers: List[Long] = bellStream.take(10).toList

// Print the first ten Bell numbers
    println(firstTenBellNumbers)


    def stream_head[T](stream: Stream[T], n: Int): Option[T] = {
        if (n < 0) {
            None // Invalid index, return None
        } else {
            try {
                Some(stream.drop(n).head) // Get the nth element by dropping n-1 elements and taking the head
            } 
            catch {
                case _: Throwable => None // If the stream doesn't have an nth element, return None
            }
        }
    }

    println(stream_head(bellStream, 5))


    def stream_tail[T](stream:  Stream[T], n: Int): Option[Stream[T]] = {
        if (n < 0) {
            None // Invalid index, return None
        } else {
            try {
                Some(stream.drop(n)) // Get the nth element by dropping n-1 elements and taking the head
            } 
            catch {
                case _: Throwable => None // If the stream doesn't have an nth element, return None
            }
        }
    }

    var newStream = stream_tail(bellStream, 5).getOrElse(Stream.empty)

    println(stream_head(newStream, 5))
    


    // c

    // 1

    def nFirstElements[T](stream: Stream[T], n: Int): Option[List[T]] = {
        if(n < 0) {
            None
        }  
        else {
            Some(stream.take(n).toList)
        }
    }

    println(nFirstElements(bellStream, 5))


    def everySecondElement[T](stream: Stream[T], n: Int): Option[List[T]] = {
        if (n < 0) {
            None // Return None for invalid input
        } else {
            def helper(stream: Stream[T], remaining: Int): List[T] = {
                stream match {
                    case head #:: tail if remaining > 0 => head :: helper(tail.drop(1), remaining - 1) // Collect every second element
                    case _ => Nil // Base case: return an empty list
                }
            }

            Some(helper(stream, n * 2)) // Double the required count to account for every second element
        }
    }

// Example usage:

    val result = everySecondElement(bellStream, 5) // Get every second number, first 5 elements
    println(result)


    // 3

    def skipNElements[T](stream: Stream[T], n: Int): Option[Stream[T]] = {
        Some(stream.drop(n))
    }

    // 4

    lazy val naturalStream: Stream[Long] = {
        def naturalLoop(n: Long): Stream[Long] = {
            n #:: naturalLoop(n + 1)
        }
        naturalLoop(0L)
    }




    val naturalNumbers: List[Long] = naturalStream.take(10).toList
    println(naturalNumbers)


    def stream_concat[T](firstStream: Stream[T], secondStream: Stream[T]): Stream[(T, T)] = {
        firstStream.zip(secondStream)
    }

    val myList: List[(Long, Long)] = stream_concat(naturalStream, bellStream).take(5).toList


    println(myList)

    // 5

    def modifyStream(stream: Stream[Long],fun: Long => Long): Stream[Long] = {
        stream.map(fun)
    }

    def fun(n: Long): Long = {
        n*n
    }

    val modifiedStreamNumbers: List[Long] = modifyStream(naturalStream, fun).take(10).toList

    println(modifiedStreamNumbers)
    
}