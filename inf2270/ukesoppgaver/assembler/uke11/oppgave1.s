#	int f(int a, int b, int x) = a*x + b/x + b%x

	.globl f

f:
	pushl 	%ebp
	movl 	%esp, %ebp

	movl	16(%ebp), %ebx 	# Move x to EBX
	movl	8(%ebp), %eax  	# Move a to EAX
	imul	%ebx 			# Multiply x with a
	movl	%eax, %ecx 		# Store result in ECX
	movl	12(%ebp), %eax 	# Move b to EAX
	idiv	%ebx 			# Divide b by X
	addl	%edx, %eax 		# Add remainder
	addl	%ecx, %eax 		# Add a * x

	pop 	%ebp
	ret
