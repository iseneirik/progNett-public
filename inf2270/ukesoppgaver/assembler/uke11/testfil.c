#include <stdio.h>

extern int f(int a, int b, int x);
extern int gcd(int a, int b);

int main(int argc, char const *argv[])
{
	printf("TEST: f(1, 2, 3): %d\n", f(1, 2, 3));
	printf("TEST: f(1, 5, 18): %d\n", f(1, 5, 18));
	printf("TEST: f(17, 4, 8): %d\n", f(17, 4, 8));

	printf("TEST: gcd(10, 5): %d\n", gcd(10, 5));
	printf("TEST: gcd(1, 7): %d\n", gcd(1, 7));
	printf("TEST: gcd(4, 10): %d\n", gcd(4, 10));


	return 0;
}