object app extends App {

    sealed trait BT[+A]
    case object Empty extends BT[Nothing]
    case class Node[+A](elem:A, left:BT[A], right:BT[A]) extends BT[A]


    def breadthBT[A](tree: BT[A]) = {
	def helper[A](nodeQueue: List[BT[A]]): List[A] = nodeQueue match {
		case Nil => Nil
		case Empty :: tail => helper(tail)
		case Node(value, leftSubtree, rightSubtree) :: tail => value :: helper(tail ++ List(leftSubtree, rightSubtree))
	}
	helper (List(tree))
}



}