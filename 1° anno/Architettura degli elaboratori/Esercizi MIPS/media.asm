#Calcolare la media di un array di 5 elementi

.text

.globl main

main:

lw $t0,num
subi $t0,$t0,1		

loop:

beq $t0,-1,end_loop	#Itero in ordine inverso sull'array

mul $t1,$t0,4		#Trovo l'indirizzo
lw $t2,nums($t1)	#Prendo l'elemento

add $a0,$a0,$t2		#Sommo tutti gli elementi

subi $t0,$t0,1

j loop

end_loop:

lwc1 $f1,num

mtc1 $a0,$f0		#Sposto gli elementi nel coprocessore 

cvt.s.w $f1,$f1		#Converto i valori in single
cvt.s.w $f0,$f0

div.s $f12,$f0,$f1	#Divido

li $v0,2		#Stampo la media
syscall

li $v0,10		
syscall


.data

nums: .word 5,3,123,-32,0
num: .word 5
