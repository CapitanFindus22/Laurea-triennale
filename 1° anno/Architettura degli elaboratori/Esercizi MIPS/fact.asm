#Fattoriale

.text

.globl main

main:

li $a0,8

jal fact

move $a0,$v0
li $v0,1
syscall

li $v0,10
syscall

fact:

bgt $a0,1,rec

li $v0,1		#Caso base
jr $ra

rec:

subu $sp,$sp,8		#Faccio spazio nella stack

sw $ra,($sp)		#Salvo il valore e l'indirizzo di ritorno nella stack
sw $a0,4($sp)


subi $a0,$a0,1

jal fact		#Ricorsione

lw $ra,($sp)		#Riprendo i valori
lw $t0,4($sp)

addi $sp,$sp,8		#Sistemo lo stack pointer

mul $v0,$v0,$t0		#Moltiplico

jr $ra
