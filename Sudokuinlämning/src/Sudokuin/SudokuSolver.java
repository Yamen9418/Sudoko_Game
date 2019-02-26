package Sudokuin;

public class SudokuSolver {
	public static final int Empty = 0; // Empty cell
	public static final int Size = 9; // Size of Sudoku grids

	boolean[][] usersnumbers = new boolean[9][9];
	static int[][] matrixint;
	

	public SudokuSolver() {
		super();
		matrixint = new int[9][9];

		// fill the matrix with 0
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				setValue(i, j, 0);
			}
		}
		//generate sudoku
		setValue(0,2,8);
		setValue(0,5,9);
		setValue(0,7,6);
		setValue(0,8,2);
		setValue(1,8,5);
		setValue(2,0,1);
		setValue(2,2,2);
		setValue(2,3,5);
		setValue(3,3,2);
		setValue(3,4,1);
		setValue(3,7,9);
		setValue(4,1,5);
		setValue(4,6,6);
		setValue(5,0,6);
		setValue(5,7,2);
		setValue(5,8,8);
		setValue(6,0,4);
		setValue(6,1,1);
		setValue(6,3,6);
		setValue(6,5,8);
		setValue(7,0,8);
		setValue(7,1,6);
		setValue(7,4,3);
		setValue(7,6,1);
		setValue(8,6,4);
	}
	

	// get the cells value
	public int getValue(int row, int col) {
		return matrixint[row][col];
	}

	// get the value from the user
	public boolean usergetValue(int row, int col) {
		return usersnumbers[row][col];
	}

	// set a value to a specific cell
	public static void setValue(int row, int col, int value) {
		matrixint[row][col] = value;
	}

	// set the value by user
	public void usersetValue(int row, int col, int value) {
		matrixint[row][col] = value;
		usersnumbers[row][col] = true;
	}

	// check if a possible number is already in a row
	private boolean isInRow(int row, int number) {
		for (int i = 0; i < Size; i++)
			if (matrixint[row][i] == number)
				return true;

		return false;
	}

	// check if a possible number is already in a column
	private boolean isInCol(int col, int number) {
		for (int i = 0; i < Size; i++)
			if (matrixint[i][col] == number)
				return true;

		return false;
	}

	// check if a possible number is in its 3x3 box
	private boolean isInBox(int row, int col, int number) {
		int r = row - row % 3;
		int c = col - col % 3;

		for (int i = r; i < r + 3; i++)
			for (int j = c; j < c + 3; j++)
				if (matrixint[i][j] == number)
					return true;

		return false;
	}

	//check if a number possible to a row,col position is ok
	private boolean isOk(int row, int col, int number) {
		return !isInRow(row, number) && !isInCol(col, number) && !isInBox(row, col, number);
	}
	
	
	public boolean solve(int row, int col) {
		if(matrixint[row][col] == Empty) {
			// try possible numbers
			for (int number = 1; number <= Size; number++) {
				if (isOk(row, col, number)) {
					// number ok. it respects sudoku constraints
					matrixint[row][col] = number;

					if (solve(row +1 , col +1 )) { // start backtracking recursively
						return true;
					} else { // if not a solution, empty the cell and continue
						matrixint[row][col] = Empty;
					}
				}
			}

			return false;
		} 
			
		return true; // sudoku solved
	}

	public boolean solve() {
		for (int row = 0; row < Size; row++) {
			for (int col = 0; col < Size; col++) {
				// search for an Empty cell
				if (matrixint[row][col] == Empty) {
					// try possible numbers
					for (int number = 1; number <= Size; number++) {
						if (isOk(row, col, number)) {
							// number ok. it respects sudoku constraints
							matrixint[row][col] = number;

							if (solve(row, col)) { // start backtracking recursively
								return true;
							} else { // if not a solution, empty the cell and continue
								matrixint[row][col] = Empty;
							}
						}
					}

					return false;
				}
			}
		}

		return true; // sudoku solved
	}

	// see the sudoku in the terminal
	public void display() {
		for (int i = 0; i < Size; i++) {
			for (int j = 0; j < Size; j++) {
				System.out.print(" " + matrixint[i][j]);
			}

			System.out.println();
		}

		System.out.println();
	}

	public static void main(String[] args) {
		SudokuSolver sudoku = new SudokuSolver();
		System.out.println("Sudoku Start values \n ");
		sudoku.display();

		// try resolution
		if (sudoku.solve(0,0)) {
			System.out.println("Solved! \n");
			sudoku.display();
		} else {
			System.out.println("Unsolvable");
		}
	}

}
