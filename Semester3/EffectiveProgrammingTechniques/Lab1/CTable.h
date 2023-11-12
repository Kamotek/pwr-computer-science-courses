#ifndef CTABLE_H
#define CTABLE_H

#include <string>

class CTable
{
private:
    std::string s_name; // nazwa obiektu
    int i_size_of_table; // rozmiar tablicy wewnatrz obiektu
    int* i_table; // wskaznik na tablice

public:
    CTable(); // konstruktor domyslny
    CTable(std::string sName, int iTableLen); // konstruktor z parametrami (nazwa i dlugosc tablicy)
    CTable(const CTable& pcOther); // konstruktor kopiujacy korzystajacy z deep copyingu
    ~CTable(); // destruktor

    void vSetName(std::string sName); // funkcja zmieniajaca nazwe obiektu
    bool bSetNewSize(int iTableLen); // funkcja zmieniajaca rozmiar tablicy obiektu
    CTable* pcClone(); // funkcja kopiujaca obiekt
    void v_mod_tab(CTable* pcTab, int iNewSize); // funkcja zmieniajaca rozmiar tablicy obiektu wskazywanego przez pointer
    void v_mod_tab(CTable cTab, int iNewSize); // funkcja zmieniajaca rozmiar tablicy obiektu podanego w parametrze
};

#endif // CTABLE_H
