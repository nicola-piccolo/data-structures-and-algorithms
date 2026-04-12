package com.github.nicolapiccolo.lists.singlyLinked;

import java.util.Iterator;
import java.util.Optional;

public class SinglyLinkedListIterator<T> implements Iterator<T> {
	private Optional<SinglyLinkedListNode<T>> currentNode;

	public SinglyLinkedListIterator(Optional<SinglyLinkedListNode<T>> head) {
		this.currentNode = head;
	}

	@Override
	public boolean hasNext() {
		return this.currentNode.isPresent();
	}

	@Override
	public T next() {
		SinglyLinkedListNode<T> node = this.currentNode.get();
		this.currentNode = node.getNextNode();
		return node.getValue();
	}
}
