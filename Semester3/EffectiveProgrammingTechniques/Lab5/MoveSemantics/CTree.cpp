#include "CTree.h"
#include <stack>

// podczas inicjacji konstruktorem domyslnym ustawiamy roota jako nullptr

// konstruktor domyslny



template<typename T>
CTree<T>::CTree() {
    root = nullptr;
}

// destruktor
template<typename T>
CTree<T>::~CTree() {
    // czyscimy wszystkie nod'y po kolei
    for (int i = 0; i < vn_tree_nodes.size(); ++i) {
        delete vn_tree_nodes[i];
        vn_tree_nodes[i] = nullptr;
    }
    // dodatkowo czyscimy sam vector i ustawiamy roota ponownie na nullptr
    vn_tree_nodes.clear();
    root = nullptr;
}


// konstruktor kopiujacy
template <typename T>
CTree<T>::CTree(const CTree<T>& other) {
    // kopiujemy wartosc wurazenia string ktore zostalo zapisane w drzewie i tworzymy drzewo od nowa
    // (najwygodniejsza opcja kopiowania drzewa
    s_basic_expression = other.s_basic_expression;

    // robimy stream z oryginalnego wyrazenia
    std::stringstream ss_expression_stream(other.s_basic_expression);
    std::string s_token;

    // przepisujemy stream element po elemencie do nowego vectora z pokedynczymi znakami
    while (ss_expression_stream >> s_token) {
        vs_tokens.push_back(s_token);
    }


    // budujemy nowe drzewo
    v_build_tree();
}


// przeciazane 3 funkcje zwracajace typ obiektu
template<>
inline std::string CTree<std::string>::s_get_type() {
    return "string";
}
template<>
inline std::string CTree<int>::s_get_type() {
    return "int";
}
template<>
inline std::string CTree<double>::s_get_type() {
    return "double";
}
template <>
inline std::string CTree<int>::s_get_name() {
    return "xd";
}

template <>
inline std::string CTree<double>::s_get_name() {
    // Assuming root is a pointer to a node storing double
    return root->s_value; // Make sure root and s_value are appropriately defined.
}

template <typename T>
inline std::string CTree<T>::s_get_name() {
    // Assuming root is a pointer to a node storing std::string
    return root->s_value; // Make sure root and s_value are appropriately defined.
}



// enter - wprowadzenie wyrazenia do drzewa 
template <typename T>
void CTree<T>::enter(std::string& expression) {
    // rozbijamy wyrazenie na stringstream
    std::stringstream ss_expression_stream(expression);
    std::string s_token;
    s_basic_expression = expression;


    // element po elemencie wpisujemy do vectora elementow
    while (ss_expression_stream >> s_token) {
        vs_tokens.push_back(s_token);
    }

    v_repair_tree(); // naprawiamy drzewo
    v_build_tree(); // budujemy drzewo

}
// dodatkowy enter, uzywamy go, aby polaczyc dwa drzewa
template <typename T>
void CTree<T>::enter(std::string& expression, std::vector<CNode<T>*> parent_tree_nodes) {
    // robimy streama z wyrazenia w parametrze funkcji
    std::stringstream ss_expression_stream(expression);
    std::string s_token;
    s_basic_expression = expression;

    // dodajemy wartosci node'ow drzewa oryginalnego do nowej puli elementow wyrazenia (-1, poniewaz odcinamy ostatniego liscia)
    for (int i = 0; i < parent_tree_nodes.size() - 1; ++i) {
        vs_tokens.push_back(parent_tree_nodes[i]->s_value);
    }

    // dodajemy elementy wyrazenia ktore ma zosrac dodane do liscia
    while (ss_expression_stream >> s_token) {
        vs_tokens.push_back(s_token);
    }

    v_repair_tree();  // naprawiamy drzewo
    v_build_tree(); // budujjemy drzewo
    return;
}

// funckja pomocnicza odwracajaca wyrazenie, uzywamy przy podmianie zmiennych na stale
template <typename T>
std::string CTree<T>::reverseString(const std::string& str) {
    std::string s_reversed_expression;
    for (int i = str.length() - 1; i >= 0; --i) {
        s_reversed_expression += str[i];
    }
    return s_reversed_expression;
}
template <>

