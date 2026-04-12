package com.github.nicolapiccolo.stacks;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class StackTest {

	@Test
	public void isEmpty_newStack_returnsTrue() {
		Stack<Integer> stack = new Stack<>();
		assertTrue(stack.isEmpty());
	}

	@Test
	public void size_newStack_returnsZero() {
		Stack<Integer> stack = new Stack<>();
		assertTrue(stack.size() == 0);
	}

	@Test
	public void constructor_zeroCapacity_throwsException() {
		assertThrows(RuntimeException.class, () -> {
			new Stack<Integer>(0);
		});
	}

	@Test
	public void constructor_negativeCapacity_throwsException() {
		assertThrows(RuntimeException.class, () -> {
			new Stack<Integer>(-1);
		});
	}

	@Test
	public void push_singleItem() {
		Stack<Integer> stack = new Stack<>();
		stack.push(10);
		assertFalse(stack.isEmpty());
		assertTrue(stack.size() == 1);
	}

	@Test
	public void push_twoItems() {
		Stack<Integer> stack = new Stack<>();
		stack.push(10);
		stack.push(20);
		assertTrue(stack.size() == 2);
	}

	@Test
	public void push_exceedsInitialCapacity_resizes() {
		Stack<Integer> stack = new Stack<>(2);
		stack.push(10);
		stack.push(20);
		stack.push(30);
		assertTrue(stack.size() == 3);
	}

	@Test
	public void pop_emptyStack_throwsException() {
		assertThrows(RuntimeException.class, () -> {
			Stack<Integer> stack = new Stack<>();
			stack.pop();
		});
	}

	@Test
	public void pop_singleItem_returnsItemAndEmptiesStack() {
		Stack<Integer> stack = new Stack<>();
		Integer value = 10;
		stack.push(value);
		assertTrue(stack.pop() == value);
		assertTrue(stack.isEmpty());
	}

	@Test
	public void pop_twoItems_returnsInReverseOrder() {
		Stack<Integer> stack = new Stack<>();
		Integer firstValue = 10;
		Integer secondValue = 20;
		stack.push(firstValue);
		stack.push(secondValue);
		assertTrue(stack.pop() == secondValue);
		assertTrue(stack.pop() == firstValue);
		assertTrue(stack.isEmpty());
	}

	@Test
	public void pop_afterResize_returnsInReverseOrder() {
		Stack<Integer> stack = new Stack<>(2);
		stack.push(10);
		stack.push(20);
		stack.push(30);
		assertTrue(stack.pop() == 30);
		assertTrue(stack.pop() == 20);
		assertTrue(stack.pop() == 10);
		assertTrue(stack.isEmpty());
	}

	@Test
	public void peek_emptyStack_throwsException() {
		assertThrows(RuntimeException.class, () -> {
			Stack<Integer> stack = new Stack<>();
			stack.peek();
		});
	}

	@Test
	public void peek_singleItem_returnsItemWithoutRemoving() {
		Stack<Integer> stack = new Stack<>();
		Integer value = 10;
		stack.push(value);
		assertTrue(stack.peek() == value);
		assertTrue(stack.size() == 1);
	}

	@Test
	public void peek_twoItems_returnsTopItem() {
		Stack<Integer> stack = new Stack<>();
		stack.push(10);
		Integer secondValue = 20;
		stack.push(secondValue);
		assertTrue(stack.peek() == secondValue);
		assertTrue(stack.size() == 2);
	}

	@Test
	public void pushAndPop_interleaved() {
		Stack<Integer> stack = new Stack<>();
		stack.push(10);
		stack.push(20);
		assertTrue(stack.pop() == 20);
		stack.push(30);
		assertTrue(stack.pop() == 30);
		assertTrue(stack.pop() == 10);
		assertTrue(stack.isEmpty());
	}

	@Test
	public void push_stringType() {
		Stack<String> stack = new Stack<>();
		String value = "hello";
		stack.push(value);
		assertTrue(stack.peek().equals(value));
	}
}
