package com.github.nicolapiccolo.lists.singlyLinked;

import java.util.Optional;

public class SinglyLinkedListAddOperation<T> extends AbstractSinglyLinkedListOperation<T> {
	private int position;
	private T newValue;
	private Optional<SinglyLinkedListNode<T>> head;
	private Optional<SinglyLinkedListNode<T>> tail;

	public SinglyLinkedListAddOperation(int position, T newValue, Optional<SinglyLinkedListNode<T>> head,
			Optional<SinglyLinkedListNode<T>> tail) {
		this.position = position;
		this.newValue = newValue;
		this.head = head;
		this.tail = tail;
	}

	@Override
	public SinglyLinkedHeadAndTailDto<T> execute() {
		SinglyLinkedListNode<T> node = new SinglyLinkedListNode<>(this.newValue);
		if (this.isEmpty()) {
			this.updateHeadAndTailWith(node);
		} else {
			this.doAdd(node);
		}
		return new SinglyLinkedHeadAndTailDto<>(this.head, this.tail);
	}

	private boolean isEmpty() {
		return this.head.isEmpty();
	}

	private void updateHeadAndTailWith(SinglyLinkedListNode<T> node) {
		this.head = Optional.of(node);
		this.tail = Optional.of(node);
	}

	private void doAdd(SinglyLinkedListNode<T> node) {
		if (this.position == 0) {
			this.setAsNewHead(node);
			return;
		}
		SinglyLinkedListNode<T> nodeBeforePosition = this.findNodeBefore(this.position, this.head);
		if (!nodeBeforePosition.hasNextNode()) {
			this.setAsNewTail(node);
			return;
		}
		this.addNodeAfter(nodeBeforePosition, node);
	}

	private void setAsNewHead(SinglyLinkedListNode<T> node) {
		SinglyLinkedListNode<T> headNode = this.head.get();
		node.setNextNode(headNode);
		this.head = Optional.of(node);
	}

	private void setAsNewTail(SinglyLinkedListNode<T> node) {
		SinglyLinkedListNode<T> tailNode = this.tail.get();
		tailNode.setNextNode(node);
		this.tail = Optional.of(node);
	}

	private void addNodeAfter(SinglyLinkedListNode<T> nodeBeforePosition, SinglyLinkedListNode<T> node) {
		SinglyLinkedListNode<T> nodeToAttachOnTheRight = nodeBeforePosition.getNextNode().get();
		nodeBeforePosition.setNextNode(node);
		node.setNextNode(nodeToAttachOnTheRight);
	}
}
