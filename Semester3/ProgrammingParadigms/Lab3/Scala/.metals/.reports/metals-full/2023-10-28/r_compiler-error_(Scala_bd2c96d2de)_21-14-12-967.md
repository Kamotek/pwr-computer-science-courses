file://<WORKSPACE>/main.scala
### java.lang.AssertionError: NoDenotation.owner

occurred in the presentation compiler.

action parameters:
offset: 1168
uri: file://<WORKSPACE>/main.scala
text:
```scala
object app extends App {


    // 1

    println("Task 1: ")

    def Log(prefix: String)(datetime: String)(text: String): String = {
        "[" + prefix + "] " + datetime + " " + text
    }

    val logMessage = Log("WARN")("2023-10-28")("Hello, world!")
    println(logMessage)


    // 2

    println("Task 2: ")

    def Map(myList: List[Int], unaryFunction: Int => Int): List[Int] = {
        myList match {
            case Nil => Nil 
            case head :: tail => unaryFunction(head) :: Map(tail, unaryFunction) 
        }
    }

    val myIntegers = List(1, 2, 3, 4, 5)

    val doubledList = Map(myIntegers, (x: Int) => x * x)

    println(doubledList)


    // 3

    println("Task 3: ")

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

    // 4

    println("Task 4: ")

    def Reduce(myList: Listp[@@])
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