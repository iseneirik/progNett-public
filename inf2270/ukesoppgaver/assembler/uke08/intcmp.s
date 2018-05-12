.globl intcmp

intcmp:
	push %ebp
	movl %esp, %ebp

	movl 8(%ebp), %eax
	subl 12(%ebp), %eax

	pop %ebp
	ret
	