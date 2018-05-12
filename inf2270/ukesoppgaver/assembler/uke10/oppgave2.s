# odd(int x): returns 1 if x is odd, 0 otherwise

	.globl odd

odd:
	push	%ebp
	movl 	%esp, %ebp

	movl 	8(%ebp), %eax
	andl	$1, %eax

	pop		%ebp
	ret
	