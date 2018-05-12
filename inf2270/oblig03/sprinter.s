# Navn:		sprinter
# Synopsis:	Skriver en tekststreng med definert format til en resultat-peker
#			som vil inneholde den ferdig formaterte strengen, den vil returnere 
#			antall bytes som er skrevet til resultat-pekeren
# C-signatur: 	int sprinter(uchar *res, uchar *format, ...);
# Registre:	%ECX - Antall bytes skrevet
#			%EDX - Format-pekeren
#			%EBX - Resultat-pekeren
# 			%EAX - Arbeidsregister
#			%ESI - Arbeidsregister
#			%EDI - Arbeidsregister

	.globl sprinter

sprinter:
	pushl	%ebp			# Standard
	movl	%esp, %ebp 		# Standard
	
	pushl 	%esi 			# Push value of %ESI to stack for restoration
	pushl 	%edi 			# Push value of %EDI to stack for restoration

	movl	$0, %ecx		# %ECX = byte-counter
	
	movl	8(%ebp), %ebx	# Move result-pointer to %EBX
	movl	12(%ebp), %edx	# Move format-pointer to %EDX
	addl	$16, %ebp		# (%EBP) is now first variable argument

main_loop:
	movb	(%edx), %al 	# Move first character of format-pointer to %AL
	
	cmpb	$37, %al 		# Compare with ASCII '%'
	je		format_switch 	# If '%' is found, jump to format_switch

	cmpb	$0, %al 		# Compare with ASCII '\0'
	je		finished		# If '\0', jump to finished

	movb	%al, (%ebx)		# Move character from format-pointer to result-pointer

	jmp 	inc_pointers 	# Increment pointers and byte-counter and continue main_loop

format_switch:
	incl	%edx			# Increment format-pointer to character after '%'
	movb	(%edx), %al 	# Move character after '%' to %AL
	
	cmpb	$37, %al 		# Compare with ASCII '%'
	je		format_percent  # If '%' is found, jump to format_percent

	cmpb	$99, %al 		# Compare with ASCII 'c'
	je		format_char 	# If 'c' is found, jump to format_char

	cmpb    $100, %al		# Compare with ASCII 'd'
	je		format_int 		# If 'd' is found, jump to format_int
	
	cmpb    $102, %al 		# Compare with ASCII 'f'
	je		format_float 	# If 'f' is found, jump to format_float
	
	cmpb    $115, %al 		# Compare with ASCII 's'
	je		format_string	# If 's' is found, jump to format_string
	
	cmpb    $120, %al 		# Compare with ASCII 'x'
	je		format_hex 		# If 'x' is found, jump to format_hex

	movb	$63, (%ebx) 	# No valid format, put '?' in result-pointer

	jmp 	inc_pointers 	# Increment pointers and byte-counter and continue main_loop

format_percent:
	movb	$37, (%ebx) 	# Put '%' in result-pointer

	jmp 	inc_pointers 	# Increment pointers and byte-counter and continue main_loop

format_char:
	movb	(%ebp), %al 	# Move argument-character to %AL
	movb	%al, (%ebx) 	# Write character to result-pointer

	addl	$4, %ebp		# (%EBP) now points to next argument

	jmp 	inc_pointers 	# Increment pointers and byte-counter and continue main_loop

format_string:
	movl	(%ebp), %eax 	# Move argument-string address to %EAX 
	movb	(%eax), %al 	# Move first character of string to %AL

	cmpb	$0, %al 		# Check for terminating '\0'-byte
	je		str_finished 	# If found, the whole string has been read into result-pointer

	movb 	%al, (%ebx) 	# Write character to result-pointer
	incl 	%ecx 			# Increment byte-counter
	incl 	%ebx 			# Increment result-pointer
	incl 	(%ebp) 			# Increment string pointer

	jmp 	format_string 	# Read next character of the string

str_finished:
	addl 	$4, %ebp 		# (%EBP) is now the next argument

	incl	%edx 			# Increment format-pointer

	jmp 	main_loop 		# Continue program loop

format_float:
	pushl 	%ecx 			# Push %ECX to stack before function-call 
	pushl 	%edx 			# Push %EDX to stack before function-call

	pushl 	4(%ebp) 		# Push bit 31-0 of float to stack
	pushl   (%ebp)			# Push bit 63-32 of float to stack
	pushl	%ebx 			# Push result-pointer to stack
 
	call	my_ftoa	 		# call my_ftoa(result-pointer, 64-bit float)
							# Writes the float as a string into the result-pointer

	addl	$12, %esp 		# Remove result-pointer and float used inn call from stack
	popl 	%edx 			# Restore %EDX
	popl 	%ecx 			# Restore %ECX

