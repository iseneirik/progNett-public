import java.util.*;

class Oppgave2 {
	public static void main(String[] args) {
		ArrayCalc a = new ArrayCalc();
		System.out.println(a.maxVal());
		System.out.println(a.minVal());
		System.out.println(a.avgVal());
		a.printArr();
		a.sortArr();
		a.printArr();
	}
} // end Oppgave2

interface NumArray {
	public int maxVal();
	public int minVal();
	public void sortArr();
	public double avgVal();
} // end NumArray

class ArrayCalc implements NumArray {
	
	private int[] arr = {1, 6, 14, 50, 2, 9, 36, 101, 2, 65};

	public int maxVal() {
		int max = arr[0];
		for (int i : arr) {
			if (i > max) {
				max = i;
			}
		}
		return max;
	} // end maxVal

	public int minVal() {
		int min = arr[0];
		for (int i : arr) {
			if (i < min) {
				min = i;
			}
		}
		return min;
	} // end minVal

	public void sortArr() {
		Arrays.sort(arr);
	}

	public double avgVal() {
		int total = 0;
		for (int i : arr) {
			total += i;
		}
		return (double)total / arr.length;
	} // end avgVal

	public void printArr() {
		for (int i : arr) {
			System.out.print(i + " ");
		}
		System.out.println();
	}
} //end Array