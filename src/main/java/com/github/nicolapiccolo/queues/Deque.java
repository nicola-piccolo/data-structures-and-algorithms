package com.github.nicolapiccolo.queues;

public class Deque<T> {
	private static final int DEFAULT_INITIAL_CAPACITY = 10;
	private T[] items;
	private int head;
	private int tail;
	private int size;

	public Deque() {
		this(DEFAULT_INITIAL_CAPACITY);
	}

	@SuppressWarnings("unchecked")
	public Deque(int initialCapacity) {
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

	public void addFirst(T value) {
		if (this.isAtCapacity()) {
			this.doubleCapacity();
		}
		this.head = this.previousIndexOf(this.head);
		this.items[this.head] = value;
		this.size++;
	}

	public void addLast(T value) {
		if (this.isAtCapacity()) {
			this.doubleCapacity();
		}
		this.items[this.tail] = value;
		this.tail = this.nextIndexOf(this.tail);
		this.size++;
	}

	public T removeFirst() {
		if (this.isEmpty()) {
			throw new IllegalStateException("Deque is empty!");
		}
		T value = this.items[this.head];
		this.items[this.head] = null;
		this.head = this.nextIndexOf(this.head);
		this.size--;
		return value;
	}

	public T removeLast() {
		if (this.isEmpty()) {
			throw new IllegalStateException("Deque is empty!");
		}
		this.tail = this.previousIndexOf(this.tail);
		T value = this.items[this.tail];
		this.items[this.tail] = null;
		this.size--;
		return value;
	}

	public T peekFirst() {
		if (this.isEmpty()) {
			throw new IllegalStateException("Deque is empty!");
		}
		return this.items[this.head];
	}

	public T peekLast() {
		if (this.isEmpty()) {
			throw new IllegalStateException("Deque is empty!");
		}
		return this.items[this.previousIndexOf(this.tail)];
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

	private int previousIndexOf(int index) {
		return (index - 1 + this.items.length) % this.items.length;
	}
}
