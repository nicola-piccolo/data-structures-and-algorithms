package com.github.nicolapiccolo.lists.doublyLinked;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Iterator;

import org.junit.Test;

public class DoublyLinkedListTest {

	@Test
	public void isEmpty_newList_returnsTrue() {
		DoublyLinkedList<Integer> list = new DoublyLinkedList<>();
		assertTrue(list.isEmpty());
	}

	@Test
	public void size_newList_returnsZero() {
		DoublyLinkedList<Integer> list = new DoublyLinkedList<>();
		assertTrue(list.size() == 0);
	}

	@Test
	public void addAsFirst_firstItem() {
		Integer value = 10;
		DoublyLinkedList<Integer> list = new DoublyLinkedList<>();
		list.addAsFirst(value);
		Iterator<Integer> iterator = list.iterator();
		assertFalse(list.isEmpty());
		assertTrue(list.size() == 1);
		assertTrue(iterator.hasNext());
		assertTrue(iterator.next() == value);
		assertFalse(iterator.hasNext());
	}

	@Test
	public void addAsFirst_twoItems() {
		Integer firstValue = 10;
		DoublyLinkedList<Integer> list = new DoublyLinkedList<>();
		list.addAsFirst(firstValue);
		Integer secondValue = 20;
		list.addAsFirst(secondValue);
		Iterator<Integer> iterator = list.iterator();
		assertFalse(list.isEmpty());
		assertTrue(list.size() == 2);
		assertTrue(iterator.next() == secondValue);
		assertTrue(iterator.next() == firstValue);
		assertFalse(iterator.hasNext());
	}

	@Test
	public void addAsLast_firstItem() {
		Integer value = 10;
		DoublyLinkedList<Integer> list = new DoublyLinkedList<>();
		list.addAsLast(value);
		Iterator<Integer> iterator = list.iterator();
		assertFalse(list.isEmpty());
		assertTrue(list.size() == 1);
		assertTrue(iterator.next() == value);
		assertFalse(iterator.hasNext());
	}

	@Test
	public void addAsLast_twoItems() {
		Integer firstValue = 10;
		DoublyLinkedList<Integer> list = new DoublyLinkedList<>();
		list.addAsLast(firstValue);
		Integer secondValue = 20;
		list.addAsLast(secondValue);
		Iterator<Integer> iterator = list.iterator();
		assertTrue(list.size() == 2);
		assertTrue(iterator.next() == firstValue);
		assertTrue(iterator.next() == secondValue);
		assertFalse(iterator.hasNext());
	}

	@Test(expected = RuntimeException.class)
	public void addAt_negativePosition_throwsException() {
		DoublyLinkedList<Integer> list = new DoublyLinkedList<>();
		list.addAt(-1, 10);
	}

	@Test(expected = RuntimeException.class)
	public void addAt_outOfBoundPosition_throwsException() {
		DoublyLinkedList<Integer> list = new DoublyLinkedList<>();
		list.addAt(10, 10);
	}

	@Test
	public void addAt_emptyListPositionZero_setAsHead() {
		DoublyLinkedList<Integer> list = new DoublyLinkedList<>();
		Integer value = 10;
		list.addAt(0, value);
		Iterator<Integer> iterator = list.iterator();
		assertTrue(list.size() == 1);
		assertTrue(iterator.next() == value);
		assertFalse(iterator.hasNext());
	}

	@Test
	public void addAt_notEmptyListLastPosition_setAsTail() {
		DoublyLinkedList<Integer> list = new DoublyLinkedList<>();
		Integer firstValue = 10;
		list.addAt(0, firstValue);
		Integer secondValue = 20;
		list.addAt(1, secondValue);
		Iterator<Integer> iterator = list.iterator();
		assertTrue(list.size() == 2);
		assertTrue(iterator.next() == firstValue);
		assertTrue(iterator.next() == secondValue);
		assertFalse(iterator.hasNext());
	}

	@Test
	public void addAt_notEmptyListMiddlePosition() {
		DoublyLinkedList<Integer> list = new DoublyLinkedList<>();
		list.addAt(0, 10);
		list.addAt(1, 20);
		list.addAt(1, 30);
		Iterator<Integer> iterator = list.iterator();
		assertTrue(list.size() == 3);
		assertTrue(iterator.next() == 10);
		assertTrue(iterator.next() == 30);
		assertTrue(iterator.next() == 20);
		assertFalse(iterator.hasNext());
	}

	@Test(expected = RuntimeException.class)
	public void first_emptyList_throwsException() {
		DoublyLinkedList<Integer> list = new DoublyLinkedList<>();
		list.first();
	}

	@Test
	public void first_notEmptyList_returnsFirstItem() {
		DoublyLinkedList<Integer> list = new DoublyLinkedList<>();
		Integer firstValue = 10;
		list.addAsLast(firstValue);
		list.addAsLast(20);
		assertTrue(list.first() == firstValue);
	}

