type 'a llist = LNil | LCons of 'a * 'a llist Lazy.t

let rec lrepeat k llist =
  let rec lrepeat2 i lazylist =
    match (i, lazylist) with
    | (0, LCons(head, tail)) -> LCons(head, lazy (lrepeat k (Lazy.force tail)))
    | (n, LCons(_, tail)) -> lrepeat2 (n - 1) (Lazy.force tail)
    | (_, LNil) -> LNil
  in lrepeat2 k llist

  (*2*)

  let rec fibgen a b =
    LCons(a, lazy (fibgen b (a + b)))
  
  let lfib = fibgen 0 1



  (*3*)

  type 'a lBT = LEmpty | LNode of 'a * (unit ->'a lBT) * (unit -> 'a lBT);;

  (*a*)

  let rec lTree n = 
      LNode(n, (function () -> lTree(2*n)), function () -> lTree(2*n+1));;

  (*b*)

  let toLazyList tree = 
    let rec helper queue = match queue with
      | [] -> LNil
      | LEmpty :: tail -> helper tail
      | LNode(value, leftSubtree, rightSubtree) :: tail ->
          LCons(value, lazy (helper (tail @ [leftSubtree(); rightSubtree()])))
    in helper [tree]