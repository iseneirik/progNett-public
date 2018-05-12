#include <stdio.h>

extern int f1(int x);
extern int f2(int x);
extern int f3(int a, int b, int c);
extern int f4(int a, int b);
extern int odd(int x);

int main(int argc, char const *argv[])
{
	printf("TEST: f1(1): %d\n", f1(1));
	printf("TEST: f1(2): %d\n", f1(2));
	printf("TEST: f1(3): %d\n", f1(3));

	printf("TEST: f2(1): %d\n", f2(1));
	printf("TEST: f2(2): %d\n", f2(2));
	printf("TEST: f2(3): %d\n", f2(3));

	printf("TEST: f3(-1, 1, 2): %d\n", f3(-1, 1, 2));
	printf("TEST: f3(0, 1, 2): %d\n", f3(0, 1, 2));
	printf("TEST: f3(1, 1, 2): %d\n", f3(1, 1, 2));
	
	printf("TEST: f4(1, 1): %d\n", f4(1, 1));
	printf("TEST: f4(1, 2): %d\n", f4(1, 2));
	printf("TEST: f4(2, 2): %d\n", f4(2, 2));
	printf("TEST: f4(18, 37): %d\n", f4(18, 37));

	printf("TEST: odd(1): %d\n", odd(1));
	printf("TEST: odd(6): %d\n", odd(6));
	printf("TEST: odd(137): %d\n", odd(137));
	printf("TEST: odd(200): %d\n", odd(200));
	
	return 0;
}