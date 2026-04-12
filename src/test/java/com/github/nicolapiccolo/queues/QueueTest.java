package com.github.nicolapiccolo.queues;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class QueueTest {

	@Test
	public void isEmpty_newQueue_returnsTrue() {
		Queue<Integer> queue = new Queue<>();
		assertTrue(queue.isEmpty());
	}

	@Test
	public void size_newQueue_returnsZero() {
		Queue<Integer> queue = new Queue<>();
		assertTrue(queue.size() == 0);
	}

	@Test(expected = RuntimeException.class)
	public void constructor_zeroCapacity_throwsException() {
		new Queue<Integer>(0);
	}

	@Test(expected = RuntimeException.class)
	public void constructor_negativeCapacity_throwsException() {
		new Queue<Integer>(-1);
	}

	@Test
	public void enqueue_singleItem() {
		Queue<Integer> queue = new Queue<>();
		queue.enqueue(10);
		assertFalse(queue.isEmpty());
		assertTrue(queue.size() == 1);
	}

	@Test
	public void enqueue_twoItems() {
		Queue<Integer> queue = new Queue<>();
		queue.enqueue(10);
		queue.enqueue(20);
		assertTrue(queue.size() == 2);
	}

	@Test(expected = RuntimeException.class)
	public void dequeue_emptyQueue_throwsException() {
		Queue<Integer> queue = new Queue<>();
		queue.dequeue();
	}

	@Test
	public void dequeue_singleItem_returnsItemAndEmptiesQueue() {
		Queue<Integer> queue = new Queue<>();
		Integer value = 10;
		queue.enqueue(value);
		assertTrue(queue.dequeue() == value);
		assertTrue(queue.isEmpty());
	}

	@Test
	public void dequeue_twoItems_returnsFIFOOrder() {
		Queue<Integer> queue = new Queue<>();
		Integer firstValue = 10;
		Integer secondValue = 20;
		queue.enqueue(firstValue);
		queue.enqueue(secondValue);
		assertTrue(queue.dequeue() == firstValue);
		assertTrue(queue.dequeue() == secondValue);
		assertTrue(queue.isEmpty());
	}

	@Test(expected = RuntimeException.class)
	public void peek_emptyQueue_throwsException() {
		Queue<Integer> queue = new Queue<>();
		queue.peek();
	}

	@Test
	public void peek_singleItem_returnsItemWithoutRemoving() {
		Queue<Integer> queue = new Queue<>();
		Integer value = 10;
		queue.enqueue(value);
		assertTrue(queue.peek() == value);
		assertTrue(queue.size() == 1);
	}

	@Test
	public void peek_twoItems_returnsFirstItem() {
		Queue<Integer> queue = new Queue<>();
		Integer firstValue = 10;
		queue.enqueue(firstValue);
		queue.enqueue(20);
		assertTrue(queue.peek() == firstValue);
	}

	@Test
	public void enqueueAndDequeue_interleaved() {
		Queue<Integer> queue = new Queue<>();
		queue.enqueue(10);
		queue.enqueue(20);
		assertTrue(queue.dequeue() == 10);
		queue.enqueue(30);
		assertTrue(queue.dequeue() == 20);
		assertTrue(queue.dequeue() == 30);
		assertTrue(queue.isEmpty());
	}

	@Test
	public void circularWrapAround_headWrapsToBeginning() {
		Queue<Integer> queue = new Queue<>(3);
		queue.enqueue(10);
		queue.enqueue(20);
		queue.enqueue(30);
		assertTrue(queue.dequeue() == 10);
		assertTrue(queue.dequeue() == 20);
		queue.enqueue(40);
		queue.enqueue(50);
		assertTrue(queue.dequeue() == 30);
		assertTrue(queue.dequeue() == 40);
		assertTrue(queue.dequeue() == 50);
		assertTrue(queue.isEmpty());
	}

	@Test
	public void circularWrapAround_tailWrapsToBeginning() {
		Queue<Integer> queue = new Queue<>(3);
		queue.enqueue(10);
		queue.enqueue(20);
		queue.dequeue();
		queue.dequeue();
		queue.enqueue(30);
		queue.enqueue(40);
		queue.enqueue(50);
		assertTrue(queue.dequeue() == 30);
		assertTrue(queue.dequeue() == 40);
		assertTrue(queue.dequeue() == 50);
		assertTrue(queue.isEmpty());
	}

	@Test
	public void resize_whileWrappedAround_preservesOrder() {
		Queue<Integer> queue = new Queue<>(3);
		queue.enqueue(10);
		queue.enqueue(20);
		queue.enqueue(30);
		queue.dequeue();
		queue.dequeue();
		queue.enqueue(40);
		queue.enqueue(50);
		queue.enqueue(60);
		assertTrue(queue.size() == 4);
		assertTrue(queue.dequeue() == 30);
		assertTrue(queue.dequeue() == 40);
		assertTrue(queue.dequeue() == 50);
		assertTrue(queue.dequeue() == 60);
		assertTrue(queue.isEmpty());
	}

	@Test
	public void resize_fromCapacityOne() {
		Queue<Integer> queue = new Queue<>(1);
		queue.enqueue(10);
		queue.enqueue(20);
		assertTrue(queue.size() == 2);
		assertTrue(queue.dequeue() == 10);
		assertTrue(queue.dequeue() == 20);
	}

	@Test
	public void enqueue_stringType() {
		Queue<String> queue = new Queue<>();
		String value = "hello";
		queue.enqueue(value);
		assertTrue(queue.peek().equals(value));
	}
}
