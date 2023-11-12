#include "CTable.h"
#include <iostream>


// konstruktor domyslny
CTable::CTable() {
    s_name = "default"; // ustalamy wartosc pola s_name
    i_size_of_table = 1;  // ustalamy domyslna dlugosc tablicy
    i_table = new int[i_size_of_table]; // dynamicznie alokujemy nasza tablice
  
    std::cout << "bezp: " << s_name; // zgodnie z poleceniem wypisujemy komunikat o utworzeniu obiektu
}

// konstruktor z parametrami, dziala podobnie jak ten bez parametrow, ale przypisuje odpowiednie wartosci
CTable::CTable(std::string sName, int iTableLen) {
    s_name = sName; 
    i_table = new int[iTableLen];
    i_size_of_table = iTableLen;
    std::cout << "parametr: " << s_name;
}

// konstruktor kopiujacy
CTable::CTable(const CTable& pcOther) {

    // robimy deep copy tzn przepisujemy wszystkie pola indywidualnie

    s_name = pcOther.s_name + "_copy";
    i_table = new int[pcOther.i_size_of_table];

    for (int ii = 0; ii < i_size_of_table; ii++) {
        i_table[ii] = pcOther.i_table[ii];
    }

    std::cout << "kopiuj: " << s_name; // komunikat o wykonaniu kopiowania
}

// destruktor
CTable::~CTable() {
    delete[] i_table; // usuwamy tabele i_table, gdyz jest to jedyny dynamicznie alokowany element tej klasy
    std::cout << "usuwam: " << s_name << "\n"; // komunikat o wystapieniu destruktora
}

// funkcja sluzaca do zmiany nazwy obiektu
void CTable::vSetName(std::string sName) {
    s_name = sName;
}

// funkcja sluzaca do zmiany dlugosci tablicy
bool CTable::bSetNewSize(int iTableLen) {
    // sprawdzamy czy nie zostal podany taki sam, badz bledny rozmiar tablicy
    if (iTableLen != i_size_of_table && iTableLen > 0) {
        delete[] i_table; // usuwamy obecna tablice
        i_table = new int[iTableLen]; // alokujemy nowa tablice
        i_size_of_table = iTableLen; // nadpisujemy zapisany rozmiar tablicy
        return true; // zwracamy wartosc true (operacja sie udala)
    }
    else {
        return false; // zwracamy wartosc false (operacja sie nie udala)
    }
}
// funkcja kopijaca obiekt
CTable* CTable::pcClone() {
    // aby nie powielac kodu korzystamy z konstruktora kopiujacego
    CTable* newTable = new CTable(*this);
    return newTable;
}

// funkcja zmieniajaca rozmiar tablicy obiektu na ktory wskazuje wskaznik w parametrze
void CTable::v_mod_tab(CTable* pcTab, int iNewSize) {
    pcTab->bSetNewSize(iNewSize);
    std::cout << "Size of a table is: " << i_size_of_table;
}

// funkcja majaca podobna funkcjonalnosc, z roznica ze tutaj w parametrze uzywamy obiektu, a nie wskaznika na obiekt
void CTable::v_mod_tab(CTable cTab, int iNewSize) {
    cTab.bSetNewSize(iNewSize);
    std::cout << "Size of a table is: " << i_size_of_table;
}
