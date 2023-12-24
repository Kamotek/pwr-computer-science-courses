#include "CMySmartPointer.h"
#include "CRefCounter.h"

template <typename T>
CMySmartPointer<T>::CMySmartPointer(T* pcPointer) {
	pc_pointer = pcPointer; // przypisanie wskaznika
	pc_counter = new CRefCounter(); // utworzenie licznika referencji
	pc_counter->iAdd(); // zwiekszenie licznika referencji
}
template <typename T>
CMySmartPointer<T>::CMySmartPointer(const CMySmartPointer& pcOther) {
	pc_pointer = pcOther.pc_pointer; // przypisanie wskaznika
	pc_counter = pcOther.pc_counter; // przypisanie licznika referencji
	pc_counter->iAdd(); // zwiekszenie licznika referencji
}

template <typename T>
CMySmartPointer<T>::~CMySmartPointer() {
	if (pc_counter->iDec() == 0) { // jesli licznik referencji jest rowny 0
		delete pc_pointer; // usun wskaznik
		delete pc_counter; // usun licznik referencji
	}
}

template <typename T>
T& CMySmartPointer<T>::operator*() {
	return(*pc_pointer); // zwroc wartosc wskaznika
}
template <typename T>
T* CMySmartPointer<T>::operator->() {
	return(pc_pointer); // zwroc wskaznik
}

template <typename T>
CMySmartPointer<T>& CMySmartPointer<T>::operator=(const CMySmartPointer<T>& pcOther) {
	if (this != &pcOther) { // sprawdz czy nie przypisujemy do samego siebie
		// Jesli licznik referencji jest rowny 0, usun wskaznik i licznik referencji
		if (pc_counter->iDec() == 0) {
			delete pc_pointer;
			delete pc_counter;
		}

		// przypisz wskaznik i licznik referencji
		pc_pointer = pcOther.pc_pointer;
		pc_counter = pcOther.pc_counter;
		pc_counter->iAdd();
	}
	return *this; // zwroc wskaznik
}
