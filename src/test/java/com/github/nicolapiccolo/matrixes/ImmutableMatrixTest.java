package com.github.nicolapiccolo.matrixes;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class ImmutableMatrixTest {
	@Test
	public void getRowDimension() {
		double[][] inputMatrix = { {1,2,3}, {4,5,6} };
		ImmutableMatrix matrix = new ImmutableMatrix(inputMatrix);
		assertTrue(2 == matrix.getRowDimension());
	}
	@Test
	public void getColumnDimension() {
		double[][] inputMatrix = { {1,2,3}, {4,5,6} };
		ImmutableMatrix matrix = new ImmutableMatrix(inputMatrix);
		assertTrue(3 == matrix.getColumnDimension());
	}
	@Test
	public void getRank() {
		double[][] inputMatrix = { {1,2,3}, {4,5,6} };
		ImmutableMatrix matrix = new ImmutableMatrix(inputMatrix);
		assertTrue(2 == matrix.getRank());
	}
	@Test
	public void getRowAt_validIndex_returnsRow() {
		double[][] inputMatrix = { {1,2,3}, {4,5,6} };
		ImmutableMatrix matrix = new ImmutableMatrix(inputMatrix);
		double[] firstRow = matrix.getRowAt(0);
		assertTrue(firstRow[0] == 1);
		assertTrue(firstRow[1] == 2);
		assertTrue(firstRow[2] == 3);
	}
	@Test(expected = RuntimeException.class)
	public void getRowAt_outOfBoundaryIndex_throwException() {
		double[][] inputMatrix = { {1,2,3}, {4,5,6} };
		ImmutableMatrix matrix = new ImmutableMatrix(inputMatrix);
		matrix.getRowAt(10);
	}
	@Test
	public void getColumnAt_validIndex_returnsColumn() {
		double[][] inputMatrix = { {1,2,3}, {4,5,6} };
		ImmutableMatrix matrix = new ImmutableMatrix(inputMatrix);
		double[] columnRow = matrix.getColumnAt(0);
		assertTrue(columnRow[0] == 1);
		assertTrue(columnRow[1] == 4);
	}
	@Test(expected = RuntimeException.class)
	public void getColumnAt_outOfBoundaryIndex_throwException() {
		double[][] inputMatrix = { {1,2,3}, {4,5,6} };
		ImmutableMatrix matrix = new ImmutableMatrix(inputMatrix);
		matrix.getColumnAt(10);
	}
	@Test
	public void getItems() {
		double[][] inputMatrix = { {1,2,3}, {4,5,6} };
		ImmutableMatrix matrix = new ImmutableMatrix(inputMatrix);
		double[][] items = matrix.getItems();
		assertTrue(items[0][0] == 1);
		assertTrue(items[0][1] == 2);
		assertTrue(items[0][2] == 3);
		assertTrue(items[1][0] == 4);
		assertTrue(items[1][1] == 5);
		assertTrue(items[1][2] == 6);
	}
}
