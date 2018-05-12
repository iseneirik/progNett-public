#include <stdio.h>

extern int intcmp(int a, int b);

int main(void)
{
	printf("%d\n", intcmp(1, 2)); // -1
	printf("%d\n", intcmp(1, 1)); //  0
	printf("%d\n", intcmp(2, 1)); //  1
	return 0;
}