import java.util.Scanner;

/**
 * Name: Ramesh Sangaraju
 * Date: 05-09-2015
 * Program: Boolean satisfiability problem
 * 
 * I have tried in two ways. I am submitting the one that give correct answer every time.
 * But, it takes too much time for larger inputs, as its worst case complexity is O(2 pow n).
 * 
 * In other method, I am counting the number of occurrences of positive and negative terms,
 * and based on the count generating a possible combination and checking it with all the clauses,
 * if that combination satisfies all the clauses, then printing that as output. It is satisfying
 * for smaller inputs but not larger inputs
 * 
 * I have commented the code for second method for reference. Please consdier the other approach
 * as well.
 */

public class p09 {
	static int[][] clauses;
	static int numberOfTerms = 0;
	static int numberOfClauses = 0;
	
	/**
	 * @param args
	 * In this program I am getting a combination by converting a number to binary
	 * format and using it to check with all the clauses.
	 * 
	 * Worst case performance is O(2 pow n).
	 */
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		//reading total number of terms and total number of clauses
		numberOfTerms = scanner.nextInt();
		numberOfClauses = scanner.nextInt();
		
		//declaring matrix to store clauses
		clauses = new int[numberOfClauses][numberOfTerms];
		
		//populating the clauses reading from input
		for(int i=0;i<numberOfClauses;i++){
			for(int j=0;j<3;j++){
				clauses[i][j] = scanner.nextInt();
			}
		}
		long totalCombinations = (long)Math.pow(2, numberOfTerms);
		
		//this array holds current combination
		int[] currentCombination = new int[numberOfTerms];
		
		//generating the binary format for the total combinations possible
		for(long i=0;i<totalCombinations; i++){
			for(int j=numberOfTerms-1;j>=0;j--){
				long k = i >> j;
				currentCombination[j] = ((k & 1) == 1) ? 1 : 0;
			}
			//checking one combination at a time
			boolean result = computeResult(currentCombination);
			//if any result satisfies printing the result and exiting
			if(result == true){
				for(int k=0;k<numberOfTerms;k++){
					System.out.print(currentCombination[k]+" ");
				}
				System.exit(1);
			}
		}
		System.out.println("not satisfiable");
	}
	
	/**
	 * calling this method for each combination to check if it satisfy all the clauses
	 * this method return true if current combination satisfy all the clauses
	 * otherwise it will return false
	 * 
	 * @param currentCombination
	 * @return boolean
	 */
	private static boolean computeResult(int[] currentCombination) {
		int finalResult = 1;
		for(int i=0;i<numberOfClauses;i++){
			int clauseResult = 0;
			//generating or result for a clause
			for(int j=0;j<3;j++){
				//'index' variable below acts as index to the current combination array
				int index = clauses[i][j];
				if(index < 0){
					//index is multiplied by -1 to get positive value
					if(currentCombination[(index*(-1))-1] == 0){
						clauseResult |= 1;
					}
				}else{
					clauseResult |= currentCombination[index-1];
				}
				if(clauseResult == 1)
					break;
			}
			
			//final result is based on the & operation on all the clauses
			finalResult &= clauseResult;
			
			//if final result is false for any one of the class, then breaking the loop
			if(finalResult == 0){
				return false;
			}
		}
		boolean result = finalResult == 1?true:false;
		return result;
	}
	
}


//other approach that I tried, giving very less time for larger inputs but answer
//is not correct all the time.

/*
 import java.util.Scanner; 
  
 public class p09 {

	static int numberOfTerms = 0;
	static int numberOfClauses = 0;
	static int[][] clauses;
	
	*//**
	 * In this program, I am counting the number of occurrences of positive and
	 * negative outcomes, and based on the count getting a possible combination
	 * and checking it with all the clauses, if that combination satisfies all 
	 * the clauses, then printing that as output.
	 * Also checking one more possible solution by complementing previous combination.
	 * 
	 * This program is working for smaller inputs, but giving wrong output for larger
	 * number of clauses.
	 * @param args
	 *//*
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		//reading total number of terms and total number of clauses
		numberOfTerms = scanner.nextInt();
		numberOfClauses = scanner.nextInt();
		
		//declaring matrix to store clauses
		clauses = new int[numberOfClauses][numberOfTerms];
		
		//two arrays that store count of positive and negative terms
		int[] positiveTermCount = new int[numberOfTerms];
		int[] negativeTermCount = new int[numberOfTerms];
		
		//initializing two arrays with zero
		for(int i=0;i<numberOfTerms;i++){
			positiveTermCount[i] = 0;
			negativeTermCount[i] = 0;
		}
		
		//populating the clauses reading from input
		//also incrementing the count in array based on value
		for(int i=0;i<numberOfClauses;i++){
			for(int j=0;j<3;j++){
				int currentTerm = scanner.nextInt();
				clauses[i][j] = currentTerm;
				if(currentTerm < 0){
					negativeTermCount[((-1)*currentTerm)-1]++;
				}else
					positiveTermCount[currentTerm-1]++;
			}
		}
		
		int[] possibbleSolution = new int[numberOfTerms];
		//if positive term is occurred more number of terms in clauses
		//then possible solution for the term is considered as 1, otherwise 0
		for(int i=0;i<numberOfTerms;i++){
			if(negativeTermCount[i] > positiveTermCount[i]){
				possibbleSolution[i] = 0;
			}else
				possibbleSolution[i] = 1;
		}
		
		//checking the possible outcome with all the clauses
		boolean result = computeResult(possibbleSolution);
		
		//if the satisfies, printing the output and exiting
		if(result == true){
			for(int k=0;k<numberOfTerms;k++){
				System.out.print(possibbleSolution[k]+" ");
			}
			System.exit(1);
		}
		
		//getting the complement of above combination and checking if that
		//satisfies all the clauses
		int[] possibleComplement = new int[numberOfTerms];
		
		for(int i=0;i<numberOfTerms;i++){
			if(possibbleSolution[i] == 0)
				possibleComplement[i] = 1;
			else
				possibleComplement[i] = 0;
		}
		
		result = computeResult(possibleComplement);
		
		//if that satisfies, printing that combination
		if(result == true){
			for(int k=0;k<numberOfTerms;k++){
				System.out.print(possibleComplement[k]+" ");
			}
			System.exit(1);
		}
		//printing that solution is not possible.
		System.out.println("not satisfiable");
	}
	
	*//**
	 * calling this method for each combination to check if it satisfy all the clauses
	 * this method return true if current combination satisfy all the clauses
	 * otherwise it will return false
	 * @param currentCombination
	 * @return
	 *//*
	private static boolean computeResult(int[] currentCombination) {
		int finalResult = 1;
		for(int i=0;i<numberOfClauses;i++){
			int clauseResult = 0;
			
			for(int j=0;j<3;j++){
				//'index' variable below acts as index to the current combination array
				int index = clauses[i][j];
				if(index < 0){
					//index is multiplied by -1 to get positive value
					if(currentCombination[(index*(-1))-1] == 0){
						clauseResult |= 1;
					}
				}else{
					clauseResult |= currentCombination[index-1];
				}	
			}
			
			//final result is based on the & operation on all the clauses
			finalResult &= clauseResult;
			
			//if final result is false for any one of the class, then breaking the loop
			if(finalResult == 0){
				return false;
			}
		}
		if(finalResult == 1)
			return true;
		else
			return false;
	}
}

*/