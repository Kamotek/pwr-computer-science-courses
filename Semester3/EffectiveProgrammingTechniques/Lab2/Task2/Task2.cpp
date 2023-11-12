#include <iostream>
#include "CNumber.h"
int main()
{
 

    // Tworzymy dwa obiekty klasy CNumber i przypisujemy im wartości używając operatora = 

    CNumber c_num_0, c_num_1;

    c_num_0 = 368;
    c_num_1 = 1567;

    // Teraz przypisujemy wartość c_num_1 do c_num_0, aby sprawdzic poprawnosc przeciazonego operatora =(CNumber pcOther)

    c_num_0 = c_num_1; //przy zadaniu 2 pojawia error gdy sie ma destruktor, a gdy go nie ma to nie wywala
    // dzieje sie tak, bo oba obiekty zaczynaja wskazywac na ta sama tablice co generuje error, bo destruktor usuwa tablice
    // trzeba zrobic deep cp zeby dzialalo

    // uzywamy sToStr(), ktora zdefiniowalismy w klasie CNumber, aby wypisac zawartosc c_num_0 na ekran
    std::cout << c_num_0.sToStr() << "\n";

    

    // uzywamy dwoch obiektow klasy CNumber, aby sprawdzic poprawnosc naszych wynikow dla wszystkich podstawowych operatorow
    // wynik uzycia operatorow zwraca rowniez obiekt klasy CNumber, tak wiec znow mozemy uzyc sToStr()
    // bez koniecznosci przypisania do nowej zmiennej

    CNumber c_num_2, c_num_3;

    c_num_2 = -13;
    c_num_3 = -12;

    std::cout << "Add: " << (c_num_2 + c_num_3).sToStr() << "\n"; //dodawanie
    std::cout << "Sub: " <<  (c_num_2 - c_num_3).sToStr() << "\n"; //odejmowanie
    std::cout << "Mul: " << (c_num_2 * c_num_3).sToStr() << "\n"; // mnozenie
    std::cout << "Div: " <<  (c_num_2 / c_num_3).sToStr() << "\n"; // dzielenie

    
}
