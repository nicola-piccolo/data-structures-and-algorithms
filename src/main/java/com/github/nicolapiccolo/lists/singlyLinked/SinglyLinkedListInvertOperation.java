package com.github.nicolapiccolo.lists.singlyLinked;

import java.util.Optional;

public class SinglyLinkedListInvertOperation implements SinglyLinkedListOperation {
	private Optional<SinglyLinkedListNode> head;
	private Optional<SinglyLinkedListNode> tail;

	public SinglyLinkedListInvertOperation(Optional<SinglyLinkedListNode> head, Optional<SinglyLinkedListNode> tail) {
		this.head = head;
		this.tail = tail;
	}

	@Override
	public void execute() {
		if (this.isOneNodeList()) {
			return;
		}
		this.doInvert();
	}

	private boolean isOneNodeList() {
		return this.head.get() == this.tail.get();
	}

	private void doInvert() {
		this.invertLinks();
		this.swapHeadAndTail();
	}

	private void invertLinks() {
		SinglyLinkedListNode originalHeadNode = this.head.get();
		Optional<SinglyLinkedListNode> currentNode = this.head;
		Optional<SinglyLinkedListNode> nextNode = currentNode.get().getNextNode();
		Optional<SinglyLinkedListNode> nodeAfterNext;
		while (nextNode.isPresent()) {
			nodeAfterNext = nextNode.get().getNextNode();
			nextNode.get().setNextNode(currentNode.get());
			currentNode = nextNode;
			nextNode = nodeAfterNext;
		}
		originalHeadNode.resetNextNode();
	}

	private void swapHeadAndTail() {
		SinglyLinkedListNode originalHeadNode = this.head.get();
		SinglyLinkedListNode originalTailNode = this.tail.get();
		this.head = Optional.of(originalTailNode);
		this.tail = Optional.of(originalHeadNode);
	}

	@Override
	public SinglyLinkedHeadAndTailDto getHeadAndTail() {
		return new SinglyLinkedHeadAndTailDto(this.head, this.tail);
	}
}
