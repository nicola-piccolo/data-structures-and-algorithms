package com.github.nicolapiccolo.lists.doublyLinked;

import java.util.Iterator;
import java.util.Optional;

public class DoublyLinkedListIterator<T> implements Iterator<T> {
	private Optional<DoublyLinkedListNode<T>> currentNode;

	public DoublyLinkedListIterator(Optional<DoublyLinkedListNode<T>> head) {
		this.currentNode = head;
	}

	@Override
	public boolean hasNext() {
		return this.currentNode.isPresent();
	}

	@Override
	public T next() {
		DoublyLinkedListNode<T> node = this.currentNode.get();
		this.currentNode = node.getNextNode();
		return node.getValue();
	}
}
