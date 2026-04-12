package com.github.nicolapiccolo.matrixes.multiplier;

import com.github.nicolapiccolo.matrixes.ImmutableMatrix;

public class MatrixMultiplier {
	public ImmutableMatrix multiply(ImmutableMatrix first, ImmutableMatrix second) {
		if (first.getRank() == 0 || second.getRank() == 0) {
			throw new IllegalArgumentException("Cannot multiply matrixes with zero rank!");
		}
		if (first.getColumnDimension() != second.getRowDimension()) {
			throw new IllegalArgumentException("Cannot multiply matrixes with incompatible dimensions!");
		}
		return this.doMultiply(first, second);
	}

	private ImmutableMatrix doMultiply(ImmutableMatrix first, ImmutableMatrix second) {
		double[][] result = new double[first.getRowDimension()][second.getColumnDimension()];
		for (int rowIndex = 0; rowIndex < first.getRowDimension(); rowIndex++) {
			for (int columnIndex = 0; columnIndex < second.getColumnDimension(); columnIndex++) {
				result[rowIndex][columnIndex] = this.calculateResultItemAt(rowIndex, columnIndex, first, second);
			}
		}
		return new ImmutableMatrix(result);
	}

	private double calculateResultItemAt(int rowIndex, int columnIndex, ImmutableMatrix first, ImmutableMatrix second) {
		double[] rowToMultiply = first.getRowAt(rowIndex);
		double[] columnToMultiply = second.getColumnAt(columnIndex);
		double resultItem = 0;
		for (int index = 0; index < rowToMultiply.length; index++) {
			resultItem += rowToMultiply[index] * columnToMultiply[index];
		}
		return resultItem;
	}
}
