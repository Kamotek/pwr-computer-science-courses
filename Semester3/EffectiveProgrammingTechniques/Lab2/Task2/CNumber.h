#ifndef CNUMBER_H
#define CNUMBER_H

#include <string>

class CNumber
{
private:
	int* pi_digits_of_number_table; //to jest tablica reprezentujaca cyfry naszej liczby
	int i_number_of_digits; // to jest zmienna majaca w sobie zawierac liczbe cyfr naszej liczby 
	bool b_is_negative; // flaga odpowiedzialna za rozroznienie czy liczba jest ujemna czy dodania
	bool b_is_bigger; // flaga odpowiedzialna za rozroznienie czy nasza liczba jest wieksza, uzywamy jej w kontekscie drugiej liczby
	void check_if_bigger(CNumber& pcOther); // wewnetrzna funkcja majaca za zadanie ustalic wartosc flagi is_bigger

	int count_digits(int number); // to jest wewnetrzna funkcja majaca za zadanie policzyc cyfry liczby



public:
	CNumber(); // konstruktor domyslny, w kontekscie tego zadania nieuzywany
	CNumber(int i_number); // konstruktor tworzacy obiekt CNumber na podstawie wartosci i_number
	~CNumber(); // destruktor, tak naprawde jedyna zmienna jaka dynamicznie alokujemy to tablica, reszta zmiennych znajduje sie na stosie


	void operator=(const int i_value); // operator uzywany do zmiany wartosci CNumber w oparciu o liczbe calkowita
	void operator=(const CNumber& pcOther); // operator uzywany do zmiany wartosci CNumber w oparciu o inny obiekt CNumber
	CNumber operator+(CNumber& pcOther); // operator uzywany do dodawania dwoch obiektow CNumber
	CNumber operator-(CNumber& pcOther); // odejmowanie
	CNumber operator*(CNumber& pcOther); // mnozenie
	CNumber operator/(CNumber& pcOther); // dzielenie
	std::string sToStr(); // funkcja zwracajaca w formie stringa wartosc CNumber, uzywana do wypisywania wyniku na ekran

};

#endif

