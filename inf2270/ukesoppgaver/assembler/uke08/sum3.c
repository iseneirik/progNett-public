#include <stdio.h>

extern int sum3(int a, int b, int c);

int main(void)
{
	printf("1 + 5 + 10 = %d\n", sum3(1, 5, 10));
	return 0;
}