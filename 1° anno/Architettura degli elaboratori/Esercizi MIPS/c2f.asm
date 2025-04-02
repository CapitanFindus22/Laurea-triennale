#Convertitore da Celsius a Fahreneit, the formula is (1.8*C°)+32

.text

.globl main

main:
l.s $f0,nf		#1.8

lwc1 $f2,C		#C°
cvt.s.w $f2,$f2

mul.s $f0,$f0,$f2	#Moltiplico

lwc1 $f2,tt		#32
cvt.s.w $f2,$f2

add.s $f12,$f0,$f2	#Sommo	

li $v0,2		#Stampo il valore
syscall

li $v0,10
syscall

.data

C: .word 25
nf: .float 1.8
tt: .word 32
