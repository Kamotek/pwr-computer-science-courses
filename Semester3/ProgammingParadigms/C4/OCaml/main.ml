(* 1

a)

Mamy tutaj do czynienia z 
a) val x: ‘a -> ‘b -> c’

val f1: (‘a -> ’b -> ’c) -> ‘a -> ‘b -> ‘c

b)
val x: ‘a
val y: ‘a list
val f2: ‘a -> ‘a list -> ‘b -> ‘a list


*)

(*2*)


let rec f x = f x;;

(*3*)

type 'a bt = Empty | Node of 'a * 'a bt * 'a bt;;
let breadthBT tree =
	let rec helper = function
		[] -> []
		| Empty :: tail -> helper tail
		| Node(value, leftSubtree, rightSubtree) :: tail ->	 value :: helper (leftSubtree :: rightSubtree :: tail)
	in helper [tree];;

