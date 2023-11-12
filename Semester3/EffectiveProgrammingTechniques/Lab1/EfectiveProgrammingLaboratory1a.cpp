#include <iostream>
#include "CTable.h"

void v_alloc_table_fill_34(int iSize); // inicjalizacja funkcji, jej cialo jest zdefiniowane ponizej
bool b_alloc_table_2_dim(int**& piTable, int iSizeX, int iSizeY);
bool b_dealloc_table_2_dim(int** piTable, int iSizeX);

int main()
{

    //  zadanie 1
    const int i_size_of_fill_array = -2; // wielkosc tablicy przekazywana w parametrze
    v_alloc_table_fill_34(i_size_of_fill_array); // wywolanie funkcji

    // zadanie 2

    int** pi_table; // Deklaracja wskaźnika do wskaźnika (czyli de'facto tablica tablic)
    int sizeX = 5; // ilosc kolumn tablicy
    int sizeY = 3; // ilosc wierszy tablicy

    // mamy tutaj do czynienia z wywolaniem funkcji i prostym sprawdzeniem, czy owa funkcja wykonala sie pomyslnie, czy cos nie wyszlo
    if (b_alloc_table_2_dim(pi_table, sizeX, sizeY)) {
        std::cout << "Alocation function from task 2 compiled succesfully" << "\n";
    }
    else {
        std::cout << "Alocation function from task 2 crashed" << "\n";
    }

    // zadanie 3

    // dealokujemy tutaj nasza funkcje, jak widac wystarczy nam tutaj jeden parametr okreslajacy rozmiar tablicy
    // sprawdzamy tez prostym warunkiem czy funkcja wykonala sie pomyslnie
    if (b_dealloc_table_2_dim(pi_table, sizeX)) {
        std::cout << "Dealocation function from task 3 compiled successfully" << "\n";
    }
    else {
        std::cout << "Dealocation function from task 2 crashed" << "\n";
    }

    // zadanie 4

    CTable c_tab; // inicjujemy obiekt
    std::cout << "\n"; // przechodzimy do nowej linii
    CTable* pc_new_tab; //inicjujemy wskaznik na obiekt 

    pc_new_tab = c_tab.pcClone(); // tutaj uzywamy metody pcClone, aby skopiowac obiekt
    std::cout << "\n"; // nowa linia

    pc_new_tab->v_mod_tab(pc_new_tab, 5); // modyfikujemy rozmiar tablicy uzywjaac funkcji 
    std::cout << "\n";

    delete pc_new_tab; // dealokujemy pamiec

    // --

    return 0;
}

// ta funkcja ma za zadanie zaalokowac dynamicznie tablice i wypelnic ja wartosciami 34
void v_alloc_table_fill_34(int iSize) {

    int i_size_of_fill_array = iSize; // przepisujemy wartosc parametru do nowej zmiennej, aby nie modyfikowac wartosci parametru

    // jezeli funkcja przyjmie negatywna wartosc, zmieni ja na 10
    if (i_size_of_fill_array < 0) {
        i_size_of_fill_array = 10;
        std::cout << "Negative size of array, changing to size of 10" << "\n";
    }

    // dynamicznie alokujemy rozmiar tablicy
    int* pi_dynamic_table = new int[i_size_of_fill_array];
    const int i_number_to_fill = 34; // definiujemy stala ktora bedziemy uzupelniac nasza tablice (34)

    // wypelniamy tablice wartoscia 34
    for (int ii = 0; ii < i_size_of_fill_array; ii++) {
        pi_dynamic_table[ii] = i_number_to_fill;
    }

    //  wypisujemy wartosc zmiennej na ekran
    for (int ii = 0; ii < i_size_of_fill_array; ii++) {
        std::cout << pi_dynamic_table[ii] << "\n";
    }

    // z racji ze jest to funkcja majaca za zadanie tylko zaalokowac tablice i wypisac ja na ekran, na tym etapie mozemy zdealokowac tablice
    delete[] pi_dynamic_table;

    return; // zwracamy nic
}

// funkcja majaca za zadanie zaalokowac tablice dwuwymiarowa
bool b_alloc_table_2_dim(int**& piTable, int iSizeX, int iSizeY) {


    piTable = new int* [iSizeX]; // dynamicznie alokujemy jednowymiarowa tablice rozmiaru X
    // a nastepnie w kazdej komorce tej tablicy alokujemy nowa tablice o dlugosci Y
    for (int ii = 0; ii < iSizeX; ii++) {
        piTable[ii] = new int[iSizeY];
    }


    // prosty warunek majacy na celu zwrocic czy tablica istnieje (operacja sie udala)
    // czy nastapily jakies komplikacje
    if (piTable) {
        return true;
    }
    else {
        return false;
    }
}


// dealokacja tablicy dwuwymiarowej
bool b_dealloc_table_2_dim(int** piTable, int iSizeX) {

    // jezeli nasza tablica istnieje przystepujemy do dzialania:
    // iterujemy usuwajac tablice zaalokowane w "pierwszym wymiarze" naszej tablicy
    // dzieki temu nie potrzebujemy do dealokacji zmiennych X i Y, gdyz wystarczy tylko zmienna X,
    //  ktora odpowiada za ilosc zaalokowanych tablic jednowymiarowych w tablicy
    if (piTable) {
        for (int ii = 0; ii < iSizeX; ii++) {
            delete[] piTable[ii];
        }
        delete[] piTable;
        piTable = nullptr; // ustawiamy tez wskaznik na nasza tablice jako nullptr

        return true; // zwracamy true, jezeli wykonalismy ta operacje
    }
    else {
        return false; // false jezeli operacja sie nie powiodla (bo np tablica pod tym wskaznikiem nie istniala)
    }

}