package com.github.nicolapiccolo.lists;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.Test;

import com.github.nicolapiccolo.lists.singlyLinked.SinglyLinkedListIterator;
import com.github.nicolapiccolo.lists.singlyLinked.SinglyLinkedListNode;

public class SinglyLinkedListIteratorTest {

	@Test
	public void hasNext_emptyHead_returnsFalse() {
		Optional<SinglyLinkedListNode<Integer>> head = Optional.empty();
		SinglyLinkedListIterator<Integer> iterator = new SinglyLinkedListIterator<>(head);
		assertFalse(iterator.hasNext());
	}

	@Test
	public void hasNext_validHead_returnsTrue() {
		Integer value = 10;
		SinglyLinkedListNode<Integer> node = new SinglyLinkedListNode<>(value);
		Optional<SinglyLinkedListNode<Integer>> head = Optional.of(node);
		SinglyLinkedListIterator<Integer> iterator = new SinglyLinkedListIterator<>(head);
		assertTrue(iterator.hasNext());
	}

	@Test
	public void next() {
		Integer value = 10;
		SinglyLinkedListNode<Integer> node = new SinglyLinkedListNode<>(value);
		Optional<SinglyLinkedListNode<Integer>> head = Optional.of(node);
		SinglyLinkedListIterator<Integer> iterator = new SinglyLinkedListIterator<>(head);
		assertTrue(iterator.next() == value);
		assertFalse(iterator.hasNext());
	}
}
