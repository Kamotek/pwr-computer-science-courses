#include <iostream>
#include "CNode.h"
#include "CTree.h"
#include "CTree.cpp"
#include "CNode.cpp"
#include <sstream>
#include <vector>
#include <string>

int main() {

    // test 1 - wprowadzamy niepoprawne wyrazenie w nadziei ze uda sie je naprawic
    std::string s_expression1 = "+ 1 + 1 +";
    CTree<double> c_tree;

    c_tree.enter(s_expression1);
    c_tree.print();

    // test 2 - kolejny przyklad naprawiania bledow
    std::cout << "-----------------------" << "\n";
    std::string s_expression2 = "/ * / - *";
    CTree<double> c_tree_2;

    c_tree_2.enter(s_expression2);
    c_tree_2.print();

    // test 3 - wprowadzamy poprawna wartosc i wypisujemy na ekran
    std::cout << "-----------------------" << "\n";

    std::string s_expression_3 = "+ * 1.5 2.9 3.5";
    CTree<double> c_tree_3;

    c_tree_3.enter(s_expression_3);
    c_tree_3.print();

    // wyliczamy wartosc c_tree_3

    c_tree_3.comp();

    // test 4 - wprowadzamy poprzednie wyrazenie, ale najpierw w formie zmiennych, a nastepnie uzywamy vars, aby zmienic te zmienne na liczby
    std::cout << "-----------------------" << "\n";

    std::string s_expression_4 = " + * a b c";
    CTree<double> c_tree_4;
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

    CTree<double> c_tree_sum;

    c_tree_sum = (c_tree_2 + c_tree_3);

    c_tree_sum.print();
    c_tree_sum.comp();

    // test 7 - pokazujemy ze struktura drzewa np 3 sie nie zmienila
    std::cout << "-----------------------" << "\n";

    c_tree_3.print();

    std::cout << "-----------------------" << "\n";


    // test 8 dzialania dla string, odejmowanie

    CTree<std::string>c_tree_s1, c_tree_s2, c_tree_s3, c_tree_s4;
    std::string s_exp1 = "- alaxalaxalax alax";
    std::string s_exp2 = "+ + abc abc abc";
    std::string s_exp3 = "* alaXalaXalaX Xa";
    std::string s_exp4 = "/ alaXABalaXABalaXAB XAB";

    c_tree_s1.enter(s_exp1);
    c_tree_s1.comp();

    c_tree_s2.enter(s_exp2);
    c_tree_s2.comp();

    c_tree_s3.enter(s_exp3);
    c_tree_s3.comp();

    c_tree_s4.enter(s_exp4);
    c_tree_s4.comp();

    //test 9 dzialania dla string - naprawianie
    std::cout << "-----------------------" << "\n";

    CTree<std::string> c_repairing_tree;
    std::string s_exp_5 = "+ + +";

    c_repairing_tree.enter(s_exp_5);
    c_repairing_tree.comp();

    //test 10 - string vars

    CTree<std::string> c_var_tree;
    std::string s_exp_6 = "+ abc zaq";

    c_var_tree.enter(s_exp_6);
    c_var_tree.vars("a b");
    c_var_tree.comp();

    c_var_tree.join("+ cd cd");
    c_var_tree.comp();

    CTree<std::string> move_tree;

    move_tree = std::move(c_var_tree);
    std::cout << "-----------------------" << "\n";
    move_tree.print();

    std::cout << "-----------------------" << "\n";
    CTree<std::string> empty_tree;

    empty_tree = (c_tree_s2 + c_tree_s3);

    empty_tree.print();
}
