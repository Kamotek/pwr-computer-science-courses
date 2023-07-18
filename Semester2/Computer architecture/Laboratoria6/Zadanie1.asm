.text

	li $v0, 5  #syscall ten pozwala uzytkownikowi wpisac liczbe ktorej wartosc znajdzie sie w rejestrze v0
	syscall
	
	sw $v0, licz1 #zapisujemy slowo znajdujace sie w rejestrze v0 do zadeklarowanego pod etykieta licz1 32-bitowego miejsca
	
	li $v0, 5  #syscall ten pozwala uzytkownikowi wpisac liczbe ktorej wartosc znajdzie sie w rejestrze v0
	syscall
	
	sw $v0, licz2 #zapisujemy slowo znajdujace sie w rejestrze v0 do zadeklarowanego pod etykieta licz2 32-bitowego miejsc
	
	
	lw $t0, licz1  #załadowanie do rejestru t0 wartości znajdujacej sie pod adresem powiazanym z etykieta licz1
	lw $t1, licz2 #załadowanie do rejestru t1 wartości znajdujacej sie pod adresem powiazanym z etykieta licz2
	
	li $t2, 0  #w tych dwoch liniach wczytuje 0 do wartosci t0, a nastepnie wpisuje te wartosc w wynik, aby ustawic wynik poczatkowy na 0
	sw $t2, wyn #czyli pierwszy krok naszego algorytmu, zerujemy wynik
	
	li $t3, 0 #to jest nasz counter 

	loop:
	beqz, $t1, endloop #bedziemy przesuwac rejestr t1, czyli mnoznik w prawo, czyli tak jakby odcinac ostatnie cyfry, a gdy cyfry sie skoncza to konczymy petle
	
	andi $t4, $t1, 0x00000001 #dokonujemy operacji maskowania, a nastepnie przypisujemy rejestrowi t4 wartosc ostatniej cyfry mnoznika
	srl, $t1, $t1, 1 #przesuwamy rejestr w prawo, czyli odcinamy ostatnia cyfre mnoznika
	

	#zapisujemy nasz niezinkrementowany licznik do nowego rejestru, gdyz w kazdym powtorzeniu petli musimy inkrementowac nasz licznik
	#a po warunku bedziemy potrzebowac niezinkrementowanej wartosci 
	move $t5, $t3 
	
	add $t3, $t3, 1 #inkrementujemy nasz licznik, aby nastepna cyfra mnoznika miala wieksza wage
	
	beqz $t4, loop #jezeli obecna cyfra mnoznika jest rowna 0, to nic nie dodajemy, tylko patrzymy na cyfre o nastepnej wadze 
	

	sllv $t6, $t0, $t5 #przesuwamy nasza liczbe mnozona o odpowiednia ilosc wag, aby uzyskac wynik czesciowy
	
	add $t2, $t2, $t6 #dodajemy nasz wynik czesciowy do wyniku koncowego
	
	#jezeli nasz wynik jest mniejszy niz 0 to znaczy ze przekroczylismy zakres, wtedy skaczemy do overflow
	blt $t2, $zero, overflow
	
	
	
	j loop #wracamy do poczatku petli	
	endloop: #tutaj jest znacznik konca petli, do ktorego idziemy jezeli poczatkowy warunek zostanie spelniioiny
	
	

	move $a0, $t2 #przesuwamy wartosc rejestru t2, czyli nasz wynik do rejestru a0, czyli rejestru ktory jest wypisywany na ekran podczas uzycia nastepnej komendy
	
	#w tych dwoch instrukcjach zapisujemy wartosc naszego wyniku do pola adresu dla naszej etykiety wyn
	la $t8, wyn 
	sw $t2, ($t8)
	
	

	  
	

	
	
	  li $v0, 1  #syscall ten drukuje liczbe znajdujaca sie w rejestrze a0
	  syscall
	  
	li $v0, 10 #konczymy program
	syscall
	
	overflow:
	
	la $a0, status #wczytujemy wartosc etykiety status do a0
	
	li $v0, 4 #wypisujemy wartosc string znajdujaca sie w rejetstzre a0
	syscall
	
	li $v0, 10 #konczymy program
	syscall
	
	
	

	

.data
licz1: .space 4  #deklaracja miejscana 32-bitowe slowo dla etykiety licz1 
licz2: .space 4 #deklaracja miejsca na 32-bitowe slowo dla etykiety licz2 
wyn: .word 0x00000000

status: .asciiz "Blad przekroczenie zakresu 32bit dla wyniku"   #zadeklarowanie etykiety status, ktora ma za zadanie zostac wypisana w przypadku przekroczenia zakresu dla wyniku
