package com.github.nicolapiccolo.lists;

import static org.junit.Assert.assertTrue;

import java.util.Optional;

import org.junit.Test;

import com.github.nicolapiccolo.lists.singlyLinked.SinglyLinkedHeadAndTailDto;
import com.github.nicolapiccolo.lists.singlyLinked.SinglyLinkedListAddOperation;
import com.github.nicolapiccolo.lists.singlyLinked.SinglyLinkedListNode;

public class SinglyLinkedListAddOperationTest {

	@Test
	public void execute_listEmpty_createFirstItem() {
		Integer value = 10;
		Integer position = 0;
		Optional<SinglyLinkedListNode<Integer>> head = Optional.empty();
		Optional<SinglyLinkedListNode<Integer>> tail = Optional.empty();
		SinglyLinkedListAddOperation<Integer> operation = new SinglyLinkedListAddOperation<>(position, value, head, tail);
		SinglyLinkedHeadAndTailDto<Integer> headAndTailDto = operation.execute();
		Optional<SinglyLinkedListNode<Integer>> updatedHead = headAndTailDto.head();
		Optional<SinglyLinkedListNode<Integer>> updatedTail = headAndTailDto.tail();
		assertTrue(updatedHead.get().getValue() == value);
		assertTrue(updatedTail.get().getValue() == value);
	}

	@Test
	public void execute_addOneNodeAsLastToExistingList_updateTail() {
		Integer firstValue = 10;
		Integer firstPosition = 0;
		Optional<SinglyLinkedListNode<Integer>> head = Optional.empty();
		Optional<SinglyLinkedListNode<Integer>> tail = Optional.empty();
		SinglyLinkedListAddOperation<Integer> firstOperation = new SinglyLinkedListAddOperation<>(firstPosition, firstValue, head,
				tail);
		SinglyLinkedHeadAndTailDto<Integer> updatedHeadAndTailDto = firstOperation.execute();
		Optional<SinglyLinkedListNode<Integer>> updatedHead = updatedHeadAndTailDto.head();
		Optional<SinglyLinkedListNode<Integer>> updatedTail = updatedHeadAndTailDto.tail();
		Integer secondValue = 20;
		Integer secondPosition = 1;
		SinglyLinkedListAddOperation<Integer> secondOperation = new SinglyLinkedListAddOperation<>(secondPosition, secondValue,
				updatedHead, updatedTail);
		SinglyLinkedHeadAndTailDto<Integer> finalHeadAndTailDto = secondOperation.execute();
		Optional<SinglyLinkedListNode<Integer>> finalHead = finalHeadAndTailDto.head();
		Optional<SinglyLinkedListNode<Integer>> finalTail = finalHeadAndTailDto.tail();
		assertTrue(finalHead.get().getValue() == firstValue);
		assertTrue(finalTail.get().getValue() == secondValue);
	}

	@Test
	public void execute_addOneNodeInTheMiddleToExistingList_headAndTailUnchanged() {
		Integer firstValue = 10;
		Integer firstPosition = 0;
		Optional<SinglyLinkedListNode<Integer>> head = Optional.empty();
		Optional<SinglyLinkedListNode<Integer>> tail = Optional.empty();
		SinglyLinkedListAddOperation<Integer> firstOperation = new SinglyLinkedListAddOperation<>(firstPosition, firstValue, head,
				tail);
		SinglyLinkedHeadAndTailDto<Integer> firstUpdateOfHeadAndTailDto = firstOperation.execute();
		Optional<SinglyLinkedListNode<Integer>> firstUpdateHead = firstUpdateOfHeadAndTailDto.head();
		Optional<SinglyLinkedListNode<Integer>> firstUpdateTail = firstUpdateOfHeadAndTailDto.tail();
		Integer secondValue = 20;
		Integer secondPosition = 1;
		SinglyLinkedListAddOperation<Integer> secondOperation = new SinglyLinkedListAddOperation<>(secondPosition, secondValue,
				firstUpdateHead, firstUpdateTail);
		SinglyLinkedHeadAndTailDto<Integer> secondUpdateOfHeadAndTailDto = secondOperation.execute();
		Optional<SinglyLinkedListNode<Integer>> secondUpdateHead = secondUpdateOfHeadAndTailDto.head();
		Optional<SinglyLinkedListNode<Integer>> secondUpdateTail = secondUpdateOfHeadAndTailDto.tail();
		Integer thirdValue = 30;
		Integer thirdPosition = 1;
		SinglyLinkedListAddOperation<Integer> thirdOperation = new SinglyLinkedListAddOperation<>(thirdPosition, thirdValue,
				secondUpdateHead, secondUpdateTail);
		SinglyLinkedHeadAndTailDto<Integer> finalUpdateOfHeadAndTailDto = thirdOperation.execute();
		Optional<SinglyLinkedListNode<Integer>> finalUpdateHead = finalUpdateOfHeadAndTailDto.head();
		Optional<SinglyLinkedListNode<Integer>> finalUpdateTail = finalUpdateOfHeadAndTailDto.tail();
		assertTrue(finalUpdateHead.get().getValue() == firstValue);
		assertTrue(finalUpdateTail.get().getValue() == secondValue);
	}
}
