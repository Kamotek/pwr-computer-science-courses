#include <iostream>
#include "CTable.h"

void v_alloc_table_fill_34(int iSize);
bool b_alloc_table_2_dim(int**& piTable, int iSizeX, int iSizeY);
bool b_dealloc_table_2_dim(int** piTable, int iSizeX);

int main()
{

    // 1
    int* i_size_of_fill_array = new int;
    *i_size_of_fill_array = -2;
    if (*i_size_of_fill_array < 0) {
        std::cout << "Negative size of array, changing to size of 10" << "\n";
        *i_size_of_fill_array = 10;
    }
    v_alloc_table_fill_34(*i_size_of_fill_array);

    // 2


    int** pi_table; // Deklaracja wskaźnika do wskaźnika
    int sizeX = 5;
    int sizeY = 3;

    if (b_alloc_table_2_dim(pi_table, sizeX, sizeY)) {
        std::cout << "Alocation function from task 2 compiled succesfully" << "\n";
    }
    else {
        std::cout << "Alocation function from task 2 crashed" << "\n";
    }

    // 3

    if (b_dealloc_table_2_dim(pi_table, sizeX)) {
        std::cout << "Dealocation function from task 3 compiled successfully" << "\n";
    }
    else {
        std::cout << "Dealocation function from task 2 crashed" << "\n";
    }

    // 4

    CTable c_tab;
    std::cout << "\n";
    CTable* pc_new_tab;

    pc_new_tab = c_tab.pcClone();
    std::cout << "\n";

    pc_new_tab->v_mod_tab(pc_new_tab, 5);

    delete pc_new_tab;

    // --



    return 0;
}

void v_alloc_table_fill_34(int iSize) {



    int* pi_dynamic_table = new int[iSize];
    const int i_number_to_fill = 34;

    for (int ii = 0; ii < iSize; ii++) {
        pi_dynamic_table[ii] = i_number_to_fill;
    }

    for (int ii = 0; ii < iSize; ii++) {
        std::cout << pi_dynamic_table[ii] << "\n";
    }

    delete[] pi_dynamic_table;
}

bool b_alloc_table_2_dim(int**& piTable, int iSizeX, int iSizeY) {

    piTable = new int* [iSizeX];
    for (int ii = 0; ii < iSizeX; ii++) {
        piTable[ii] = new int[iSizeY];
    }

    if (piTable) {
        return true;
    }
    else {
        return false;
    }
}


bool b_dealloc_table_2_dim(int** piTable, int iSizeX) {

    if (piTable) {
        for (int ii = 0; ii < iSizeX; ii++) {
            delete[] piTable[ii];
        }
        delete[] piTable;
        piTable = nullptr;

        return true;
    }
    else {
        return false;
    }

}