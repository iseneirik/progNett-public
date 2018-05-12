	.globl f
f:
	push %ebp
	movl %esp, %ebp

	movl 8(%ebp), %eax
	addl 12(%ebp), %eax
	addl 12(%ebp), %eax
	addl 12(%ebp), %eax
	addl 16(%ebp), %eax

	pop %ebp
	ret
