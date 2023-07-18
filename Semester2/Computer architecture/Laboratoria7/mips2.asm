.data
numall: .space 480   # Tablica o rozmiarze 60 * 4 bajty (60 liczb całkowitych)
primes: .space 480
msg: .asciiz "Liczby pierwsze w tablicy z wykreślonymi liczbami: \n"
msg2: .asciiz "Liczby pierwsze:  \n"
newline: .asciiz "\n"
liczbaLiczb: .asciiz "Liczb pierwszych jest: "

nprimes: .word 0

.text
.globl main
main:
#--------------------------------------------------------------------------------

    li $t0, 1      # Początkowa wartość
    la $s0, numall  # Adres początku tablicy

    # Pętla wczytująca kolejne wartości do tablicy
    li $t1, 0        # Licznik pętli
    loop:
        add $t2, $s0, $t1    # Adres aktualnego elementu tablicy

        sw $t0, ($t2)       # Zapisanie wartości do tablicy
	
        addi $t0, $t0, 1     # Zwiększenie wartości o 1
        
        addi $t1, $t1, 4     # Przesunięcie licznika o 4 bajty (indeksowanie w bajtach)

        bne $t1, 480, loop   # Warunek zakończenia pętli (licznik = 120)
       
        #---
        
        #wykreslanie
        la $s0, numall        # Adres początku tablicy
	li $t1, 0    # Licznik pętli
	
	
        
        loop2:
       add $t2, $s0, $t1  # Adres aktualnego elementu tablicy
       lw $t3, ($t2)      # Wczytanie wartości z tablicy do argumentu t3
       addi $t1, $t1, 4   # Przesunięcie licznika o 4 bajty (indeksowanie w bajtach)
      
      la $s1, numall #drugi adres, bo bedziemy pracowac na tej samej tablicy, ale w dwoch roznych miejscach
      li $t4, 0 #drugi licznik, podobny powod jak wyzej
      
      loop3:
      add $t5, $s1, $t4 #inkrementujemy adres petli wewnetrznej
      lw $t6, ($t5) #zapisujemy wartosc aktualnego adresu petli wewnetrznnej
      beq $t6, 1, setZero
      beq $t3, 1, skip 
      beqz $t6, skip #tutaj sa warunki ktore nalezy pominac, aby algorytm zadzialal prawidlowo
      beqz $t3, skip
      bge $t3, $t6, skip
      div $t6, $t3 #dzielimy nasza potencjalna wielokrotnosc przez wybrana liczbe z petli zewnetrznej
      mfhi $s3 #zbieramy informacje o wystapieniu reszty z dzielenia
      beqz $s3, setZero #jezeli nie ma reszty, tzn ze znalezlismy wielokrotnosc, a wiec ustawiamy wartosc na 0
      skip:
      addi $t4, $t4, 4
      ble $t4, 480, loop3
          
    bne $t1, 120, loop2  # Warunek zakończenia pętli (licznik = 120)
    j end    
        
        setZero:
        sw $zero,($t5)
        j skip         
        #---  	
 end:
la $s0, numall        # Adres początku tablicy
li $t1, 0    # Licznik pętli

	la $s3, primes
	li $t8 , 0 #licznik dla primes
	li $t9, 0 #licznik wszystkich liczb pierwszych
	
 addPrimesLoop:
  	 add $t2, $s0, $t1 #inkrementujemy wartosc $s0 o wielokrotnosci 4, aby odczytywac kolejne slowa
   	 lw $t0, ($t2)    #odczytujemy wartosc z adresu t2 do $t0
 	 beqz $t0, skipAdding #jezeli nasze #t0 jest rowne 0, tzn ze to nie byla liczba pierwsza wiec jej nie zapisujemy, tylko przeskakujemy do nastepnej iteracji
 	 add $t6, $s3, $t8 #jezeli to jest liczba pierwsza to przygotowujemy nowe miejsce w pamieci na dodanie jej
 	 sw $t0, ($t6) #wpisujemy to slowo w nasza paiec
 	 add $t8, $t8, 4 #inkrementujemy o 32 bity nasz adres, aby miec moc tam wpisac nastepne slowo
 	 add $t9, $t9, 1 # inkerementujemy licznik liczb pierwszych

 skipAdding:
 
     addi $t1, $t1, 4   # Przesunięcie licznika o 4 bajty (indeksowanie w bajtach)
    bne $t1, 480, addPrimesLoop  # Warunek zakończenia pętli (licznik = 240)
    
    sw $t9, nprimes
	 

	la $a0, liczbaLiczb
	li $v0, 4
	syscall
	
	move $a0, $t9
	li $v0, 1
	syscall
	
	la $a0, newline
	li $v0, 4
	syscall
	
	la $a0, msg2
	li $v0, 4
	syscall
		
	la $s0, primes        # Adres początku tablicy
	li $t1, 0    # Licznik pętli
	outputLoop:
	add $t5, $s0, $t1
	lw $a0, ($t5)
	li $v0, 1
	syscall
	la $a0, newline
	li $v0, 4
	syscall
	addi $t1, $t1, 4
	sub $t8, $t8, 4
	bnez $t8, outputLoop
	
    # Zakończenie programu
  
    li $v0, 10       # Kod usługi 10 - zakończenie programu
    syscall          # Wywołanie systemowe
