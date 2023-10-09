#include <string>
#include <iostream>


class CTable
{
	std::string s_name;

	int* i_table;

public:

	CTable() {
		s_name = "default";
		i_table = new int[1];

		std::cout << "bezp: " << s_name;
	}

	CTable(std::string sName, int iTableLen) {
		s_name = sName;
		i_table = new int[iTableLen];

		std::cout << "parametr: " << s_name;
	}

	CTable(CTable& pcOther) {
		s_name = pcOther.s_name + "_copy";
		i_table = pcOther.i_table;

		std::cout << "kopiuj: " << s_name;
	}

	~CTable() {
		std::cout << "usuwam: " << s_name << "\n";
	}

	void vSetName(std::string sName) {
		s_name = sName;
	}

	bool bSetNewSize(int iTableLen) {
		if (iTableLen != sizeof(i_table)) {
			i_table = new int[iTableLen];
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
	}
	void v_mod_tab(CTable cTab, int iNewSize) {
		cTab.bSetNewSize(iNewSize);
	}

};


