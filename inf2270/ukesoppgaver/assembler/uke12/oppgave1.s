# int n_ones(unsigned int v)
# Counts number of '1' bits in v

	.globl n_ones

n_ones:
	push	%ebp
	movl 	%esp, %ebp

	movl	$0, %edx
	movl	8(%ebp), %ebx

loop:
	cmpl	$0, %ebx
	jz		finish

	shrl	%ebx
	jnc		loop
	incl	%edx
	jmp 	loop

finish:
	movl	%edx, %eax
	pop		%ebp
	ret
