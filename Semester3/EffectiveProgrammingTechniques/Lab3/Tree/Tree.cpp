#include <iostream>
#include "CNode.h"
#include "CTree.h"
#include <sstream>
#include <vector>
#include <string>



int main() {

    // test 1 - noramlne wyrazenie, zainicjowanie go, zbudowanie drzewo i wyliczenie
    std::string s_expression1 = "+ + +";
    CTree c_tree;

    c_tree.enter(s_expression1);
    c_tree.print();


    return 0;
}

