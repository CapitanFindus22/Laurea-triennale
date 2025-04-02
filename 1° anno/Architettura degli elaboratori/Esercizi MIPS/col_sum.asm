#Sommare gli elementi della colonna scelta in input

#L'indice x,y è dato da 	(num. colonne)*x + y

.text

.globl main

main:

li $v0,42		#Per generare numeri random [0,500)
li $a1,500

lb $t5,col		#Prendo le dimensioni della matrice
lb $t6,righe

loop:

beq $t7,$t6,end_loop	#La riempo di numeri random

li $t8,0

loop_in:

beq $t8,$t5,end_loop_in	#Loop interno colonne

syscall

mul $t9,$t7,$t5		#Trovo l'indice
add $t9,$t9,$t8
mul $t9,$t9,4

sw $a0,mtr($t9)

addi $t8,$t8,1

j loop_in

end_loop_in:

addi $t7,$t7,1

j loop

end_loop:

li $v0,4		#Stampo la stringa
la $a0,q
syscall

li $v0,5		#$a0 rappresenta la colonna scelta
syscall

subi $v0,$v0,1

loop2:			#Basta un solo loop, mi interessa una colonna specifica

beq $t1,$t6,end_loop2

mul $t2,$t1,$t5
add $t2,$v0,$t2
mul $t2,$t2,4

lh $t2,mtr($t2)

add $t3,$t3,$t2

addi $t1,$t1,1

j loop2

end_loop2:

move $a0,$t3
li $v0,1
syscall

li $v0,10
syscall

.data

mtr: .word 7:20		#Matrice di 20 elementi posti a 7, 5x4
righe: .byte 4
col: .byte 5
q: .ascii "Inserisci un numero intero tra 1 e 5: \0"