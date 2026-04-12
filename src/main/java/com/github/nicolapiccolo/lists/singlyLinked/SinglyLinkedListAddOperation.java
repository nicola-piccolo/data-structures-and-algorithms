package com.github.nicolapiccolo.lists.singlyLinked;

import java.util.Optional;

public class SinglyLinkedListAddOperation implements SinglyLinkedListOperation {
	private Integer position;
	private Integer newValue;
	private Optional<SinglyLinkedListNode> head;
	private Optional<SinglyLinkedListNode> tail;

	public SinglyLinkedListAddOperation(Integer position, Integer newValue, Optional<SinglyLinkedListNode> head,
			Optional<SinglyLinkedListNode> tail) {
		this.position = position;
		this.newValue = newValue;
		this.head = head;
		this.tail = tail;
	}

	@Override
	public void execute() {
		SinglyLinkedListNode node = new SinglyLinkedListNode(this.newValue);
		if (this.isEmpty()) {
			this.updateHeadAndTailWith(node);
		} else {
			this.doAdd(node);
		}
	}

	private boolean isEmpty() {
		return this.head.isEmpty();
	}

	private void updateHeadAndTailWith(SinglyLinkedListNode node) {
		this.head = Optional.of(node);
		this.tail = Optional.of(node);
	}

	private void doAdd(SinglyLinkedListNode node) {
		if (this.position == 0) {
			this.setAsNewHead(node);
			return;
		}
		SinglyLinkedListNode nodeBeforePosition = this.findNodeBefore(this.position);
		if (!nodeBeforePosition.hasNextNode()) {
			this.setAsNewTail(node);
			return;
		}
		this.addNodeAfter(nodeBeforePosition, node);
	}

	private void setAsNewHead(SinglyLinkedListNode node) {
		SinglyLinkedListNode headNode = this.head.get();
		node.setNextNode(headNode);
		this.head = Optional.of(node);
	}

	private SinglyLinkedListNode findNodeBefore(Integer position) {
		SinglyLinkedListNode nodeBeforePosition = this.head.get();
		for (int index = 1; index < position; index++) {
			Optional<SinglyLinkedListNode> nextNode = nodeBeforePosition.getNextNode();
			nodeBeforePosition = nextNode.get();
		}
		return nodeBeforePosition;
	}

	private void setAsNewTail(SinglyLinkedListNode node) {
		SinglyLinkedListNode tailNode = this.tail.get();
		tailNode.setNextNode(node);
		this.tail = Optional.of(node);
	}

	private void addNodeAfter(SinglyLinkedListNode nodeBeforePosition, SinglyLinkedListNode node) {
		SinglyLinkedListNode nodeToAttachOnTheRight = nodeBeforePosition.getNextNode().get();
		nodeBeforePosition.setNextNode(node);
		node.setNextNode(nodeToAttachOnTheRight);
	}

	@Override
	public SinglyLinkedHeadAndTailDto getHeadAndTail() {
		return new SinglyLinkedHeadAndTailDto(this.head, this.tail);
	}
}
