
#include "CNumber.h"
#include <string>
#include <iostream>
	
 //konstruktor domyslny, na potrzeby tego zadania nieuzywany
	CNumber::CNumber()  {
		pi_digits_of_number_table = new int[1];
		pi_digits_of_number_table[0] = 0;
		i_number_of_digits = 1;
		b_is_bigger = true;
		b_is_negative = false;
	}

	// konstruktor przyjmujacy liczbe calkowita
	CNumber::CNumber(int i_number) {
		
		
		b_is_bigger = true; // ustawiamy domyslnie flage b_is_bigger na true

		// jezeli liczba jest mniejsza od 0
		if (i_number < 0) {
			b_is_negative = true;  //ustawiamy flage is_negative na true
			i_number = std::abs(i_number);  // zmieniamy wartosc na wartosc absolutna, gdyz bedziemy pracowac na liczbach calkowitch
		}



		i_number_of_digits = count_digits(i_number); // liczymy cyfry naszej liczby
		

		pi_digits_of_number_table = new int[i_number_of_digits]; // alokujemy tablice o dlugosci naszej liczby

		// element po elemencie dodajemy cyfry naszej liczby do tablicy
		for (int ii = 0; ii < i_number_of_digits; ii++) {
			pi_digits_of_number_table[ii] = i_number % 10;
			i_number = i_number / 10;
		}

	}

	// destruktor - jedyna zmienna jaka alokujemy dynamicznie, a nie na stosie wywolan to tablica cyfr
	CNumber::~CNumber() {
		delete[] pi_digits_of_number_table;
	}

	
	// operator przypisania dla wartosci int
	void CNumber::operator=(int i_value) {
		
		b_is_bigger = true; // ustawiamy flage domyslnie na true;

		if (i_value < 0) { 
			b_is_negative = true; 
			i_value = std::abs(i_value); 
			// jezeli liczba jest ujemna ustawiamy flage is negative = true i nasza wartosc  
			// na wartosc absolutna
		}


		i_number_of_digits = count_digits(i_value); // liczymy cyfry naszej wartosci int
		

		pi_digits_of_number_table = new int[i_number_of_digits]; // dynamicznie alokujemy nasza tablice cyfr

		// wpisujemy po kolei nasze cyfry do tablicy
		for (int ii = 0; ii < i_number_of_digits; ii++) {
			pi_digits_of_number_table[ii] = i_value % 10;
			i_value = i_value / 10;
		} 
	}

	// operator przypisania dla CNumber

	void CNumber::operator=(const CNumber& pcOther)
	{

		// ustawiamy domyslnie flage b_is_bigger na true;
		b_is_bigger = true;

		/*
		 pi_digits_of_number_table = pcOther.pi_digits_of_number_table;
		 i_number_of_digits = pcOther.i_number_of_digits;
		*/
		// to wyzej nie zadziala, bo to plytka kopia i destruktor robi konflikt wtedy
		// aby uniknac generowania errorow, nalezy wykonac deep copy:
		

	  if (this == &pcOther) {
		return; // Self-assignment check
	}
		 b_is_negative = pcOther.b_is_negative; // kopiujemy flage is_negative
		i_number_of_digits = pcOther.i_number_of_digits; // kopiujemy liczbe cyfr
		pi_digits_of_number_table = new int[i_number_of_digits]; // alokujemy nowa tablice

		for (int ii = 0; ii < i_number_of_digits; ii++) {
			pi_digits_of_number_table[ii] = pcOther.pi_digits_of_number_table[ii]; // przepisujemy po kolei cyfry z tablicy do tablicy
		}
	}

		//-------------------------------------------------------

		// operator dodawania +

		CNumber CNumber::operator+(CNumber& pcOther) {
			// tworzymy zmianna CNumber result, ktora na koncu zwrocimy
			CNumber result;

			// ustawiamy maksymalna mozliwe liczbe cyfr wyniku (w przypadku dodawania najwieksza + 1 rzad wielkosci, np 99 + 3 = 102)
			int i_max_result_digits = std::max(i_number_of_digits, pcOther.i_number_of_digits) + 1;
			result.pi_digits_of_number_table = new int[i_max_result_digits];


			// ustalamy flage b_is_bigger, czyli sprawdzamy czy nasza pierwsza liczba jest wieksza od pcOther
			check_if_bigger(pcOther);


			// jezeli obie liczby maja taki sam znak, mozemy przeprowadzic normalne dodawanie
			// idea:
			// dodajemy obie liczby do wyniku czastkowego, nastepnie sprawdzamy czy wynik ten jest jednocyfrowy czy dwucyfrowy
			// jezeli dwucyfrowy to odcinamy koncowke i bedziemy ja przenosic do kolejnej pozycji 
			// innymi slowy analogia do dodawania pisemnego
			if ((!pcOther.b_is_negative && !b_is_negative) || (pcOther.b_is_negative && b_is_negative)) {
				
				int i_carry = 0;

				for (int ii = 0; ii < i_max_result_digits; ii++) {
					int i_sum = i_carry;

					if (ii < i_number_of_digits) {
						i_sum += pi_digits_of_number_table[ii];
					}

					if (ii < pcOther.i_number_of_digits) {
						i_sum += pcOther.pi_digits_of_number_table[ii];
					}

						result.pi_digits_of_number_table[ii] = i_sum % 10;
						i_carry = i_sum / 10;

				}



				// ustawiamy nasza liczbe cyfr na poczatkowo obliczona maksymalna liczbe i jezeli dodajemy do niej 1
				// jezeli carry jest wieksze od 0
				result.i_number_of_digits = i_max_result_digits + (i_carry > 0);

				// sprawdzamy znaki - jezeli oba sa dodatnie to nasz wynik jest dodatni, jezeli ujemne to wynik tez jest ujemny
				if (pcOther.b_is_negative && b_is_negative) {
					result.b_is_negative = true;
				}
				else {
					result.b_is_negative = false;
				}
		
			}
			// pozostale przypadki
			else {
				// jezeli pierwsza liczba jest ujemna a druga dodatnia
				if (b_is_negative && !pcOther.b_is_negative) {
					b_is_negative = false;
					result = pcOther - *this;
					b_is_negative = true;
					result.b_is_negative = true;
					
				}
				// jezeli pierwsza jest dodatnia a druga ujemna
				else if (!b_is_negative && pcOther.b_is_negative) {
					pcOther.b_is_negative = false;
					result = *this - pcOther;
					if (b_is_bigger) {
						result.b_is_negative = false;
					}
					else { result.b_is_negative = true; }
					pcOther.b_is_negative = true;
				}
			}

			return result;
		}

		// operacja odejmowania -

		CNumber CNumber::operator-(CNumber& pcOther) {

			// tworzymy tymczasowy obiekt result, ktory na koncu zwrocimy
			CNumber result;


			// ustalamy potencjalna liczbe cyf wyniku (dla odejmowania taka sama, jak najwieksza liczba)
			int i_max_result_digits = std::max(i_number_of_digits, pcOther.i_number_of_digits);
			result.pi_digits_of_number_table = new int[i_max_result_digits];

			

			// ustawiamy flage b_is_bigger, czyli sprawdzamy ktora liczba jest wieksza
			check_if_bigger(pcOther);


			// jezeli pierwsza liczba jest wieksza i dodatnia, a druga jest tez dodatnia
			// przeprowadzamy klasyczne odejmowanie tzn:
			// inicjujemy zmienna i_borrow i w petli:
			// ustawiamy wartosc i_diff na i_borrow, dodajemy do niej cyfre z odpowiedniego miejsca w tablicy
			// a nastepnie odejmujemy odpowiadajaca cyfre z tablicy pcOher i akumulujemy wynik w i_diff
			// sprawdzamy czy nie powstala nam w ten sposob liczba ujemna, jezeli tak to dodajemy 10 do wyniku i -1 do i_borrow
			// (analogia do dzialania pisemnego, robimy pozyczke z kolejnej wartosci)
			if (b_is_bigger && !b_is_negative && !pcOther.b_is_negative) {
				int i_borrow = 0;
				for (int ii = 0; ii < i_max_result_digits; ii++) {
					int i_diff = i_borrow;

					if (ii < i_number_of_digits) {
						i_diff += pi_digits_of_number_table[ii];
					}

					if (ii < pcOther.i_number_of_digits) {
						i_diff -= pcOther.pi_digits_of_number_table[ii];
					}

					if (i_diff < 0) {
						i_diff += 10;
						i_borrow = -1;
					}
					else {
						i_borrow = 0;
					}

					result.pi_digits_of_number_table[ii] = i_diff;
				}



				// usuwamy niepotrzebne 0 z tablicy zmniejszajac jej wartosc
				while (i_max_result_digits > 1 && result.pi_digits_of_number_table[i_max_result_digits - 1] == 0) {
					i_max_result_digits--;
				}
				// ustalamy liczbe cyfr w wyniku
				result.i_number_of_digits = i_max_result_digits;
			} // inne przypadki - rozwiazujemy je poprzez "kombinowanie" ze zmiana znakow, uzywajac dzialan + i -
			// jezeli obie liczby sa dodatnie, a pierwsza jest mniejsza
			else if (!b_is_bigger && !b_is_negative && !pcOther.b_is_negative) {
				result = pcOther - *this;
				result.b_is_negative = true;
			} 
			// jezeli pierwsza liczba jest mniejsza i dodatnia a druga ujemna
			else if (!b_is_bigger && !b_is_negative && pcOther.b_is_negative) {
				pcOther.b_is_negative = false;
				b_is_negative = false;
				result = *this + pcOther;
				result.b_is_negative = true;
				pcOther.b_is_negative = true;

			}
			// jezeli pierwsz liczba jest wieksza i dodatnia a druga ujemna
			else if (b_is_bigger && !b_is_negative && pcOther.b_is_negative) {
				pcOther.b_is_negative = false;
				b_is_negative = false;
				result = *this + pcOther;
				result.b_is_negative = false;
				pcOther.b_is_negative = true;

			}

			// jezeli pierwsza liczba jest wieksza i ujemna a druga dodatnia
			else if (b_is_bigger && b_is_negative && !pcOther.b_is_negative) {

				b_is_negative = false;
				result = pcOther + *this;
				result.b_is_negative = true;
				b_is_negative = true;
			}

			//jezeli pierwsza liczba jest mniejsza i ujemna i druga dodatnia
			else if (!b_is_bigger && b_is_negative && !pcOther.b_is_negative) {
				b_is_negative = false;
				result = pcOther - *this;
				b_is_negative = true;

			}

			// jezeli pierwsza liczba jest ujemna i druga jest ujemna
			else if (b_is_negative && pcOther.b_is_negative) {
				pcOther.b_is_negative = false;
				result = *this + pcOther;
				pcOther.b_is_negative = true;
				if (b_is_bigger) {
					result.b_is_negative = true;
				}
				else {
					result.b_is_negative = false;
				}
			}

			// zwracamy wynik
			return result;
		}

		// operator mnozenia

		CNumber CNumber::operator*(CNumber& pcOther) {

			// ustalamy maksymalna mozliwa dlugosc naszego wyniku (czyli suma cyfr naszej liczby, a takze liczby pcOther)
			int resultDigits = i_number_of_digits + pcOther.i_number_of_digits;

			// tworzymy na stosie nasz obiekt result, ktory na koncu zwrocimy
			CNumber result;


			// mnozenie jest obustronne, a takze dziala tak samo w przypadku ujemnych jak i dodanich liczb
			// dlatego na tym etapie mozemy sprawdzic jakie znaki maja czynniki naszego mnozenia a takze ustalic
			// znak wartosci zwracanej		
			
			if ((pcOther.b_is_negative && b_is_negative) || (!pcOther.b_is_negative && !b_is_negative)) {
				result.b_is_negative = false;
			}
			else {
				result.b_is_negative = true;
			}


			// dynamicznie alokujemy tablice cyfr naszego CNumber result i ustawiamy jej ustalona wczesniej dlugosc
			result.pi_digits_of_number_table = new int[resultDigits];

			// wpisujemy w wynik najpierw wartosc 0 
			for (int i = 0; i < resultDigits; i++) {
				result.pi_digits_of_number_table[i] = 0;
			}

			// dokonujemy tutaj operacji mnozenia
			// idea: do odpowiedniego miejsca w tabeli dodajemy wynik mnozenia dwoch cyfr powiekszony 
			// o ewentualne i_carry, nastepnie sprawdzamy, czy nasza cyfra na pewno jest cyfra - jezeli nie jest
			// to ucinamy ostatnia cyfre tej liczby i wpisujemy je do zminnej i_carry


			for (int i = 0; i < i_number_of_digits; i++) {
				int carry = 0;

				for (int j = 0; j < pcOther.i_number_of_digits; j++) {
					int tempNumber = pi_digits_of_number_table[i] * pcOther.pi_digits_of_number_table[j] + carry;
					carry = tempNumber / 10;
					tempNumber %= 10;

					result.pi_digits_of_number_table[i + j] += tempNumber;

					if (result.pi_digits_of_number_table[i + j] >= 10) {
						carry += result.pi_digits_of_number_table[i + j] / 10;
						result.pi_digits_of_number_table[i + j] %= 10;
					}
				}

				// jezeli i_carry > 0 tzn ze musimy go jeszcze dopisac do odpowiedniego miejsca w tablicy
				int index = i + pcOther.i_number_of_digits;

				while (carry > 0) {
					result.pi_digits_of_number_table[index] += carry;
					carry /= 10;
					index++;
				}
			}

			// ustalamy zmienna reprezentujaca dlugosc tablicy w wyniku
			result.i_number_of_digits = resultDigits;
			// zwracamy wynik
			return result;
		}

		// operator dzielenia

		CNumber CNumber::operator/(CNumber& pcOther) {

			// sprawdzamy czy mamy do czynienia z dzieleniem przez 0 - zdecydowalem sie wtedy na wyrzucenie komunikatu o bledzie
			// i zmiane cyfry 0 na 1

			if (pcOther.pi_digits_of_number_table[0] == 0 && pcOther.i_number_of_digits == 1) {
				// Division by zero is undefined
				std::cout << ("Division by zero, correction: 0 -> 1\n");
				pcOther = 1;
			}

			// umieszczamy na stosie nasza zmienna obiektowa result, ktora pozniej zwrocimy (to jest nasz wynik)
			CNumber result;

			// ustalamy wielkosc wyniku na ilosc cyfr naszej dzielonej (np 5000/2 = 2500, tzn liczba cyfr sie nie zmiania)
			int i_result_digits = i_number_of_digits;


			// w przypadku dzielenia bez wzgledu na znak postepujemy tak samo
			// jedyne co sie zmienia to znak wyniku
			// sprawdzamy tutaj czy ustawic wynikowi flage liczby ujemnej czy dodatniej

			if ((pcOther.b_is_negative && b_is_negative) || (!pcOther.b_is_negative && !b_is_negative)) {
				result.b_is_negative = false;
			}
			else {
				result.b_is_negative = true;
			}



			// dynamicznie alokujemy nasza tablice wyniku (pozniej za sprawa destruktora automatycznie sie dealokuje)
			result.pi_digits_of_number_table = new int[i_result_digits];

			// ta zmienna to nasza tymczasowa reszta, dzielenie odbywa sie analogicznie do dzielenia pisemnego
			int remainder = 0;


			// konwertujemy nasz dzielnik na wartosc long, poniewaz podczas dzielnia bedziemy wykorzystywac cala liczbe
			long iValueAbs = 0;
			int mul = 1;
			for (int i = 0; i < pcOther.i_number_of_digits; i++) {
				iValueAbs += pcOther.pi_digits_of_number_table[i] * mul;
				mul *= 10;
			}


			// tutaj odbywa sie dzielenie
			// idea: bierzemy pierwsza cyfre, dodajemy do niej nasza tymczasowa reszte powiekszona o jeden rzad wielkosci
			// a nastepnie dzielimy to przez dzielnik i dodajemy do liczby znajdujacej sie
			//  na obecnie obslugiwanej w petli pozycji
			for (int i = i_number_of_digits - 1; i >= 0; i--) {
				int currentDigit = pi_digits_of_number_table[i] + remainder * 10;
				result.pi_digits_of_number_table[i] = currentDigit / iValueAbs;
				remainder = currentDigit % iValueAbs;
			}

			// jezeli w wyniku tego dzialania powstaly nic nie znaczace zera, "usuwamy je" zmieniajac dlugosc tablicy
			while (i_result_digits > 1 && result.pi_digits_of_number_table[i_result_digits - 1] == 0) {
				i_result_digits--;
			}

			// ustawiamy dlugosc tablicy na te skorygowana
			result.i_number_of_digits = i_result_digits;

			// zwracamy wynik (obiekt CNumber)
			return result;
		}

		



		//-------------------------------------------------------



		


	// funkcja ta sluzy ustaleniu flagi b_is_bigger
	
		void CNumber::check_if_bigger(CNumber& pcOther) {

			// sprawdzamy czy dlugosc naszej liczby jest mniejsza od ilosci cyfr drugiej liczby
			// jezeli tak - ustawiamy flage b_is_bigger na false
		if (i_number_of_digits < pcOther.i_number_of_digits) {
			b_is_bigger = false;
		}

		// sprawdzamy czy dlugosc naszej liczby jest wieksza od ilosci cyfr drugiej liczby
		// jezeli tak - ustawiamy flage b_is_bigger na true
		else if (i_number_of_digits > pcOther.i_number_of_digits) {
			b_is_bigger = true;
		}
		else {
			// w przypadku gdy liczba cyfr jest taka sama sprawdzamy po kolei cyfra po cyfrze
			// aby ocenic ktora liczba jest wieksza
			// warunkiem zakonczenia petli jest sytuacja gdy dojdziemy do konca obu cyfr (czyli sa takie same)
			// albo gdy zmienimy flage b_coontinue_loop na false (czyli znalezlismy odpowiedz na pytanie ktora liczba jest wieksza)
			int i = 0;
			bool b_continue_loop = true;

			while (i < i_number_of_digits && b_continue_loop) {
				if (pi_digits_of_number_table[i] < pcOther.pi_digits_of_number_table[i]) {
					b_is_bigger = false;
					b_continue_loop = false;
				}
				else if (pi_digits_of_number_table[i] > pcOther.pi_digits_of_number_table[i]) {
					b_is_bigger = true;
					b_continue_loop = false;
				}
				// cyfry sa takie same, a wiec przechodzimy do kolejnego indexu
				i++;
			}
		}
	}

	


	// ta funkcja ma na celu wyliczyc ilosc cyfr w naszej liczbie

	int CNumber::count_digits(int i_number) {
		i_number = std::abs(i_number); // uzywamy wartosci abs, aby wyeliminowac znak -
		
		// konwertujemy nasza liczbe do typu string, aby nastepnie zwrocic jego dlugosc
		std::string numStr = std::to_string(i_number); 
		return numStr.length();
	}



	// ta funkcja sluzy do przygotowania naszego obiektu do wypisania na ekran
	std::string CNumber::sToStr() {
	
		int i_number = 0; // liczba ktora na koncu zwrocimy
		
		int i_multiplier = 1; // pomocnicza zmienna uzywana w petli celem nadania prawidlowej wadze cyfrze
		
		// petla ktora iteruje po calej tablicy cyfr i tworzy z nich calkowita liczbe
		for (int ii = 0; ii < i_number_of_digits; ii++) {
			i_number = i_number + (pi_digits_of_number_table[ii] * i_multiplier);
			i_multiplier = i_multiplier * 10;
		}


		// dzialamy caly czas na wartosciach absolutnych, dlatego w tym miejscu trzeba sprawdzic czy flaga b_is_negative
		// jest ustawiona na wartosc true - jezeli tak, to znaczy ze nasza liczba musi miec znak - z przodu
		if (b_is_negative) {
			i_number = -i_number;
		}

		// zwracamy nasza liczbe przekonwertowana do typu string
		return std::to_string(i_number);
	}



