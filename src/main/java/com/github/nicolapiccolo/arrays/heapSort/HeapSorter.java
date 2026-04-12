package com.github.nicolapiccolo.arrays.heapSort;

import java.util.Arrays;

import com.github.nicolapiccolo.arrays.heaps.ArrayHeap;

public class HeapSorter {
	public <T extends Comparable<T>> T[] sort(T[] arrayToSort) {
		if(arrayToSort == null || arrayToSort.length < 2) {
			return arrayToSort;
		}
		return this.doSort(arrayToSort);
	}
	private <T extends Comparable<T>> T[] doSort(T[] arrayToSort) {
		ArrayHeap<T> heap = new ArrayHeap<>(arrayToSort.length);
		this.insertItemsIntoHeap(arrayToSort, heap);
		return this.getSortedItemsFrom(heap, arrayToSort);
	}
	private <T extends Comparable<T>> void insertItemsIntoHeap(T[] arrayToSort, ArrayHeap<T> heap) {
		for(T item : arrayToSort) {
			heap.insert(item);
		}
	}
	private <T extends Comparable<T>> T[] getSortedItemsFrom(ArrayHeap<T> heap, T[] original) {
		T[] sortedItems = Arrays.copyOf(original, heap.size());
		for(int index=0; heap.size()>0; index++) {
			sortedItems[index] = heap.removeRoot();
		}
		return sortedItems;
	}
}
