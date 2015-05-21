import java.util.Scanner;

public class p06 {
	public static void main(String args[]) {
		int capacity = 0;
		int numberOfCars = 0;
		Scanner scanner = new Scanner(System.in);
		// read the length of ferry and number of cars
		capacity = scanner.nextInt();
		numberOfCars = scanner.nextInt();
		int[] cars = new int[numberOfCars];
		// reading the car lengths
		for (int i = 0; i < numberOfCars; ++i) {
			cars[i] = scanner.nextInt();
		}
		// matrix to store the possible options
		boolean[][] matrix = new boolean[numberOfCars + 1][capacity + 1];

		matrix[0][0] = true;
		int sum = 0;

		for (int i = 1; i <= numberOfCars; ++i) {
			sum += cars[i - 1];
			for (int j = 0; j <= capacity; ++j) {
				// filling the left side of the ferry
				if (sum <= capacity) {
					if (matrix[i - 1][j]) {
						matrix[i][j] = true;
						matrix[i][j + cars[i - 1]] = true;
					}
				}
				// considering the options to fill right side of the ferry
				else {
					if (matrix[i - 1][j]) {
						if (sum - j <= capacity)
							matrix[i][j] = true;
						if (j + cars[i - 1] <= capacity)
							matrix[i][j + cars[i - 1]] = true;
					}
				}
			}
		}

		// initializing some temporary variables
		int carNum = numberOfCars;
		int tempCapacity = capacity;
		int count = 0;
		
		// checking the number of cars that can fit in the ferry
		for (count = carNum; count > 0; count--) {
			if (!matrix[count][tempCapacity])
				continue;
			else
				break;
		}

		System.out.println(count);
		carNum = count;
		// character array that store L or R of the ferry
		char[] leftOrRight = new char[carNum];
		
		// back tracking possible solutions
		while (carNum > 0) {
			if (matrix[carNum][tempCapacity]
					&& !matrix[carNum - 1][tempCapacity]) {
				leftOrRight[carNum - 1] = 'L';
				tempCapacity -= cars[carNum - 1];
			} else if (matrix[carNum][tempCapacity]
					&& matrix[carNum - 1][tempCapacity]) {
				leftOrRight[carNum - 1] = 'R';
			}
			carNum--;
		}
		
		// printing the possible solution
		for (int i = 0; i < count; i++) {
			System.out.println(leftOrRight[i]);
		}
	}
}
