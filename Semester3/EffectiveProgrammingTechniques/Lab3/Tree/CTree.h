#ifndef CTREE_H
#define CTREE_H

#include <string>
#include <iostream>
#include <sstream>
#include <vector>
#include <string>
#include "CNode.h"

class CTree {
	friend class CNode;
	private:
		CNode* root; // korzen naszego drzewa, pierwszy node - poniewaz on wystarczy do opisania calego drzewa
		std::vector<std::string> vs_tokens;
		std::vector<CNode*> vn_tree_nodes;

		std::string s_basic_expression;

		void v_make_nodes(); // funkcja pomocnicza, tworzy node
		void v_repair_tree(); // funkcja pomocnicza, naprawia drzewo
		void v_build_tree(); // funkcja pomocnicza, buduje drzewo
		double calculate(); // funkcja pomocnicza, liczy rekurencyjnie wartosc node

	public:

		CTree(); // konstruktor domyslny
		~CTree(); // destruktor
		CTree(CTree& other); //  konstruktor kopiujacy

		void print(); // funkcja przeszukujaca drzewo i wypisujaca po kolei jego elementy
		void enter(std::string& s_expression); // funkcja pozwalajaca wprowadzic wartosci do drzewa
		void enter(std::string& s_expression, std::vector<CNode*> vn_tree_nodes); // funkcja pozwalajaca wprowadzic wartosci do drzewa
		void join(std::string s_expression); // funkcja pozwalajaca dolaczyc kolejne drzewo
		void vars(std::string s_values); // funkcja zmieniajaca wartosci zmiennych na stale
		void comp();

		CTree operator+(CTree c_another_tree);
		CTree& operator=(CTree& c_another_tree);
		CTree& operator=(const CTree& other);
		CTree& operator+=(const CTree& c_another_tree);
};

#endif 
