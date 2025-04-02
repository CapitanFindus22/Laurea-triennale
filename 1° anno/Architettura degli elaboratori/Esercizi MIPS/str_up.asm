#Trasformare una stringa in input in Uppercase

.text

.globl main

main:

la $a0,str
li $v0,4
syscall

la $a0,in		#Prendo una stringa in input di 20 caratteri
li $a1,20
li $v0,8
syscall

li $t0,21		#Aggiungo il terminatore
sb $0,in($t0)

li $t1,0

loop:

beq $t1,$t0,end_loop

lb $t2,in($t1)		#Prendo il carattere

blt $t2,97,maiusc
bgt $t2,122,maiusc

minusc:			#Minuscolo

sub $t2,$t2,32
sb $t2,in($t1)

maiusc:			#Maiuscolo

addi $t1,$t1,1

j loop

end_loop:

li $v0,4
syscall

li $v0,10
syscall

.data

str: .asciiz "Inserisci la stringa: "
in: .space 21