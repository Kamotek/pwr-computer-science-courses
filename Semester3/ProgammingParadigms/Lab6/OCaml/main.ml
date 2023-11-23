(* Definicja sygnatury modułu reprezentującego punkt w przestrzeni trójwymiarowej *)
module type Point3DSig = sig
  type point3D
  val create_point : float -> float -> float -> point3D
  val distance : point3D -> point3D -> float
end

(* Implementacja modułu reprezentującego punkt w przestrzeni trójwymiarowej *)
module Point3DModule : Point3DSig = struct
  type point3D = { x: float; y: float; z: float }

  (* Funkcja tworząca punkt *)
  let create_point x y z = { x = x; y = y; z = z }

  (* Funkcja obliczająca odległość między dwoma punktami *)
  let distance p1 p2 =
    let dx = p2.x -. p1.x in
    let dy = p2.y -. p1.y in
    let dz = p2.z -. p1.z in
    sqrt (dx *. dx +. dy *. dy +. dz *. dz)
end



(*/2*)

module type lineSig = sig
  type line
  val create_line : Point3DModule.point3D -> Point3DModule.point3D -> line
end