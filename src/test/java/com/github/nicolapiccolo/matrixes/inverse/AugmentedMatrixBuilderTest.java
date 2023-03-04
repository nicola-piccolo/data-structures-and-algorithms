package com.github.nicolapiccolo.matrixes.inverse;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.github.nicolapiccolo.matrixes.ImmutableMatrix;

public class AugmentedMatrixBuilderTest {
	@Test(expected = RuntimeException.class)
	public void buildFrom_zeroRank_throwsException() {
		double[][] inputMatrix = { {} };
		ImmutableMatrix matrix = new ImmutableMatrix(inputMatrix);
		AugmentedMatrixBuilder builder = new AugmentedMatrixBuilder();
		builder.buildFrom(matrix);
	}
	@Test(expected = RuntimeException.class)
	public void buildFrom_nonSquareMatrix_throwsException() {
		double[][] inputMatrix = { {1,2,3}, {4,5,6} };
		ImmutableMatrix matrix = new ImmutableMatrix(inputMatrix);
		AugmentedMatrixBuilder builder = new AugmentedMatrixBuilder();
		builder.buildFrom(matrix);
	}
	@Test
	public void getItems() {
		double[][] inputMatrix = { {1,2}, {3,4} };
		ImmutableMatrix matrix = new ImmutableMatrix(inputMatrix);
		AugmentedMatrixBuilder builder = new AugmentedMatrixBuilder();
		ImmutableMatrix result = builder.buildFrom(matrix);
		double[][] items = result.getItems();
		assertTrue(items[0][0] == 1);
		assertTrue(items[0][1] == 2);
		assertTrue(items[0][2] == 1);
		assertTrue(items[0][3] == 0);
		assertTrue(items[1][0] == 3);
		assertTrue(items[1][1] == 4);
		assertTrue(items[1][2] == 0);
		assertTrue(items[1][3] == 1);
	}
}
