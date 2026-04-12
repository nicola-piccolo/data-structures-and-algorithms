package com.github.nicolapiccolo.queues;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class DequeTest {

	@Test
	public void isEmpty_newDeque_returnsTrue() {
		Deque<Integer> deque = new Deque<>();
		assertTrue(deque.isEmpty());
	}

	@Test
	public void size_newDeque_returnsZero() {
		Deque<Integer> deque = new Deque<>();
		assertTrue(deque.size() == 0);
	}

	@Test
	public void constructor_zeroCapacity_throwsException() {
		assertThrows(RuntimeException.class, () -> {
			new Deque<Integer>(0);
		});
	}

	@Test
	public void constructor_negativeCapacity_throwsException() {
		assertThrows(RuntimeException.class, () -> {
			new Deque<Integer>(-1);
		});
	}

	@Test
	public void addLast_singleItem() {
		Deque<Integer> deque = new Deque<>();
		deque.addLast(10);
		assertFalse(deque.isEmpty());
		assertTrue(deque.size() == 1);
	}

	@Test
	public void addFirst_singleItem() {
		Deque<Integer> deque = new Deque<>();
		deque.addFirst(10);
		assertFalse(deque.isEmpty());
		assertTrue(deque.size() == 1);
	}

	@Test
	public void addFirst_twoItems_removeFirst_returnsFIFOOrder() {
		Deque<Integer> deque = new Deque<>();
		deque.addFirst(10);
		deque.addFirst(20);
		assertTrue(deque.removeFirst() == 20);
		assertTrue(deque.removeFirst() == 10);
	}

	@Test
	public void addLast_twoItems_removeFirst_returnsFIFOOrder() {
		Deque<Integer> deque = new Deque<>();
		deque.addLast(10);
		deque.addLast(20);
		assertTrue(deque.removeFirst() == 10);
		assertTrue(deque.removeFirst() == 20);
	}

	@Test
	public void addLast_twoItems_removeLast_returnsLIFOOrder() {
		Deque<Integer> deque = new Deque<>();
		deque.addLast(10);
		deque.addLast(20);
		assertTrue(deque.removeLast() == 20);
		assertTrue(deque.removeLast() == 10);
	}

	@Test
	public void addFirst_twoItems_removeLast_returnsFIFOOrder() {
		Deque<Integer> deque = new Deque<>();
		deque.addFirst(10);
		deque.addFirst(20);
		assertTrue(deque.removeLast() == 10);
		assertTrue(deque.removeLast() == 20);
	}

	@Test
	public void removeFirst_emptyDeque_throwsException() {
		assertThrows(RuntimeException.class, () -> {
			Deque<Integer> deque = new Deque<>();
			deque.removeFirst();
		});
	}

	@Test
	public void removeLast_emptyDeque_throwsException() {
		assertThrows(RuntimeException.class, () -> {
			Deque<Integer> deque = new Deque<>();
			deque.removeLast();
		});
	}

	@Test
	public void peekFirst_emptyDeque_throwsException() {
		assertThrows(RuntimeException.class, () -> {
			Deque<Integer> deque = new Deque<>();
			deque.peekFirst();
		});
	}

	@Test
	public void peekLast_emptyDeque_throwsException() {
		assertThrows(RuntimeException.class, () -> {
			Deque<Integer> deque = new Deque<>();
			deque.peekLast();
		});
	}

	@Test
	public void peekFirst_returnsWithoutRemoving() {
		Deque<Integer> deque = new Deque<>();
		Integer value = 10;
		deque.addLast(value);
		assertTrue(deque.peekFirst() == value);
		assertTrue(deque.size() == 1);
	}

	@Test
	public void peekLast_returnsWithoutRemoving() {
		Deque<Integer> deque = new Deque<>();
		Integer value = 10;
		deque.addLast(value);
		assertTrue(deque.peekLast() == value);
		assertTrue(deque.size() == 1);
	}

	@Test
	public void peekFirst_andPeekLast_twoItems() {
		Deque<Integer> deque = new Deque<>();
		Integer firstValue = 10;
		Integer secondValue = 20;
		deque.addLast(firstValue);
		deque.addLast(secondValue);
		assertTrue(deque.peekFirst() == firstValue);
		assertTrue(deque.peekLast() == secondValue);
	}

	@Test
	public void addFirst_wrapsHeadBackward() {
		Deque<Integer> deque = new Deque<>(3);
		deque.addFirst(10);
		deque.addFirst(20);
		deque.addFirst(30);
		assertTrue(deque.removeFirst() == 30);
		assertTrue(deque.removeFirst() == 20);
		assertTrue(deque.removeFirst() == 10);
	}

	@Test
	public void mixedOperations_addFirstAndAddLast() {
		Deque<Integer> deque = new Deque<>();
		deque.addFirst(10);
		deque.addLast(20);
		deque.addFirst(30);
		deque.addLast(40);
		assertTrue(deque.removeFirst() == 30);
		assertTrue(deque.removeFirst() == 10);
		assertTrue(deque.removeFirst() == 20);
		assertTrue(deque.removeFirst() == 40);
	}

	@Test
	public void resize_whileWrappedAround_preservesOrder() {
		Deque<Integer> deque = new Deque<>(3);
		deque.addLast(10);
		deque.addLast(20);
		deque.addLast(30);
		deque.removeFirst();
		deque.removeFirst();
		deque.addLast(40);
		deque.addLast(50);
		deque.addLast(60);
		assertTrue(deque.size() == 4);
		assertTrue(deque.removeFirst() == 30);
		assertTrue(deque.removeFirst() == 40);
		assertTrue(deque.removeFirst() == 50);
		assertTrue(deque.removeFirst() == 60);
	}

	@Test
	public void resize_viaAddFirst_preservesOrder() {
		Deque<Integer> deque = new Deque<>(2);
		deque.addFirst(10);
		deque.addFirst(20);
		deque.addFirst(30);
		assertTrue(deque.size() == 3);
		assertTrue(deque.removeFirst() == 30);
		assertTrue(deque.removeFirst() == 20);
		assertTrue(deque.removeFirst() == 10);
	}

	@Test
	public void interleaved_addAndRemoveFromBothEnds() {
		Deque<Integer> deque = new Deque<>();
		deque.addLast(10);
		deque.addLast(20);
		assertTrue(deque.removeFirst() == 10);
		deque.addFirst(30);
		assertTrue(deque.removeLast() == 20);
		assertTrue(deque.removeFirst() == 30);
		assertTrue(deque.isEmpty());
	}

	@Test
	public void resize_fromCapacityOne() {
		Deque<Integer> deque = new Deque<>(1);
		deque.addLast(10);
		deque.addLast(20);
		assertTrue(deque.size() == 2);
		assertTrue(deque.removeFirst() == 10);
		assertTrue(deque.removeFirst() == 20);
	}

	@Test
	public void stringType() {
		Deque<String> deque = new Deque<>();
		deque.addFirst("hello");
		deque.addLast("world");
		assertTrue(deque.peekFirst().equals("hello"));
		assertTrue(deque.peekLast().equals("world"));
	}
}
