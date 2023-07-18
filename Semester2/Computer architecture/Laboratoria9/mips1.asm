#=============================================
.eqv		STACK_SIZE 2048
#=============================================
.data
# obszar na zapamiętanie adresu stosu systemowego
sys_stack_addr:	.word	 0
# deklaracja własnego obszaru stosu
stack:		.space	 STACK_SIZE

array: .word 1, 2, 3, 4, 5, 6, 7, 8, 9, 10
# ============================================
.text
# czynności inicjalizacyjne
sw	$sp,	sys_stack_addr	# zachowanie adresu stosu systemowego
la 	$sp, 	stack+STACK_SIZE # zainicjowanie obszaru stosu
# początek programu programisty - zakładamy, że main
# wywoływany jest tylko raz
main:

subi	$sp,	$sp,	4 #zrobienie miejsca na zmienna w mainie S


# przygotowanie do wywolania

la	$t0,	array

sub	$sp,	$sp,	4
sw	$t0,	($sp)		#wpisujemy adres tablicy

li	$t0,	10
sub 	$sp,	$sp, 	4		
sw	$t0,	($sp)		#wpisujemy wielkosc tablicy


# -4 - adres tablicy
# -8 - rozmiar tablicy
# -12, -16, -20, -24 - miejsce na zmienne lokalne funkcji
jal	sum



#tworzymy nowe miejsce na stosie, aby dodac do niego wartosc nowej zmiennej
lw	$t0,	($sp)		#wczytujemy wartosc return funkcji
sw	$t0,	12($sp)		#zapisujemy return w nowym miejscu na stosie, jako zmienna s

add	$sp,	$sp,	12	#wyrownujemy wskaznik stosu, 

li	$v0,	1
lw	$a0,	($sp)		#wczytujemy zmienna s i ja wypisujemy
syscall

add	$sp,	$sp,	4


# koniec podprogramu main:


lw 	$sp, 	sys_stack_addr # odtworzenie wskaźnika stosu systemowego

li 	$v0,	 10	# zakonczenie programu
syscall

#=====================================================


#wywolanie metody


#0 - zmienna lokalna s
#+4 - zmienna lokalna i
#+8 - rejestr ra
#+12 - wynik
#+16 - rozmiar tablicy
#+20 - adres tablicy

sum:

#t0 - adres tablicy
#t1 - licznik iteracji (i)
#t2 - suma czesciowa (s)


subi	$sp,	$sp, 4		#robimy miejsce na wynik

subi	$sp,	$sp,	4	#robimy miejsce na $ra
sw	$ra,	($sp)		#zapisuje do offsetu 8sp adres powrotu


lw	$t1,	8($sp)		#zaladowanie wielkosci tablicy(czyli naszego licznika) do rejestru

lw	$t0,	12($sp)		#zaladowanie adresu tablicy do rejestru

subi	$sp,	$sp,	4	#robimy miejsce na dwie zmienne lokalne
subi	$sp,	$sp,	4

sll	$t1,	$t1,	2	#mnozymy licznik 4x, aby przeskakiwac o cale slowa

sw	$t1,	4($sp)		#zapiisujemy wartosc zmiennej lokalnej (licznika i)
subi	$t1,	$t1,	4	#i = array_size-1
loop:

#--array[i]---
add	$t3,	$t1,	$t0	#do t3 dodajemy adres naszej tablicy + offset wynikajacy z aktualnej iteracji
lw	$t4,	($t3)		#czytamy liczbe znajdujaca sie w naszej tablicy
#---s = s+array[i]
add	$t2,	$t2,	$t4	#dodajemy nasza wczytana wartosc do sumy czesciowej

#--zapisywanie elementow na stosie--
sw	$t2,	($sp)		#zapisujemy sume czesciowa na odpowiedniej pozycji stosu (ostatniej)
sw	$t1,	4($sp)		#zapisujemy tez nasz licznik

sub	$t1,	$t1,	4	#zmniejszamy licznik o 4, czyli przesuwamy sie o jedno slowo w naszej tablic/ i = i-1
bge	$t1,	$zero,	loop	# warunek konca petli / while(i>=0)

lw	$t0,	($sp)		#wczytujemy nasza sume czesciowa i przepisujemy ja do miejsca odpowiedzialnego za nasz wynik
sw	$t0,	12($sp)

addi	$sp,	$sp,	4	#usuwamy sume czastkowa S
addi	$sp,	$sp,	4	#usuwamy zmienna licznik i
lw	$ra,	($sp)		#wczytujemy do #ra adres naszego powrotu
addi	$sp,	$sp,	4	#usuwamy ze stosu ra, ustawiajac tym samym wskaznik na wyniku

jr	$ra				#wychodzimy 
