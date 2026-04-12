package com.github.nicolapiccolo.lists.singlyLinked;

import java.util.Optional;

public class SinglyLinkedListInvertOperation<T> implements SinglyLinkedListOperation<T> {
	private Optional<SinglyLinkedListNode<T>> head;
	private Optional<SinglyLinkedListNode<T>> tail;

	public SinglyLinkedListInvertOperation(Optional<SinglyLinkedListNode<T>> head, Optional<SinglyLinkedListNode<T>> tail) {
		this.head = head;
		this.tail = tail;
	}

	@Override
	public SinglyLinkedHeadAndTailDto<T> execute() {
		if (!this.isOneNodeList()) {
			this.doInvert();
		}
		return new SinglyLinkedHeadAndTailDto<>(this.head, this.tail);
	}

	private boolean isOneNodeList() {
		return !this.head.get().hasNextNode();
	}

	private void doInvert() {
		this.invertLinks();
		this.swapHeadAndTail();
	}

	private void invertLinks() {
		SinglyLinkedListNode<T> originalHeadNode = this.head.get();
		Optional<SinglyLinkedListNode<T>> currentNode = this.head;
		Optional<SinglyLinkedListNode<T>> nextNode = currentNode.get().getNextNode();
		Optional<SinglyLinkedListNode<T>> nodeAfterNext;
		while (nextNode.isPresent()) {
			nodeAfterNext = nextNode.get().getNextNode();
			nextNode.get().setNextNode(currentNode.get());
			currentNode = nextNode;
			nextNode = nodeAfterNext;
		}
		originalHeadNode.resetNextNode();
	}

	private void swapHeadAndTail() {
		SinglyLinkedListNode<T> originalHeadNode = this.head.get();
		SinglyLinkedListNode<T> originalTailNode = this.tail.get();
		this.head = Optional.of(originalTailNode);
		this.tail = Optional.of(originalHeadNode);
	}
}
