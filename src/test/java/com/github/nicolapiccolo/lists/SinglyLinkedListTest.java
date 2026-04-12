package com.github.nicolapiccolo.lists;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Iterator;

import org.junit.Test;

import com.github.nicolapiccolo.lists.singlyLinked.SinglyLinkedList;

public class SinglyLinkedListTest {

	@Test
	public void isEmpty_newList_returnsTrue() {
		SinglyLinkedList list = new SinglyLinkedList();
		assertTrue(list.isEmpty());
	}

	@Test
	public void size_newList_returnsZero() {
		SinglyLinkedList list = new SinglyLinkedList();
		assertTrue(list.size() == 0);
	}

	@Test
	public void addAsFirst_firstItem() {
		Integer value = 10;
		SinglyLinkedList list = new SinglyLinkedList();
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
		SinglyLinkedList list = new SinglyLinkedList();
		list.addAsFirst(firstValue);
		Integer secondValue = 20;
		list.addAsFirst(secondValue);
		Iterator<Integer> iterator = list.iterator();
		assertFalse(list.isEmpty());
		assertTrue(list.size() == 2);
		assertTrue(iterator.hasNext());
		assertTrue(iterator.next() == secondValue);
		assertTrue(iterator.hasNext());
		assertTrue(iterator.next() == firstValue);
		assertFalse(iterator.hasNext());
	}

	@Test
	public void addAsLast_firstItem() {
		Integer value = 10;
		SinglyLinkedList list = new SinglyLinkedList();
		list.addAsLast(value);
		Iterator<Integer> iterator = list.iterator();
		assertFalse(list.isEmpty());
		assertTrue(list.size() == 1);
		assertTrue(iterator.hasNext());
		assertTrue(iterator.next() == value);
		assertFalse(iterator.hasNext());
	}

	@Test
	public void addAsLast_twoItems() {
		Integer firstValue = 10;
		SinglyLinkedList list = new SinglyLinkedList();
		list.addAsLast(firstValue);
		Integer secondValue = 20;
		list.addAsLast(secondValue);
		Iterator<Integer> iterator = list.iterator();
		assertFalse(list.isEmpty());
		assertTrue(list.size() == 2);
		assertTrue(iterator.hasNext());
		assertTrue(iterator.next() == firstValue);
		assertTrue(iterator.hasNext());
		assertTrue(iterator.next() == secondValue);
		assertFalse(iterator.hasNext());
	}

	@Test(expected = RuntimeException.class)
	public void addAt_negativePosition_throwsException() {
		SinglyLinkedList list = new SinglyLinkedList();
		Integer position = -1;
		Integer value = 10;
		list.addAt(position, value);
	}

	@Test(expected = RuntimeException.class)
	public void addAt_outOfBoundPosition_throwsException() {
		SinglyLinkedList list = new SinglyLinkedList();
		Integer position = 10;
		Integer value = 10;
		list.addAt(position, value);
	}

	@Test
	public void addAt_emptyListPositionZero_setAsHead() {
		SinglyLinkedList list = new SinglyLinkedList();
		Integer position = 0;
		Integer value = 10;
		list.addAt(position, value);
		Iterator<Integer> iterator = list.iterator();
		assertFalse(list.isEmpty());
		assertTrue(list.size() == 1);
		assertTrue(iterator.hasNext());
		assertTrue(iterator.next() == value);
		assertFalse(iterator.hasNext());
	}

	@Test
	public void addAt_notEmptyListLastPosition_setAsTail() {
		SinglyLinkedList list = new SinglyLinkedList();
		Integer firstPosition = 0;
		Integer firstValue = 10;
		list.addAt(firstPosition, firstValue);
		Integer secondPosition = 1;
		Integer secondValue = 20;
		list.addAt(secondPosition, secondValue);
		Iterator<Integer> iterator = list.iterator();
		assertFalse(list.isEmpty());
		assertTrue(list.size() == 2);
		assertTrue(iterator.hasNext());
		assertTrue(iterator.next() == firstValue);
		assertTrue(iterator.hasNext());
		assertTrue(iterator.next() == secondValue);
		assertFalse(iterator.hasNext());
	}

	@Test
	public void addAt_notEmptyListMiddlePosition() {
		SinglyLinkedList list = new SinglyLinkedList();
		Integer firstPosition = 0;
		Integer firstValue = 10;
		list.addAt(firstPosition, firstValue);
		Integer secondPosition = 1;
		Integer secondValue = 20;
		list.addAt(secondPosition, secondValue);
		Integer thirdPosition = 1;
		Integer thirdValue = 30;
		list.addAt(thirdPosition, thirdValue);
		Iterator<Integer> iterator = list.iterator();
		assertFalse(list.isEmpty());
		assertTrue(list.size() == 3);
		assertTrue(iterator.hasNext());
		assertTrue(iterator.next() == firstValue);
		assertTrue(iterator.hasNext());
		assertTrue(iterator.next() == thirdValue);
		assertTrue(iterator.hasNext());
		assertTrue(iterator.next() == secondValue);
		assertFalse(iterator.hasNext());
	}

