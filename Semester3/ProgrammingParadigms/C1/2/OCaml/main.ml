

let rec count(value, list) = (
    let isEqual = (fun (y, z) -> if y = z then 1 else 0) in
    if list = [] then 0
    else isEqual(value, List.hd list) + count(value, List.tl list)
);;


let list = [1;2;3;5;5;6;5;7;8;5;9;5;3;6;5];;

let result = count(5, list);;

Printf.printf "%d\n" result;