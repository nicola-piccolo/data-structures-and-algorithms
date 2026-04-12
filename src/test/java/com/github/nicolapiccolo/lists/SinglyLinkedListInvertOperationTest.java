package com.github.nicolapiccolo.lists;

import static org.junit.Assert.assertTrue;

import java.util.Optional;

import org.junit.Test;

import com.github.nicolapiccolo.lists.singlyLinked.SinglyLinkedHeadAndTailDto;
import com.github.nicolapiccolo.lists.singlyLinked.SinglyLinkedListInvertOperation;
import com.github.nicolapiccolo.lists.singlyLinked.SinglyLinkedListNode;

public class SinglyLinkedListInvertOperationTest {

	@Test
	public void getHeadAndTail() {
		Optional<SinglyLinkedListNode> head = Optional.empty();
		Optional<SinglyLinkedListNode> tail = Optional.empty();
		SinglyLinkedListInvertOperation operation = new SinglyLinkedListInvertOperation(head, tail);
		SinglyLinkedHeadAndTailDto headAndTailDto = operation.getHeadAndTail();
		assertTrue(headAndTailDto.head() == head);
		assertTrue(headAndTailDto.tail() == tail);
	}

	@Test
	public void execute_oneNodeEmpty_noChanges() {
		Integer value = 10;
		SinglyLinkedListNode node = new SinglyLinkedListNode(value);
		Optional<SinglyLinkedListNode> head = Optional.of(node);
		Optional<SinglyLinkedListNode> tail = Optional.of(node);
		SinglyLinkedListInvertOperation operation = new SinglyLinkedListInvertOperation(head, tail);
		operation.execute();
		SinglyLinkedHeadAndTailDto headAndTailDto = operation.getHeadAndTail();
		Optional<SinglyLinkedListNode> updatedHead = headAndTailDto.head();
		Optional<SinglyLinkedListNode> updatedTail = headAndTailDto.tail();
		assertTrue(updatedHead.get() == head.get());
		assertTrue(updatedTail.get() == tail.get());
	}

	@Test
	public void execute_twoNodes_inverts() {
		Integer firstValue = 10;
		SinglyLinkedListNode firstNode = new SinglyLinkedListNode(firstValue);
		Integer secondValue = 20;
		SinglyLinkedListNode secondNode = new SinglyLinkedListNode(secondValue);
		firstNode.setNextNode(secondNode);
		Optional<SinglyLinkedListNode> head = Optional.of(firstNode);
		Optional<SinglyLinkedListNode> tail = Optional.of(secondNode);
		SinglyLinkedListInvertOperation operation = new SinglyLinkedListInvertOperation(head, tail);
		operation.execute();
		SinglyLinkedHeadAndTailDto updatedHeadAndTailDto = operation.getHeadAndTail();
		Optional<SinglyLinkedListNode> updatedHead = updatedHeadAndTailDto.head();
		Optional<SinglyLinkedListNode> updatedTail = updatedHeadAndTailDto.tail();
		assertTrue(updatedHead.get().getValue() == secondValue);
		assertTrue(updatedTail.get().getValue() == firstValue);
	}
}
