#include <stdio.h>

int main()
{
	unsigned int size = 10; // 計算したい要素数

	unsigned int x[] = {
			0x11111111,
			0x22222222,
			0x33333333,
			0x44444444,
			0x55555555,
			0x66666666,
			0x77777777,
			0x88888888,
			0x99999999,
			0xaaaaaaaa};
	unsigned int *xp = x;

	unsigned int vl;

	while (size > 0)
	{
		// 実際に計算される要素数vlを格納
		// Store the number of elements vl that are actually calculated
		asm volatile("vsetvli %0, %1, e32, m2"
								 : "=r"(vl)
								 : "r"(size));

		// 計算したい要素数sizeをデクリメント
		// Decrement the number of elements size that you want to calculate
		size -= vl;

		// VLE32.V
		asm volatile("vle32.v v2,(%0)" ::"r"(xp));
		// The size of the type pointed to by the pointer corresponds to one unit. This time, it is [int type size × vl] units.
		xp += vl; // ポインタの指す型のサイズが1単位に相当。今回は[int型サイズ × vl]分。
	}

	asm volatile("unimp");
	return 0;
}