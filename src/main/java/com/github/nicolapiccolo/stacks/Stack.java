package com.github.nicolapiccolo.stacks;

import java.util.Arrays;

public class Stack<T> {
	private static final int DEFAULT_INITIAL_CAPACITY = 10;
	private T[] items;
	private int size;

	public Stack() {
		this(DEFAULT_INITIAL_CAPACITY);
	}

	@SuppressWarnings("unchecked")
	public Stack(int initialCapacity) {
		if (initialCapacity < 1) {
			throw new IllegalArgumentException("Initial capacity must be at least 1!");
		}
		this.items = (T[]) new Object[initialCapacity];
		this.size = 0;
	}

	public int size() {
		return this.size;
	}

	public boolean isEmpty() {
		return this.size == 0;
	}

	public void push(T value) {
		if (this.isAtCapacity()) {
			this.doubleCapacity();
		}
		this.items[this.size++] = value;
	}

	private boolean isAtCapacity() {
		return this.size == this.items.length;
	}

	private void doubleCapacity() {
		this.items = Arrays.copyOf(this.items, this.items.length * 2);
	}

	public T pop() {
		if (this.isEmpty()) {
			throw new IllegalStateException("Stack is empty!");
		}
		T value = this.items[--this.size];
		this.items[this.size] = null;
		return value;
	}

	public T peek() {
		if (this.isEmpty()) {
			throw new IllegalStateException("Stack is empty!");
		}
		return this.items[this.size - 1];
	}
}
