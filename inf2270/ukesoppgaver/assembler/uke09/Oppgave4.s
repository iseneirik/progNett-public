  .globl	f
f:  pushl	%ebp
    movl	%esp,%ebp
    movl	$0,%eax
    movl	8(%esp),%edx
    movb	%dl,%al
    popl	%ebp
    ret

# denne funksjonen tar de 8 minst signifikante bitene til et tall