// funkcja majaca za zadanie zmienic zmienne na stale wartosci
inline void CTree<std::string>::vars(std::string s_values) {
    std::vector<std::string> reversedValues;
    reversedValues.push_back(s_values);
    std::reverse(reversedValues.begin(), reversedValues.end());

    std::vector<std::string> values;
    for (const std::string& str : reversedValues) {
        std::stringstream ss(str);
        std::string token;
        while (ss >> token) {
            values.push_back(token);
        }
    }

    size_t valueIndex = 0;
    for (size_t i = 0; i < vn_tree_nodes.size(); ++i) {
        if (vn_tree_nodes[i]->b_is_variable) {
            vn_tree_nodes[i]->b_is_variable = false;
            vn_tree_nodes[i]->b_is_number = true;
            vn_tree_nodes[i]->s_value = values[valueIndex];
            ++valueIndex;

            for (size_t j = i + 1; j < vn_tree_nodes.size(); ++j) {
                if (vn_tree_nodes[j]->b_is_variable &&
                    vn_tree_nodes[j]->s_value == vn_tree_nodes[i]->s_value) {
                    vn_tree_nodes[j]->b_is_variable = false;
                    vn_tree_nodes[j]->b_is_number = true;
                    vn_tree_nodes[j]->s_value = vn_tree_nodes[i]->s_value;
                }
            }
        }
    }
}

// funkcja podmieniajaca elementy zmienne na stale (a -> 3)
template <typename T>
inline void CTree<T>::vars(std::string s_values) {
    s_values = reverseString(s_values);
    std::stringstream ss(s_values);
    std::vector<int> values;
    int num;

    // uzywamy streamu tak samo jak dla normalnych wyrazen
    while (ss >> num) {
        values.push_back(num);
    }

    // szukamy elementow ktore sa zmiennymi i ustawiamy ich wartosc na taka jaka mamy obecnie na liscie
    for (size_t i = 0; i < vn_tree_nodes.size(); ++i) {
        if (vn_tree_nodes[i]->b_is_variable) {
            vn_tree_nodes[i]->b_is_variable = false;
            vn_tree_nodes[i]->b_is_number = true;
            vn_tree_nodes[i]->s_value = std::to_string(values.back());
            values.pop_back(); // usuwamy zuzyta wartosc

            // sprawdzamy i podmieniamy wszystkie takie same zmienne
            for (size_t j = i + 1; j < vn_tree_nodes.size(); ++j) {
                if (vn_tree_nodes[j]->b_is_variable &&
                    vn_tree_nodes[j]->s_value == vn_tree_nodes[i]->s_value) {
                    vn_tree_nodes[j]->b_is_variable = false;
                    vn_tree_nodes[j]->b_is_number = true;
                    vn_tree_nodes[j]->s_value = vn_tree_nodes[i]->s_value;
                }
            }
        }
    }
}

// funkcja pomocnicza zamieniajaca vector pojedynczych elementow typu string na node ktora pozniej mozna dodac do drzewa

