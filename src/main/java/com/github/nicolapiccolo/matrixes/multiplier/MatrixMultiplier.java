package com.github.nicolapiccolo.matrixes.multiplier;

import com.github.nicolapiccolo.matrixes.ImmutableMatrix;

public class MatrixMultiplier {
	public ImmutableMatrix multiply(ImmutableMatrix first, ImmutableMatrix second){
		if(first.getRank()==0 || second.getRank()==0) {
			throw new RuntimeException("Cannot multiply matrixes with zero rank!");
		}
		if(first.getColumnDimension() != second.getRowDimension()) {
			throw new RuntimeException("Cannot multiply matrixes with incompatible dimensions!");
		}
		return this.doMultiply(first, second);
	}
	private ImmutableMatrix doMultiply(ImmutableMatrix first, ImmutableMatrix second){
		double[][] result = this.createResultMatrix(first, second);
		for(int rowIndex=0;rowIndex<first.getRowDimension();rowIndex++) {
			for(int columnIndex=0;columnIndex<second.getColumnDimension();columnIndex++) {
				int resultItem = this.calculateResultItemAt(rowIndex, columnIndex, first, second);
				result[rowIndex][columnIndex] = resultItem;
			}
		}
		return this.buildImmutableMatrixFrom(result);
	}
	private double[][] createResultMatrix(ImmutableMatrix first, ImmutableMatrix second){
		return new double[first.getRowDimension()][second.getColumnDimension()];
	}
	private ImmutableMatrix buildImmutableMatrixFrom(double[][] result) {
		return new ImmutableMatrix(result);
	}
	private int calculateResultItemAt(int rowIndex, int columnIndex, ImmutableMatrix first, ImmutableMatrix second) {
		double[] rowToMultiply = first.getRowAt(rowIndex);
		double[] columnToMultiply = second.getColumnAt(columnIndex);
		int resultItem = 0;
		for(int index=0; index<rowToMultiply.length; index++) {
			resultItem += rowToMultiply[index] * columnToMultiply[index];
		}
		return resultItem;
	}
}
