.data
numall: .space 240   # Tablica o rozmiarze 60 * 4 bajty (60 liczb całkowitych)
msg: .asciiz "Liczby pierwsze w tablicy z wykreślonymi liczbami: \n"
msg2: .asciiz "Liczby pierwsze:  \n"
newline: .asciiz "\n"
liczbaLiczb: .asciiz "Liczb pierwszych jest: "

primes: .space 240
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

        bne $t1, 240, loop   # Warunek zakończenia pętli (licznik = 120)

#----------------------------------------------------------------------------------------
	j cokolwiek
	eratostenes_check:
	li $t8, 2
	loop2:
		lw $t7, ($t2)
		beq $t7, 1, eratostenes_true
		beq $t7, $t8, eratostenes_true
		div $t7, $t8
		mfhi $s2
		beqz $s2, eratostenes_false
		addi $t8, $t8, 1
		j loop2
		
		 
#--------------------------------------------------------------------------------------

cokolwiek:
    # Wyświetlanie zawartości tablicy 	
    la $a0, msg     # Przekazanie adresu wiadomości do argumentu a0
    li $v0, 4       # Kod usługi 4 - wyświetlanie łańcucha znaków
    syscall         # Wywołanie systemowe

    # Pętla wyświetlająca zawartość tablicy
    li $t1, 0       # Licznik pętli
    li $t4, 0 #drugi licznik
    la $s3, primes
    li $t3, 0
    li $t9, 0
    display_loop:
        add $t2, $s0, $t1     # Adres aktualnego elementu tablicy
        
        j eratostenes_check
        
        eratostenes_true:
        lw $a0, ($t2)        # Wczytanie wartości z tablicy do argumentu a0
        li $v0, 1            # Kod usługi 1 - wyświetlanie liczby całkowitej
        syscall              # Wywołanie systemowe
        
        
        add $t3, $s3, $t4
        move $t5, $a0
        add $t9, $t9, 1
        sb $t5, ($t3)
        addi $t4, $t4, 4

        
        
        j next
        
        eratostenes_false:
        li $a0, 0
        li $v0, 1
        syscall
        
        next:
        la $a0, newline #
  	li $v0, 4       #
      	syscall         #


        addi $t1, $t1, 4     # Przesunięcie licznika o 4 bajty (indeksowanie w bajtach)
        bne $t1, 240, display_loop   # Warunek zakończenia pętli (licznik = 120)
        
        sw $t9, nprimes
        
        #--- wypisywanie liczb pierwszych
        
        la $a0, liczbaLiczb
        li $v0, 4
        syscall
        
        move $a0, $t9
        li $v0, 1
        syscall
        
        la $a0, newline
        li $v0, 4
        syscall
        
        la $s3, primes
        li $t1, 0       # Licznik pętli
        prime_loop:
        add $t3, $s3, $t1
        
        lb $a0, ($t3)
        li $v0, 1
        syscall
        
                la $a0, newline
        li $v0, 4
        syscall
        
        
        	add $t1, $t1, 4
        	sub $t9, $t9, 1
        	beqz $t9, end
        	j prime_loop
        	
        


    # Zakończenie programu
    end:
    li $v0, 10       # Kod usługi 10 - zakończenie programu
    syscall          # Wywołanie systemowe
