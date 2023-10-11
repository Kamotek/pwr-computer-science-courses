#include "CTable.h"
#include <iostream>

CTable::CTable() {
    s_name = "default";
    i_size_of_table = 1;
    i_table = new int[i_size_of_table];

    std::cout << "bezp: " << s_name;
}

CTable::CTable(std::string sName, int iTableLen) {
    s_name = sName;
    i_table = new int[iTableLen];
    i_size_of_table = iTableLen;
    std::cout << "parametr: " << s_name;
}

CTable::CTable(const CTable& pcOther) {
    s_name = pcOther.s_name + "_copy";
    i_table = new int[pcOther.i_size_of_table];

    for (int ii = 0; ii < i_size_of_table; ii++) {
        i_table[ii] = pcOther.i_table[ii];
    }

    std::cout << "kopiuj: " << s_name;
}

CTable::~CTable() {
    delete[] i_table;
    std::cout << "usuwam: " << s_name << "\n";
}

void CTable::vSetName(std::string sName) {
    s_name = sName;
}

bool CTable::bSetNewSize(int iTableLen) {
    if (iTableLen != i_size_of_table) {
        delete[] i_table;
        i_table = new int[iTableLen];
        i_size_of_table = iTableLen;
        return true;
    }
    else {
        return false;
    }
}

CTable* CTable::pcClone() {
    CTable* newTable = new CTable(*this);
    return newTable;
}

void CTable::v_mod_tab(CTable* pcTab, int iNewSize) {
    pcTab->bSetNewSize(iNewSize);
    std::cout << "Size of a table is: " << i_size_of_table;
}

void CTable::v_mod_tab(CTable cTab, int iNewSize) {
    cTab.bSetNewSize(iNewSize);
    std::cout << "Size of a table is: " << i_size_of_table;
}
