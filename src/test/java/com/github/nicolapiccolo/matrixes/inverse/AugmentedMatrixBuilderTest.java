package com.github.nicolapiccolo.matrixes.inverse;

import static org.junit.jupiter.api.Assertions.assertTrue;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import com.github.nicolapiccolo.matrixes.ImmutableMatrix;

public class AugmentedMatrixBuilderTest {
	@Test
	public void buildFrom_zeroRank_throwsException() {
		assertThrows(RuntimeException.class, () -> {
			double[][] inputMatrix = { {} };
			ImmutableMatrix matrix = new ImmutableMatrix(inputMatrix);
			AugmentedMatrixBuilder builder = new AugmentedMatrixBuilder();
			builder.buildFrom(matrix);
		});
	}

	@Test
	public void buildFrom_nonSquareMatrix_throwsException() {
		assertThrows(RuntimeException.class, () -> {
			double[][] inputMatrix = { { 1, 2, 3 }, { 4, 5, 6 } };
			ImmutableMatrix matrix = new ImmutableMatrix(inputMatrix);
			AugmentedMatrixBuilder builder = new AugmentedMatrixBuilder();
			builder.buildFrom(matrix);
		});
	}

	@Test
	public void getItems() {
		double[][] inputMatrix = { { 1, 2 }, { 3, 4 } };
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