template <typename T>
inline void CTree<T>::v_make_nodes() {
    // errorm jezeli vector jest pusty
    if (vs_tokens.empty()) {
        std::cout << "No tokens to build the tree.\n";
        return;
    }

    // tworzymy nowe node i dodajemy do vectora nodow
    for (size_t i = 0; i < vs_tokens.size(); ++i) {
        CNode<T>* new_node = new CNode<T>(vs_tokens[i], i == 0); // i == 0 gdyz 0 ma byc rootem, wiec przekazujemy do konstruktora true
        vn_tree_nodes.push_back(new_node);
    }

}
// naprawiamy drzewo
template <typename T>
void CTree<T>::v_repair_tree() {
    // deklaracja stosu
    std::stack<std::string> s_token_stack;
    // deklaracja zmiennej liczacej ilosc nie-operatorow w naszym wyrazeniu, istotny element algorytmu
    int i_number_counter = 0;


    // wrzucamy wszystkie elementy wyrazenia z wektoru na stos
    for (std::string token : vs_tokens) {
        s_token_stack.push(token);
    }
    // czyscimy nasz wektor, poniewaz bedziemy go aktualizowac
    vs_tokens.clear();

    // pierwszy element jest wyjatkowy, jezeli jest to operator musimy dodac dwie jedynki (gdyz operator ma dwa liscie)
    if (s_token_stack.top() == "-" || s_token_stack.top() == "+" || s_token_stack.top() == "*" || s_token_stack.top() == "/" || s_token_stack.top() == "sin" || s_token_stack.top() == "cos") {
        if (s_get_type() == "int" || s_get_type() == "double") {
            vs_tokens.push_back("1");
            vs_tokens.push_back("1");
        }
        else if (s_get_type() == "string") {
            vs_tokens.push_back("a");
            vs_tokens.push_back("a");
        }
        vs_tokens.push_back(s_token_stack.top());
        s_token_stack.pop();
        i_number_counter = 1;

    }
    // algorytm dziala nastepujaco:
    // jezeli mamy do czynienia z nie-operatorem to usuwamy go ze stosu i dodajemy do wektora, inkrementujac i_number_counter
    // jezeli mamy na stosie operator to sprawdzamy, czy ma on swoje dzieci (i_number_counter >=2)
    // jezeli mu tych dzieci brakuje to dodajemy (naprawiamy stos)
    // po dodaniu operatora dekrementujemy i_number_cunter
    while (!s_token_stack.empty()) {
        if (s_token_stack.top() == "-" || s_token_stack.top() == "+" || s_token_stack.top() == "*" || s_token_stack.top() == "/" || s_token_stack.top() == "sin" || s_token_stack.top() == "cos") {
            if (i_number_counter == 0 || i_number_counter == 1) {
                if (s_get_type() == "int" || s_get_type() == "double") {
                    vs_tokens.push_back("1");
                }
                else if (s_get_type() == "string") {
                    vs_tokens.push_back("a");
                }
                vs_tokens.push_back(s_token_stack.top());
                s_token_stack.pop();
                i_number_counter = 0;
            }
            else if (i_number_counter >= 2) {
                vs_tokens.push_back(s_token_stack.top());
                s_token_stack.pop();
                i_number_counter--;
            }

        }
        else {
            vs_tokens.push_back(s_token_stack.top());
            s_token_stack.pop();
            i_number_counter++;
        }
    }


    // na koncu odwracamy nasz wektor, gdyz po operacjach na stosie jest on odwrocony
    std::reverse(vs_tokens.begin(), vs_tokens.end());
}

template <typename T>
void CTree<T>::join(std::string s_expression) {
    CTree tempTree;
    tempTree.enter(s_expression, vn_tree_nodes);

    // usuwamy stare node
    for (int i = 0; i < vn_tree_nodes.size(); ++i) {
        delete vn_tree_nodes[i];
    }
    // czyscimy wektory, aby sie upewnic ze na pewno sa puste
    vn_tree_nodes.clear();
    vs_tokens.clear();


    // dodajemy nowe elementy wyrazenia w formie string, aby moc przebudowac drzewo
    for (int i = 0; i < tempTree.vs_tokens.size(); ++i) {
        vs_tokens.push_back(tempTree.vs_tokens[i]);
    }

    // przebudowujemy drzewo
    v_repair_tree();
    v_build_tree();

    // nie trzeba usuwac tempTree, gdyz jest lokalna zmienna na stosie, ktora sama sie usunie
}

// funkcja sluzaca do wyliczenia wartosci drzewa

template<>
inline void CTree<std::string>::comp() {
    std::cout << "Result of comp is: " << root->calculate() << "\n";

}

