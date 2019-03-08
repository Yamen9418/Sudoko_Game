package Sudokuin;

public class SudokuSolver {
	public static final int Empty = 0; /* Empty cell */
	public static final int Size = 9; /* Size of Sudoku grids */
	
	boolean[][] usersnumbers = new boolean[9][9]; /* boolean matrix */
	int[][] matrixint;

	
	/** Constructor */
	public SudokuSolver() {
		super();
		matrixint = new int[9][9];

		// fill the matrix with 0
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				setValue(i, j, 0);
			}
		}
	}

	/**
	 *  get the cells value.
	 *  @param  row the row number.
	 *  @param  col the column number.
	 *  @return the value of the cells.
	 */ 
	public int getValue(int row, int col) {
		return matrixint[row][col];
	}
	

	/** get the value from the user.
	 * read cells value after the user has inserted it.
	 * @param row the row number.
	 * @param col the column number.
	 * @return true if the user has inserted a digit
	 *  in cell(row,col), else false.
	 */
	public boolean usergetValue(int row, int col) {
		return usersnumbers[row][col];
	}
	

	/** set a value into a specific cell.
	 * submit a value to a cell(row,col).
	 * @param row the rows number.
	 * @param col the columns number.
	 * @param value the desired value to submit.
	 */
	public void setValue(int row, int col, int value) {
		matrixint[row][col] = value;
	}
	

	/** set the value by user into a specifik cell.
	 * get the value which user has desired.
	 * @param row the rows number.
	 * @param col the columns number.
	 * @param value the desired value.
	 */
	public void usersetValue(int row, int col, int value) {
		matrixint[row][col] = value;
		usersnumbers[row][col] = true;
	}
	

	/** check if a possible number is already in a row.
	 * check whether the number is already in the same row.
	 * @param row the rows number.
	 * @param col the columns number.
	 * @param number the desired number.
	 * @return true if the number does exist in the same
	 * row, else false.
	 */
	private boolean isInRow(int row, int col, int number) {
		for (int i = 0; i < Size; i++)
			if (matrixint[row][i] == number && col != i)
				return true;

		return false;
	}

	
	/** check if a possible number is already in a column.
	 * check whether the number is already in the same column.
	 * @param row the rows number.
	 * @param col the columns number.
	 * @param number the desired number.
	 * @return true if the number does exist in the same 
	 * column, else false.
	 */
	private boolean isInCol(int row, int col, int number) {
		for (int i = 0; i < Size; i++)
			if (matrixint[i][col] == number && row != i)
				return true;

		return false;
	}

	
	/** check if a possible number is in its 3x3 box.
	 * @param row the rows number.
	 * @param col the columns number.
	 * @param number the desired number.
	 * @return true if the number is already in its
	 * 3x3 box, else false.
	 */
	private boolean isInBox(int row, int col, int number) {
		int r = row - row % 3;
		int c = col - col % 3;

		for (int i = r; i < r + 3; i++)
			for (int j = c; j < c + 3; j++)
				if (matrixint[i][j] == number && row != i && col != j)
					return true;

		return false;
	}
	
	

	/** check if it's possible to key a number.
	 * check if the number respects Sudokus rules.
	 * @param row the rows number.
	 * @param col the columns number.
	 * @param number the desired number.
	 * @return true if the number isn't in the same row,
	 * same column or same 3x3 box, else false.
	 */
	public boolean isOk(int row, int col, int number) {
		return !isInRow(row, col, number) && !isInCol(row, col, number) && !isInBox(row, col, number);
	}
	
	
	/** solve Sudoku with help of solve(row, col) method "Backtracking".
	 * check if the sudoku is solvable.
	 * @return true if solve(row, col) returns true, else false.
	 */
	public boolean solve() {
		return solve(0, 0);
	}

	
	
	private boolean solve(int row, int col) {
		if (col >= 9) {
			row = row + 1;
			col = 0;
		}
		if (row >= 9) {
			return true;
		}

		if (matrixint[row][col] == Empty) {
			// try possible numbers
			for (int number = 1; number <= Size; number++) {
				if (isOk(row, col, number)) {
					// number ok. it respects sudoku constraints
					matrixint[row][col] = number;

					if (solve(row, col + 1)) { // start backtracking recursively
						return true;
					} else { // if not a solution, empty the cell and continue
						matrixint[row][col] = Empty;
					}

				}
			}
			return false;
		} else if (matrixint[row][col] != Empty) {
			if (isOk(row, col, matrixint[row][col])) {
				return solve(row, col + 1);
			}
		}
		return false;
	}

	
	/** show the sudoku in the terminal.
	 * show a empty sudoku in the terminal.
	 */
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
		if (sudoku.solve(0, 0)) {
			System.out.println("Solved! \n");
			sudoku.display();
		} else {
			System.out.println("Unsolvable");
		}
	}

}
