package com.github.nicolapiccolo.arrays.heap.heapSort;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.github.nicolapiccolo.arrays.heapSort.HeapSorter;

public class HeapSorterTest {
	@Test
	public void nullArray_nothingToSort() {
		Integer[] arrayToSort = null;
		HeapSorter sorter = new HeapSorter();
		Integer[] sortedItems = sorter.sort(arrayToSort);
		assertTrue(sortedItems == null);
	}
	@Test
	public void emptyArray_nothingToSort() {
		Integer[] arrayToSort = new Integer[0];
		HeapSorter sorter = new HeapSorter();
		Integer[] sortedItems = sorter.sort(arrayToSort);
		assertTrue(sortedItems.length == 0);
	}
	@Test
	public void oneElementArray_nothingToSort() {
		Integer element = 4;
		Integer[] arrayToSort = new Integer[]{element};
		HeapSorter sorter = new HeapSorter();
		Integer[] sortedItems = sorter.sort(arrayToSort);
		assertTrue(sortedItems[0] == element);
	}
	@Test
	public void twoElementOrderedArray_nothingToSort() {
		Integer firstElement = 4;
		Integer secondElement = 40;
		Integer[] arrayToSort = new Integer[]{firstElement, secondElement};
		HeapSorter sorter = new HeapSorter();
		Integer[] sortedItems = sorter.sort(arrayToSort);
		assertArrayEquals(sortedItems, new Integer[]{firstElement, secondElement});
	}
	@Test
	public void twoElementUnorderedArray_sort() {
		Integer firstElement = 4;
		Integer secondElement = 40;
		Integer[] arrayToSort = new Integer[]{secondElement, firstElement};
		HeapSorter sorter = new HeapSorter();
		Integer[] sortedItems = sorter.sort(arrayToSort);
		assertArrayEquals(sortedItems, new Integer[]{firstElement, secondElement});
	}
	@Test
	public void threeElementOrderedArray_nothingToSort() {
		Integer firstElement = 4;
		Integer secondElement = 40;
		Integer thirdElement = 400;
		Integer[] arrayToSort = new Integer[]{firstElement, secondElement, thirdElement};
		HeapSorter sorter = new HeapSorter();
		Integer[] sortedItems = sorter.sort(arrayToSort);
		assertArrayEquals(sortedItems, new Integer[]{firstElement, secondElement, thirdElement});
	}
	@Test
	public void threeElementUnorderedArray_sort() {
		Integer firstElement = 4;
		Integer secondElement = 40;
		Integer thirdElement = 400;
		Integer[] arrayToSort = new Integer[]{thirdElement, secondElement, firstElement};
		HeapSorter sorter = new HeapSorter();
		Integer[] sortedItems = sorter.sort(arrayToSort);
		assertArrayEquals(sortedItems, new Integer[]{firstElement, secondElement, thirdElement});
	}
}
