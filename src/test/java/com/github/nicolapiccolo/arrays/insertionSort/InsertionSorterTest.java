package com.github.nicolapiccolo.arrays.insertionSort;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class InsertionSorterTest {
	@Test
	public void nullArray_nothingToSort() {
		Integer[] arrayToSort = null;
		InsertionSorter sorter = new InsertionSorter();
		sorter.sort(arrayToSort);
		assertTrue(arrayToSort == null);
	}

	@Test
	public void emptyArray_nothingToSort() {
		Integer[] arrayToSort = new Integer[0];
		InsertionSorter sorter = new InsertionSorter();
		sorter.sort(arrayToSort);
		assertTrue(arrayToSort.length == 0);
	}

	@Test
	public void oneElementArray_nothingToSort() {
		Integer element = 4;
		Integer[] arrayToSort = new Integer[] { element };
		InsertionSorter sorter = new InsertionSorter();
		sorter.sort(arrayToSort);
		assertTrue(arrayToSort[0] == element);
	}

	@Test
	public void twoElementOrderedArray_nothingToSort() {
		Integer firstElement = 4;
		Integer secondElement = 40;
		Integer[] arrayToSort = new Integer[] { firstElement, secondElement };
		InsertionSorter sorter = new InsertionSorter();
		sorter.sort(arrayToSort);
		assertArrayEquals(arrayToSort, new Integer[] { firstElement, secondElement });
	}

	@Test
	public void twoElementUnorderedArray_sort() {
		Integer firstElement = 4;
		Integer secondElement = 40;
		Integer[] arrayToSort = new Integer[] { secondElement, firstElement };
		InsertionSorter sorter = new InsertionSorter();
		sorter.sort(arrayToSort);
		assertArrayEquals(arrayToSort, new Integer[] { firstElement, secondElement });
	}

	@Test
	public void threeElementOrderedArray_nothingToSort() {
		Integer firstElement = 4;
		Integer secondElement = 40;
		Integer thirdElement = 400;
		Integer[] arrayToSort = new Integer[] { firstElement, secondElement, thirdElement };
		InsertionSorter sorter = new InsertionSorter();
		sorter.sort(arrayToSort);
		assertArrayEquals(arrayToSort, new Integer[] { firstElement, secondElement, thirdElement });
	}

	@Test
	public void threeElementUnorderedArray_sort() {
		Integer firstElement = 4;
		Integer secondElement = 40;
		Integer thirdElement = 400;
		Integer[] arrayToSort = new Integer[] { thirdElement, secondElement, firstElement };
		InsertionSorter sorter = new InsertionSorter();
		sorter.sort(arrayToSort);
		assertArrayEquals(arrayToSort, new Integer[] { firstElement, secondElement, thirdElement });
	}

	@Test
	public void stringArray_sort() {
		String[] arrayToSort = new String[] { "cherry", "apple", "banana" };
		InsertionSorter sorter = new InsertionSorter();
		sorter.sort(arrayToSort);
		assertArrayEquals(arrayToSort, new String[] { "apple", "banana", "cherry" });
	}
}
