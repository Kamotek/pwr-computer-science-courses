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

    def toStream[A](tree: lBT[A]) =  {
        def helper(queue: List[lBT[A]]): Stream[A] = queue match {
            case LEmpty :: tail => helper(tail)
            case LNode(value, leftSubtree, rightSubtree)::tail => value #::helper(tail++List(leftSubtree(), rightSubtree()))
            case Nil => Stream.Empty;
        }
        helper(List(tree))
    }

}

