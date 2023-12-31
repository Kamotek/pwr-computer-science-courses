#include <string>
#ifndef CNODE_H
#define CNODE_H

class CNode {
    friend class CTree;
    private:
        CNode* c_node_left; // lewe dziecko 
        CNode* c_node_right; // prawe dziecko
        CNode* c_node_parent; // rodzic

        bool b_is_root; // flaga czy nasz node to korzen calego drzewa
        bool b_has_right_kid; // flaga ktora mowi ze node ma leawe dziecko
        bool b_has_left_kid; // flaga ktora mowi czy node ma lewe dziecko

        bool b_is_full; // flaga, ktora mowi czy konkretna gal�� wychodzaca od node'a jest skonczona (pelna)



        void v_check_value_status(std::string s_value); // funkcja do sprawdzania jaka flage zaznaczyc,  czy to operator, czy to zmienna czy stala
        bool b_check_number_status(std::string& s_value); // funkcja mowiaca czy nasz string to liczba

    public:

        std::string s_value; // wartosc naszego node'a

        double calculate();
        bool b_is_operator; // flaga okreslajaca czy node jest operatorem (+-/*sincos)
        bool b_is_number; // flaga ktora decyduje czy mamy do czynienia z liczba (jezeli tak to dzieci beda puste
        bool b_is_variable; // flaga mowiaca czy wartosc naszego node to liczba czy zmienna

        CNode(std::string value, bool b_is_root); // konstruktor domyslny - node powstaje po przekazaniu do niego wartosci
        ~CNode(); // destruktor 
        CNode(CNode& other); // konstruktor kopiujacy

        bool b_add_child(CNode& c_new_child); // przypisywanie wartosci node_left node_right
        std::string v_to_string(); // wypisanie node'a na ekran
};

#endif 
