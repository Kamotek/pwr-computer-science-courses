.text
li $v0, 5     # Prompt for first number
syscall
sw $v0, licz1 # Store first number in memory

li $v0, 5     # Prompt for second number
syscall
sw $v0, licz2 # Store second number in memory

li $t0, 0     # Initialize multiplier to 0
lw $t1, licz2 # Load second number into register $t1
li $t2, 0     # Initialize result to 0

loop:
beq $t0, $zero, endloop # If multiplier is 0, exit loop
and $t3, $t0, 1         # Get rightmost bit of multiplier
beq $t3, $zero, skip    # If bit is 0, skip to next bit

li $t4, 0   # Initialize temporary register for multiplication
li $t5, 1   # Initialize power of 2 to multiply by
li $t6, 0   # Initialize counter for multiplication loop

innerloop:
beq $t6, $t3, endinnerloop # If we've multiplied by the current bit enough times, exit inner loop
add $t4, $t4, $t1         # Add second number to temporary register
add $t6, $t6, 1           # Increment multiplication loop counter
sll $t5, $t5, 1           # Double power of 2
j innerloop
endinnerloop:
sllv $t4, $t4, $t0  # Shift temporary register by multiplier position
add $t2, $t2, $t4   # Add temporary result to final result

skip:
sll $t0, $t0, 1    # Shift multiplier left by 1
j loop
endloop:

li $v0, 1       # Print result
move $a0, $t2
syscall

li $v0, 10      # Exit program
syscall

.data
licz1: .word 0  # Space to store first number
licz2: .word 0  # Space to store second number
