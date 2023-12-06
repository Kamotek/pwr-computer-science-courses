file://<WORKSPACE>/main.scala
### java.lang.AssertionError: NoDenotation.owner

occurred in the presentation compiler.

action parameters:
offset: 1080
uri: file://<WORKSPACE>/main.scala
text:
```scala
object app extends App {

    // 1

    def lreapeat[T](k: Int)(lxs: LazyList[T]): LazyList[T] = {
        def funHelp(n: Int, element: LazyList[T]): LazyList[T] = {
            if (n < k) {
                element match {
                    case x #:: xs => x #:: funHelp(n + 1, element)
                    case _ => LazyList.empty
                }
            }
            else {
                element match {
                    case _ #:: xs => funHelp(0, element) ++ funHelp(0, xs)
                    case _ => LazyList.empty
                }
            }
        }
        funHelp(0, lxs)
    }
 
    // 2

    val lfib = {
        def fibGen(a:Int, b: Int): Stream[Int] = {
            a #:: fibGen(b,a+b)
        }
        fibGen(0,1)
    }

    // 3

    // a

    sealed trait lBT[+A]
    case object LEmpty extends lBT[Nothing]
    case class LNode[+A](elem: A, left:()=>lBT[A], right:()=>lBT[A]) extends lBT[A]


    def lTree(n: Int): lBT[Int] = {
        LNode(n, ()=>lTree(2*n),()=>lTree(2*n+1))
    }

    // b

    def toStream[A](tree: lBT[A]): Strean[]@@

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