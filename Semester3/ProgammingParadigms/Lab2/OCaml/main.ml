(*1*)

Printf.printf "Task 1:\n ";;

let rec lastElement = function
  | [] -> None
  | [element] -> Some element
  | element :: myList -> lastElement myList


let myList = [1; 2; 3; 4; 5];;
let result = lastElement myList;;
match result with
| Some x -> Printf.printf "Last element is: %d\n" x
| None -> Printf.printf "List is empty\n";;




(*2*)
Printf.printf "Task 2:\n "
let rec twoLastElements myList =
  match myList with
  | [] | [_] -> None
  | [x; y] -> Some (x, y)
  | element :: tailList -> twoLastElements tailList


let myList = ["a";"b";"c";"d"]

let result = twoLastElements myList

let () =
match result with
| Some (x, y) -> Printf.printf "Two last elements: %s, %s\n" x y
| None -> Printf.printf "List is empty or contains only one element\n";;


(*3*)
Printf.printf "Task 3:\n "

let rec listLength myList = (
  if myList = [] then 0 else 1 + listLength(List.tl myList)  
)

let myList = [1;2;3;4;5];;

let result = listLength(myList);;

print_int result;;

(*4*)
Printf.printf "Task 4:\n ";;
let rec reverse myList =
  let rec reverseHelp list wynik =
    match list with
    | [] -> wynik
    | x :: xs -> reverseHelp xs (x :: wynik)
  in
  reverseHelp myList []


  let lista = [1; 2; 3; 4; 5];;
let odwrocona = reverse lista;;

let print_number number = Printf.printf "%d\n" number;;

List.iter print_number odwrocona;;

(*5*)
Printf.printf "Task 5:\n ";;
let rec isPalindrome(myList) = (
  myList = reverse myList
)

let myList = ["a";"d";"d"];;

let result = isPalindrome(myList);;

Printf.printf "Is this a palindrome?: %b\n" result;;

(*6*)
Printf.printf "Task 6:\n ";;
let rec deleteDuplicates myList = 
  let rec deleteDuplicatesHelp myList result = 
    match myList with 
    | [] -> result
    | x :: xs -> if List.mem x result then deleteDuplicatesHelp xs result else deleteDuplicatesHelp xs (x :: result)
  in 
  List.rev (deleteDuplicatesHelp myList [])


let myList = [1;2;1;2;3;4;5;6;1;1;7;8;9;1;2;3];;

let result = deleteDuplicates myList;;

List.iter print_number result;;

(*7*)

Printf.printf "Task 7:\n ";;

let rec evenIndex myList = 
  match myList with 
  | [] -> []  
  | x :: rest -> x :: evenIndex (List.tl rest)
  
let myList = [1;2;3;4;5;6;7;8;9;10];;

let result = evenIndex myList;;

List.iter print_number result;;

(*8*)

Printf.printf "Task 8:\n ";;

let rec isPrime number =
  if number <= 1 then
    false
  else if number <= 3 then
    true
  else if number mod 2 = 0 || number mod 3 = 0 then
    false
  else
    let rec isPrimeHelp k =
      if k * k > number then
        true
      else if number mod k = 0 then
        false
      else
        isPrimeHelp (k + 2)
    in
    isPrimeHelp 5



    let result = isPrime 13;;

    Printf.printf "This number is prime: %b\n" result;;