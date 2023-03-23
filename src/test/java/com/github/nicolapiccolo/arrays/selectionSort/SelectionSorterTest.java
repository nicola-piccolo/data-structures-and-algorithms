package com.github.nicolapiccolo.arrays.selectionSort;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class SelectionSorterTest {
	@Test
	public void nullArray_nothingToSort() {
		Integer[] arrayToSort = null;
		SelectionSorter sorter = new SelectionSorter();
		sorter.sort(arrayToSort);
		assertTrue(arrayToSort == null);
	}
	@Test
	public void emptyArray_nothingToSort() {
		Integer[] arrayToSort = new Integer[0];
		SelectionSorter sorter = new SelectionSorter();
		sorter.sort(arrayToSort);
		assertTrue(arrayToSort.length == 0);
	}
	@Test
	public void oneElementArray_nothingToSort() {
		Integer element = 4;
		Integer[] arrayToSort = new Integer[]{element};
		SelectionSorter sorter = new SelectionSorter();
		sorter.sort(arrayToSort);
		assertTrue(arrayToSort[0] == element);
	}
	@Test
	public void twoElementOrderedArray_nothingToSort() {
		Integer firstElement = 4;
		Integer secondElement = 40;
		Integer[] arrayToSort = new Integer[]{firstElement, secondElement};
		SelectionSorter sorter = new SelectionSorter();
		sorter.sort(arrayToSort);
		assertArrayEquals(arrayToSort, new Integer[]{firstElement, secondElement});
	}
	@Test
	public void twoElementUnorderedArray_sort() {
		Integer firstElement = 4;
		Integer secondElement = 40;
		Integer[] arrayToSort = new Integer[]{secondElement, firstElement};
		SelectionSorter sorter = new SelectionSorter();
		sorter.sort(arrayToSort);
		assertArrayEquals(arrayToSort, new Integer[]{firstElement, secondElement});
	}
	@Test
	public void threeElementOrderedArray_nothingToSort() {
		Integer firstElement = 4;
		Integer secondElement = 40;
		Integer thirdElement = 400;
		Integer[] arrayToSort = new Integer[]{firstElement, secondElement, thirdElement};
		SelectionSorter sorter = new SelectionSorter();
		sorter.sort(arrayToSort);
		assertArrayEquals(arrayToSort, new Integer[]{firstElement, secondElement, thirdElement});
	}
	@Test
	public void threeElementUnorderedArray_sort() {
		Integer firstElement = 4;
		Integer secondElement = 40;
		Integer thirdElement = 400;
		Integer[] arrayToSort = new Integer[]{thirdElement, secondElement, firstElement};
		SelectionSorter sorter = new SelectionSorter();
		sorter.sort(arrayToSort);
		assertArrayEquals(arrayToSort, new Integer[]{firstElement, secondElement, thirdElement});
	}
}
