package com.github.nicolapiccolo.arrays.heapSort;

import com.github.nicolapiccolo.arrays.heaps.ArrayHeap;

public class HeapSorter {
	public Integer[] sort(Integer[] arrayToSort) {
		if(arrayToSort == null || arrayToSort.length < 2) {
			return arrayToSort;
		}
		return this.doSort(arrayToSort);
	}
	private Integer[] doSort(Integer[] arrayToSort) {
		ArrayHeap heap = new ArrayHeap(arrayToSort.length);
		this.insertItemsIntoHeap(arrayToSort, heap);
		Integer[] sortedItems = this.getSortedItemsFrom(heap);
		return sortedItems;
	}
	private void insertItemsIntoHeap(Integer[] arrayToSort, ArrayHeap heap) {
		for(Integer item : arrayToSort) {
			heap.insert(item);
		}
	}
	private Integer[] getSortedItemsFrom(ArrayHeap heap) {
		Integer[] sortedItems = new Integer[heap.size()];
		for(int index=0; heap.size()>0; index++) {
			sortedItems[index] = heap.removeRoot();
		}
		return sortedItems;
	}
}
