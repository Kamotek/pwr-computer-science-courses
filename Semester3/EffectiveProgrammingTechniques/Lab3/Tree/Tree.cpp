#include <iostream>
#include "CNode.h"
#include "CTree.h"
#include <sstream>
#include <vector>
#include <string>



int main() {

    // test 1 - wprowadzamy niepoprawne wyrazenie w nadziei ze uda sie je naprawic
    std::string s_expression1 = "+ 1 + 1 +";
    CTree c_tree;

    c_tree.enter(s_expression1);
    c_tree.print();

    // test 2 - kolejny przyklad naprawiania bledow
    std::cout << "-----------------------" << "\n";
    std::string s_expression2 = "/ * / - *";
    CTree c_tree_2;

    c_tree_2.enter(s_expression2);
    c_tree_2.print();

    // test 3 - wprowadzamy poprawna wartosc i wypisujemy na ekran
    std::cout << "-----------------------" << "\n";

    std::string s_expression_3 = "+ * 1 2 3";
    CTree c_tree_3;

    c_tree_3.enter(s_expression_3);
    c_tree_3.print();

    // wyliczamy wartosc c_tree_3

    c_tree_3.comp();
    
    // test 4 - wprowadzamy poprzednie wyrazenie, ale najpierw w formie zmiennych, a nastepnie uzywamy vars, aby zmienic te zmienne na liczby
    std::cout << "-----------------------" << "\n";

    std::string s_expression_4 = " + * a b c";
    CTree c_tree_4;
    c_tree_4.enter(s_expression_4);
    c_tree_4.print();
    c_tree_4.comp();
    c_tree_4.vars("1 2 3");
    c_tree_4.print();
    c_tree_4.comp();

    // test 5 - dodajemy wyrazenie do gotowego drzewa uzywajac join
    std::cout << "-----------------------" << "\n";

    c_tree_4.join("+ 7 5");
    c_tree_4.print();
    c_tree_4.comp();

    // test 6 - uzywamy operatorow, aby przypisac sume dwoch drzew do trzeciego drzewa

    CTree c_tree_sum;

    c_tree_sum = (c_tree_2 + c_tree_3);

    c_tree_sum.print();
    c_tree_sum.comp();

    // test 7 - pokazujemy ze struktura drzewa np 3 sie nie zmienila
    std::cout << "-----------------------" << "\n";

    c_tree_3.print();

    return 0;
}

