package com.github.nicolapiccolo.lists.doublyLinked;

import java.util.Optional;

public class DoublyLinkedListNode<T> {
	private T value;
	private Optional<DoublyLinkedListNode<T>> nextNode;
	private Optional<DoublyLinkedListNode<T>> previousNode;

	public DoublyLinkedListNode(T value) {
		this.value = value;
		this.resetNextNode();
		this.resetPreviousNode();
	}

	public T getValue() {
		return this.value;
	}

	public void setNextNode(DoublyLinkedListNode<T> nextNode) {
		this.nextNode = Optional.of(nextNode);
	}

	public void resetNextNode() {
		this.nextNode = Optional.empty();
	}

	public boolean hasNextNode() {
		return this.nextNode.isPresent();
	}

	public Optional<DoublyLinkedListNode<T>> getNextNode() {
		return this.nextNode;
	}

	public void setPreviousNode(DoublyLinkedListNode<T> previousNode) {
		this.previousNode = Optional.of(previousNode);
	}

	public void resetPreviousNode() {
		this.previousNode = Optional.empty();
	}

	public boolean hasPreviousNode() {
		return this.previousNode.isPresent();
	}

	public Optional<DoublyLinkedListNode<T>> getPreviousNode() {
		return this.previousNode;
	}
}
