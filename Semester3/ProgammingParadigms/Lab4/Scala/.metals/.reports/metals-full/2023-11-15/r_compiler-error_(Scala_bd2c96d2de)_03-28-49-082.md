file://<WORKSPACE>/main.scala
### java.lang.AssertionError: NoDenotation.owner

occurred in the presentation compiler.

action parameters:
offset: 3698
uri: file://<WORKSPACE>/main.scala
text:
```scala
import java.awt.geom.Point2D
import scala.util.Random

object App extends App {



// 1

    type point2D = (Double, Double)

    def square(x: Double): Double = {
        x*x
    }

    def distance(x: point2D, y: point2D): Double = {
        Math.sqrt(square(y(0)-x(0))+square(y(1)-x(1)))
    }

    def makePrecision(x: Double, p: Int): Double = {
        BigDecimal(x).setScale(p, BigDecimal.RoundingMode.HALF_UP).toDouble
    }

    val x1 = makePrecision(3.45, 1)
    val y1 = makePrecision(2.11, 1)

    val x2 = makePrecision(7.23, 1)
    val y2 = makePrecision(2, 1)

    val myPoint1: point2D = (x1, y1)
    val myPoint2: point2D = (x2, y2)

    println(makePrecision(distance(myPoint1, myPoint2),1))


    // 1b

    type pointND = (Double, Double, Double)

    def distanceN(x: pointND, y: pointND): Double = {
        def distanceHelp(x: pointND, y: pointND): Double = {
            x.productIterator.zip(y.productIterator).foldLeft(0.0) {
                case (acc, (a: Double, b: Double)) => acc + square(b - a) 
                case (acc, (a: pointND, b: pointND)) if a.productArity == b.productArity => acc + distanceHelp(a, b) 
                case (acc, _) => acc
            }
        }

        Math.sqrt(distanceHelp(x, y))   
    }       


    val a1 = makePrecision(Random.between(1.0, 10.0), 1)
    val a2 = makePrecision(Random.between(1.0, 10.0), 1)

    val b1 = makePrecision(Random.between(1.0, 10.0), 1)
    val b2 = makePrecision(Random.between(1.0, 10.0), 1)

    val c1 = makePrecision(Random.between(1.0, 10.0), 1)
    val c2 = makePrecision(Random.between(1.0, 10.0), 1)

    val myPoint4: pointND = (a1, b1, c1)
    val myPoint5: pointND = (a2, b2, c2)


    println(makePrecision(distanceN(myPoint4, myPoint5),1))


    // 2

    def personToStr(x: Person): Unit = {
        println("---")
        println(s"Imie: ${x(0)} Nazwisko: ${x(1)}")
        println(s"Wiek: ${x(2)} But: ${x(3)}")
        println("---")
    }


        def personXToStr(x: PersonX): Unit = {
        println("---")
        println(s"Imie: ${x.imie} Nazwisko: ${x.nazwisko}")
        println(s"Wiek: ${x.wiek} But: ${x.but}")
        println("---")
    }

    // first 
    type Person = (String, String, Int, Int)
    type Partnership = (Person, Person)

    //badamy mutowalnosc pierwszego przypadku

    val myPerson1: Person = ("Grzegorz", "Brzeczyszczykiewicz", 28, 42)
    var myPerson2: Person = ("Alicja", "Zaczarowana", 23, 38)

    personToStr(myPerson2)

    myPerson2 = ("Alan", "Odczarowany", 23, 38)

    personToStr(myPerson2)


    // wnioski co do krotki: 
     //    1. Nie da sie zmieniac mutowalnosci pojedynczych elementow.
     //    2. Jezeli zadeklarujemy krotke jako var to mozemy zmienic krotke na podobna, ale calkiem nowa, wiec w ten spopsob jakos sie niby da

    // drugi przypadek

        // second
    case class PersonX(var imie: String, var nazwisko: String, wiek: Int, but: Int)
    case class PartnershipX(osoba1: PersonX, osoba2: PersonX)


    val myPerson3 = PersonX("Gracjan", "Roztocki", 53, 43)
    var myPerson4 = PersonX("Pawel", "Skoczek", 25, 44)

    personXToStr(myPerson3)
    myPerson3.imie = "Piotr"
    myPerson3.nazwisko = "Zyla"
    personXToStr(myPerson3)

    // wnioski co do caseclass: 
        // 1. Definiujemy nazwe elementow i mozemy ustalac ich mutowalnosc (chociaz nie jest to dobra praktyka)
        // 2. Mozemy zmieniac wartosci wewnatrz caseclass bez wzgledu na to, czy sam obiekt jest staly czy zmienny

    

    def getOlder(rel: Partnership): Person = {
        (rel(0), rel(1)) match {
            case (x, y) if x(2) > y(2) => x
            case (x, y) if x(2) <= y(2) => y
            case _ => var myPerson4(@@)
        }
    }
    


    }
```



#### Error stacktrace:

```
dotty.tools.dotc.core.SymDenotations$NoDenotation$.owner(SymDenotations.scala:2576)
	scala.meta.internal.pc.SignatureHelpProvider$.isValid(SignatureHelpProvider.scala:83)
	scala.meta.internal.pc.SignatureHelpProvider$.notCurrentApply(SignatureHelpProvider.scala:92)
	scala.meta.internal.pc.SignatureHelpProvider$.$anonfun$1(SignatureHelpProvider.scala:48)
	scala.collection.StrictOptimizedLinearSeqOps.loop$3(LinearSeq.scala:280)
	scala.collection.StrictOptimizedLinearSeqOps.dropWhile(LinearSeq.scala:282)
	scala.collection.StrictOptimizedLinearSeqOps.dropWhile$(LinearSeq.scala:278)
	scala.collection.immutable.List.dropWhile(List.scala:79)
	scala.meta.internal.pc.SignatureHelpProvider$.signatureHelp(SignatureHelpProvider.scala:48)
	scala.meta.internal.pc.ScalaPresentationCompiler.signatureHelp$$anonfun$1(ScalaPresentationCompiler.scala:375)
```
#### Short summary: 

java.lang.AssertionError: NoDenotation.owner