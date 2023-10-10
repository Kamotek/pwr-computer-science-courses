#include <string>
#include <iostream>


class CTable
{
	std::string s_name;
	int i_size_of_table;
	int* i_table;

public:

	CTable() {
		s_name = "default";
		i_size_of_table = 1;
		i_table = new int[i_size_of_table];

		std::cout << "bezp: " << s_name;
	}

	CTable(std::string sName, int iTableLen) {
		s_name = sName;
		i_table = new int[iTableLen];
		i_size_of_table = iTableLen;
		std::cout << "parametr: " << s_name;
	}

	CTable(CTable& pcOther) {
		s_name = pcOther.s_name + "_copy";
		i_table = new int[pcOther.i_size_of_table];

		for (int ii = 0; ii < i_size_of_table; ii++) {
			i_table[ii] = pcOther.i_table[ii];
		}

		std::cout << "kopiuj: " << s_name;
	}

	~CTable() {

		delete[] i_table;

		std::cout << "usuwam: " << s_name << "\n";
	}

	void vSetName(std::string sName) {
		s_name = sName;
	}

	bool bSetNewSize(int iTableLen) {
		if (iTableLen != sizeof(i_table)) {
			delete[] i_table;
			i_table = new int[iTableLen];
			i_size_of_table = iTableLen;
			return true;
		}
		else {
			return false;
		}
	}

	CTable* pcClone() {
		CTable* newTable = new CTable(*this);

		return newTable;
	}

	void v_mod_tab(CTable* pcTab, int iNewSize) { 
		pcTab->bSetNewSize(iNewSize);

		std::cout << "Size of a table is: " << i_size_of_table;
	}
	void v_mod_tab(CTable cTab, int iNewSize) {
		cTab.bSetNewSize(iNewSize);

		std::cout << "Size of a table is: " << i_size_of_table;
	}

};


