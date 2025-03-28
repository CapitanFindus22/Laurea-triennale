#Sezione testuale
.text

#Il label main diventa globale
.globl main

#Funzione
main:

lb $t1,n1
lb $t2,n2
add $t0,$t1,$t2

li $v0,10
syscall

#Fine funzione
.end

#Sezione dati
.data

n1: .byte 3
n2: .byte 5
