package com.github.nicolapiccolo.lists.doublyLinked;

import java.util.Optional;

abstract class AbstractDoublyLinkedListOperation<T> implements DoublyLinkedListOperation<T> {

	protected DoublyLinkedListNode<T> findNodeAt(int position, Optional<DoublyLinkedListNode<T>> head) {
		DoublyLinkedListNode<T> currentNode = head.get();
		for (int index = 0; index < position; index++) {
			currentNode = currentNode.getNextNode().get();
		}
		return currentNode;
	}
}
