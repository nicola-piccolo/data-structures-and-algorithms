package com.github.nicolapiccolo.matrixes;

public class ImmutableMatrix {
	private int rowDimension;
	private int columnDimension;
	private double[][] items;
	
	public ImmutableMatrix(double[][] originalItems) {
		this.rowDimension = this.getRowDimensionFrom(originalItems);
		this.columnDimension = this.getColumnDimensionFrom(originalItems);
		this.items = this.buildItemsFrom(originalItems);
	}
	
	private int getRowDimensionFrom(double[][] originalItems) {
		return originalItems.length;
	}
	
	private int getColumnDimensionFrom(double[][] originalItems) {
		int rowDimension = originalItems.length;
		if(rowDimension == 0) {
			return 0;
		}
		double[] firstRow = originalItems[0];
		return firstRow.length;
	}
	
	private double[][] buildItemsFrom(double[][] originalItems) {
		int rowDimension = this.getRowDimensionFrom(originalItems);
		int columnDimension = this.getColumnDimensionFrom(originalItems);
		double[][] newItems = this.buildEmptyItemsWith(rowDimension, columnDimension);
		for(int rowIndex=0; rowIndex<rowDimension; rowIndex++) {
			double[] rowToCopy = originalItems[rowIndex];
			this.copyRowWith(newItems, rowIndex, rowToCopy);
		}
		return newItems;
	}
	
	private double[][] buildEmptyItemsWith(int rowDimension, int columnDimension){
		return new double[rowDimension][columnDimension];
	}
	
	private void copyRowWith(double[][] items, int rowIndex, double[] rowToCopy) {
		double[] rowToUpdate = items[rowIndex];
		for(int columnIndex=0;columnIndex<rowToUpdate.length; columnIndex++) {
			rowToUpdate[columnIndex] = rowToCopy[columnIndex];
		}
	}
	
	public int getRowDimension() {
		return this.rowDimension;
	}
	
	public int getColumnDimension() {
		return this.columnDimension;
	}
	
	public int getRank() {
		return Math.min(this.rowDimension, this.columnDimension);
	}
	
	public double[] getRowAt(int rowIndex) {
		if(rowIndex >= this.rowDimension) {
			throw new RuntimeException("Row index out of boundary!");
		}
		return this.doGetRowAt(rowIndex);
	}
	
	private double[] doGetRowAt(int rowIndex) {
		double[] row = new double[this.columnDimension];
		for(int columnIndex=0; columnIndex<this.columnDimension; columnIndex++) {
			row[columnIndex]=this.items[rowIndex][columnIndex];
		}
		return row;
	}

	public double[] getColumnAt(int columnIndex) {
		if(columnIndex >= this.columnDimension) {
			throw new RuntimeException("Column index out of boundary!");
		}
		return this.doGetColumnAt(columnIndex);
	}

	private double[] doGetColumnAt(int columnIndex) {
		double[] row = new double[this.rowDimension];
		for(int rowIndex=0; rowIndex<this.rowDimension; rowIndex++) {
			row[rowIndex]=this.items[rowIndex][columnIndex];
		}
		return row;
	}
	
	public double[][] getItems() {
		return this.buildItemsFrom(this.items);
	}
}
