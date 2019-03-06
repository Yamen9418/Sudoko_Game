package Sudokuin;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SudokuSolverTest {
	SudokuSolver testCase;

	@BeforeEach
	void setUp() throws Exception {
		testCase = new SudokuSolver();
	}

	@AfterEach
	void tearDown() throws Exception {
		testCase = null;
	}

	@Test
	void testSetValue() {
		testCase.setValue(0, 0, 1);
		assertTrue(testCase.matrixint[0][0] == 1);
	}
	
	@Test
	void testgetValue() {
		testCase.setValue(0, 0, 1);
		assertTrue(testCase.getValue(0, 0) == 1);
	}
	
	@Test
	void testUserSetValue() {
		testCase.usersetValue(0, 0, 1);
		assertTrue(testCase.matrixint[0][0] == 1);
		assertTrue(testCase.usersnumbers[0][0] == true);
	}
	
	@Test
	void testIsOk() {
		testCase.usersetValue(0, 0, 1);
		assertFalse(testCase.isOk(0, 5, 1));
		assertFalse(testCase.isOk(5, 0, 1));
		assertFalse(testCase.isOk(2, 2, 1));
	}
	
	@Test
	void testEmptySolve() {
		assertTrue(testCase.solve());
	}
	
	@Test
	void testGeneratedSolve() {
		testCase.usersetValue(0,2,8);
		testCase.usersetValue(0,5,9);
		testCase.usersetValue(0,7,6);
		testCase.usersetValue(0,8,2);
		testCase.usersetValue(1,8,5);
		testCase.usersetValue(2,0,1);
		testCase.usersetValue(2,2,2);
		testCase.usersetValue(2,3,5);
		testCase.usersetValue(3,3,2);
		testCase.usersetValue(3,4,1);
		testCase.usersetValue(3,7,9);
		testCase.usersetValue(4,1,5);
		testCase.usersetValue(4,6,6);
		testCase.usersetValue(5,0,6);
		testCase.usersetValue(5,7,2);
		testCase.usersetValue(5,8,8);
		testCase.usersetValue(6,0,4);
		testCase.usersetValue(6,1,1);
		testCase.usersetValue(6,3,6);
		testCase.usersetValue(6,5,8);
		testCase.usersetValue(7,0,8);
		testCase.usersetValue(7,1,6);
		testCase.usersetValue(7,4,3);
		testCase.usersetValue(7,6,1);
		testCase.usersetValue(8,6,4);
		assertTrue(testCase.solve());
	}
	
//	@Test
	void testUnsolvable() {
		testCase.usersetValue(0, 0, 1);
		testCase.usersetValue(0, 1, 1);
		assertFalse(testCase.isOk(0, 1,1));
		assertFalse(testCase.solve());
	}

}
