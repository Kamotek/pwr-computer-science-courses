import java.awt.geom.Point2D
import scala.util.Random
import App.WeekDay

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

    type pointND = (Double, Double, Double, Double)

    def distanceN(x: pointND, y: pointND): Double = {
        def distanceHelp(x: pointND, y: pointND): Double = {
            x.productIterator.zip(y.productIterator).foldLeft(0.0) {
                case (acc, (a: Double, b: Double)) => acc + square(b - a) 
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

    val d1 = makePrecision(Random.between(1.0, 10.0), 1)
    val d2 = makePrecision(Random.between(1.0, 10.0), 1)

    val myPoint4: pointND = (a1, b1, c1, d1)
    val myPoint5: pointND = (a2, b2, c2, d2)


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
            case _ => rel(0)
        }
    }

    val myPartnership: Partnership = (myPerson1, myPerson2)

    personToStr(getOlder(myPartnership))
    


    // 3
    enum WeekDay:
        case Poniedzialek, Wtorek, Sroda, Czwartek, Piatek, Sobota, Niedziela

    def weekDayToString(day: WeekDay): String = day match {
            case WeekDay.Poniedzialek => "Poniedziałek"
            case WeekDay.Wtorek => "Wtorek"
            case WeekDay.Sroda => "Środa"
            case WeekDay.Czwartek => "Czwartek"
            case WeekDay.Piatek => "Piątek"
            case WeekDay.Sobota => "Sobota"
            case WeekDay.Niedziela => "Niedziela"
        }

    def nextDay(day: WeekDay): WeekDay = day match {
        case WeekDay.Poniedzialek => WeekDay.Wtorek
        case WeekDay.Wtorek => WeekDay.Sroda
        case WeekDay.Sroda => WeekDay.Czwartek
        case WeekDay.Czwartek => WeekDay.Piatek
        case WeekDay.Piatek => WeekDay.Sobota
        case WeekDay.Sobota => WeekDay.Niedziela
        case WeekDay.Niedziela => WeekDay.Poniedzialek
    }


    val dzien: WeekDay = WeekDay.Czwartek
    println(weekDayToString(dzien)) 

    val nastepnyDzien = nextDay(dzien)
    println(weekDayToString(nastepnyDzien)) 


    // 4

    enum Maybe[+A]:
        case Just(value: A)
        case Nothing

    val maybeInt: Maybe[Int] = Maybe.Just(10)
    val maybeString: Maybe[String] = Maybe.Nothing


    def safeHead[T](myList: List[T]): Maybe[T] = {
        myList match {
            case head :: _ => Maybe.Just(head)
            case Nil => Maybe.Nothing
        }
    }

    var myList = List(1,2,3,4)



    println(maybeInt)
    println(maybeString)
    println(safeHead(myList))

    // 5
    
    enum SolidFigure: 
        case Prostopadloscian(dlugosc: Double, szerokosc: Double, wysokosc: Double)
        case Stozek(promien: Double, wysokosc: Double)
        case Kula(promien: Double)
        case Walec(promien: Double, wysokosc: Double)

    def volume(figura: SolidFigure): Double = figura match {
            case SolidFigure.Prostopadloscian(dlugosc, szerokosc, wysokosc) => dlugosc * szerokosc * wysokosc
            case SolidFigure.Stozek(promien, wysokosc) => (1.0/3.0) * math.Pi * promien * promien * wysokosc
            case SolidFigure.Kula(promien) => (4.0/3.0) * math.Pi * promien * promien * promien
            case SolidFigure.Walec(promien, wysokosc) => math.Pi * promien * promien * wysokosc
    }


    val prostopadloscian = SolidFigure.Prostopadloscian(3.0, 4.0, 5.0)
    val stozek = SolidFigure.Stozek(2.0, 6.0)
    val kula = SolidFigure.Kula(3.0)
    val walec = SolidFigure.Walec(2.0, 4.0)

    val objetoscProstopadloscianu = volume(prostopadloscian)
    val objetoscStozka = volume(stozek)
    val objetoscKuli = volume(kula)
    val objetoscWalca = volume(walec)

    println(s"Prostopadloscian: $objetoscProstopadloscianu")
    println(s"Stozek: $objetoscStozka")
    println(s"Kula: $objetoscKuli")
    println(s"Walec: $objetoscWalca")


 }