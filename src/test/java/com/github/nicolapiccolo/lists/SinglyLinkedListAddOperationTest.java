package com.github.nicolapiccolo.lists;

import static org.junit.Assert.assertTrue;

import java.util.Optional;

import org.junit.Test;

import com.github.nicolapiccolo.lists.singlyLinked.SinglyLinkedHeadAndTailDto;
import com.github.nicolapiccolo.lists.singlyLinked.SinglyLinkedListAddOperation;
import com.github.nicolapiccolo.lists.singlyLinked.SinglyLinkedListNode;

public class SinglyLinkedListAddOperationTest {

	@Test
	public void getHeadAndTail() {
		Integer value = 10;
		Integer position = 0;
		Optional<SinglyLinkedListNode> head = Optional.empty();
		Optional<SinglyLinkedListNode> tail = Optional.empty();
		SinglyLinkedListAddOperation operation = new SinglyLinkedListAddOperation(position, value, head, tail);
		SinglyLinkedHeadAndTailDto headAndTailDto = operation.getHeadAndTail();
		assertTrue(headAndTailDto.head() == head);
		assertTrue(headAndTailDto.tail() == tail);
	}

	@Test
	public void execute_listEmpty_createFirstItem() {
		Integer value = 10;
		Integer position = 0;
		Optional<SinglyLinkedListNode> head = Optional.empty();
		Optional<SinglyLinkedListNode> tail = Optional.empty();
		SinglyLinkedListAddOperation operation = new SinglyLinkedListAddOperation(position, value, head, tail);
		operation.execute();
		SinglyLinkedHeadAndTailDto headAndTailDto = operation.getHeadAndTail();
		Optional<SinglyLinkedListNode> updatedHead = headAndTailDto.head();
		Optional<SinglyLinkedListNode> updatedTail = headAndTailDto.tail();
		assertTrue(updatedHead.get().getValue() == value);
		assertTrue(updatedTail.get().getValue() == value);
	}

	@Test
	public void execute_addOneNodeAsLastToExistingList_updateTail() {
		Integer firstValue = 10;
		Integer firstPosition = 0;
		Optional<SinglyLinkedListNode> head = Optional.empty();
		Optional<SinglyLinkedListNode> tail = Optional.empty();
		SinglyLinkedListAddOperation firstOperation = new SinglyLinkedListAddOperation(firstPosition, firstValue, head,
				tail);
		firstOperation.execute();
		SinglyLinkedHeadAndTailDto updatedHeadAndTailDto = firstOperation.getHeadAndTail();
		Optional<SinglyLinkedListNode> updatedHead = updatedHeadAndTailDto.head();
		Optional<SinglyLinkedListNode> updatedTail = updatedHeadAndTailDto.tail();
		Integer secondValue = 20;
		Integer secondPosition = 1;
		SinglyLinkedListAddOperation secondOperation = new SinglyLinkedListAddOperation(secondPosition, secondValue,
				updatedHead, updatedTail);
		secondOperation.execute();
		SinglyLinkedHeadAndTailDto finalHeadAndTailDto = secondOperation.getHeadAndTail();
		Optional<SinglyLinkedListNode> finalHead = finalHeadAndTailDto.head();
		Optional<SinglyLinkedListNode> finalTail = finalHeadAndTailDto.tail();
		assertTrue(finalHead.get().getValue() == firstValue);
		assertTrue(finalTail.get().getValue() == secondValue);
	}

	@Test
	public void execute_addOneNodeInTheMiddleToExistingList_headAndTailUnchanged() {
		Integer firstValue = 10;
		Integer firstPosition = 0;
		Optional<SinglyLinkedListNode> head = Optional.empty();
		Optional<SinglyLinkedListNode> tail = Optional.empty();
		SinglyLinkedListAddOperation firstOperation = new SinglyLinkedListAddOperation(firstPosition, firstValue, head,
				tail);
		firstOperation.execute();
		SinglyLinkedHeadAndTailDto firstUpdateOfHeadAndTailDto = firstOperation.getHeadAndTail();
		Optional<SinglyLinkedListNode> firstUpdateHead = firstUpdateOfHeadAndTailDto.head();
		Optional<SinglyLinkedListNode> firstUpdateTail = firstUpdateOfHeadAndTailDto.tail();
		Integer secondValue = 20;
		Integer secondPosition = 1;
		SinglyLinkedListAddOperation secondOperation = new SinglyLinkedListAddOperation(secondPosition, secondValue,
				firstUpdateHead, firstUpdateTail);
		secondOperation.execute();
		SinglyLinkedHeadAndTailDto secondUpdateOfHeadAndTailDto = secondOperation.getHeadAndTail();
		Optional<SinglyLinkedListNode> secondUpdateHead = secondUpdateOfHeadAndTailDto.head();
		Optional<SinglyLinkedListNode> secondUpdateTail = secondUpdateOfHeadAndTailDto.tail();
		Integer thirdValue = 30;
		Integer thirdPosition = 1;
		SinglyLinkedListAddOperation thirdOperation = new SinglyLinkedListAddOperation(thirdPosition, thirdValue,
				secondUpdateHead, secondUpdateTail);
		thirdOperation.execute();
		SinglyLinkedHeadAndTailDto finalUpdateOfHeadAndTailDto = thirdOperation.getHeadAndTail();
		Optional<SinglyLinkedListNode> finalUpdateHead = finalUpdateOfHeadAndTailDto.head();
		Optional<SinglyLinkedListNode> finalUpdateTail = finalUpdateOfHeadAndTailDto.tail();
		assertTrue(finalUpdateHead.get().getValue() == firstValue);
		assertTrue(finalUpdateTail.get().getValue() == secondValue);
	}
}
