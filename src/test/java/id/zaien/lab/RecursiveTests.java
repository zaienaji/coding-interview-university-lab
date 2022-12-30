package id.zaien.lab;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class RecursiveTests {
	
	@Test
	void recursiveTest() {

		int base = 2;
		int exponent = 7;

		int raise = raise(base, exponent);

		assertThat(raise).isEqualTo(128);

	}

	private int raise(int base, int exponent) {

		if (exponent < 4)
			return raise(base, exponent, 1);

		int half = raise(base, exponent / 2);
		int full = half * half;
		
		if (exponent % 2 == 0)
			return full;

		return full * base;
	}

	private int raise(int base, int exponent, int accumulation) {

		if (exponent == 1)
			return accumulation * base;

		return raise(base, exponent - 1, accumulation * base);
	}

	@Test
	void palindromeTest() {
		// was it a car or a cat i saw
		boolean isPalindrome = isPalindrome("wasitacaroracatisaw");
		assertThat(isPalindrome).isTrue();

		boolean isAnotherPalindrome = isPalindrome("gohangasalamiimalasagnahog".toCharArray());
		assertThat(isAnotherPalindrome).isTrue();
	}

	private boolean isPalindrome(String data) {
		if (data.length() <= 1)
			return true;

		if (data.charAt(0) != data.charAt(data.length() - 1))
			return false;

		return isPalindrome((data.substring(1, data.length() - 1)));
	}

	private boolean isPalindrome(char[] data) {
		int i = 0;
		int j = data.length - 1;

		while (i < j) {
			if (i == j)
				return true;

			if (data[i] != data[j])
				return false;

			i++;
			j--;
		}

		return true;
	}

	@Test
	void binarySearchTest() {
		int[] data = { 1, 3, 5, 7, 9, 10, 15, 41, 50, 72, 90, 108 };
		int key = 9;

		int keyIndex = doBinarySearch(data, key, 0, data.length);

		assertThat(keyIndex).isNotEqualTo(-1);
	}

	private int doBinarySearch(int[] data, int key, int start, int stop) {

		int middle = (start + stop) / 2;

		if (key == data[middle])
			return middle;
		else if (key < data[middle])
			return doBinarySearch(data, key, start, middle - 1);
		else
			return doBinarySearch(data, key, middle + 1, stop);
	}

	@Test
	void choosingSubsetTest() {

		// N-choose-K, written as C(n,k) --> combination of k from n.
		// example, choose a team member consist of 4 people from 60 total peoples.
		
		int permutation = c(4, 2);

		assertThat(permutation).isEqualTo(6);
	}
	
	private int c(int n, int k) {
		
		if (k == 0 || k == n)
			return 1;
		else
			return c(n-1, k) + c(n-1, k-1);
	}

}
