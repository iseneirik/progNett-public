	.globl f2
f2:
	push %ebp
	movl %esp, ebp

	movl $0, %eax
	subl 8(%ebp), %eax
	subl 12(%ebp), %eax

	pop %ebp
	ret