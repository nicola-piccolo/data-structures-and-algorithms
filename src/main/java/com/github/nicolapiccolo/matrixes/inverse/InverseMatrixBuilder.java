package com.github.nicolapiccolo.matrixes.inverse;

import com.github.nicolapiccolo.matrixes.ImmutableMatrix;

public class InverseMatrixBuilder {
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
		for(int diagonalIndex=0;diagonalIndex<items.length;diagonalIndex++) {
			if(items[diagonalIndex][diagonalIndex]==0) {
				throw new RuntimeException("Cannot calculate matrix inverse!");
			}
			for(int rowIndex=0;rowIndex<items.length;rowIndex++) {
				if(rowIndex != diagonalIndex) {
					double ratio = items[rowIndex][diagonalIndex]/items[diagonalIndex][diagonalIndex];
					for(int columnIndex=0; columnIndex<2*items.length; columnIndex++) {
						items[rowIndex][columnIndex] -= ratio * items[diagonalIndex][columnIndex]; 
					}
				}
			}
		}
	}
	
	private void normalizeMatrix(double[][] items) {
		for(int rowIndex=0; rowIndex<items.length; rowIndex++) {
			for(int columnIndex=items.length;columnIndex<2*items.length;columnIndex++) {
				items[rowIndex][columnIndex] /= items[rowIndex][rowIndex]; 
			}
		}
	}
	
	private ImmutableMatrix buildInverseImmutableMatrixFrom(double[][] items) {
		double[][] inverse = new double[items.length][items.length];
		for(int rowIndex=0;rowIndex<items.length;rowIndex++) {
			for(int columnIndex=0;columnIndex<items.length;columnIndex++) {
				inverse[rowIndex][columnIndex] = items[rowIndex][items.length+columnIndex];
			}
		}
		return new ImmutableMatrix(inverse);
	}
}
