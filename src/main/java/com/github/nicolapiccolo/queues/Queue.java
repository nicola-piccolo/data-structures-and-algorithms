package com.github.nicolapiccolo.queues;

public class Queue<T> {
	private static final int DEFAULT_INITIAL_CAPACITY = 10;
	private T[] items;
	private int head;
	private int tail;
	private int size;

	public Queue() {
		this(DEFAULT_INITIAL_CAPACITY);
	}

	@SuppressWarnings("unchecked")
	public Queue(int initialCapacity) {
		if (initialCapacity < 1) {
			throw new IllegalArgumentException("Initial capacity must be at least 1!");
		}
		this.items = (T[]) new Object[initialCapacity];
		this.head = 0;
		this.tail = 0;
		this.size = 0;
	}

	public int size() {
		return this.size;
	}

	public boolean isEmpty() {
		return this.size == 0;
	}

	public void enqueue(T value) {
		if (this.isAtCapacity()) {
			this.doubleCapacity();
		}
		this.items[this.tail] = value;
		this.tail = this.nextIndexOf(this.tail);
		this.size++;
	}

	private boolean isAtCapacity() {
		return this.size == this.items.length;
	}

	@SuppressWarnings("unchecked")
	private void doubleCapacity() {
		T[] newItems = (T[]) new Object[this.items.length * 2];
		this.copyItemsInto(newItems);
		this.items = newItems;
		this.head = 0;
		this.tail = this.size;
	}

	private void copyItemsInto(T[] newItems) {
		int currentIndex = this.head;
		for (int i = 0; i < this.size; i++) {
			newItems[i] = this.items[currentIndex];
			currentIndex = this.nextIndexOf(currentIndex);
		}
	}

	private int nextIndexOf(int index) {
		return (index + 1) % this.items.length;
	}

	public T dequeue() {
		if (this.isEmpty()) {
			throw new IllegalStateException("Queue is empty!");
		}
		T value = this.items[this.head];
		this.items[this.head] = null;
		this.head = this.nextIndexOf(this.head);
		this.size--;
		return value;
	}

	public T peek() {
		if (this.isEmpty()) {
			throw new IllegalStateException("Queue is empty!");
		}
		return this.items[this.head];
	}
}
