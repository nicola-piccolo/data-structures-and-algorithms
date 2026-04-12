package com.github.nicolapiccolo.lists;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Optional;

import org.junit.Test;

import com.github.nicolapiccolo.lists.singlyLinked.SinglyLinkedListIterator;
import com.github.nicolapiccolo.lists.singlyLinked.SinglyLinkedListNode;

public class SinglyLinkedListIteratorTest {

	@Test
	public void hasNext_emptyHead_returnsFalse() {
		Optional<SinglyLinkedListNode> head = Optional.empty();
		SinglyLinkedListIterator iterator = new SinglyLinkedListIterator(head);
		assertFalse(iterator.hasNext());
	}

	@Test
	public void hasNext_validHead_returnsTrue() {
		Integer value = 10;
		SinglyLinkedListNode node = new SinglyLinkedListNode(value);
		Optional<SinglyLinkedListNode> head = Optional.of(node);
		SinglyLinkedListIterator iterator = new SinglyLinkedListIterator(head);
		assertTrue(iterator.hasNext());
	}

	@Test
	public void next() {
		Integer value = 10;
		SinglyLinkedListNode node = new SinglyLinkedListNode(value);
		Optional<SinglyLinkedListNode> head = Optional.of(node);
		SinglyLinkedListIterator iterator = new SinglyLinkedListIterator(head);
		assertTrue(iterator.next() == value);
		assertFalse(iterator.hasNext());
	}
}
