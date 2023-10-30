let print_list lst =
  Printf.printf "[";
  List.iter (fun x -> Printf.printf "%d; " x) lst;
  Printf.printf "]\n"

(*1*)

let rec my_map f lst =
  match lst with
  | [] -> []
  | head :: tail -> (f head) :: my_map f tail



  let increment x = x + 1;;
  let input_list1 = [1; 2; 3; 4; 5];;
  
  let result1 = my_map increment input_list1;;

  print_list result1;;
  (*2*)

  let rec my_filter lst pred =
    match lst with
    | [] -> []
    | head :: tail when pred head -> head :: my_filter tail pred
    | _ :: tail -> my_filter tail pred


  let pred x = x mod 2 = 0;;

  let input_list2 = [1;2;3;4;5;6;7;8;9];;

  let result2 = my_filter input_list2 pred;;

  print_list result2;;

  (*3*)

  let rec my_reduce lst op acc =
    match lst with
    | [] -> acc
    | head :: tail -> my_reduce tail op (op head acc)
  
  let op x y = x + y
  
  let input_list3 = [1; 2; 3; 4; 5];;
  let result3 = my_reduce input_list3 op 0;;

  print_int result3;;
  Printf.printf("\n")

  (*4*)

  let my_average lst =
    float_of_int (my_reduce lst (fun x y -> x + y) 0) /. float_of_int (List.length lst)
  
  let input_list4 = [1;2;3;4;5;6;7;8;9];;
  let result4 = my_average input_list4;;

  print_float result4;;
  Printf.printf("\n")

  (*5*)

  let rec generate_acronym_recursive str =
    let rec aux acc = function
      | [] -> String.concat "" (List.rev acc)
      | word :: rest ->
          match String.trim word with
          | "" -> aux acc rest
          | s -> aux ((String.make 1 (Char.uppercase_ascii (String.get s 0))) :: acc) rest
    in
    aux [] (String.split_on_char ' ' str)
  ;;
  
  let input_string = "zaklad ubezpieczen spolecznych";;
  let acronym = generate_acronym_recursive input_string;;
  print_endline acronym;; 

  (*6*)

  let rec reduce_list myList = 
    let cubeValue value = value*value*value in 
    let sum = my_reduce myList (fun x y -> x + y) 0 in 
    match myList with
    | [] -> []
    | head :: tail when cubeValue head < sum -> head :: reduce_list tail
    | head :: tail -> reduce_list tail

  let input_list6 = [1;2;3;4;5;6;7;8;9];;
  let result6 = reduce_list input_list6;;

  print_list result6