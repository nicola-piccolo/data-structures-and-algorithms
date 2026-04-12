package com.github.nicolapiccolo.lists.doublyLinked;

import java.util.Optional;

public class DoublyLinkedListRemoveOperation<T> extends AbstractDoublyLinkedListOperation<T> {
	private int position;
	private Optional<DoublyLinkedListNode<T>> head;
	private Optional<DoublyLinkedListNode<T>> tail;

	public DoublyLinkedListRemoveOperation(int position, Optional<DoublyLinkedListNode<T>> head,
			Optional<DoublyLinkedListNode<T>> tail) {
		this.position = position;
		this.head = head;
		this.tail = tail;
	}

	@Override
	public DoublyLinkedHeadAndTailDto<T> execute() {
		if (this.isOneNodeList()) {
			this.resetHeadAndTail();
		} else {
			this.doRemove();
		}
		return new DoublyLinkedHeadAndTailDto<>(this.head, this.tail);
	}

	private boolean isOneNodeList() {
		return !this.head.get().hasNextNode();
	}

	private void resetHeadAndTail() {
		this.head = Optional.empty();
		this.tail = Optional.empty();
	}

	private void doRemove() {
		DoublyLinkedListNode<T> nodeToRemove = this.findNodeAt(this.position, this.head);
		if (!nodeToRemove.hasPreviousNode()) {
			this.removeHead(nodeToRemove);
		} else if (!nodeToRemove.hasNextNode()) {
			this.removeTail(nodeToRemove);
		} else {
			this.removeMiddle(nodeToRemove);
		}
	}

	private void removeHead(DoublyLinkedListNode<T> nodeToRemove) {
		DoublyLinkedListNode<T> newHead = nodeToRemove.getNextNode().get();
		newHead.resetPreviousNode();
		this.head = Optional.of(newHead);
	}

	private void removeTail(DoublyLinkedListNode<T> nodeToRemove) {
		DoublyLinkedListNode<T> newTail = nodeToRemove.getPreviousNode().get();
		newTail.resetNextNode();
		this.tail = Optional.of(newTail);
	}

	private void removeMiddle(DoublyLinkedListNode<T> nodeToRemove) {
		DoublyLinkedListNode<T> nodeBefore = nodeToRemove.getPreviousNode().get();
		DoublyLinkedListNode<T> nodeAfter = nodeToRemove.getNextNode().get();
		nodeBefore.setNextNode(nodeAfter);
		nodeAfter.setPreviousNode(nodeBefore);
	}
}
