.data

coefs: .float  2.3 3.45 7.67 5.32
degree:		.word	3

msg_head:	.asciiz	"=====-menu-====="
msg_menu:	.asciiz	"Wybierz opcje: "
menu_end:	.asciiz "2. Koniec programu"
menu_start:	.asciiz	"1. Zacznij dzialanie programu"

msg_result:	.asciiz "Wynikiem wielomianu jest: "
msg_prompt:	.asciiz "Wprowadz wartosc x: "
newline:	.asciiz "\n"

.text

menu:
	#wypisywane sa poszczegolne komunikaty, a na koniec zapisujemy wartosc naszej opcji
li	$v0,	4

la	$a0,	msg_head
syscall
la	$a0,	newline
syscall
la	$a0,	menu_start
syscall
la	$a0,	newline
syscall
la	$a0,	menu_end
syscall
la	$a0,	newline
syscall
la	$a0,	msg_menu
syscall


#jezeli nasza opcja jest rowna 1, to idziemy dalej, a jezeli jest rowna 2, to idziemy do konca programu

li	$v0, 	5
syscall

beq	$v0,	1,	con
beq	$v0,	2,	end



#tutaj wczytujemy wartosc naszego x i zapisujemy go jako wartosc double
con:
la	$a0,	msg_prompt
li	$v0,	4
syscall

li	$v0,	7
syscall

mov.d	$f4,	$f0	#przepisujemy do rejestru f4, nasza zapisana wartosc, ktora znajduje sie domylsnie w f0
mov.d	$f0,	$f8	#zresetowanie wartosci double f0

#t0 - wspolczynniki
#t1 - stopien
#f2 - wartosc x
#f0 - wynik

#czynnosci inicjalizacyjne funkcji

la	$t0,	coefs	#wczytujemy nasze stale i stopien wielomianu
lw	$t1,	degree



jal	eval_poly	#skaczemy do naszego podprogramu


la	$a0,	msg_result	#wypisujemy komunikat o wyniku
li	$v0,	4
syscall

mov.d	$f12,	$f0	#przepisujemy wynik z rejestru f0 do rejestru bedacego argumentem syscalla
li	$v0,	3
syscall

la	$a0,	newline	
li	$v0,	4
syscall

j	menu

#===koniec programu===


end:
li	$v0,	10
syscall

#===podprogram do wyliczania wartosci====

#f0 = coef[i] + x*f0
#f0 - nasz wynik tymczasowy
#f2 - tutaj wczytujemy liczby stale
#f4 - x

eval_poly:




sll	$t1,	$t1,	2	#nasz stopien bedzie robil za licznik i przesuniecie w tablicy stalych jednoczesnie



loop:
add	$t4,	$t1,	$t0	#dodajemy do t0, czyli adresu coefs coraz mniejsza wartosc t1, aby iterowac od konca

l.s	$f2,	($t4)	#wczytujemy floaty z coefs i konwertujemy je na wartosci double
cvt.d.s	$f2,	$f2


mul.d	$f0,	$f0,	$f4	#wykonujemy dzialania obliczajace wartosci czastkowe
add.d	$f0,	$f0,	$f2


sub	$t1,	$t1,	4	
bne	$t4,	$t0,	loop	#jezeli nasz rejestr t4 wskaze wartosc adresu coefs to  przestajemy iterowac
#koniec petli


jr	$ra
#koniec podprogramu









