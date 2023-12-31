import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.{Future, Await}
import scala.concurrent.duration._
import scala.util.{Try}


object app extends App {
    sealed trait Result[+X]
    case class Success[X](value: X) extends Result[X]
    case class Failure(message: String) extends Result[Nothing]

    implicit class ResultOps[A](result: Result[A]) {
        def >>= [B](function: A => Result[B]): Result[B] = {
            result match {
                case Success(value) => function(value)
                case Failure(message) => Failure(message)
            }
        }
    }

// testowanie czy dziala
    val successResult: Result[Int] = Success(42)
    val failureResult: Result[String] = Failure("Operation failed")

// operacja dzielenia, trzecia w sekwencji
    def divideByTwo(dividend: Int): Result[Int] = {
        dividend match {
            case x if x % 2 != 0 => Failure("This number is not even!")
            case x => Success(x / 2)
        }
    }

    def sqrtNumber(number: Int): Result[Int] = {
        number match {
            case x if x <= 0 => Failure("This number doesn't have root")
            case x => Success(Math.sqrt(x).toInt)
        }
    }

    // ---

// funkcja wczytujaca wartosc z pliku i obliczjaca jej sume, druga w sekwencji


// pierwsza funkcja w sekwencji, wczytuje haslo


    def sumNumbersWithInputValidation(filename: String): Result[Int] = {
        def sumNumbersFromFile(filename: String): Result[Int] = {
            try {
                val source = scala.io.Source.fromFile(filename)
                val lines = source.getLines().toSeq
                source.close()
                if (lines.isEmpty) {
                    Failure("Error, empty file")
                }
                else {
                    val numbers = lines.map(line => scala.util.Try(line.toInt))
                        if (numbers.exists(_.isFailure)) {
                            Failure("A value that isn't a number has been found")
                        }
                        else {
                            Success(numbers.map(_.get).sum)
                        }
                }
            }
            catch {
                case e: Exception => Failure(s"Error reading file: ${e.getMessage}")
            }
        }

        val inputWithTimeLimit: Future[String] = Future {
            print("Enter password: ")
            scala.io.StdIn.readLine()
        }
        val userInput: Result[String] = Try(Await.result(inputWithTimeLimit, 10.seconds)) match {
            case scala.util.Success(password) => Success(password)
            case scala.util.Failure(_: java.util.concurrent.TimeoutException) => Failure("Session expired")
            case scala.util.Failure(ex) => Failure(s"Error: ${ex.getMessage}")
        }

        val data: Result[Int] = userInput match {
            case Success("haslo-maslo") =>
                sumNumbersFromFile(filename) match {
                    case Success(sum) => Success(sum)
                    case Failure(errorMessage) => Failure(s"There was an error: $errorMessage")
                }
            case _ => Failure("Permission denied")
        } 

        data
    }

    // (f1(f2(f3))) f1 -> f2 -> f3
    val data: Result[Int] = sumNumbersWithInputValidation("example.txt")
    println("---xxx---")
    println(data)
   

    val result: Result[Int] = handleResult(data, divideByTwo)
    println(result)
    val result2: Result[Int] = handleResult(result, sqrtNumber)
    println(result2)
    // ---

    def handleResult[X, Y](resultMonad: Result[X], unaryFun: X => Result[Y]): Result[Y] = {
        resultMonad match {
            case Success(value) => unaryFun(value)
            case Failure(message) => Failure(message)
        }
    }



    println(sumNumbersWithInputValidation("example.txt") >>= divideByTwo >>= sqrtNumber)

    //
    


}