package com.github.nicolapiccolo.lists.singlyLinked;

import java.util.Optional;

public class SinglyLinkedListRemoveOperation<T> extends AbstractSinglyLinkedListOperation<T> {
	private int position;
	private Optional<SinglyLinkedListNode<T>> head;
	private Optional<SinglyLinkedListNode<T>> tail;

	public SinglyLinkedListRemoveOperation(int position, Optional<SinglyLinkedListNode<T>> head,
			Optional<SinglyLinkedListNode<T>> tail) {
		this.position = position;
		this.head = head;
		this.tail = tail;
	}

	@Override
	public SinglyLinkedHeadAndTailDto<T> execute() {
		if (this.isOneNodeList()) {
			this.resetHeadAndTail();
		} else {
			this.doRemove();
		}
		return new SinglyLinkedHeadAndTailDto<>(this.head, this.tail);
	}

	private boolean isOneNodeList() {
		return !this.head.get().hasNextNode();
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
		SinglyLinkedListNode<T> nodeBeforePosition = this.findNodeBefore(this.position, this.head);
		if (this.shouldUpdateTail(nodeBeforePosition)) {
			this.setAsNewTail(nodeBeforePosition);
			return;
		}
		this.removeNodeAfter(nodeBeforePosition);
	}

	private void moveHeadToTheRight() {
		SinglyLinkedListNode<T> headNode = this.head.get();
		this.head = headNode.getNextNode();
	}

	private boolean shouldUpdateTail(SinglyLinkedListNode<T> nodeBeforePosition) {
		SinglyLinkedListNode<T> nodeOnPosition = nodeBeforePosition.getNextNode().get();
		return !nodeOnPosition.hasNextNode();
	}

	private void setAsNewTail(SinglyLinkedListNode<T> newTailNode) {
		newTailNode.resetNextNode();
		this.tail = Optional.of(newTailNode);
	}

	private void removeNodeAfter(SinglyLinkedListNode<T> nodeBeforePosition) {
		SinglyLinkedListNode<T> nodeOnPosition = nodeBeforePosition.getNextNode().get();
		SinglyLinkedListNode<T> nodeAfterPosition = nodeOnPosition.getNextNode().get();
		nodeBeforePosition.setNextNode(nodeAfterPosition);
	}
}
