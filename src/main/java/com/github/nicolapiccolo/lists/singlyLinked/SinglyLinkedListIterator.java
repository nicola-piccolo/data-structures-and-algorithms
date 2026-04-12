package com.github.nicolapiccolo.lists.singlyLinked;

import java.util.Iterator;
import java.util.Optional;

public class SinglyLinkedListIterator implements Iterator<Integer> {
	private Optional<SinglyLinkedListNode> currentNode;

	public SinglyLinkedListIterator(Optional<SinglyLinkedListNode> head) {
		this.currentNode = head;
	}

	@Override
	public boolean hasNext() {
		return this.currentNode.isPresent();
	}

	@Override
	public Integer next() {
		SinglyLinkedListNode node = this.currentNode.get();
		this.currentNode = node.getNextNode();
		return node.getValue();
	}
}
