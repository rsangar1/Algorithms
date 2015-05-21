/**
 * Name: Ramesh Sangaraju
 * Date : 05 March, 2015
 */

import java.util.Scanner;

public class p04 {
	static int[] cheques;
	static boolean[][] matrix;
	public static void main(String[] args){
		try{
			Scanner scanner = new Scanner(System.in);
			int debitAmount = scanner.nextInt();
			int numberOfCheques = scanner.nextInt();
			cheques = new int[numberOfCheques+1];
			int sum = 0;
			int i, j;
			//reading the cheque values
			for(i=1;i<=numberOfCheques;i++){
				cheques[i] = scanner.nextInt();
				sum += cheques[i];
			}
			//constructing a boolean matrix to know the possible cheque values
			matrix = new boolean[numberOfCheques+1][sum+1];
			matrix[0][0] = true;
			for(i=1;i<=numberOfCheques;++i){
				for(j=0;j<=debitAmount;++j){
					if(matrix[i-1][j]==true){
						matrix[i][j] = true;
						matrix[i][j+cheques[i]] = true;
					}
				}
			}
			int remainingAmount = debitAmount;
			int chequeNumber = numberOfCheques;
			//checking if given debit amount is possible
			if(matrix[chequeNumber][remainingAmount]==false){
				System.out.println("Not possible");
			}else{
				//checking for the given amount with different cheques
				while(chequeNumber>0){
					if(matrix[chequeNumber][remainingAmount] && 
							!matrix[chequeNumber - 1][remainingAmount])	{
						System.out.println(chequeNumber);
						remainingAmount -= cheques[chequeNumber];
					}
					chequeNumber--;
				}
			}
		}catch(ArrayIndexOutOfBoundsException aiobe){
			System.out.println("Error while reading error "+aiobe.getMessage());
		}catch(Exception exception){
			System.out.println("Exception occured in program "+exception.getMessage());
		}
	}
}
