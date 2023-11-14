let rec listLength myList = 
  let rec listLengthHelp myList count = 
    match myList with 
    | head :: tail -> listLengthHelp tail count+1
    | [] -> count
  in
  listLengthHelp myList 0
  
  let myList = [1;2;3;4;5;6];;

  print_int (listLength myList)
