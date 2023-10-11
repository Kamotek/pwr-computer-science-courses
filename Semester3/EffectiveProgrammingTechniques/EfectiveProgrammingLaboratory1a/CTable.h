#ifndef CTABLE_H
#define CTABLE_H

#include <string>

class CTable
{
private:
    std::string s_name;
    int i_size_of_table;
    int* i_table;

public:
    CTable();
    CTable(std::string sName, int iTableLen);
    CTable(const CTable& pcOther);
    ~CTable();

    void vSetName(std::string sName);
    bool bSetNewSize(int iTableLen);
    CTable* pcClone();
    void v_mod_tab(CTable* pcTab, int iNewSize);
    void v_mod_tab(CTable cTab, int iNewSize);
};

#endif // CTABLE_H
