object app3 extends App {
    def replicate[T](x : T, number : Int):List[T] = {
        if (number > 0) {
            List(x) ++ replicate(x, number - 1)
        } else Nil
  }


    var value = 10
    var number = 3
  
    var myList = replicate(value, number)

    myList.foreach(element => println(element))

}
