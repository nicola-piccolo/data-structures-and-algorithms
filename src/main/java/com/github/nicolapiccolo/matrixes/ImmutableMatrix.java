package com.github.nicolapiccolo.matrixes;

public class ImmutableMatrix {
	private int rowDimension;
	private int columnDimension;
	private double[][] items;

	public ImmutableMatrix(double[][] originalItems) {
		this.validateItems(originalItems);
		this.rowDimension = originalItems.length;
		this.columnDimension = this.getColumnDimensionFrom(originalItems);
		this.items = this.buildItemsFrom(originalItems);
	}

	private void validateItems(double[][] originalItems) {
		if (originalItems.length == 0) {
			return;
		}
		int expectedColumnLength = originalItems[0].length;
		for (int rowIndex = 1; rowIndex < originalItems.length; rowIndex++) {
			if (originalItems[rowIndex].length != expectedColumnLength) {
				throw new IllegalArgumentException("Jagged arrays are not supported!");
			}
		}
	}

	private int getColumnDimensionFrom(double[][] originalItems) {
		if (originalItems.length == 0) {
			return 0;
		}
		return originalItems[0].length;
	}

	private double[][] buildItemsFrom(double[][] originalItems) {
		double[][] newItems = new double[originalItems.length][this.columnDimension];
		for (int rowIndex = 0; rowIndex < originalItems.length; rowIndex++) {
			System.arraycopy(originalItems[rowIndex], 0, newItems[rowIndex], 0, this.columnDimension);
		}
		return newItems;
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

	public double getItemAt(int rowIndex, int columnIndex) {
		this.validateRowIndex(rowIndex);
		this.validateColumnIndex(columnIndex);
		return this.items[rowIndex][columnIndex];
	}

	public double[] getRowAt(int rowIndex) {
		this.validateRowIndex(rowIndex);
		double[] row = new double[this.columnDimension];
		System.arraycopy(this.items[rowIndex], 0, row, 0, this.columnDimension);
		return row;
	}

	public double[] getColumnAt(int columnIndex) {
		this.validateColumnIndex(columnIndex);
		double[] column = new double[this.rowDimension];
		for (int rowIndex = 0; rowIndex < this.rowDimension; rowIndex++) {
			column[rowIndex] = this.items[rowIndex][columnIndex];
		}
		return column;
	}

	private void validateRowIndex(int rowIndex) {
		if (rowIndex < 0 || rowIndex >= this.rowDimension) {
			throw new IllegalArgumentException("Row index out of boundary!");
		}
	}

	private void validateColumnIndex(int columnIndex) {
		if (columnIndex < 0 || columnIndex >= this.columnDimension) {
			throw new IllegalArgumentException("Column index out of boundary!");
		}
	}

	public double[][] getItems() {
		return this.buildItemsFrom(this.items);
	}
}
