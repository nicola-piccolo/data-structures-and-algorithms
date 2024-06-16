package com.github.nicolapiccolo.matrixes.inverse;

import com.github.nicolapiccolo.matrixes.ImmutableMatrix;

public class AugmentedMatrixBuilder {
	public ImmutableMatrix buildFrom(ImmutableMatrix originalMatrix) {
		if (originalMatrix.getRank() == 0) {
			throw new RuntimeException("Cannot build augmented matrix from a zero rank matrix!");
		}
		if (originalMatrix.getColumnDimension() != originalMatrix.getRowDimension()) {
			throw new RuntimeException("Cannot build augmented matrix for a non-square matrix!");
		}
		return this.doBuildFrom(originalMatrix);
	}

	private ImmutableMatrix doBuildFrom(ImmutableMatrix originalMatrix) {
		double[][] augmentedMatrix = this.buildEmptyAugmentedMatrix(originalMatrix.getRowDimension(),
				originalMatrix.getColumnDimension());
		this.copyOriginalMatrixInto(augmentedMatrix, originalMatrix);
		this.createIdentityMatrixInto(augmentedMatrix);
		return new ImmutableMatrix(augmentedMatrix);
	}

	private double[][] buildEmptyAugmentedMatrix(int originalRowDimension, int originalColumnDimension) {
		int augmentedMatrixRowDimension = originalRowDimension;
		int augmentedMatrixColumnDimension = originalColumnDimension * 2;
		double[][] augmentedMatrix = new double[augmentedMatrixRowDimension][augmentedMatrixColumnDimension];
		return augmentedMatrix;
	}

	private void copyOriginalMatrixInto(double[][] augmentedMatrix, ImmutableMatrix originalMatrix) {
		for (int rowIndex = 0; rowIndex < originalMatrix.getRowDimension(); rowIndex++) {
			for (int columnIndex = 0; columnIndex < originalMatrix.getRowDimension(); columnIndex++) {
				augmentedMatrix[rowIndex][columnIndex] = originalMatrix.getItemAt(rowIndex, columnIndex);
			}
		}
	}

	private void createIdentityMatrixInto(double[][] augmentedMatrix) {
		for (int rowIndex = 0; rowIndex < augmentedMatrix.length; rowIndex++) {
			this.fillRowRightHalfWithZeroesAt(rowIndex, augmentedMatrix);
			this.setDiagonalOneAt(rowIndex, augmentedMatrix);
		}
	}

	private void fillRowRightHalfWithZeroesAt(int rowIndex, double[][] augmentedMatrix) {
		int augmentedMatrixColumnsSize = augmentedMatrix.length * 2;
		for (int columnIndex = augmentedMatrix.length; columnIndex < augmentedMatrixColumnsSize; columnIndex++) {
			augmentedMatrix[rowIndex][columnIndex] = 0;
		}
	}

	private void setDiagonalOneAt(int rowIndex, double[][] augmentedMatrix) {
		int diagonalOneColumnIndex = augmentedMatrix.length + rowIndex;
		augmentedMatrix[rowIndex][diagonalOneColumnIndex] = 1;
	}
}
