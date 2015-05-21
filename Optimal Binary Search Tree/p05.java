import java.util.Scanner;

/**
 * @author Ramesh Sangaraju 
 * Date : 12-03-2015 
 * Program - 5
 */
public class p05 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		// reading total number of terms
		int totalTerms = scanner.nextInt();
		int[] frequency = new int[totalTerms];
		// reading number of times each term is called
		for (int i = 0; i < totalTerms; i++) {
			frequency[i] = scanner.nextInt();
		}
		// calling the optimalBinarySearchTree function
		int result = optimalBinarySearchTree(frequency);
		System.out.println(result);
	}

	/**
	 * This method return the optimal number of traversals
	 * 
	 * @param frequency
	 * @return
	 */
	private static int optimalBinarySearchTree(int[] frequency) {
		int totalTerms = frequency.length;
		// declaring two arrays
		int[][] sum = new int[totalTerms][totalTerms];
		int[][] traversals = new int[totalTerms][totalTerms];
		// updating the diagonal of traversals matrix to frequency values
		for (int i = 0; i < totalTerms; i++) {
			sum[i][i] = frequency[i];
		}
		// for each length, calculating the minimum value
		for (int length = 1; length <= totalTerms; length++) {
			for (int row = 0; row < totalTerms - length; row++) {
				int column = row + length;
				traversals[row][column] = Integer.MAX_VALUE;
				sum[row][column] = sum[row][column - 1] + frequency[column];
				for (int root = row; root < column; root++) {
					int min = traversals[row][root]
							+ traversals[root + 1][column] + sum[row][column];
					// assigning min value to e[row][column] if less
					if (min < traversals[row][column]) {
						traversals[row][column] = min;
					}
				}
			}
		}
		// returning last element in first row where the result is available
		return traversals[0][totalTerms - 1];
	}
}
