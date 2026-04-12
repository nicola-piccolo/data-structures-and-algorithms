package com.github.nicolapiccolo.lists;

import static org.junit.Assert.assertTrue;

import java.util.Optional;

import org.junit.Test;

import com.github.nicolapiccolo.lists.singlyLinked.SinglyLinkedHeadAndTailDto;
import com.github.nicolapiccolo.lists.singlyLinked.SinglyLinkedListNode;
import com.github.nicolapiccolo.lists.singlyLinked.SinglyLinkedListRemoveOperation;

public class SinglyLinkedListRemoveOperationTest {

	@Test
	public void execute_oneNodeEmpty_resetHeadAndTail() {
		Integer value = 10;
		SinglyLinkedListNode<Integer> node = new SinglyLinkedListNode<>(value);
		Optional<SinglyLinkedListNode<Integer>> head = Optional.of(node);
		Optional<SinglyLinkedListNode<Integer>> tail = Optional.of(node);
		Integer position = 0;
		SinglyLinkedListRemoveOperation<Integer> operation = new SinglyLinkedListRemoveOperation<>(position, head, tail);
		SinglyLinkedHeadAndTailDto<Integer> headAndTailDto = operation.execute();
		Optional<SinglyLinkedListNode<Integer>> updatedHead = headAndTailDto.head();
		Optional<SinglyLinkedListNode<Integer>> updatedTail = headAndTailDto.tail();
		assertTrue(updatedHead.isEmpty());
		assertTrue(updatedTail.isEmpty());
	}

	@Test
	public void execute_twoNodesFirstIndex_removeHead() {
		Integer firstValue = 10;
		SinglyLinkedListNode<Integer> firstNode = new SinglyLinkedListNode<>(firstValue);
		Integer secondValue = 20;
		SinglyLinkedListNode<Integer> secondNode = new SinglyLinkedListNode<>(secondValue);
		firstNode.setNextNode(secondNode);
		Optional<SinglyLinkedListNode<Integer>> head = Optional.of(firstNode);
		Optional<SinglyLinkedListNode<Integer>> tail = Optional.of(secondNode);
		Integer position = 0;
		SinglyLinkedListRemoveOperation<Integer> operation = new SinglyLinkedListRemoveOperation<>(position, head, tail);
		SinglyLinkedHeadAndTailDto<Integer> updatedHeadAndTailDto = operation.execute();
		Optional<SinglyLinkedListNode<Integer>> updatedHead = updatedHeadAndTailDto.head();
		Optional<SinglyLinkedListNode<Integer>> updatedTail = updatedHeadAndTailDto.tail();
		assertTrue(updatedHead.get().getValue() == secondValue);
		assertTrue(updatedTail.get().getValue() == secondValue);
	}

	@Test
	public void execute_twoNodesSecondIndex_removeTail() {
		Integer firstValue = 10;
		SinglyLinkedListNode<Integer> firstNode = new SinglyLinkedListNode<>(firstValue);
		Integer secondValue = 20;
		SinglyLinkedListNode<Integer> secondNode = new SinglyLinkedListNode<>(secondValue);
		firstNode.setNextNode(secondNode);
		Optional<SinglyLinkedListNode<Integer>> head = Optional.of(firstNode);
		Optional<SinglyLinkedListNode<Integer>> tail = Optional.of(secondNode);
		Integer position = 1;
		SinglyLinkedListRemoveOperation<Integer> operation = new SinglyLinkedListRemoveOperation<>(position, head, tail);
		SinglyLinkedHeadAndTailDto<Integer> updatedHeadAndTailDto = operation.execute();
		Optional<SinglyLinkedListNode<Integer>> updatedHead = updatedHeadAndTailDto.head();
		Optional<SinglyLinkedListNode<Integer>> updatedTail = updatedHeadAndTailDto.tail();
		assertTrue(updatedHead.get().getValue() == firstValue);
		assertTrue(updatedTail.get().getValue() == firstValue);
	}
}