template <typename T>
inline void CTree<T>::comp() {
    bool b_has_variables = false; // wewnetrzna flaga oceniajaca czy drzewo ma w sobie zmienne, ktore trzeba podmienic na stale wartosci

    // sprawdzamy jak ustawic flage (iterujemy przez nod'y i reagujemy gdy ktorys z nich nie zostal zmieniony na wartosc stala)
    for (int i = 0; i < vn_tree_nodes.size(); ++i) {
        if (vn_tree_nodes[i]->b_is_variable && !b_has_variables) {
            b_has_variables = true;
        }
    }
    // jezeli drzewo ma zmienne
    if (b_has_variables) {
        std::cout << "Error: Variables present in the tree.\n";
    }
    // jezeli drzewo nie ma zmiennych
    else {
        // If no variables, perform the calculation
        std::cout << "Result of comp is: " << root->calculate() << "\n";
    }
}



// wewnetrznie uzywana funkcja sluzaca do zbudowania drzewa
template <typename T>
void CTree<T>::v_build_tree() {

    v_make_nodes();

    root = vn_tree_nodes[0]; // pierwszy node w vectorze to root

    // uzywamy funkcji zdefniniowanej w nodzie aby dodac dziecko
    for (int i = 1; i < vn_tree_nodes.size(); ++i) {
        root->b_add_child(*vn_tree_nodes[i]);
    }
}
// funkcja zajmujaca sie wypisaniem wartosci drzewa na ekran (wypisujemy cale drzewo)
template <typename T>
void CTree<T>::print() {
    for (int i = 0; i < vn_tree_nodes.size(); ++i) {
        std::cout << "Node " << i << ": " << vn_tree_nodes[i]->v_to_string() << std::endl;
    }
}

// operator dodawania
template <typename T>
CTree<T> CTree<T>::operator+(CTree<T> c_another_tree) {
    CTree tempTree(*this);
    tempTree.join(c_another_tree.s_basic_expression);
    return std::move(tempTree);
}
// operator przypisania wartosci
template <typename T>
CTree<T>& CTree<T>::operator=(CTree<T>& c_another_tree) {
    for (int i = 0; i < c_another_tree.vs_tokens.size(); i++) {
        vs_tokens.push_back(c_another_tree.vs_tokens[i]);
    }
    v_build_tree();

    return std::move(this);
}

// operator przypisania wartosci
template <typename T>
CTree<T>& CTree<T>::operator=(const CTree<T>& c_another_tree) {
    if (this != &c_another_tree) { // Sprawdzenie, czy nie przypisujemy do samego siebie
        // Wyczyœæ bie¿¹ce dane, jeœli to konieczne
        for (int i = 0; i < c_another_tree.vs_tokens.size(); i++) {
            vs_tokens.push_back(c_another_tree.vs_tokens[i]);
        }
        // przebudowanie drzewa
        v_build_tree();
    }
    return std::move(this);
}

// przeciazony operator przypisania
template <typename T>
CTree<T>& CTree<T>::operator+=(const CTree<T>& c_another_tree) {
    this->join(c_another_tree.s_basic_expression);
    return std::move(this);
}
template <typename T>


// operator przenoszacy
CTree<T>& CTree<T>::operator=(CTree<T>&& pcOther) noexcept {
    if (this != &pcOther) { // Check for self-assignment
        // czyscimy aktualny stan naszego obiektu
        for (auto node : vn_tree_nodes) {
            delete node;
        }
        vn_tree_nodes.clear();
        vs_tokens.clear();
        root = nullptr;

        // przenosimy zasoby z other do this
        vn_tree_nodes = std::move(pcOther.vn_tree_nodes);
        vs_tokens = std::move(pcOther.vs_tokens);
        root = pcOther.root;

        // czyscimy other
        pcOther.vn_tree_nodes.clear();
        pcOther.vs_tokens.clear();
        pcOther.root = nullptr;
    }
    return *this;
}

// konstruktor przenoszacy
template <typename T>
CTree<T>::CTree(CTree<T>&& other) noexcept {
	// przenosimy zasoby z other do this
	vn_tree_nodes = std::move(other.vn_tree_nodes);
	vs_tokens = std::move(other.vs_tokens);
	root = other.root;

	// czyscimy other
	other.vn_tree_nodes.clear();
	other.vs_tokens.clear();
	other.root = nullptr;
}
