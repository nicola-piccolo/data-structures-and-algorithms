package com.github.nicolapiccolo.arrays.insertionSort;

public class InsertionSorter {
	public void sort(Integer[] arrayToSort) {
		if(arrayToSort == null || arrayToSort.length < 2) {
			return;
		}
		this.doSort(arrayToSort);
	}
	private void doSort(Integer[] arrayToSort) {
		for(int index=1; index<arrayToSort.length; index++) {
			this.sortSubarrayUntil(index, arrayToSort);
		}
	}
	private void sortSubarrayUntil(int index, Integer[] arrayToSort) {
		Integer newItemToInsert = arrayToSort[index];
		int cursorMovingToLeft=index-1;
		while(this.shouldShiftToRight(arrayToSort, cursorMovingToLeft, newItemToInsert)) {
			this.shiftToRight(arrayToSort, cursorMovingToLeft);
			cursorMovingToLeft--;
		}
		this.insertNewItem(arrayToSort, cursorMovingToLeft, newItemToInsert);
	}
	private boolean shouldShiftToRight(Integer[] arrayToSort, int cursorMovingToLeft, Integer newItemToInsert) {
		if(cursorMovingToLeft<0) {
			return false;
		}
		Integer itemAtCursor = arrayToSort[cursorMovingToLeft];
		return itemAtCursor.compareTo(newItemToInsert)>0;
	}
	private void shiftToRight(Integer[] arrayToSort, int cursorMovingToLeft) {
		arrayToSort[cursorMovingToLeft+1] = arrayToSort[cursorMovingToLeft];
	}
	private void insertNewItem(Integer[] arrayToSort, int cursorMovingToLeft, Integer newItemToInsert) {
		arrayToSort[cursorMovingToLeft+1] = newItemToInsert;
	}
}
