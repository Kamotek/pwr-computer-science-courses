
object app extends App {


    // 2
// curry
    def curry3[A, B, C, D](f: (A, B, C) => D): A => B => C => D =
  (x: A) => (y: B) => (z: C) => f(x, y, z)

// Uncurry a function of arity 3
def uncurry3[A, B, C, D](f: A => B => C => D): (A, B, C) => D =
  (x: A, y: B, z: C) => f(x)(y)(z)


  // 3


    def sumProd(xs: List[Int]): (Int, Int) = xs match {
    case head :: tail =>
        val (s, p) = sumProd(tail)
        (head + s, head * p)
    case Nil => (0, 1)
    }

    val myList = List(1, 2, 3, 4, 5)
    val x = sumProd(myList)

    def printResult(result: (Int, Int)): Unit = {
    val (sum, product) = result
    println(s"Sum: $sum, Product: $product")
    }

    printResult(x)

    def sumProd2(list: List[Int]): (Int, Int) =
    list.foldLeft((0, 1)) { case ((s, p), h) => (s + h, p * h) }

    val y = sumProd2(myList)
    printResult(y)


    // 5 a

    def insert[A](order: ((Int, Int), (Int, Int)) => Boolean, x: (Int, Int), lst: List[(Int, Int)]): List[(Int, Int)] = lst match {
    case Nil => List(x)
    case y :: ys =>
        if (order(x, y)) x :: y :: ys
        else y :: insert(order, x, ys)
    }

    def insertionSort(order: ((Int, Int), (Int, Int)) => Boolean, lst: List[(Int, Int)]): List[(Int, Int)] = lst match {
    case Nil => Nil
    case x :: xs => insert(order, x, insertionSort(order, xs))
    }

    def myOrder(a: (Int, Int), b: (Int, Int)): Boolean = a._1 <= b._1 && a._2 <= b._2

    val sortedList = insertionSort(myOrder, List((1, 2), (1, 1), (1, 3), (2, 1), (2, 2), (1, 4)))

    def printList(lst: List[(Int, Int)]): Unit = {
    print("[")
    lst.foreach { case (a, b) => print(s"($a,$b) ") }
    println("]")
    }

    printList(sortedList)
    println()


    // 5b

    def merge[A](cmp: ((Int, Int), (Int, Int)) => Boolean, list1: List[(Int, Int)], list2: List[(Int, Int)]): List[(Int, Int)] = (list1, list2) match {
    case (Nil, lst) => lst
    case (lst, Nil) => lst
    case (h1 :: t1, h2 :: t2) =>
        if (cmp(h1, h2)) h1 :: merge(cmp, t1, list2)
        else h2 :: merge(cmp, list1, t2)
    }

    def split(lst: List[(Int, Int)]): (List[(Int, Int)], List[(Int, Int)]) = lst match {
    case Nil => (Nil, Nil)
    case List(x) => (List(x), Nil)
    case x :: y :: rest =>
        val (left, right) = split(rest)
        (x :: left, y :: right)
    }

    def mergeSort(cmp: ((Int, Int), (Int, Int)) => Boolean, lst: List[(Int, Int)]): List[(Int, Int)] = lst match {
    case Nil => Nil
    case List(x) => List(x)
    case _ =>
        val (left, right) = split(lst)
        merge(cmp, mergeSort(cmp, left), mergeSort(cmp, right))
    }

    def myOrder2(a: (Int, Int), b: (Int, Int)): Boolean = a._1 <= b._1 && a._2 <= b._2

    val sortedList2 = mergeSort(myOrder2, List((1, 2), (1, 1), (1, 3), (2, 1), (2, 2), (1, 4)))

    def printList1(lst: List[(Int, Int)]): Unit = {
    print("[")
    lst.foreach { case (a, b) => print(s"($a,$b) ") }
    println("]")
    }

    printList1(sortedList2)

}