
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
    def divide(divisor: Result[Int], dividend: Int): Result[Int] = {
        divisor match {
            case Success(value) if value != 0 =>
                Success(dividend / value)
            case Success(_) =>
                Failure("Division by zero")
            case Failure(message) =>
                Failure(message)
        }
    }

    def divideX(divisor: Int, divident: Int): Result[Int] = {
        if(divident!=0) {
            Success(divident/divisor)
        } else {
            Failure("Divison by 0")
        }

    }


    // ---

// funkcja wczytujaca wartosc z pliku i obliczjaca jej sume, druga w sekwencji
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
                 divide(Success(numbers.map(_.get).sum), 10000)
                 //Success(numbers.map(_.get).sum)
                }
            }
        }
        catch {
            case e: Exception => Failure(s"Error reading file: ${e.getMessage}")
        }
    }

// pierwsza funkcja w sekwencji, wczytuje haslo


    def sumNumbersWithInputValidation(filename: String): Result[Int] = {
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

    val data: Result[Int] = sumNumbersWithInputValidation("example.txt")
    println(data)
   
    // ---

    def handleResult[X, Y](resultMonad: Result[X], unaryFun: X => Result[Y]): Result[Y] = {
        resultMonad match {
            case Success(value) => unaryFun(value)
            case Failure(message) => Failure(message)
        }
    }


    def double(value: Int): Result[Int] = {
        Success(value*2)
    }

    println(handleResult(data, double))

    //
    
    val result = sumNumbersWithInputValidation("example.txt") >>= { sum =>
        sumNumbersFromFile("example.txt") >>= { sum =>
            divideX(sum, 10000)
        }
    }

    println("----")
    println(result)


}