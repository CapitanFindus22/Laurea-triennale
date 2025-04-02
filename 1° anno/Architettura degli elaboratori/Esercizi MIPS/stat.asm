#Generare un array random e trovare il minimo

.text

.globl main

main:

lb $t0,size
subi $t0,$t0,1

li $v0,42		#Per generare numeri random [0,1000)
li $a1,1000

la $t1,arr

loop:

beq $t0,-1,end_loop

syscall

sh $a0,($t1)		#Inserisco il valore
addiu $t1,$t1,2
subi $t0,$t0,1

j loop

end_loop:

jal min

move $a0,$v0
li $v0,1
syscall

li $v0,10
syscall

min:

lb $t0,size 

lh $t3,arr($0)		#Prendo come riferimento il primo elemento

subi $t0,$t0,1

loop_min:

beqz $t0,end_loop_min

mul $t1,$t0,2
lh $t2,arr($t1)

bgt $t2,$t3,skip_min

move $t3,$t2		#Scambio

skip_min:

subi $t0,$t0,1 

j loop_min

end_loop_min:

move $v0,$t3

jr $ra

.data 

arr: .space 20
size: .byte 10