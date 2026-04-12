package com.github.nicolapiccolo.lists.doublyLinked;

import java.util.Optional;

public class DoublyLinkedListInvertOperation<T> implements DoublyLinkedListOperation<T> {
	private Optional<DoublyLinkedListNode<T>> head;
	private Optional<DoublyLinkedListNode<T>> tail;

	public DoublyLinkedListInvertOperation(Optional<DoublyLinkedListNode<T>> head, Optional<DoublyLinkedListNode<T>> tail) {
		this.head = head;
		this.tail = tail;
	}

	@Override
	public DoublyLinkedHeadAndTailDto<T> execute() {
		if (!this.isOneNodeList()) {
			this.doInvert();
		}
		return new DoublyLinkedHeadAndTailDto<>(this.head, this.tail);
	}

	private boolean isOneNodeList() {
		return !this.head.get().hasNextNode();
	}

	private void doInvert() {
		this.swapAllLinks();
		this.swapHeadAndTail();
	}

	private void swapAllLinks() {
		Optional<DoublyLinkedListNode<T>> currentNode = this.head;
		while (currentNode.isPresent()) {
			DoublyLinkedListNode<T> node = currentNode.get();
			Optional<DoublyLinkedListNode<T>> next = node.getNextNode();
			Optional<DoublyLinkedListNode<T>> previous = node.getPreviousNode();
			this.swapPreviousLink(node, next);
			this.swapNextLink(node, previous);
			currentNode = next;
		}
	}

	private void swapPreviousLink(DoublyLinkedListNode<T> node, Optional<DoublyLinkedListNode<T>> next) {
		if (next.isPresent()) {
			node.setPreviousNode(next.get());
		} else {
			node.resetPreviousNode();
		}
	}

	private void swapNextLink(DoublyLinkedListNode<T> node, Optional<DoublyLinkedListNode<T>> previous) {
		if (previous.isPresent()) {
			node.setNextNode(previous.get());
		} else {
			node.resetNextNode();
		}
	}

	private void swapHeadAndTail() {
		Optional<DoublyLinkedListNode<T>> originalHead = this.head;
		this.head = this.tail;
		this.tail = originalHead;
	}
}
