#include "CNode.h"
#include <iostream>


	// konstruktor 
	CNode::CNode(std::string s_value, bool b_is_root) {
		//ustawiamy wszystkie node w relacji jako nullpointery
		 c_node_right = nullptr;
		 c_node_left = nullptr;
		 c_node_parent = nullptr;

		b_is_root = b_is_root; // info o tym czy node jest rootem jest generowane w drzewie
		b_is_variable = false; // czy ten node to zmienna?
		b_is_number = false; // czy ten node to liczba?
		b_is_operator = false; // czy ten node to operator?
		b_has_left_kid = false; // czy ten node ma lewe dziecko?
		b_has_right_kid = false; // czy ten noda ma prawe dziecko?

		b_is_full = false; // flaga zwracajaca czy droga pod tym node'm jest juz zapelniona

		CNode::s_value = s_value; // przypisujemy watrtosc z parametru do wartosci pola

		v_check_value_status(s_value); // funkcja decydujaca o tym, jaka flage ustawic na true

		//jezeli jest to root, tzn ze sam jest swoim rodzicem
		if (b_is_root) {
			c_node_parent = this;
		}

}

	CNode::~CNode() {
	// akurat w tym programie wszystko mamy na stosie // 

	}
	//konstruktor kopiujacy, przekazujemy wszystkie wartosci
	CNode::CNode(CNode& other) {
		s_value = other.s_value;
		b_is_root = other.b_is_root;
		b_is_variable = other.b_is_variable;
		b_is_number = other.b_is_number;
		b_is_operator = other.b_is_operator;
		b_has_left_kid = other.b_has_left_kid;
		b_has_right_kid = other.b_has_right_kid;
		b_is_full = other.b_is_full;

		// rekursywnie tworzymy kopie wszystkich node'ow w relacji w lewo
		if (other.c_node_left != nullptr) {
			c_node_left = new CNode(*other.c_node_left); // Recursive copy
			c_node_left->c_node_parent = this;
		}
		else {
			c_node_left = nullptr;
		}
		// i w prawo
		if (other.c_node_right != nullptr) {
			c_node_right = new CNode(*other.c_node_right); // Recursive copy
			c_node_right->c_node_parent = this;
		}
		else {
			c_node_right = nullptr;
		}

		// nie kopiujemy rodzica osobno, poniewaz w rekurencji sami sie ustawiaja
	}


	// sprawdzamy czy s_value jest liczba
	bool CNode::b_check_number_status( std::string& s_value) {
		if (s_value.empty()) {
			return false; // pusty string nie jest liczba
		}
		bool hasDot = false; // tutaj sprawdzamy, czy pojawia sie znak . (liczba zmiennoprzecinkowa)
		for (char c : s_value) { // sprawdzamy tez czy poszczegolne znaki sa cyframi 
			if (!std::isdigit(c)) {
				if (c == '.' && !hasDot) {
					hasDot = true;
				}
				else {
					return false; // wartosc ta nie jest liczba 
				}
			}
		}

		return true; // wartosc ta jest liczba
	}


	void CNode::v_check_value_status(std::string s_value) {
		// sprawdzamy czy wartosc naszego stringa to ktorys z operatorow
		if (s_value == "+" || s_value == "-" || s_value == "*" || s_value == "/" || s_value == "sin" || s_value == "cos") {
			b_is_operator = true;
		} // sprawdzamy czy wartosc jest liczba
		else if (b_check_number_status(s_value)) {
			b_is_number = true;
		}
		else { // jezeli nie jest ani operatorem ani liczba, to znaczy ze jest zmienna
			b_is_variable = true;
		}
	}
	bool CNode::b_add_child(CNode& c_new_child) {


		// podstawowym warunkiem jest to, ze nasz node jest operatorem
		// bo tylko operatory maja dwojke dzieci
		// reszta to liscie
		if(b_is_operator && !b_is_root) {
			// w tym warunku sprawdzamy czy node nie ma dwojki dzieci, ktore sa wartosciami
			// jezeli tak to od razu konczymy iteracje i zaznaczamy ze tu nie ma nic wiecej do dodania
			if (b_has_left_kid && b_has_right_kid && !c_node_left->b_is_operator && !c_node_right->b_is_operator) {
				b_is_full = true;
				return c_node_parent->b_add_child(c_new_child);
				
			}

			if (b_has_left_kid && b_has_right_kid) {
				if (c_node_left->b_is_full && c_node_right->b_is_full) {
					b_is_full = true;
					return c_node_parent->b_add_child(c_new_child);
				}
			}

			// warunek; nie ma dzieci, wiec dodajemy na lewa strone
			if (!b_has_left_kid && !b_has_right_kid) {
				b_has_left_kid = true; // ustawiamy flage o posiadaniu lewego dziecka na true
				c_node_left = &c_new_child; // ustawiamy
				c_node_left->c_node_parent = this;
				return true; // zwracamy true
			}
			// warunek; jest tylko lewe dziecko
			else if (b_has_left_kid && !b_has_right_kid) {
				// sprawdzamy czy lewe dziecko to operator/nie jest oznaczone jako pelne
				// jezeli tak to rekurencyjnie dodajemy do niego dzieci
				if (c_node_left->b_is_operator == true && !c_node_left->b_is_full) {

					if (c_node_left->b_add_child(c_new_child)) {
						return true;
					}
					else { // w przeciwnym przypadku przekazujemy nod'e do rodzica i odcinamy ta sciezke
						c_node_left->b_is_full = true;
						return c_node_parent->b_add_child(c_new_child);
						
					}
				} // jezeli prawy jest wolny i jest operatorem lub gdy jest pusty
				else if (!c_node_left->b_is_operator || c_node_left->b_is_full) {
					c_node_right = &c_new_child; //ustawiamy wskaznik na nowe dziecko
					c_node_right->c_node_parent = this; //ustawiamy ten node, jako dziecko nowego node
					b_has_right_kid = true; // nadajemy flage has_right na true
					return true; // zwracamy true (udana operacja)

				}
				// w innych przypadkach idziemy wyzej
				else {
					c_node_right->b_is_full = true;
					return c_node_parent->b_add_child(c_new_child);
					

				}
			}
			else if(!b_has_left_kid && b_has_right_kid) {

				// sprawdzamy czy prawe dziecko to operator
				// jezeli tak to rekurencyjnie dodajemy do niego dzieci
				if (c_node_right->b_is_operator == true && !c_node_right->b_is_full) {
					if (c_node_right->b_add_child(c_new_child)) {
						return true;
					}
					else {
						c_node_right->b_is_full = true;
						return c_node_parent->b_add_child(c_new_child);
					
					} // zwracamy czy operacja sie powiodla czy nie
				}
				else if(!c_node_right->b_is_operator || c_node_right->b_is_full) {
					// jezeli prawe dziecko to nie jest operator/jest juz pelny
					c_node_right = &c_new_child; //ustawiamy wskaznik na nowe dziecko
					c_node_right->c_node_parent = this; //ustawiamy ten node, jako dziecko nowego node
					b_has_right_kid = true; // nadajemy flage has_right na true
					return true; // zwracamy true (udana operacja)
				}
			}
			// warunek; jest dwojka dzieci
			else if (b_has_left_kid && b_has_right_kid && !b_is_full) {
				// warunek; lewe dziecko to operator
				if (c_node_left->b_is_operator == true && !c_node_left->b_is_full) {
//					if (!c_node_left->b_has_left_kid || !c_node_left->b_has_right_kid) {
						if (c_node_left->b_add_child(c_new_child)) {
							return true;
						}
						else {
							c_node_left->b_is_full = true;
							return c_node_parent->b_add_child(c_new_child);
						
						} // rekurencyjnie dodajemy nasze nowe dziecko do operatora i
						//zwracamy czy operacja sie udala czy nie
//					}
				}
				else if (!c_node_left->b_is_operator && c_node_right->b_is_full) {
					b_is_full = true;
					return c_node_parent->b_add_child(c_new_child);
				}
				// warunek; prawe dziecko to operator
				else if (c_node_right->b_is_operator == true && !c_node_right->b_is_full) {

					//if (!c_node_left->b_has_left_kid || !c_node_left->b_has_right_kid) {
						if (c_node_right->b_add_child(c_new_child)) {
							return true;
						}
						else {
							c_node_right->b_is_full = true;
							return c_node_parent->b_add_child(c_new_child);
							
						}// rekurencyjnie dodajemy nasze nowe dziecko do operatora
						// i zwracamy czy operacja sie powiodla czy nie
					//}
				}
				else {
					return false; // zwracamy falsz jezeli nie ma zadnego operatora wsrod dzieci
				}
			}
		}
		else {
			return false; // zwracamy falsz jezeli nasz node nie jest operatorem (a wiec nie przyjmuje dzieci bo jest lisciem)
		}
	}
	// funkcja ta sluzy do wypisania wartosci node i jego dzieci
	std::string CNode::v_to_string() {
		if (b_is_operator) {
			return s_value + "Left kid: " + c_node_left->s_value + " " + "Right kid: " + c_node_right->s_value;
		}
		else {
			return s_value + "(leaf)";
		}
	}
