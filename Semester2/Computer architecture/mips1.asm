.text
main:
	la $a0, World
	li $a1, 10
	
	
	li $v0, 8
	syscall
	
	li $v0, 4
	syscall
	
	li $v0, 10
	syscall
	
	
	


.data
World: .space 10