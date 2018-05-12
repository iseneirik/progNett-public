#int gcd (int a, int b)
#{
#  while (a != b) {
#    if (a < b) b -= a;
#    else       a -= b;
#  }
#  return a;
#}

	.globl gcd

gcd:
	pushl	%ebp
	movl 	%esp, %ebp

	movl	8(%ebp), %eax
	movl	12(%ebp), %ebx

loop:
	cmp		%eax, %ebx
	ja		cmp2
	jb		cmp1
	jmp		finish

cmp1:
	subl	%ebx, %eax
	jmp 	loop

cmp2:
	subl	%eax, %ebx
	jmp 	loop

finish:
	pop	 	%ebp
	ret
