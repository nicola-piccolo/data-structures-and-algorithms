package com.github.nicolapiccolo.lists.singlyLinked;

import java.util.Iterator;
import java.util.Optional;

public class SinglyLinkedList implements Iterable<Integer> {
	private Optional<SinglyLinkedListNode> head;
	private Optional<SinglyLinkedListNode> tail;
	private Integer size;

	public SinglyLinkedList() {
		this.initializeList();
	}

	private void initializeList() {
		this.head = Optional.empty();
		this.tail = Optional.empty();
		this.size = 0;
	}

	public Integer size() {
		return this.size;
	}

	public boolean isEmpty() {
		return this.size == 0;
	}

	public void addAsFirst(Integer value) {
		Integer position = 0;
		SinglyLinkedListOperation operation = this.buildAddOperation(position, value);
		this.doAddOperation(operation);
	}

	private SinglyLinkedListOperation buildAddOperation(Integer position, Integer value) {
		return new SinglyLinkedListAddOperation(position, value, this.head, this.tail);
	}

	private void doAddOperation(SinglyLinkedListOperation operation) {
		operation.execute();
		this.incrementSize();
		this.updateHeadAndTailWith(operation);
	}

	private void incrementSize() {
		this.size++;
	}

	private void updateHeadAndTailWith(SinglyLinkedListOperation operation) {
		SinglyLinkedHeadAndTailDto headAndTail = operation.getHeadAndTail();
		this.head = headAndTail.head();
		this.tail = headAndTail.tail();
	}

	public void addAsLast(Integer value) {
		Integer position = this.size();
		SinglyLinkedListOperation operation = this.buildAddOperation(position, value);
		this.doAddOperation(operation);
	}

	public void addAt(Integer position, Integer value) {
		if (position < 0) {
			throw new RuntimeException("Negative positions are not allowed!");
		}
		if (position > this.size()) {
			throw new RuntimeException("Position is out of bound!");
		}
		SinglyLinkedListOperation operation = this.buildAddOperation(position, value);
		this.doAddOperation(operation);
	}

	public Iterator<Integer> iterator() {
		return new SinglyLinkedListIterator(this.head);
	}

	public Integer first() {
		if (this.isEmpty()) {
			throw new RuntimeException("Empty list!");
		}
		SinglyLinkedListNode first = this.head.get();
		return first.getValue();
	}

	public Integer last() {
		if (this.isEmpty()) {
			throw new RuntimeException("Empty list!");
		}
		SinglyLinkedListNode last = this.tail.get();
		return last.getValue();
	}

	public void removeFirst() {
		if (this.isEmpty()) {
			throw new RuntimeException("Empty list!");
		}
		Integer position = 0;
		SinglyLinkedListOperation operation = this.buildRemoveOperation(position);
		this.doRemoveOperation(operation);
	}

	private SinglyLinkedListOperation buildRemoveOperation(Integer position) {
		return new SinglyLinkedListRemoveOperation(position, this.head, this.tail);
	}

	private void doRemoveOperation(SinglyLinkedListOperation operation) {
		operation.execute();
		this.decrementSize();
		this.updateHeadAndTailWith(operation);
	}

	private void decrementSize() {
		this.size--;
	}

	public void removeLast() {
		if (this.isEmpty()) {
			throw new RuntimeException("Empty list!");
		}
		Integer position = this.size() - 1;
		SinglyLinkedListOperation operation = this.buildRemoveOperation(position);
		this.doRemoveOperation(operation);
	}

	public void removeFrom(Integer position) {
		if (position < 0) {
			throw new RuntimeException("Negative positions are not allowed!");
		}
		if (position >= this.size()) {
			throw new RuntimeException("Position is out of bound!");
		}
		SinglyLinkedListOperation operation = this.buildRemoveOperation(position);
		this.doRemoveOperation(operation);
	}

	public void invert() {
		if (this.isEmpty()) {
			return;
		}
		SinglyLinkedListOperation operation = this.buildInvertOperation();
		operation.execute();
		this.updateHeadAndTailWith(operation);
	}

	private SinglyLinkedListOperation buildInvertOperation() {
		return new SinglyLinkedListInvertOperation(this.head, this.tail);
	}

}
