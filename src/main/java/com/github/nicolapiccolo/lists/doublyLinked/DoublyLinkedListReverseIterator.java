package com.github.nicolapiccolo.lists.doublyLinked;

import java.util.Iterator;
import java.util.Optional;

public class DoublyLinkedListReverseIterator<T> implements Iterator<T> {
	private Optional<DoublyLinkedListNode<T>> currentNode;

	public DoublyLinkedListReverseIterator(Optional<DoublyLinkedListNode<T>> tail) {
		this.currentNode = tail;
	}

	@Override
	public boolean hasNext() {
		return this.currentNode.isPresent();
	}

	@Override
	public T next() {
		DoublyLinkedListNode<T> node = this.currentNode.get();
		this.currentNode = node.getPreviousNode();
		return node.getValue();
	}
}
