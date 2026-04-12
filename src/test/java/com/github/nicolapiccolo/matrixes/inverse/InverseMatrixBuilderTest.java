package com.github.nicolapiccolo.matrixes.inverse;

import static org.junit.jupiter.api.Assertions.assertTrue;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import com.github.nicolapiccolo.matrixes.ImmutableMatrix;

public class InverseMatrixBuilderTest {
	@Test
	public void buildFrom_zeroDeterminantMatrix_throwsException() {
		assertThrows(RuntimeException.class, () -> {
			double[][] inputMatrix = { { 1, 1 }, { 2, 2 } };
			ImmutableMatrix matrix = new ImmutableMatrix(inputMatrix);
			InverseMatrixBuilder builder = new InverseMatrixBuilder();
			builder.buildFrom(matrix);
		});
	}

	@Test
	public void buildFrom_identityMatrix_returnsIdentityMatrix() {
		double[][] inputMatrix = { { 1, 0 }, { 0, 1 } };
		ImmutableMatrix matrix = new ImmutableMatrix(inputMatrix);
		InverseMatrixBuilder builder = new InverseMatrixBuilder();
		ImmutableMatrix result = builder.buildFrom(matrix);
		double[][] items = result.getItems();
		assertTrue(items[0][0] == 1);
		assertTrue(items[0][1] == 0);
		assertTrue(items[1][0] == 0);
		assertTrue(items[1][1] == 1);
	}

	@Test
	public void buildFrom_validMatrix_returnsInverseMatrix() {
		double[][] inputMatrix = { { 1, -1 }, { 0, 2 } };
		ImmutableMatrix matrix = new ImmutableMatrix(inputMatrix);
		InverseMatrixBuilder builder = new InverseMatrixBuilder();
		ImmutableMatrix result = builder.buildFrom(matrix);
		double[][] items = result.getItems();
		assertTrue(items[0][0] == 1);
		assertTrue(items[0][1] == 0.5);
		assertTrue(items[1][0] == 0);
		assertTrue(items[1][1] == 0.5);
	}
}
