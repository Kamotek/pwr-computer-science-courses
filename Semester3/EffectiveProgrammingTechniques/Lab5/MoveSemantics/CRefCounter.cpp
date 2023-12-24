#include "CRefCounter.h"

 // konstruktor domyslny
CRefCounter::CRefCounter() {
	i_count;
}

// destruktor
CRefCounter::~CRefCounter() {
	delete i_count;
}

// konstruktor kopiujacy
CRefCounter::CRefCounter(const CRefCounter& other) {
	i_count = other.i_count;
}

int CRefCounter::iAdd() {
	return(++i_count);
}
int CRefCounter::iDec() {
	return(--i_count);
}
int CRefCounter::iGet() {
	return(i_count);
}