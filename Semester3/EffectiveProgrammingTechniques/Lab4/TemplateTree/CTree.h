#include <string>
#include <iostream>
#include <sstream>
#include <vector>
#include <string>
#include "CNode.h"
#ifndef CTREE_H
#define CTREE_H



template <typename T> // okreslamy klase szablonowa
class CTree {
	template <typename T> // zaprzyjazniona klasa CNode rownierz jest generyczna
	friend class CNode;
private:
	CNode<T>* root; // korzen naszego drzewa, pierwszy node - poniewaz on wystarczy do opisania calego drzewa

	std::vector<std::string> vs_tokens; // wewnetrznie uzywany wektor wartosci string, zawierajacy elementy wyrazenia
	std::vector<CNode<T>*> vn_tree_nodes; // odpowiednik wektoru powyzej, ale zawiera juz stworzone obiekty klasy Node

	std::string s_basic_expression; // 

	std::string s_get_type(); // funkcja zwracajaca typ obiektu
	void v_make_nodes(); // funkcja pomocnicza, tworzy node
	void v_repair_tree(); // funkcja pomocnicza, naprawia drzewo
	void v_build_tree(); // funkcja pomocnicza, buduje drzewo
	std::string reverseString(const std::string& str); // funkcja pomocnicza, tworzy odwrotnosc wyrazenia string

public:
	std::string s_get_name(); // funkcja zwracajaca wartosc roota


	CTree<T>(); // konstruktor domyslny
	~CTree<T>(); // destruktor
	CTree<T>(const CTree<T>& other); //  konstruktor kopiujacy


	void print(); // funkcja przeszukujaca drzewo i wypisujaca po kolei jego elementy
	void enter(std::string& s_expression); // funkcja pozwalajaca wprowadzic wartosci do drzewa
	void enter(std::string& s_expression, std::vector<CNode<T>*> vn_tree_nodes); // funkcja pozwalajaca wprowadzic wartosci do drzewa
	void join(std::string s_expression); // funkcja pozwalajaca dolaczyc kolejne drzewo
	void vars(std::string s_values); // funkcja zmieniajaca wartosci zmiennych na stale
	void comp(); // funkcja ktora wylicza wartosc drzewa

	CTree<T> operator+(CTree<T> c_another_tree);
	CTree<T>& operator=(CTree<T>& c_another_tree);
	CTree<T>& operator=(const CTree<T>& other);
	CTree<T>& operator+=(const CTree<T>& c_another_tree);
};

	

#endif 
