	.globl f1

# f1(int x) = (x + 1)*4

f1:		pushl	%ebp
		movl	%esp, %ebp

		movl 	$1, %eax
		addl	8(%ebp), %eax
		movl	$4, %ebx
		mull	%ebx

		pop %ebp
		ret

# -----------------------------------

	.globl f2

# f2(int x) = x^4

f2:		push 	%ebp
		movl 	%esp, %ebp

		movl 	8(%ebp), %eax
		movl	8(%ebp), %ebx
		mull	%ebx
		movl	%eax, %ebx
		mull	%ebx

		pop 	%ebp
		ret

# -----------------------------------

	.globl f3

# f3(int a, int b, int c) = if a < 0 ? b : c

f3:	
	push 	%ebp
	movl	%esp, %ebp

	movl	8(%ebp), %eax
	addl	$0, %eax
	jns		ret2
	movl	12(%ebp), %eax
	jmp 	finish


ret2:
	movl	16(%ebp), %eax
	

finish:
	pop		%ebp
	ret

# ---------------------------------------

	.globl f4

# f4(int a, int b) = (a^2 + b^2)/2

f4:
	push	%ebp
	movl 	%esp, %ebp

	movl 	8(%ebp), %eax
	mull	%eax
	movl	%eax, %ebx
	movl	12(%ebp), %eax
	mull	%eax
	addl	%ebx, %eax
	movl 	$2, %ebx
	divl	%ebx

	pop 	%ebp
	ret
