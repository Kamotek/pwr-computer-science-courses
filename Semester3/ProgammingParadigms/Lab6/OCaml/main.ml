module type Point3DSig = sig
  type point3D
  val create_point : float -> float -> float -> point3D
  val distance : point3D -> point3D -> float
end

module Point3DModule : Point3DSig = struct
  type point3D = { x: float; y: float; z: float }

  let create_point x y z = { x = x; y = y; z = z }

  let distance p1 p2 =
    let dx = p2.x -. p1.x in
    let dy = p2.y -. p1.y in
    let dz = p2.z -. p1.z in
    sqrt (dx *. dx +. dy *. dy +. dz *. dz)
end



(*/2*)

module type LineSegmentSig = sig
  type point3D
  type lineSegment
  val create_segment : point3D -> point3D -> lineSegment
  val length : lineSegment -> float
end

module LineSegmentModule (P : Point3DSig) : LineSegmentSig with type point3D = P.point3D = struct
  type point3D = P.point3D
  type lineSegment = { start_point: point3D; end_point: point3D }

  (* Funkcja tworząca odcinek na podstawie dwóch punktów *)
  let create_segment p1 p2 = { start_point = p1; end_point = p2 }

  (* Funkcja obliczająca długość odcinka *)
  let length segment =
    let start_p = segment.start_point in
    let end_p = segment.end_point in
    P.distance start_p end_p
end

(* Testowanie modułu *)
module Point3D = struct
  type point3D = { x: float; y: float; z: float }
  
  let create_point x y z = { x = x; y = y; z = z }

  let distance p1 p2 =
    let dx = p2.x -. p1.x in
    let dy = p2.y -. p1.y in
    let dz = p2.z -. p1.z in
    sqrt (dx *. dx +. dy *. dy +. dz *. dz)
end

module LineSegment = LineSegmentModule(Point3D);;

let point_a = Point3D.create_point 0.0 0.0 0.0;;
let point_b = Point3D.create_point 1.0 1.0 1.0;;

let segment_ab = LineSegment.create_segment point_a point_b;;
let segment_length = LineSegment.length segment_ab;;
Printf.printf "Length of the line segment: %.2f\n" segment_length;;

(*3*)

module type BinaryTreeSig = sig
  type 'a binary_tree
  val empty_tree : 'a binary_tree
  val insert : 'a -> 'a binary_tree -> 'a binary_tree
  val search : 'a -> 'a binary_tree -> bool
  val inorder_traversal : 'a binary_tree -> 'a list
  val preorder_traversal : 'a binary_tree -> 'a list
  val postorder_traversal : 'a binary_tree -> 'a list
end

module BinaryTreeModule : BinaryTreeSig = struct
  type 'a binary_tree =
    | Empty
    | Node of 'a * 'a binary_tree * 'a binary_tree

  let empty_tree = Empty

  let rec insert value tree =
    match tree with
    | Empty -> Node (value, Empty, Empty)
    | Node (v, left, right) ->
        if value < v then
          Node (v, insert value left, right)
        else if value > v then
          Node (v, left, insert value right)
        else
          tree

  let rec search value tree =
    match tree with
    | Empty -> false
    | Node (v, left, right) ->
        if value = v then
          true
        else if value < v then
          search value left
        else
          search value right

  let rec inorder_traversal tree =
    match tree with
    | Empty -> []
    | Node (v, left, right) ->
        inorder_traversal left @ [v] @ inorder_traversal right

  let rec preorder_traversal tree =
    match tree with
    | Empty -> []
    | Node (v, left, right) ->
        [v] @ preorder_traversal left @ preorder_traversal right

  let rec postorder_traversal tree =
    match tree with
    | Empty -> []
    | Node (v, left, right) ->
        postorder_traversal left @ postorder_traversal right @ [v]
end

(*   *)
module IntBinaryTree = BinaryTreeModule;;

let tree =
  List.fold_left (fun acc x -> IntBinaryTree.insert x acc) IntBinaryTree.empty_tree [5; 3; 7; 2; 4; 6; 8];;

let search_result_1 = IntBinaryTree.search 6 tree;;
let search_result_2 = IntBinaryTree.search 9 tree;;

let inorder_elements = IntBinaryTree.inorder_traversal tree;;
let preorder_elements = IntBinaryTree.preorder_traversal tree;;
let postorder_elements = IntBinaryTree.postorder_traversal tree;;

Printf.printf "Inorder traversal result: [%s]\n" (String.concat "; " (List.map string_of_int inorder_elements));;
Printf.printf "Preorder traversal result: [%s]\n" (String.concat "; " (List.map string_of_int preorder_elements));;
Printf.printf "Postorder traversal result: [%s]\n" (String.concat "; " (List.map string_of_int postorder_elements));;

Printf.printf "Search result for 6: %b\n" search_result_1;
Printf.printf "Search result for 9: %b\n" search_result_2;


(*4*)

module type CoordinateSig = sig
  type t
  val distance : t -> t -> float
  val add : t -> t -> t
end

module IntCoordinate : CoordinateSig with type t = int = struct
  type t = int
  let distance a b = float_of_int (abs (b - a))
  let add a b = a + b
end

module MakePoint (C : CoordinateSig) = struct
  type point = { x: C.t; y: C.t }

  let create_point x y = { x; y }

  let distance p1 p2 =
    let dx = C.distance p1.x p2.x in
    let dy = C.distance p1.y p2.y in
    sqrt (dx *. dx +. dy *. dy)
end

module IntPoint = MakePoint(IntCoordinate)

module IntTranslation = struct
  type point = { x: int; y: int }
  type shift = { dx: int; dy: int }

  let create_shift dx dy = { dx; dy }

  let translate_point shift { x; y } =
    let new_x = x + shift.dx in
    let new_y = y + shift.dy in
    { x = new_x; y = new_y }
end

let convert_to_translation_point { IntPoint.x; y } = { IntTranslation.x; y }

let point = IntPoint.create_point 3 4;;
Printf.printf "Original point: x=%d, y=%d\n" point.x point.y;;

let shift = IntTranslation.create_shift 2 3;;
let translated_point = IntTranslation.translate_point shift (convert_to_translation_point point);;
Printf.printf "Translated point: x=%d, y=%d\n" translated_point.x translated_point.y
