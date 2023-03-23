package com.github.nicolapiccolo.arrays.selectionSort;

public class SelectionSorter {
	public void sort(Integer[] arrayToSort) {
		if(arrayToSort == null || arrayToSort.length<2) {
			return;
		}
		this.doSort(arrayToSort);
	}
	
	private void doSort(Integer[] arrayToSort) {
		for(int index=0;index<arrayToSort.length;index++) {
			int minimumItemIndex = this.findMinimumItemStartingFrom(index, arrayToSort);
			this.swapItems(arrayToSort, index, minimumItemIndex);
		}
	}
	
	private int findMinimumItemStartingFrom(int index, Integer[] arrayToSort) {
		Integer currentMinimumItemIndex = index;
		for(int currentItemIndex=index+1;currentItemIndex<arrayToSort.length;currentItemIndex++) {
			if(arrayToSort[currentMinimumItemIndex].compareTo(arrayToSort[currentItemIndex])>0) {
				currentMinimumItemIndex = currentItemIndex;
			}
		}
		return currentMinimumItemIndex;
	}
	
	private void swapItems(Integer[] arrayToSort, int index, int minimumItemIndex) {
		Integer itemToSwap = arrayToSort[index];
		arrayToSort[index] = arrayToSort[minimumItemIndex];
		arrayToSort[minimumItemIndex] = itemToSwap;
	}
}