	@Test(expected = RuntimeException.class)
	public void first_emptyList_throwsException() {
		SinglyLinkedList list = new SinglyLinkedList();
		list.first();
	}

	@Test
	public void first_notEmptyList_returnsFirstItem() {
		SinglyLinkedList list = new SinglyLinkedList();
		Integer firstValue = 10;
		list.addAsLast(firstValue);
		Integer lastValue = 20;
		list.addAsLast(lastValue);
		assertTrue(list.first() == firstValue);
	}

	@Test(expected = RuntimeException.class)
	public void last_emptyList_throwsException() {
		SinglyLinkedList list = new SinglyLinkedList();
		list.last();
	}

	@Test
	public void last_notEmptyList_returnsLastItem() {
		SinglyLinkedList list = new SinglyLinkedList();
		Integer firstValue = 10;
		list.addAsLast(firstValue);
		Integer lastValue = 20;
		list.addAsLast(lastValue);
		assertTrue(list.last() == lastValue);
	}

	@Test(expected = RuntimeException.class)
	public void removeFirst_emptyList_throwsException() {
		SinglyLinkedList list = new SinglyLinkedList();
		list.removeFirst();
	}

	@Test
	public void removeFirst_oneNodeList_resetAll() {
		SinglyLinkedList list = new SinglyLinkedList();
		Integer firstValue = 10;
		list.addAsLast(firstValue);
		assertTrue(list.size() == 1);
		list.removeFirst();
		assertTrue(list.isEmpty());
	}

	@Test
	public void removeFirst_twoNodeList_removeFirst() {
		SinglyLinkedList list = new SinglyLinkedList();
		Integer firstValue = 10;
		list.addAsLast(firstValue);
		Integer secondValue = 20;
		list.addAsLast(secondValue);
		assertTrue(list.size() == 2);
		list.removeFirst();
		Iterator<Integer> iterator = list.iterator();
		assertTrue(list.size() == 1);
		assertTrue(iterator.hasNext());
		assertTrue(iterator.next() == secondValue);
		assertFalse(iterator.hasNext());
	}

	@Test(expected = RuntimeException.class)
	public void removeLast_emptyList_throwsException() {
		SinglyLinkedList list = new SinglyLinkedList();
		list.removeLast();
	}

	@Test
	public void removeLast_oneNodeList_resetAll() {
		SinglyLinkedList list = new SinglyLinkedList();
		Integer firstValue = 10;
		list.addAsLast(firstValue);
		assertTrue(list.size() == 1);
		list.removeLast();
		assertTrue(list.isEmpty());
	}

	@Test
	public void removeLast_twoNodeList_removeLast() {
		SinglyLinkedList list = new SinglyLinkedList();
		Integer firstValue = 10;
		list.addAsLast(firstValue);
		Integer secondValue = 20;
		list.addAsLast(secondValue);
		assertTrue(list.size() == 2);
		list.removeLast();
		Iterator<Integer> iterator = list.iterator();
		assertTrue(list.size() == 1);
		assertTrue(iterator.hasNext());
		assertTrue(iterator.next() == firstValue);
		assertFalse(iterator.hasNext());
	}

	@Test
	public void removeFrom_threeNodeListMiddlePosition_removeMiddle() {
		SinglyLinkedList list = new SinglyLinkedList();
		Integer firstValue = 10;
		list.addAsLast(firstValue);
		Integer secondValue = 20;
		list.addAsLast(secondValue);
		Integer thirdValue = 30;
		list.addAsLast(thirdValue);
		assertTrue(list.size() == 3);
		Integer position = 1;
		list.removeFrom(position);
		Iterator<Integer> iterator = list.iterator();
		assertTrue(list.size() == 2);
		assertTrue(iterator.hasNext());
		assertTrue(iterator.next() == firstValue);
		assertTrue(iterator.hasNext());
		assertTrue(iterator.next() == thirdValue);
		assertFalse(iterator.hasNext());
	}

	@Test
	public void invert_oneNodeList_noChanges() {
		SinglyLinkedList list = new SinglyLinkedList();
		Integer firstValue = 10;
		list.addAsLast(firstValue);
		assertTrue(list.size() == 1);
		list.invert();
		Iterator<Integer> iterator = list.iterator();
		assertTrue(iterator.hasNext());
		assertTrue(iterator.next() == firstValue);
	}

	@Test
	public void invert_twoNodeList_inverts() {
		SinglyLinkedList list = new SinglyLinkedList();
		Integer firstValue = 10;
		list.addAsLast(firstValue);
		Integer secondValue = 20;
		list.addAsLast(secondValue);
		assertTrue(list.size() == 2);
		list.invert();
		Iterator<Integer> iterator = list.iterator();
		assertTrue(iterator.hasNext());
		assertTrue(iterator.next() == secondValue);
		assertTrue(iterator.hasNext());
		assertTrue(iterator.next() == firstValue);
	}

}
