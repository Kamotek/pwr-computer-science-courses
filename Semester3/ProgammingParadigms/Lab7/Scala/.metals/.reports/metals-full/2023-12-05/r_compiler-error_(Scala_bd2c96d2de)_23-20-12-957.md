file://<WORKSPACE>/main.scala
### java.lang.AssertionError: NoDenotation.owner

occurred in the presentation compiler.

action parameters:
offset: 631
uri: file://<WORKSPACE>/main.scala
text:
```scala

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.{Future, Await}
import scala.concurrent.duration._
import scala.util.{Try}


object app extends App {
    sealed trait Result[+X]

    case class Success[X](value: X) extends Result[X]

    case class Failure(message: String) extends Result[Nothing]

// Usage examples
    val successResult: Result[Int] = Success(42)
    val failureResult: Result[String] = Failure("Operation failed")

// Function that performs division and returns a Result monad
    def divide(dividend: Int, divisor: Result[Int]): Result[Int] = {
        if (divisor != Result[@@0) {
            Success(dividend / divisor)
        } 
        else {
            Failure("Division by zero")
        }
    }

// Using the divide function with Result monad

    // ---


 def sumNumbersFromFile(filename: String): Result[Int] = {
  try {
    val source = scala.io.Source.fromFile(filename)
    val lines = source.getLines().toSeq
    source.close()

    if (lines.isEmpty) {
      Failure("Error, empty file")
    } else {
      val numbers = lines.map(line => scala.util.Try(line.toInt))

      if (numbers.exists(_.isFailure)) {
        Failure("A value that isn't a number has been found")
      } else {
        Success(numbers.map(_.get).sum)
      }
    }
  } catch {
    case e: Exception => Failure(s"Error reading file: ${e.getMessage}")
  }
}

val inputWithTimeLimit: scala.concurrent.Future[String] = scala.concurrent.Future {
  print("Enter password: ")
  scala.io.StdIn.readLine()
}

val userInput: Result[String] = scala.util.Try(scala.concurrent.Await.result(inputWithTimeLimit, scala.concurrent.duration.DurationInt(10).seconds)) match {
  case scala.util.Success(password) => Success(password)
  case scala.util.Failure(_: java.util.concurrent.TimeoutException) => Failure("Session expired")
  case scala.util.Failure(ex) => Failure(s"Error: ${ex.getMessage}")
}

val data: Result[Int] = userInput match {
  case Success("haslo-maslo") =>
    divide(1000, sumNumbersFromFile("example.txt")) match {
      case Success(sum) => Success(sum)
      case Failure(errorMessage) => Failure(s"There was an error: $errorMessage")
    }

  case _ => Failure("Permission denied")
}

println(data)
   
    // ---


}
```



#### Error stacktrace:

```
dotty.tools.dotc.core.SymDenotations$NoDenotation$.owner(SymDenotations.scala:2576)
	scala.meta.internal.pc.SignatureHelpProvider$.isValid(SignatureHelpProvider.scala:83)
	scala.meta.internal.pc.SignatureHelpProvider$.notCurrentApply(SignatureHelpProvider.scala:96)
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