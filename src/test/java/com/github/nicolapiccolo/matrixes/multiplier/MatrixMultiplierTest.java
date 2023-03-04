package com.github.nicolapiccolo.matrixes.multiplier;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.github.nicolapiccolo.matrixes.ImmutableMatrix;

public class MatrixMultiplierTest {
	@Test(expected = RuntimeException.class)
	public void multiply_zeroRank_throwException() {
		double[][] inputMatrix = { {} };
		ImmutableMatrix matrix = new ImmutableMatrix(inputMatrix);
		MatrixMultiplier multiplier = new MatrixMultiplier();
		multiplier.multiply(matrix, matrix);
	}
	@Test(expected = RuntimeException.class)
	public void multiply_incompatibleDimensions_throwException() {
		double[][] inputMatrix = { {1,2,3}, {4,5,6} };
		ImmutableMatrix matrix = new ImmutableMatrix(inputMatrix);
		MatrixMultiplier multiplier = new MatrixMultiplier();
		multiplier.multiply(matrix, matrix);
	}
	@Test
	public void getColumnAt_validIndex_returnsColumn() {
		double[][] firstItems = { {1,2,3}, {4,5,6} };
		double[][] secondItems = { {1,2}, {3,4}, {5,6} };
		ImmutableMatrix first = new ImmutableMatrix(firstItems);
		ImmutableMatrix second = new ImmutableMatrix(secondItems);
		MatrixMultiplier multiplier = new MatrixMultiplier();
		ImmutableMatrix result = multiplier.multiply(first, second);
		double[][] resultItems = result.getItems();
		assertTrue(resultItems[0][0] == 22);
		assertTrue(resultItems[0][1] == 28);
		assertTrue(resultItems[1][0] == 49);
		assertTrue(resultItems[1][1] == 64);
	}
}
