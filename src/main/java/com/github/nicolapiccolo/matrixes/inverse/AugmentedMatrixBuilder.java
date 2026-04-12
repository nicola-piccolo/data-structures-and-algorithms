package com.github.nicolapiccolo.matrixes.inverse;

import com.github.nicolapiccolo.matrixes.ImmutableMatrix;

public class AugmentedMatrixBuilder {
	public ImmutableMatrix buildFrom(ImmutableMatrix originalMatrix) {
		if (originalMatrix.getRank() == 0) {
			throw new IllegalArgumentException("Cannot build augmented matrix from a zero rank matrix!");
		}
		if (originalMatrix.getColumnDimension() != originalMatrix.getRowDimension()) {
			throw new IllegalArgumentException("Cannot build augmented matrix for a non-square matrix!");
		}
		return this.doBuildFrom(originalMatrix);
	}

	private ImmutableMatrix doBuildFrom(ImmutableMatrix originalMatrix) {
		int rowDimension = originalMatrix.getRowDimension();
		int columnDimension = originalMatrix.getColumnDimension();
		double[][] augmentedMatrix = new double[rowDimension][columnDimension * 2];
		this.copyOriginalMatrixInto(augmentedMatrix, originalMatrix);
		this.setDiagonalOnes(augmentedMatrix);
		return new ImmutableMatrix(augmentedMatrix);
	}

	private void copyOriginalMatrixInto(double[][] augmentedMatrix, ImmutableMatrix originalMatrix) {
		for (int rowIndex = 0; rowIndex < originalMatrix.getRowDimension(); rowIndex++) {
			for (int columnIndex = 0; columnIndex < originalMatrix.getColumnDimension(); columnIndex++) {
				augmentedMatrix[rowIndex][columnIndex] = originalMatrix.getItemAt(rowIndex, columnIndex);
			}
		}
	}

	private void setDiagonalOnes(double[][] augmentedMatrix) {
		int rowDimension = augmentedMatrix.length;
		for (int rowIndex = 0; rowIndex < rowDimension; rowIndex++) {
			augmentedMatrix[rowIndex][rowDimension + rowIndex] = 1;
		}
	}
}
