package com.github.nicolapiccolo.lists.doublyLinked;

import java.util.Optional;

public class DoublyLinkedListAddOperation<T> extends AbstractDoublyLinkedListOperation<T> {
	private int position;
	private T newValue;
	private Optional<DoublyLinkedListNode<T>> head;
	private Optional<DoublyLinkedListNode<T>> tail;

	public DoublyLinkedListAddOperation(int position, T newValue, Optional<DoublyLinkedListNode<T>> head,
			Optional<DoublyLinkedListNode<T>> tail) {
		this.position = position;
		this.newValue = newValue;
		this.head = head;
		this.tail = tail;
	}

	@Override
	public DoublyLinkedHeadAndTailDto<T> execute() {
		DoublyLinkedListNode<T> node = new DoublyLinkedListNode<>(this.newValue);
		if (this.isEmpty()) {
			this.setAsOnly(node);
		} else {
			this.doAdd(node);
		}
		return new DoublyLinkedHeadAndTailDto<>(this.head, this.tail);
	}

	private boolean isEmpty() {
		return this.head.isEmpty();
	}

	private void setAsOnly(DoublyLinkedListNode<T> node) {
		this.head = Optional.of(node);
		this.tail = Optional.of(node);
	}

	private void doAdd(DoublyLinkedListNode<T> node) {
		if (this.position == 0) {
			this.setAsNewHead(node);
		} else if (!this.isAppendingAtEnd()) {
			this.insertInMiddle(node);
		} else {
			this.setAsNewTail(node);
		}
	}

	private void setAsNewHead(DoublyLinkedListNode<T> node) {
		DoublyLinkedListNode<T> oldHead = this.head.get();
		node.setNextNode(oldHead);
		oldHead.setPreviousNode(node);
		this.head = Optional.of(node);
	}

	private boolean isAppendingAtEnd() {
		DoublyLinkedListNode<T> nodeBeforePosition = this.findNodeAt(this.position - 1, this.head);
		return !nodeBeforePosition.hasNextNode();
	}

	private void insertInMiddle(DoublyLinkedListNode<T> node) {
		DoublyLinkedListNode<T> nodeAtPosition = this.findNodeAt(this.position, this.head);
		DoublyLinkedListNode<T> nodeBefore = nodeAtPosition.getPreviousNode().get();
		nodeBefore.setNextNode(node);
		node.setPreviousNode(nodeBefore);
		node.setNextNode(nodeAtPosition);
		nodeAtPosition.setPreviousNode(node);
	}

	private void setAsNewTail(DoublyLinkedListNode<T> node) {
		DoublyLinkedListNode<T> oldTail = this.tail.get();
		oldTail.setNextNode(node);
		node.setPreviousNode(oldTail);
		this.tail = Optional.of(node);
	}
}
