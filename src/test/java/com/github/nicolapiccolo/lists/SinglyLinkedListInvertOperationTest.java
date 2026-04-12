package com.github.nicolapiccolo.lists;

import static org.junit.Assert.assertTrue;

import java.util.Optional;

import org.junit.Test;

import com.github.nicolapiccolo.lists.singlyLinked.SinglyLinkedHeadAndTailDto;
import com.github.nicolapiccolo.lists.singlyLinked.SinglyLinkedListInvertOperation;
import com.github.nicolapiccolo.lists.singlyLinked.SinglyLinkedListNode;

public class SinglyLinkedListInvertOperationTest {

	@Test
	public void execute_oneNodeEmpty_noChanges() {
		Integer value = 10;
		SinglyLinkedListNode<Integer> node = new SinglyLinkedListNode<>(value);
		Optional<SinglyLinkedListNode<Integer>> head = Optional.of(node);
		Optional<SinglyLinkedListNode<Integer>> tail = Optional.of(node);
		SinglyLinkedListInvertOperation<Integer> operation = new SinglyLinkedListInvertOperation<>(head, tail);
		SinglyLinkedHeadAndTailDto<Integer> headAndTailDto = operation.execute();
		Optional<SinglyLinkedListNode<Integer>> updatedHead = headAndTailDto.head();
		Optional<SinglyLinkedListNode<Integer>> updatedTail = headAndTailDto.tail();
		assertTrue(updatedHead.get() == head.get());
		assertTrue(updatedTail.get() == tail.get());
	}

	@Test
	public void execute_twoNodes_inverts() {
		Integer firstValue = 10;
		SinglyLinkedListNode<Integer> firstNode = new SinglyLinkedListNode<>(firstValue);
		Integer secondValue = 20;
		SinglyLinkedListNode<Integer> secondNode = new SinglyLinkedListNode<>(secondValue);
		firstNode.setNextNode(secondNode);
		Optional<SinglyLinkedListNode<Integer>> head = Optional.of(firstNode);
		Optional<SinglyLinkedListNode<Integer>> tail = Optional.of(secondNode);
		SinglyLinkedListInvertOperation<Integer> operation = new SinglyLinkedListInvertOperation<>(head, tail);
		SinglyLinkedHeadAndTailDto<Integer> updatedHeadAndTailDto = operation.execute();
		Optional<SinglyLinkedListNode<Integer>> updatedHead = updatedHeadAndTailDto.head();
		Optional<SinglyLinkedListNode<Integer>> updatedTail = updatedHeadAndTailDto.tail();
		assertTrue(updatedHead.get().getValue() == secondValue);
		assertTrue(updatedTail.get().getValue() == firstValue);
	}
}
