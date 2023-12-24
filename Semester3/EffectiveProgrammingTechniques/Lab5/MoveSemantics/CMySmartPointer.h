#include "CRefCounter.h"
#pragma once
#ifndef CMYSMARTPOINTER_H
#define CMYSMARTPOINTER_H

// klasa szablonowa
template <typename T>
class CMySmartPointer {
public:
	CMySmartPointer(T* pcPointer); // konstruktor
	CMySmartPointer(const CMySmartPointer& pcOther); // konstruktor kopiujacy
	~CMySmartPointer(); // destruktor

	CMySmartPointer<T>& operator=(const CMySmartPointer<T>& pcOther); // operator przypisania
	T& operator*(); // operator dereferencji
	T* operator->(); // operator dereferencji

private:
	CRefCounter *pc_counter; // wskaznik na licznik referencji
	T* pc_pointer; // wskaznik na obiekt

};
#endif
