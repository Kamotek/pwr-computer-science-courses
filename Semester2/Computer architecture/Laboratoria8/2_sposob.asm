.data
	menu_msg: .asciiz "===Wpisz rodzaj operacji=== \n 1 - aby zapisac tablice \n 2 - aby odczytac tablice \n 3 - zakoncz program \n====== \n Twoj wybor: "

	gen_msg_gnl: .asciiz "Podaj dlugosci tablicy do wygenerowania: "
	gen_msg_row:.asciiz "Podaj jak dluga ma byc tablica: "
	gen_msg_col: .asciiz "Podaj jak wysoka ma byc tablica: "

	index_msg_row: .asciiz "Podaj numer wiersza: "
	index_msg_col: .asciiz "Podaj numer kolumny: "

	insert_msg_val: .asciiz "Twoja liczba: "

	newline: .asciiz "\n"

	RAM: .space 4096
.text
	#--- generator tablicy---

	#t0 - dlugosc wiersza
	#t1 - dlugosc kolumny

	li $v0, 4
	la $a0, gen_msg_gnl   #wyswietlamy naglowek o generowaniu tablicy
	syscall

    	li $v0, 4
  	la $a0, newline #przejscie do nowej linii
	syscall

	li $v0, 4
	la $a0, gen_msg_row #wyswietlamy tekst o podaniu dlugosci wiersza macierzy
	syscall

	li $v0, 5	#wpisujemy te dlugosc
	syscall

	move $t0, $v0 #zapisujemy do tymczasowego rejestru nasza dlugosc

	li $v0, 4
	la $a0, gen_msg_col	#wyswietlamy tekst o podaniu dlugosci kolumny macierzy
	syscall

	li $v0, 5	#wpisujemy te dlugosc
	syscall

	move $t1, $v0 	#zapisujemy ja do tymczasowego rejestru

    	li $v0, 4
   	la $a0, newline	#przechodzimy do nowej linii
    	syscall

	#---


	#t0 - dlugosc wiersza
	#t1 - dlugosc kolumny
	#t3 - counter kolejnych wierszy, zewnetrzna petla
	#t4 - cointer kolejnych kolumn, wewnetrzna petlta
	#t9 - adres w pamieci RAM
	#

	la $t9, RAM 
	
	move $t3, $t9 #adres wszerz
	move $t4, $t9 #adres wzdluz
	
	li $t7, 0
	li $t8, 0
	l_loop:
		add $t7, $t7, 1
		sll $t8, $t1, 2
		add $t4, $t4, $t8
		sw $t4, ($t3)
		add $t3, $t3, 4
		bne $t7, $t1, l_loop
	
	
	
	li $t3, 0
	li $t4, 0
	li $t7, 0
	mul $t8, $t0, 4 #offset na ten jeden wiersz z adresami
	add $t9, $t9, $t8
	gen_loop_outer: 
		beq $t1, $t3, menu #jezeli nasza podana wysokosc macierzy zrowna sie z licznikiem, tzn ze cala macierz zostala juz wygenerowana
		mul $t4, $t3, 100 #mnozymy nasz licznik zewnetrzny *100, aby uzyskac odpowiednia liczbe


		
		la $t8, RAM
		add $t8, $t8, $t7
		add $t7, $t7, 4
		lw $t2, ($t8)
		
		
		li $t5, 0 #za kazdym razem resetujemy tutaj wartosc t5, ktora odpowiada za wygenerowanie odpowiedniej liczby
		gen_loop_inner:
			addi $t5, $t5, 1 #w kazdym powtorzeniu petli wewnetrznej inkrementujemy te wartosc o 1 i dodajemy do wynik
			add $t6, $t5, $t4 #nasz wynik, czyli inkrementowana wartosc + t4 z zewnetrznej petli odpowiedzialny za setki
			sw $t6, ($t2) #wpisujemy wartosc w odpowiednie miejsce adreu
			
			addi $t2, $t2, 4 #offset wynikajacy z dlugosci slowa
			bne $t5, $t1, gen_loop_inner #wyjscie z petli, gdy licznik wewnetrzny sie zrowna z dlugoscia wiersza
		addi $t3, $t3, 1 #inkrementujemy licznik zewnetrzny, uzywany jako cyfra setek i warunek wyjscia z petli
		j gen_loop_outer #powrot do poczatku petli
		
	#--- menu glowne ---
	#t1 - szerokosc
	#t2 - wysokosc 
	#t9 - wybor opcji
	
	menu:

    	li $v0, 4       # Kod wywołania systemowego dla wyświetlania ciągu znaków
    	la $a0, menu_msg     # Ładowanie adresu etykiety menu_msg do rejestru $a0
    	syscall         # Wywołanie systemowe
    
   	li $v0, 5       # Kod wywołania systemowego dla wczytywania liczby całkowitej
   	syscall         # Wywołanie systemowe
    
    	move $t9, $v0 #zapisujemy nasza liczbe do rejestru
    
    	beq $t9, 3, end #w tym momencie skaczemy do konca programu, jezeli zostanie wybrana odpowiednia opcja
        
    	li $v0, 4
   	la $a0, newline #nowa linia
    	syscall
    
    	li $v0, 4
    	la $a0, index_msg_row #komunikat o wpisaniu indexu wiersza
    	syscall
    
    	li $v0, 5 #wpisanie liczby odpowiadajacej naszemu indeksowi wiersza
    	syscall
    
    	move $t1, $v0 #zapisanie tej liczby w rejestrze
    
    	li $v0, 4
    	la $a0, newline #nowa linia
    	syscall
    
    	li $v0, 4
    	la $a0, index_msg_col #komunikat o wpisaniu indexu kolumny
    	syscall
    
    	li $v0, 5 #wprowadzenie wliczby odpowiadajacej indeksowi kolumny
    	syscall 
    
    	move $t2, $v0 #zapisnanie tej liczby do rejestru
    
    	#---
   
    	beq $t9 1, insert #jezeli wybralismy opcje1, to skaczemy do czesci programu odpowiadajacej za wprowadzanie nowej wartosci
    	beq $t9 2, read #jezeli wybralismy opcje2, to skaczemy do czesci programu odpowiadajacej za odzcytanie wartosci
    
    
    
    
    #---zapis liczby
    
    	insert:
    	
    	#t1 - wiersz
    	#t2 - kolumna
    	la $s1, RAM #wczytujemy adres ramu
    	
    	sub $t1, $t1, 1 #odejmujemy od wartosci kolumny i wiersza 1, aby zaczynac od indeksu "0", a nie "1"
    	sub $t2, $t2, 1
    	
    	sll $t1, $t1, 2 #mnozymy wartosc wiersza 4x, aby otrzymac offset 
    	add $s1, $s1, $t1 #dodajemy go do wartosci s1, czyli naszego ramu
    	
    	sll $t2, $t2, 2 #mnozymy wartosc kolumn 2x aby otrzymac offset naszej szukanej liczby
    	
    	lw $s2, ($s1) # wczytujemy adres naszego wybranego wiersza
    	add $s2, $t2, $s2  #dodajemy do tego adresu offset wynikajacy z numeru kolumny
    	
    	    

    	
    	
    	#wprowadzamy nowa wartosc i zapisujemy ja w polu na jaki wskazuje rejestr t3, czyli nasz obliczony offset wzgledem poczatku ramu
    	la $a0, insert_msg_val
    	li $v0, 4
    	syscall
    	li $v0, 5
    	syscall
    	
    	sw $v0, ($s2) #tutaj zapisujemy nasza wartosc
    
    	j menu
    
    
    #--- odczyt liczby, dziala analogicznie jak zapis, z jedyna roznica wynikajaca z tego ze nie zapisujemy, tylko odczytujemy, offset liczymy tak samo
    	read:
    
    	
    	
    	#t1 0 wiersz
    	#t2 kolumna

	

    	la $s1, RAM
    	
    	sub $t1, $t1, 1
    	sub $t2, $t2, 1
    	
    	sll $t1, $t1, 2
    	add $s1, $s1, $t1
    	
    	sll $t2, $t2, 2
    	
    	lw $s2, ($s1)
    	add $s2, $t2, $s2
    	
    	    	
    	li $v0, 1
    	lw $a0, ($s2)
    	syscall
    	
    	
 	la $a0, newline
 	li $v0, 4
 	syscall
    	
    	j menu
    	
    	
    
    
    
    #--- koniec ---
    
    	end:
    	li $v0, 10
    	syscall
