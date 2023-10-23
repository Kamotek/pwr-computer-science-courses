let rec palindrome(word) = (
    word = List.rev word
);;

let myList = ["a";"b";"c"];;

let myBoolean = palindrome(myList);;

Printf.printf "%b\n" myBoolean
