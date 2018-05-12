#include <stdio.h>

extern int f(int a, int b, int c);

int main(void)
{
	printf("%d\n", f(2, 3, 1));
	return 0;
}