// funkcja ta liczy wartosc node (rekurencyjnie)
	double CNode::calculate() {
		if (b_is_number) {
			// jezeli node to liczba to konwertujemy ja i zwracamy
			return std::stod(s_value);
		}
		else if (b_is_operator) {
			// jezeli node to operator to bedziemy prowadzic operacje na jego dzieciach
			double left_value = 0.0, right_value = 0.0;

			// wywolujemy metode rekursywnie dla dzieci
			if (c_node_left != nullptr) {
				left_value = c_node_left->calculate();
			}
			if (c_node_right != nullptr) {
				right_value = c_node_right->calculate();
			}

			// zwracamy wartosc w zaleznosci od operatora
			if (s_value == "+") {
				return left_value + right_value;
			}
			else if (s_value == "-") {
				return left_value - right_value;
			}
			else if (s_value == "*") {
				return left_value * right_value;
			}
			else if (s_value == "/") {
				if (right_value != 0) {
					return left_value / right_value;
				}
				else {
					// dzielenie przez zero, zwracamy komunikat i wartosc 0, aby program nie przestal dzialac
					std::cout << "Error: Division by zero\n";
					return 0.0; // zwracamy wartosc 0
				}
			}
			else if (s_value == "sin") {
				return sin(left_value);
			}
			else if (s_value == "cos") {
				return cos(left_value);
			}
			else {
				// niezdefiniowane operatory
				std::cerr << "Error: Unsupported operator\n";
				return 0.0; // tez zwracamy 0
			}
		}
		else {
			// wyjatkowa sytuacja gdy z jakiegos powodu node nie jest ani operatorem, ani liczba (czyli np dalej jest zmienna)
			std::cout << "Error: Invalid node type\n";
			return 0.0; // zwracamy 0 
		}
	}



