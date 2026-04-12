package com.github.nicolapiccolo.matrixes.inverse;

import com.github.nicolapiccolo.matrixes.ImmutableMatrix;

public class InverseMatrixBuilder {
	private static final double EPSILON = 1e-10;

	public ImmutableMatrix buildFrom(ImmutableMatrix originalMatrix) {
		ImmutableMatrix augmentedMatrix = this.buildAugmentedMatrixFrom(originalMatrix);
		double[][] result = this.calculateInverseMatrixFrom(augmentedMatrix);
		return this.buildInverseImmutableMatrixFrom(result);
	}

	private ImmutableMatrix buildAugmentedMatrixFrom(ImmutableMatrix originalMatrix) {
		AugmentedMatrixBuilder builder = new AugmentedMatrixBuilder();
		return builder.buildFrom(originalMatrix);
	}

	private double[][] calculateInverseMatrixFrom(ImmutableMatrix originalMatrix) {
		double[][] items = originalMatrix.getItems();
		this.applyJordanGaussEliminationTo(items);
		this.normalizeMatrix(items);
		return items;
	}

	private void applyJordanGaussEliminationTo(double[][] items) {
		int rowDimension = items.length;
		for (int diagonalIndex = 0; diagonalIndex < rowDimension; diagonalIndex++) {
			if (Math.abs(items[diagonalIndex][diagonalIndex]) < EPSILON) {
				throw new IllegalStateException("Cannot calculate matrix inverse!");
			}
			for (int currentRowIndex = 0; currentRowIndex < rowDimension; currentRowIndex++) {
				if (currentRowIndex != diagonalIndex) {
					this.multiplyAndSumToCurrentRow(currentRowIndex, diagonalIndex, items);
				}
			}
		}
	}

	private void multiplyAndSumToCurrentRow(int currentRowIndex, int diagonalIndex, double[][] items) {
		double ratio = items[currentRowIndex][diagonalIndex] / items[diagonalIndex][diagonalIndex];
		int columnDimension = items.length * 2;
		for (int columnIndex = 0; columnIndex < columnDimension; columnIndex++) {
			items[currentRowIndex][columnIndex] -= ratio * items[diagonalIndex][columnIndex];
		}
	}

	private void normalizeMatrix(double[][] items) {
		int rowDimension = items.length;
		int columnDimension = items.length * 2;
		for (int rowIndex = 0; rowIndex < rowDimension; rowIndex++) {
			for (int columnIndex = rowDimension; columnIndex < columnDimension; columnIndex++) {
				items[rowIndex][columnIndex] /= items[rowIndex][rowIndex];
			}
		}
	}

	private ImmutableMatrix buildInverseImmutableMatrixFrom(double[][] items) {
		int rank = items.length;
		double[][] inverse = new double[rank][rank];
		for (int rowIndex = 0; rowIndex < rank; rowIndex++) {
			for (int columnIndex = 0; columnIndex < rank; columnIndex++) {
				inverse[rowIndex][columnIndex] = items[rowIndex][rank + columnIndex];
			}
		}
		return new ImmutableMatrix(inverse);
	}
}