inc_float_str:				# Finds '\0'-byte of my_ftoa-string
	movb 	(%ebx), %al 	# Move character to %AL
	
	cmpb 	$0, %al 		# Check if character is '\0'
	je 		float_finished  # Found end of string

	incl 	%ecx 			# Increment byte-counter
	incl 	%ebx 			# Increment result-pointer 

	jmp 	inc_float_str   # Continue searching for '\0'	
	
float_finished:
	addl	$8, %ebp 		# (%EBP) is now the next argument 	

	incl 	%edx 			# Increment format-pointer

	jmp 	main_loop 		# Continue program loop 

format_int:
	pushl 	%edx 			# Push format-pointer to stack for restoration	
	movl 	$0, %esi 		# %ESI is now digit-counter

	movl	(%ebp), %eax 	# Move int-argument to %EAX
	cmp 	$0, %eax 		# Compare to check if int is negative
	jns 	not_negative 	# If not negative, don't add '-'

	movb 	$45, (%ebx) 	# Add '-' to result if number is negative
	negl 	%eax 			# Negate integer value

	incl 	%ecx 			# Increment byte-counter
	incl 	%ebx 			# Increment result-pointer

not_negative:
	movl 	$10, %edi 		# Put 10 in %EDI for division (Decimal)
	movl 	$0, %edx 		# Set %EDX to $0 for division
	divl 	%edi	 		# Divide %EAX by 10, remainder stored in %EDX

	addl	$48, %edx 		# Add 48 ('0') to remainder (To get ASCII representation)
	push	%edx 			# Push digit to stack (We get them in wrong order)
	incl 	%esi 			# Increment digit-counter 

	cmp 	$0, %eax 		# Check if we have reached 0
	jne 	not_negative    # If we have not reached 0, continue

write_digits:
	popl	%edx  			# Pop top digit (Most significant) to %EDX
	movb	%dl, (%ebx) 	# Put character to result-pointer

	incl	%ecx 			# Increment byte-counter
	incl	%ebx			# Increment result-pointer
	decl 	%esi 			# Reduce digit-counter (ZeroFlag will be set when %ESI is 0)

	jnz		write_digits	# Digit-counter not zero? More digits, Continue!

	popl	%edx 			# Restore %EDX
	incl 	%edx 			# Increment format-pointer

	addl 	$4, %ebp 		# (%EBP) is now the next argument

	jmp 	main_loop		# Continue program loop

format_hex:
	pushl 	%edx 			# Push format-pointer to stack for restoration
	movl 	$0, %esi 		# %ESI is now digit-counter

	movl	(%ebp), %eax 	# Move int-argument to %EAX

find_hex_bytes:
	movl	$16, %edi 		# Put 16 in %EDI for division (Hex)
	movl	$0, %edx 		# Set %EDX to $0 for division (divl uses %EAX:%EDX)
	divl 	%edi 			# Divde %EAX by 16

	cmp 	$9, %edx 		# Check if remainder is > 9
	ja		ten_or_above 	# If number is greater > 9, jump

	addl 	$48, %edx 		# Add 48 ('0') to remainder
	push 	%edx 			# Push digit to stack
	incl 	%esi 			# Increment digit-counter

	jmp 	check_hex_zero  # Check if we have reached 0

ten_or_above:
	addl 	$87, %edx 		# Add 87 ('a'-10) to remainder
	push 	%edx 			# Push digit to stack
	incl 	%esi 			# Increment digit-counter

check_hex_zero:
	cmp 	$0, %eax 		# Check if we have reached 0
	jne 	find_hex_bytes 	# If we have not reached 0, continue

	jmp 	write_digits 	# Write digits to result-pointer
	
inc_pointers:
	incl	%ecx 			# Increment byte-counter
	incl	%edx			# Increment format-pointer
	incl	%ebx			# Increment result-pointer

	jmp 	main_loop		# Continue program-loop
	
finished:
	movb	$0, (%ebx) 		# NULL-Terminates the result-pointer
	movl 	%ecx, %eax 		# Moves number of bytes written to %eax for return

	popl 	%edi 			# Restore %EDI
	popl 	%esi 			# Restore %ESI
	popl 	%ebp			# Restore %EBP
	ret 					# Returns number of bytes written to result-pointer
 