	@Test(expected = RuntimeException.class)
	public void last_emptyList_throwsException() {
		DoublyLinkedList<Integer> list = new DoublyLinkedList<>();
		list.last();
	}

	@Test
	public void last_notEmptyList_returnsLastItem() {
		DoublyLinkedList<Integer> list = new DoublyLinkedList<>();
		list.addAsLast(10);
		Integer lastValue = 20;
		list.addAsLast(lastValue);
		assertTrue(list.last() == lastValue);
	}

	@Test(expected = RuntimeException.class)
	public void removeFirst_emptyList_throwsException() {
		DoublyLinkedList<Integer> list = new DoublyLinkedList<>();
		list.removeFirst();
	}

	@Test
	public void removeFirst_oneNodeList_resetAll() {
		DoublyLinkedList<Integer> list = new DoublyLinkedList<>();
		list.addAsLast(10);
		list.removeFirst();
		assertTrue(list.isEmpty());
	}

	@Test
	public void removeFirst_twoNodeList_removeFirst() {
		DoublyLinkedList<Integer> list = new DoublyLinkedList<>();
		list.addAsLast(10);
		Integer secondValue = 20;
		list.addAsLast(secondValue);
		list.removeFirst();
		Iterator<Integer> iterator = list.iterator();
		assertTrue(list.size() == 1);
		assertTrue(iterator.next() == secondValue);
		assertFalse(iterator.hasNext());
	}

	@Test(expected = RuntimeException.class)
	public void removeLast_emptyList_throwsException() {
		DoublyLinkedList<Integer> list = new DoublyLinkedList<>();
		list.removeLast();
	}

	@Test
	public void removeLast_oneNodeList_resetAll() {
		DoublyLinkedList<Integer> list = new DoublyLinkedList<>();
		list.addAsLast(10);
		list.removeLast();
		assertTrue(list.isEmpty());
	}

	@Test
	public void removeLast_twoNodeList_removeLast() {
		DoublyLinkedList<Integer> list = new DoublyLinkedList<>();
		Integer firstValue = 10;
		list.addAsLast(firstValue);
		list.addAsLast(20);
		list.removeLast();
		Iterator<Integer> iterator = list.iterator();
		assertTrue(list.size() == 1);
		assertTrue(iterator.next() == firstValue);
		assertFalse(iterator.hasNext());
	}

	@Test
	public void removeFrom_threeNodeListMiddlePosition_removeMiddle() {
		DoublyLinkedList<Integer> list = new DoublyLinkedList<>();
		Integer firstValue = 10;
		list.addAsLast(firstValue);
		list.addAsLast(20);
		Integer thirdValue = 30;
		list.addAsLast(thirdValue);
		list.removeFrom(1);
		Iterator<Integer> iterator = list.iterator();
		assertTrue(list.size() == 2);
		assertTrue(iterator.next() == firstValue);
		assertTrue(iterator.next() == thirdValue);
		assertFalse(iterator.hasNext());
	}

	@Test
	public void invert_oneNodeList_noChanges() {
		DoublyLinkedList<Integer> list = new DoublyLinkedList<>();
		Integer firstValue = 10;
		list.addAsLast(firstValue);
		list.invert();
		Iterator<Integer> iterator = list.iterator();
		assertTrue(iterator.next() == firstValue);
	}

	@Test
	public void invert_twoNodeList_inverts() {
		DoublyLinkedList<Integer> list = new DoublyLinkedList<>();
		Integer firstValue = 10;
		list.addAsLast(firstValue);
		Integer secondValue = 20;
		list.addAsLast(secondValue);
		list.invert();
		Iterator<Integer> iterator = list.iterator();
		assertTrue(iterator.next() == secondValue);
		assertTrue(iterator.next() == firstValue);
	}

	@Test
	public void reverseIterator_twoItems_iteratesBackward() {
		DoublyLinkedList<Integer> list = new DoublyLinkedList<>();
		Integer firstValue = 10;
		list.addAsLast(firstValue);
		Integer secondValue = 20;
		list.addAsLast(secondValue);
		Iterator<Integer> iterator = list.reverseIterator();
		assertTrue(iterator.hasNext());
		assertTrue(iterator.next() == secondValue);
		assertTrue(iterator.hasNext());
		assertTrue(iterator.next() == firstValue);
		assertFalse(iterator.hasNext());
	}

	@Test
	public void reverseIterator_threeItems_iteratesBackward() {
		DoublyLinkedList<Integer> list = new DoublyLinkedList<>();
		list.addAsLast(10);
		list.addAsLast(20);
		list.addAsLast(30);
		Iterator<Integer> iterator = list.reverseIterator();
		assertTrue(iterator.next() == 30);
		assertTrue(iterator.next() == 20);
		assertTrue(iterator.next() == 10);
		assertFalse(iterator.hasNext());
	}
}
