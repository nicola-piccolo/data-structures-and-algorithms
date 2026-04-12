package com.github.nicolapiccolo.lists.singlyLinked;

import java.util.Optional;

public class SinglyLinkedListRemoveOperation implements SinglyLinkedListOperation {
	private Integer position;
	private Optional<SinglyLinkedListNode> head;
	private Optional<SinglyLinkedListNode> tail;

	public SinglyLinkedListRemoveOperation(Integer position, Optional<SinglyLinkedListNode> head,
			Optional<SinglyLinkedListNode> tail) {
		this.position = position;
		this.head = head;
		this.tail = tail;
	}

	@Override
	public void execute() {
		if (this.isOneNodeList()) {
			this.resetHeadAndTail();
		} else {
			this.doRemove();
		}
	}

	private boolean isOneNodeList() {
		return this.head.get() == this.tail.get();
	}

	private void resetHeadAndTail() {
		this.head = Optional.empty();
		this.tail = Optional.empty();
	}

	private void doRemove() {
		if (this.position == 0) {
			this.moveHeadToTheRight();
			return;
		}
		SinglyLinkedListNode nodeBeforePosition = this.findNodeBefore(this.position);
		if (this.shouldUpdateTail(nodeBeforePosition)) {
			this.setAsNewTail(nodeBeforePosition);
			return;
		}
		this.removeNodeAfter(nodeBeforePosition);
	}

	private void moveHeadToTheRight() {
		SinglyLinkedListNode headNode = this.head.get();
		this.head = headNode.getNextNode();
	}

	private SinglyLinkedListNode findNodeBefore(Integer position) {
		SinglyLinkedListNode nodeBeforePosition = this.head.get();
		for (int index = 1; index < position; index++) {
			Optional<SinglyLinkedListNode> nextNode = nodeBeforePosition.getNextNode();
			nodeBeforePosition = nextNode.get();
		}
		return nodeBeforePosition;
	}

	private boolean shouldUpdateTail(SinglyLinkedListNode nodeBeforePosition) {
		SinglyLinkedListNode nodeOnPosition = nodeBeforePosition.getNextNode().get();
		return !nodeOnPosition.hasNextNode();
	}

	private void setAsNewTail(SinglyLinkedListNode newTailNode) {
		newTailNode.resetNextNode();
		this.tail = Optional.of(newTailNode);
	}

	private void removeNodeAfter(SinglyLinkedListNode nodeBeforePosition) {
		SinglyLinkedListNode nodeOnPosition = nodeBeforePosition.getNextNode().get();
		SinglyLinkedListNode nodeAfterPosition = nodeOnPosition.getNextNode().get();
		nodeBeforePosition.setNextNode(nodeAfterPosition);
	}

	@Override
	public SinglyLinkedHeadAndTailDto getHeadAndTail() {
		return new SinglyLinkedHeadAndTailDto(this.head, this.tail);
	}
}
