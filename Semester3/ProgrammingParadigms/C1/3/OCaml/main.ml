let value = 5;;
let number = 10;;

let rec replicate(value, number) = (if number <= 0 then [] else value :: replicate(value, number-1));;

let x = replicate(value, number);;

let print_number number = Printf.printf "%d\n" number;;

List.iter print_number x;;