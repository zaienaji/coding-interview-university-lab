package id.zaien.lab;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.LinkedList;
import java.util.List;

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
		// example, choose a team member consist of 2 people from 4 total peoples.
		
		int combination = c(4, 2);

		assertThat(combination).isEqualTo(6);
	}
	
	private int c(int n, int k) {
		
		if (k == 0 || k == n)
			return 1;
		else
			return c(n-1, k) + c(n-1, k-1);
	}
	
	@Test
	void printCombinationTest() {
		// N-choose-K, written as C(n,k) --> combination of k from n.
		// example, choose a team member consist of 2 people from 4 total peoples.
		
		String expected = "1 2 \r\n"
				+ "1 3 \r\n"
				+ "1 4 \r\n"
				+ "2 3 \r\n"
				+ "2 4 \r\n"
				+ "3 4 \r\n"
				+ "---";
		
		List<List<Integer>> result = combine(4, 2);
		
		StringBuilder actual = new StringBuilder();
		for (List<Integer> comb : result) {
			for (int i : comb) {
				actual.append(i + " ");
			}
			
			actual.append(System.lineSeparator());
		}
		
		actual.append("---");
		
		assertThat(actual.toString()).isEqualTo(expected);
	}

	private List<List<Integer>> combine(int n, int k) {
		List<List<Integer>> result = new LinkedList<>();
		
		if (k==0) {
			result.add(new LinkedList<>());
			return result;
		}
		
		backtrack(1, new LinkedList<Integer>(), n, k, result);
		
		return result;
	}

	private void backtrack(int start, LinkedList<Integer> current, int n, int k, List<List<Integer>> result) {
		
		if (current.size()==k)
			result.add(new LinkedList<>(current));
		
		for (int i=start; i<=n && current.size()<k; i++) {
			current.add(i);
			backtrack(i+1, current, n, k, result);
			current.removeLast();
		}
	}
	
	@Test
	void permutationTest() {
		String expected = "ABCD\r\n"
				+ "ABDC\r\n"
				+ "ACBD\r\n"
				+ "ACDB\r\n"
				+ "ADBC\r\n"
				+ "ADCB\r\n"
				+ "BACD\r\n"
				+ "BADC\r\n"
				+ "BCAD\r\n"
				+ "BCDA\r\n"
				+ "BDAC\r\n"
				+ "BDCA\r\n"
				+ "CABD\r\n"
				+ "CADB\r\n"
				+ "CBAD\r\n"
				+ "CBDA\r\n"
				+ "CDAB\r\n"
				+ "CDBA\r\n"
				+ "DABC\r\n"
				+ "DACB\r\n"
				+ "DBAC\r\n"
				+ "DBCA\r\n"
				+ "DCAB\r\n"
				+ "DCBA\r\n"
				+ "";
		
		StringBuilder permutation = new StringBuilder();
		permute("", "ABCD", permutation);
		
		assertThat(permutation.toString()).isEqualTo(expected);
	}

	private void permute(String sofar, String rest, StringBuilder permutation) {
		
		if (rest.isEmpty())
			permutation.append(sofar).append(System.lineSeparator());
		else {
			for (int i=0; i<rest.length(); i++) {
				String next = sofar + rest.charAt(i);
				String remaining = rest.substring(0, i)+rest.substring(i+1);
				
				permute(next, remaining, permutation);
			}
		}
	}
	
	@Test
	void subsetsTest() {
		
		recSubsets("", "ABCD");
	}

	private void recSubsets(String sofar, String rest) {
		
		if (rest.isEmpty())
			System.out.println(sofar);
		else {
			recSubsets(sofar+rest.charAt(0), rest.substring(1));
			recSubsets(sofar, rest.substring(1));
		}
	}
	
	@Test
	void anagramTest() {
		Lexicon lexicon = new Lexicon("lexicon.txt");
		
		boolean isAnagram = isAnagram("", "toon", lexicon);
		
		assertThat(isAnagram).isTrue();
	}

	private boolean isAnagram(String sofar, String rest, Lexicon lexicon) {
		
		if (rest.isEmpty()) {
			if (lexicon.isContainWord(sofar)) {
				System.out.println(sofar);
				return true;
			}
		} else {
			for (int i=0; i<rest.length(); i++) {
				String next = sofar + rest.charAt(i);
				String remaining = rest.substring(0, i)+rest.substring(i+1);
				
				if (isAnagram(next, remaining, lexicon)) return true;
			}
		}
		
		return false;
	}
	
	@Test
	void nqueenTest() {
		int size=4;
		Board board = new Board(size, size);
		
		solve(board, 1);
		
		int[] result = board.result();
		assertThat(result[0]).isEqualTo(2);
		assertThat(result[1]).isEqualTo(4);
		assertThat(result[2]).isEqualTo(1);
		assertThat(result[3]).isEqualTo(3);
	}

	private boolean solve(Board board, int column) {
		
		if (column > board.columnSize()) return true;
		
		for (int rowToTry=1; rowToTry<=board.rowSize(); rowToTry++) {
			if (board.isSavePosition(column, rowToTry)) {
				board.addQueen(column, rowToTry);
				
				if (solve(board, column+1)) return true;
				
				board.removeQueen(column);
			}
		}
		
		return false;
	}

}
