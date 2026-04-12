package com.github.nicolapiccolo.arrays.selectionSort;

public class SelectionSorter {
	public <T extends Comparable<T>> void sort(T[] arrayToSort) {
		if (arrayToSort == null || arrayToSort.length < 2) {
			return;
		}
		this.doSort(arrayToSort);
	}

	private <T extends Comparable<T>> void doSort(T[] arrayToSort) {
		for (int index = 0; index < arrayToSort.length - 1; index++) {
			int minimumItemIndex = this.findMinimumItemStartingFrom(index, arrayToSort);
			if (minimumItemIndex != index) {
				this.swapItems(arrayToSort, index, minimumItemIndex);
			}
		}
	}

	private <T extends Comparable<T>> int findMinimumItemStartingFrom(int index, T[] arrayToSort) {
		int currentMinimumItemIndex = index;
		for (int currentItemIndex = index + 1; currentItemIndex < arrayToSort.length; currentItemIndex++) {
			if (arrayToSort[currentMinimumItemIndex].compareTo(arrayToSort[currentItemIndex]) > 0) {
				currentMinimumItemIndex = currentItemIndex;
			}
		}
		return currentMinimumItemIndex;
	}

	private <T> void swapItems(T[] arrayToSort, int index, int minimumItemIndex) {
		T itemToSwap = arrayToSort[index];
		arrayToSort[index] = arrayToSort[minimumItemIndex];
		arrayToSort[minimumItemIndex] = itemToSwap;
	}
}
