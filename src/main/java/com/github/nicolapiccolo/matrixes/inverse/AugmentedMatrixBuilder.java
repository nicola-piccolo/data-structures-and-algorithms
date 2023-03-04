package com.github.nicolapiccolo.matrixes.inverse;

import com.github.nicolapiccolo.matrixes.ImmutableMatrix;

public class AugmentedMatrixBuilder {
	public ImmutableMatrix buildFrom(ImmutableMatrix originalMatrix) {
		if(originalMatrix.getRank()==0) {
			throw new RuntimeException("Cannot build augmented matrix from a zero rank matrix!");
		}
		if(originalMatrix.getColumnDimension()!=originalMatrix.getRowDimension()) {
			throw new RuntimeException("Cannot build augmented matrix for a non-square matrix!");
		}
		return this.doBuildFrom(originalMatrix);
	}
	
	private ImmutableMatrix doBuildFrom(ImmutableMatrix originalMatrix) {
		double[][] augmentedMatrix = new double[originalMatrix.getRowDimension()][2*originalMatrix.getColumnDimension()];
		this.copyOriginalMatrixInto(augmentedMatrix, originalMatrix);
		this.createIdentityMatrixInto(augmentedMatrix);
		return new ImmutableMatrix(augmentedMatrix);
	}
	
	private void copyOriginalMatrixInto(double[][] augmentedMatrix, ImmutableMatrix originalMatrix) {
		double[][] originalItems = originalMatrix.getItems();
		for(int rowIndex=0; rowIndex<originalMatrix.getRowDimension(); rowIndex++) {
			for(int columnIndex=0; columnIndex<originalMatrix.getRowDimension(); columnIndex++) {
				augmentedMatrix[rowIndex][columnIndex] = originalItems[rowIndex][columnIndex]; 
			}
		}
	}
	
	private void createIdentityMatrixInto(double[][] augmentedMatrix) {
		for(int rowIndex=0; rowIndex<augmentedMatrix.length; rowIndex++) {
			for(int columnIndex=augmentedMatrix.length; columnIndex<(2*augmentedMatrix.length); columnIndex++) {
				augmentedMatrix[rowIndex][columnIndex] = 0;
			}
			augmentedMatrix[rowIndex][augmentedMatrix.length + rowIndex] = 1;
		}
	}
}
