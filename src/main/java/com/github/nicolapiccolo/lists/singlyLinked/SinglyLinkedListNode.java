package com.github.nicolapiccolo.lists.singlyLinked;

import java.util.Optional;

public class SinglyLinkedListNode<T> {
	private T value;
	private Optional<SinglyLinkedListNode<T>> nextNode;

	public SinglyLinkedListNode(T value) {
		this.value = value;
		this.resetNextNode();
	}

	public void setNextNode(SinglyLinkedListNode<T> nextNode) {
		this.nextNode = Optional.of(nextNode);
	}

	public void resetNextNode() {
		this.nextNode = Optional.empty();
	}

	public boolean hasNextNode() {
		return this.nextNode.isPresent();
	}

	public Optional<SinglyLinkedListNode<T>> getNextNode() {
		return this.nextNode;
	}

	public T getValue() {
		return this.value;
	}
}
