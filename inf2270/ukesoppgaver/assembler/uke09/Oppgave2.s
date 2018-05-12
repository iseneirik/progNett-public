# Oppgave1
orb	$3, %al
# Oppgave2
orw $3, %ax
# Oppgave3
orl $3, %eax
# Oppgave4
andb $0x3F, %al
# Oppgave5
andw $0x3FFF, %ax
# Oppgave6
andl $0x3FFFFFFF, %eax
# Oppgave7
cmpb $0, %al
jz null
jnz annet
# Oppgave8
andb $0xC0, %al
cmpb $0x80, %al
jz ja
jnz nei
