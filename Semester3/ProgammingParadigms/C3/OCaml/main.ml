let print_result (sum, product) =
  print_string "Sum: ";
  print_int sum;
  print_string ", Product: ";
  print_int product;
  print_newline () 


  let print_list lst =
    let rec print_elements = function
      | [] -> ()
      | [x] -> print_int x
      | x :: xs ->
          print_int x;
          print_string "; ";
          print_elements xs
    in
    print_string "[";
    print_elements lst;
    print_string "]"

    let print_list1 lst =
      let rec print_elements = function
        | [] -> ()
        | [(a, b)] -> Printf.printf "(%d,%d)" a b
        | (a, b) :: xs ->
            Printf.printf "(%d,%d); " a b;
            print_elements xs
      in
      print_string "[";
      print_elements lst;
      print_string "]"


(*1
 a)
int -> int -> 'a) -> 'a = <fun>

b)

val f2 : (string -> 'a) -> string -> string -> 'a = <fun>



*)

(*2*)

let curry3 f x y z = f (x,y,z);;

let uncurry3 f (x,y,z) = f x y z;;

(*
curry3 : ('a * 'b * 'c -> 'd) -> 'a -> 'b -> 'c -> 'd
uncurry3 : ('a -> 'b -> 'c -> 'd) -> 'a * 'b * 'c -> 'd


*)

(*3*)

let rec sumProd xs = 
  match xs with 
    | head::tail -> let (s, p) = sumProd tail in (head+s, head*p)
    | [] -> (0,1)
;;
    let myList = [1;2;3;4;5];;

    let x = sumProd myList;;

    print_result x

let sumProd2 list = List.fold_left (fun (s, p) h -> (s + h, p * h)) (0, 1) list;;

let y = sumProd2 myList;;

print_result y;;

(*
   4
   
   a)

   dla listy: 1 2 3 4 5 6 7 8 9

   stanie sie cos takiego

   small: []
   larger: 1 2 3 4 5 6 7 8 9

innymi slowy mamy do czynienia z nieskonczona rekurencja i zapchaniem stosu wywolan


  b)

  nie uwzgledniamy elementu rownego pivotowi, tzn ze posortujemy, ale bez powtorzen

   *)

   (*5*)

   let rec insert order x = function
  | [] -> [x]
  | y :: ys ->
      if order x y then x :: y :: ys
      else y :: insert order x ys

let rec insertionSort order = function
  | [] -> []
  | x :: xs -> insert order x (insertionSort order xs)

  let myOrder a b = a <= b;;

  let sortedList = insertionSort myOrder [(1,2);(1,1);(1,3);(2,1);(2,2);(1,4)];;


  print_list1 sortedList;;

  print_newline ();;



  (*/// b*)

