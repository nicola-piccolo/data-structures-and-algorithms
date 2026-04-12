package com.github.nicolapiccolo.lists;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.Test;

import com.github.nicolapiccolo.lists.singlyLinked.SinglyLinkedListNode;

public class SinglyLinkedListNodeTest {

	@Test
	public void getValue() {
		Integer value = 10;
		SinglyLinkedListNode<Integer> node = new SinglyLinkedListNode<>(value);
		assertTrue(node.getValue() == value);
	}

	@Test
	public void setNextNode() {
		Integer value = 10;
		SinglyLinkedListNode<Integer> node = new SinglyLinkedListNode<>(value);
		assertFalse(node.hasNextNode());
		SinglyLinkedListNode<Integer> nextNode = new SinglyLinkedListNode<>(value);
		node.setNextNode(nextNode);
		assertTrue(node.hasNextNode());
	}

	@Test
	public void resetNextNode() {
		Integer value = 10;
		SinglyLinkedListNode<Integer> node = new SinglyLinkedListNode<>(value);
		SinglyLinkedListNode<Integer> nextNode = new SinglyLinkedListNode<>(value);
		node.setNextNode(nextNode);
		assertTrue(node.hasNextNode());
		node.resetNextNode();
		assertFalse(node.hasNextNode());
	}

	@Test
	public void getNextNode_noNextNode_returnsEmptyOptional() {
		Integer value = 10;
		SinglyLinkedListNode<Integer> node = new SinglyLinkedListNode<>(value);
		Optional<SinglyLinkedListNode<Integer>> nextNode = node.getNextNode();
		assertTrue(nextNode.isEmpty());
	}

	@Test
	public void getNextNode_hasNextNode_returnsNextNode() {
		Integer value = 10;
		SinglyLinkedListNode<Integer> node = new SinglyLinkedListNode<>(value);
		assertFalse(node.hasNextNode());
		SinglyLinkedListNode<Integer> nextNode = new SinglyLinkedListNode<>(value);
		node.setNextNode(nextNode);
		Optional<SinglyLinkedListNode<Integer>> result = node.getNextNode();
		assertTrue(result.isPresent());
		assertTrue(result.get() == nextNode);
	}
}
