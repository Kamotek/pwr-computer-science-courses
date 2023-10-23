let rec flatten1 list1 = if list1 = [] then [] else List.hd list1 @ flatten1(List.tl list1);;

let list2 = flatten1([[1;2;3];[4;5;6];[3;3;3]]);;

let print_number number = Printf.printf "%d\n" number;;

List.iter print_number list2;;

