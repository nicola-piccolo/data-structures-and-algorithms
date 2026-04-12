package com.github.nicolapiccolo.lists.singlyLinked;

import java.util.Optional;

abstract class AbstractSinglyLinkedListOperation<T> implements SinglyLinkedListOperation<T> {

	protected SinglyLinkedListNode<T> findNodeBefore(int position, Optional<SinglyLinkedListNode<T>> head) {
		SinglyLinkedListNode<T> nodeBeforePosition = head.get();
		for (int index = 1; index < position; index++) {
			Optional<SinglyLinkedListNode<T>> nextNode = nodeBeforePosition.getNextNode();
			nodeBeforePosition = nextNode.get();
		}
		return nodeBeforePosition;
	}
}
