let rec sqrList(list) = (if list = [] then [] else [List.hd list * List.hd list] @ sqrList(List.tl list));;

let list = [1;2;3;4;5;6;7;8;9;10];;

let squaredList = sqrList(list);;

let print_number number = Printf.printf "%d\n" number;;

List.iter print_number squaredList;;