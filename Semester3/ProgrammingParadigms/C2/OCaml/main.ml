(*1 - myślę że też 1*)

(*2*)

let rec fib n = (if n <= 1 then n else (fib(n-1)) + (fib(n-2)));;

print_int (fib 15);;

print_newline ();;

let rec fib_tail n =
  let rec fib_helper n a b =
    match n with
    | 0 -> a
    | 1 -> b
    | _ -> fib_helper (n - 1) b (a + b)
  in
  if n <= 1 then n
  else fib_helper n 0 1;;

  print_int (fib_tail 15);;

  print_newline ();;
  (*3*)

  let root3 a =
    let rec root3_helper x epsilon =
      let nextX = x +. (a /. (x *. x) -. x) /. 3.0 in
      if abs_float (nextX *. nextX *. nextX -. a) <= epsilon *. abs_float a then
        nextX
      else
        root3_helper nextX epsilon
    in
    let initialX = if a > 1.0 then a /. 3.0 else a in
    let epsilon = 1e-15 in
    root3_helper initialX epsilon
  ;;
  
  let result3 = root3 16.0 in
  print_float result3;;
  print_newline ();;
  

  (*4*)

  let pattern = [-2; -1; 0; 1; 2];;

  match pattern with
    | _ :: _ :: x :: _ :: _ -> print_endline ("x = " ^ string_of_int x)
    | _ -> print_endline "Brak dopasowania"
    ;;

  let pattern2 = [(1, 2); (0, 1)];;

  match pattern2 with
    | _ :: (x, _) :: _ -> print_endline ("x = " ^ string_of_int x)
    | _ -> print_endline "Brak dopasowania"
    ;;

    (*5*)


    let rec init_segment xs ys =
      let rec check_segment xstail ystail =
        match (xstail, ystail) with
        | [], _ -> true (* Empty list is an initial segment of any list. *)
        | x :: xsRest, y :: ysRest when x = y -> check_segment xsRest ysRest
        | _ -> false
      in
      check_segment xs ys
    ;;
    
    let result = init_segment [1; 2; 3; 4; 5] [1; 2; 3; 4] in
    print_endline (string_of_bool result)

    (*6*)

    let rec replace_nth xs n x =
      let rec replace_nth_helper lst index value =
        match (lst, index) with
        | [], _ -> []
        | head :: tail, 0 -> value :: tail
        | head :: tail, i when i > 0 -> head :: replace_nth_helper tail (i - 1) value
        | _ -> lst
      in
      if n >= 0 then
        replace_nth_helper xs n x
      else
        xs
    ;;
    
    let original_list = ['o'; 'l'; 'a'; 'm'; 'a'; 'k'; 'o'; 't'; 'a'] in
    let modified_list = replace_nth original_list 1 's' in
    let modified_list_str = String.concat "; " (List.map Char.escaped modified_list) in
    print_endline ("[" ^ modified_list_str ^ "]")
    
    