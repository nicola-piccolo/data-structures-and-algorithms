package com.github.nicolapiccolo.lists.singlyLinked;

import java.util.Optional;

public class SinglyLinkedListNode {
	private Integer value;
	private Optional<SinglyLinkedListNode> nextNode;

	public SinglyLinkedListNode(Integer value) {
		this.value = value;
		this.resetNextNode();
	}

	public void setNextNode(SinglyLinkedListNode nextNode) {
		this.nextNode = Optional.of(nextNode);
	}

	public void resetNextNode() {
		this.nextNode = Optional.empty();
	}

	public boolean hasNextNode() {
		return this.nextNode.isPresent();
	}

	public Optional<SinglyLinkedListNode> getNextNode() {
		return this.nextNode;
	}

	public Integer getValue() {
		return this.value;
	}
}
