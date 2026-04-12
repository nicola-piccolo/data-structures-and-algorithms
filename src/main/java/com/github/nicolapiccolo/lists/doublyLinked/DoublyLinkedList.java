package com.github.nicolapiccolo.lists.doublyLinked;

import java.util.Iterator;
import java.util.Optional;

public class DoublyLinkedList<T> implements Iterable<T> {
	private Optional<DoublyLinkedListNode<T>> head;
	private Optional<DoublyLinkedListNode<T>> tail;
	private int size;

	public DoublyLinkedList() {
		this.initializeList();
	}

	private void initializeList() {
		this.head = Optional.empty();
		this.tail = Optional.empty();
		this.size = 0;
	}

	public int size() {
		return this.size;
	}

	public boolean isEmpty() {
		return this.size == 0;
	}

	public void addAsFirst(T value) {
		int position = 0;
		DoublyLinkedListOperation<T> operation = this.buildAddOperation(position, value);
		this.doAddOperation(operation);
	}

	private DoublyLinkedListOperation<T> buildAddOperation(int position, T value) {
		return new DoublyLinkedListAddOperation<>(position, value, this.head, this.tail);
	}

	private void doAddOperation(DoublyLinkedListOperation<T> operation) {
		this.updateHeadAndTailWith(operation.execute());
		this.size++;
	}

	private void updateHeadAndTailWith(DoublyLinkedHeadAndTailDto<T> headAndTail) {
		this.head = headAndTail.head();
		this.tail = headAndTail.tail();
	}

	public void addAsLast(T value) {
		int position = this.size();
		DoublyLinkedListOperation<T> operation = this.buildAddOperation(position, value);
		this.doAddOperation(operation);
	}

	public void addAt(int position, T value) {
		if (position < 0) {
			throw new IllegalArgumentException("Negative positions are not allowed!");
		}
		if (position > this.size()) {
			throw new IllegalArgumentException("Position is out of bound!");
		}
		DoublyLinkedListOperation<T> operation = this.buildAddOperation(position, value);
		this.doAddOperation(operation);
	}

	public Iterator<T> iterator() {
		return new DoublyLinkedListIterator<>(this.head);
	}

	public Iterator<T> reverseIterator() {
		return new DoublyLinkedListReverseIterator<>(this.tail);
	}

	public T first() {
		return this.head.orElseThrow(() -> new IllegalStateException("Empty list!")).getValue();
	}

	public T last() {
		return this.tail.orElseThrow(() -> new IllegalStateException("Empty list!")).getValue();
	}

	public void removeFirst() {
		if (this.isEmpty()) {
			throw new IllegalStateException("Empty list!");
		}
		int position = 0;
		DoublyLinkedListOperation<T> operation = this.buildRemoveOperation(position);
		this.doRemoveOperation(operation);
	}

	private DoublyLinkedListOperation<T> buildRemoveOperation(int position) {
		return new DoublyLinkedListRemoveOperation<>(position, this.head, this.tail);
	}

	private void doRemoveOperation(DoublyLinkedListOperation<T> operation) {
		this.updateHeadAndTailWith(operation.execute());
		this.size--;
	}

	public void removeLast() {
		if (this.isEmpty()) {
			throw new IllegalStateException("Empty list!");
		}
		int position = this.size() - 1;
		DoublyLinkedListOperation<T> operation = this.buildRemoveOperation(position);
		this.doRemoveOperation(operation);
	}

	public void removeFrom(int position) {
		if (position < 0) {
			throw new IllegalArgumentException("Negative positions are not allowed!");
		}
		if (position >= this.size()) {
			throw new IllegalArgumentException("Position is out of bound!");
		}
		DoublyLinkedListOperation<T> operation = this.buildRemoveOperation(position);
		this.doRemoveOperation(operation);
	}

	public void invert() {
		if (this.isEmpty()) {
			return;
		}
		DoublyLinkedListOperation<T> operation = this.buildInvertOperation();
		this.updateHeadAndTailWith(operation.execute());
	}

	private DoublyLinkedListOperation<T> buildInvertOperation() {
		return new DoublyLinkedListInvertOperation<>(this.head, this.tail);
	}
}
