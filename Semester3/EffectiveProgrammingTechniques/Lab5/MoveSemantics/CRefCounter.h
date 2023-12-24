#pragma once
#ifndef CREFCOUNTER_H
#define CREFCOUNTER_H
class CRefCounter
{
public:
	CRefCounter(); // konstruktor domyslny
	~CRefCounter(); // destruktor
	CRefCounter(const CRefCounter& other); // konstruktor kopiujacy

	int iAdd(); // funkcja zwiekszajaca licznik referencji
	int iDec(); // funkcja zmniejszajaca licznik referencji
	int iGet(); // funkcja zwracajaca wartosc licznika referencji
private:
	int i_count; // licznik referencji
};

#endif