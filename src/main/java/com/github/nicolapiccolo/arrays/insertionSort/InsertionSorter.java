package com.github.nicolapiccolo.arrays.insertionSort;

public class InsertionSorter {
	public <T extends Comparable<T>> void sort(T[] arrayToSort) {
		if (arrayToSort == null || arrayToSort.length < 2) {
			return;
		}
		doSort(arrayToSort);
	}

	private <T extends Comparable<T>> void doSort(T[] arrayToSort) {
		for (int index = 1; index < arrayToSort.length; index++) {
			sortSubarrayUntil(index, arrayToSort);
		}
	}

	private <T extends Comparable<T>> void sortSubarrayUntil(int index, T[] arrayToSort) {
		T newItemToInsert = arrayToSort[index];
		int cursorMovingToLeft = index - 1;
		while (shouldShiftToRight(arrayToSort, cursorMovingToLeft, newItemToInsert)) {
			shiftToRight(arrayToSort, cursorMovingToLeft);
			cursorMovingToLeft--;
		}
		insertNewItem(arrayToSort, cursorMovingToLeft, newItemToInsert);
	}

	private <T extends Comparable<T>> boolean shouldShiftToRight(T[] arrayToSort, int cursorMovingToLeft, T newItemToInsert) {
		if (cursorMovingToLeft < 0) {
			return false;
		}
		T itemAtCursor = arrayToSort[cursorMovingToLeft];
		return itemAtCursor.compareTo(newItemToInsert) > 0;
	}

	private <T> void shiftToRight(T[] arrayToSort, int cursorMovingToLeft) {
		arrayToSort[cursorMovingToLeft + 1] = arrayToSort[cursorMovingToLeft];
	}

	private <T> void insertNewItem(T[] arrayToSort, int cursorMovingToLeft, T newItemToInsert) {
		arrayToSort[cursorMovingToLeft + 1] = newItemToInsert;
	}
}
