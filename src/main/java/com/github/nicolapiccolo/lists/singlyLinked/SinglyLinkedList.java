package com.github.nicolapiccolo.lists.singlyLinked;

import java.util.Iterator;
import java.util.Optional;

public class SinglyLinkedList<T> implements Iterable<T> {
	private Optional<SinglyLinkedListNode<T>> head;
	private Optional<SinglyLinkedListNode<T>> tail;
	private int size;

	public SinglyLinkedList() {
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
		SinglyLinkedListOperation<T> operation = this.buildAddOperation(position, value);
		this.doAddOperation(operation);
	}

	private SinglyLinkedListOperation<T> buildAddOperation(int position, T value) {
		return new SinglyLinkedListAddOperation<>(position, value, this.head, this.tail);
	}

	private void doAddOperation(SinglyLinkedListOperation<T> operation) {
		this.updateHeadAndTailWith(operation.execute());
		this.size++;
	}

	private void updateHeadAndTailWith(SinglyLinkedHeadAndTailDto<T> headAndTail) {
		this.head = headAndTail.head();
		this.tail = headAndTail.tail();
	}

	public void addAsLast(T value) {
		int position = this.size();
		SinglyLinkedListOperation<T> operation = this.buildAddOperation(position, value);
		this.doAddOperation(operation);
	}

	public void addAt(int position, T value) {
		if (position < 0) {
			throw new IllegalArgumentException("Negative positions are not allowed!");
		}
		if (position > this.size()) {
			throw new IllegalArgumentException("Position is out of bound!");
		}
		SinglyLinkedListOperation<T> operation = this.buildAddOperation(position, value);
		this.doAddOperation(operation);
	}

	public Iterator<T> iterator() {
		return new SinglyLinkedListIterator<>(this.head);
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
		SinglyLinkedListOperation<T> operation = this.buildRemoveOperation(position);
		this.doRemoveOperation(operation);
	}

	private SinglyLinkedListOperation<T> buildRemoveOperation(int position) {
		return new SinglyLinkedListRemoveOperation<>(position, this.head, this.tail);
	}

	private void doRemoveOperation(SinglyLinkedListOperation<T> operation) {
		this.updateHeadAndTailWith(operation.execute());
		this.size--;
	}

	public void removeLast() {
		if (this.isEmpty()) {
			throw new IllegalStateException("Empty list!");
		}
		int position = this.size() - 1;
		SinglyLinkedListOperation<T> operation = this.buildRemoveOperation(position);
		this.doRemoveOperation(operation);
	}

	public void removeFrom(int position) {
		if (position < 0) {
			throw new IllegalArgumentException("Negative positions are not allowed!");
		}
		if (position >= this.size()) {
			throw new IllegalArgumentException("Position is out of bound!");
		}
		SinglyLinkedListOperation<T> operation = this.buildRemoveOperation(position);
		this.doRemoveOperation(operation);
	}

	public void invert() {
		if (this.isEmpty()) {
			return;
		}
		SinglyLinkedListOperation<T> operation = this.buildInvertOperation();
		this.updateHeadAndTailWith(operation.execute());
	}

	private SinglyLinkedListOperation<T> buildInvertOperation() {
		return new SinglyLinkedListInvertOperation<>(this.head, this.tail);
	}

}
