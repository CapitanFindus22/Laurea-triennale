#Regola di collatz

.text

.globl main

main:

la $a0,out
li $v0,4
syscall

li $v0,5
syscall
move $a0,$v0

jal collatz

la $a0,lun
li $v0,4
syscall

move $a0,$v1
li $v0,1
syscall

li $v0,10
syscall




collatz:

addi $a1,$a1,1	

bne $a0,1,skip		

move $v1,$a1		#Caso base
jr $ra

skip:

subu $sp,$sp,4		#Salvo l'indirizzo di ritorno

sw $ra,($sp)

move $t0,$a0		#Stampo uno spazio
la $a0,space
li $v0,4
syscall
move $a0,$t0

rem $t0,$a0,2
beqz $t0,case

mul $a0,$a0,3		#Pari
addi $a0,$a0,1
j end

case:

div $a0,$a0,2		#Dispari

end:

li $v0,1		
syscall

jal collatz		#Ricorsione

lw $ra,($sp)		#Prendo l'indirizzo di ritorno
addi $sp,$sp,4

jr $ra			

.data

out: .asciiz "Inserire un numero intero positivo: "
space: .asciiz " "
lun: .asciiz "\n Lunghezza: "