package com.github.nicolapiccolo.trees.binarysearch;

import static org.junit.Assert.*;

import java.util.Iterator;
import java.util.Optional;

import org.junit.Test;

public class BinarySearchCoreTreeTest {

	@Test
	public void size_emptyTree_zero() {
		BinarySearchCoreTree<String> tree = new BinarySearchCoreTree<String>();
		assertEquals(0, tree.size());
	}

	@Test
	public void put_root() {
		BinarySearchCoreTree<String> tree = new BinarySearchCoreTree<String>();
		Integer key = 3;
		String value = "value";
		tree.put(key, value);
		assertEquals(1, tree.size());
		Iterator<BinarySearchTreeNodePayload<String>> iterator = tree.iterator();
		BinarySearchTreeNodePayload<String> rootPayload = iterator.next();
		assertEquals(key, rootPayload.getKey());
		assertEquals(value, rootPayload.getValue());
	}

	@Test
	public void put_rootAndOverwrite() {
		BinarySearchCoreTree<String> tree = new BinarySearchCoreTree<String>();
		Integer key = 3;
		String value = "value";
		tree.put(key, value);
		String anotherValue = "anotherValue";
		tree.put(key, anotherValue);
		assertEquals(1, tree.size());
		Iterator<BinarySearchTreeNodePayload<String>> iterator = tree.iterator();
		BinarySearchTreeNodePayload<String> rootPayload = iterator.next();
		assertEquals(key, rootPayload.getKey());
		assertEquals(anotherValue, rootPayload.getValue());
	}

	@Test
	public void put_rootAndLeftChild() {
		BinarySearchCoreTree<String> tree = new BinarySearchCoreTree<String>();
		Integer key = 3;
		String value = "value";
		tree.put(key, value);
		Integer leftKey = 2;
		String leftValue = "leftValue";
		tree.put(leftKey, leftValue);
		assertEquals(2, tree.size());
		Iterator<BinarySearchTreeNodePayload<String>> iterator = tree.iterator();
		BinarySearchTreeNodePayload<String> payload = iterator.next();
		assertEquals(leftKey, payload.getKey());
		assertEquals(leftValue, payload.getValue());
	}

	@Test
	public void put_rootAndRightChild() {
		BinarySearchCoreTree<String> tree = new BinarySearchCoreTree<String>();
		Integer key = 3;
		String value = "value";
		tree.put(key, value);
		Integer rightKey = 5;
		String rightValue = "rightValue";
		tree.put(rightKey, rightValue);
		assertEquals(2, tree.size());
		Iterator<BinarySearchTreeNodePayload<String>> iterator = tree.iterator();
		BinarySearchTreeNodePayload<String> payload = iterator.next();
		assertEquals(key, payload.getKey());
		assertEquals(value, payload.getValue());
		BinarySearchTreeNodePayload<String> rightPayload = iterator.next();
		assertEquals(rightKey, rightPayload.getKey());
		assertEquals(rightValue, rightPayload.getValue());
	}
	
	@Test
	public void put_rootAndLeftAndRightChildren() {
		BinarySearchCoreTree<String> tree = new BinarySearchCoreTree<String>();
		Integer key = 3;
		String value = "value";
		tree.put(key, value);
		Integer leftKey = 2;
		String leftValue = "leftValue";
		tree.put(leftKey, leftValue);
		Integer rightKey = 5;
		String rightValue = "rightValue";
		tree.put(rightKey, rightValue);
		assertEquals(3, tree.size());
		Iterator<BinarySearchTreeNodePayload<String>> iterator = tree.iterator();
		BinarySearchTreeNodePayload<String> leftPayload = iterator.next();
		assertEquals(leftKey, leftPayload.getKey());
		assertEquals(leftValue, leftPayload.getValue());		
		BinarySearchTreeNodePayload<String> payload = iterator.next();
		assertEquals(key, payload.getKey());
		assertEquals(value, payload.getValue());
		BinarySearchTreeNodePayload<String> rightPayload = iterator.next();
		assertEquals(rightKey, rightPayload.getKey());
		assertEquals(rightValue, rightPayload.getValue());
	}
	
	@Test
	public void put_rootAndTwoMoreLevels() {
		BinarySearchCoreTree<String> tree = new BinarySearchCoreTree<String>();
		Integer key = 3;
		String value = "value";
		tree.put(key, value);
		Integer leftKey = 2;
		String leftValue = "leftValue";
		tree.put(leftKey, leftValue);
		Integer rightKey = 5;
		String rightValue = "rightValue";
		tree.put(rightKey, rightValue);
		Integer leafKey = 1;
		String leafValue = "leafValue";
		tree.put(leafKey, leafValue);
		assertEquals(4, tree.size());
		Iterator<BinarySearchTreeNodePayload<String>> iterator = tree.iterator();
		BinarySearchTreeNodePayload<String> leafPayload = iterator.next();
		assertEquals(leafKey, leafPayload.getKey());
		assertEquals(leafValue, leafPayload.getValue());		
	}
	
	@Test
	public void get_emptyTree_empty() {
		BinarySearchCoreTree<String> tree = new BinarySearchCoreTree<String>();
		Optional<String> result = tree.get(3);
		assertTrue(result.isEmpty());
	}
	
	@Test
	public void get_validTreeWrongKey() {
		BinarySearchCoreTree<String> tree = new BinarySearchCoreTree<String>();
		Integer key = 3;
		String value = "value";
		tree.put(key, value);
		Integer leftKey = 2;
		String leftValue = "leftValue";
		tree.put(leftKey, leftValue);
		Integer rightKey = 5;
		String rightValue = "rightValue";
		tree.put(rightKey, rightValue);
		Optional<String> result = tree.get(1000);
		assertTrue(result.isEmpty());
	}
	
	@Test
	public void get_validTreeValidKey() {
		BinarySearchCoreTree<String> tree = new BinarySearchCoreTree<String>();
		Integer key = 3;
		String value = "value";
		tree.put(key, value);
		Integer leftKey = 2;
		String leftValue = "leftValue";
		tree.put(leftKey, leftValue);
		Integer rightKey = 5;
		String rightValue = "rightValue";
		tree.put(rightKey, rightValue);
		Optional<String> result = tree.get(rightKey);
		assertEquals(rightValue, result.get());
	}
	
	@Test
	public void get_rootKey() {
		BinarySearchCoreTree<String> tree = new BinarySearchCoreTree<String>();
		Integer key = 3;
		String value = "value";
		tree.put(key, value);
		Integer leftKey = 2;
		String leftValue = "leftValue";
		tree.put(leftKey, leftValue);
		Integer rightKey = 5;
		String rightValue = "rightValue";
		tree.put(rightKey, rightValue);
		Optional<String> result = tree.get(key);
		assertEquals(value, result.get());
	}
	
	@Test
	public void delete_emptyTree() {
		BinarySearchCoreTree<String> tree = new BinarySearchCoreTree<String>();
		tree.delete(3);
		assertEquals(0, tree.size());
	}

	@Test
	public void delete_root() {
		BinarySearchCoreTree<String> tree = new BinarySearchCoreTree<String>();
		Integer key = 3;
		String value = "value";
		tree.put(key, value);
		assertEquals(1, tree.size());
		tree.delete(key);
		assertEquals(0, tree.size());
	}
	
	@Test
	public void delete_wrongKey() {
		BinarySearchCoreTree<String> tree = new BinarySearchCoreTree<String>();
		Integer key = 3;
		String value = "value";
		tree.put(key, value);
		assertEquals(1, tree.size());
		tree.delete(3000);
		assertEquals(1, tree.size());
	}
	
	@Test
	public void delete_leftChild() {
		BinarySearchCoreTree<String> tree = new BinarySearchCoreTree<String>();
		Integer key = 3;
		String value = "value";
		tree.put(key, value);
		Integer leftKey = 2;
		String leftValue = "leftValue";
		tree.put(leftKey, leftValue);
		Integer rightKey = 5;
		String rightValue = "rightValue";
		tree.put(rightKey, rightValue);
		tree.delete(leftKey);
		Iterator<BinarySearchTreeNodePayload<String>> iterator = tree.iterator();
		BinarySearchTreeNodePayload<String> payload = iterator.next();
		assertEquals(key, payload.getKey());
		assertEquals(value, payload.getValue());
		BinarySearchTreeNodePayload<String> rightPayload = iterator.next();
		assertEquals(rightKey, rightPayload.getKey());
		assertEquals(rightValue, rightPayload.getValue());
	}

	@Test
	public void delete_rightChild() {
		BinarySearchCoreTree<String> tree = new BinarySearchCoreTree<String>();
		Integer key = 3;
		String value = "value";
		tree.put(key, value);
		Integer leftKey = 2;
		String leftValue = "leftValue";
		tree.put(leftKey, leftValue);
		Integer rightKey = 5;
		String rightValue = "rightValue";
		tree.put(rightKey, rightValue);
		tree.delete(rightKey);
		Iterator<BinarySearchTreeNodePayload<String>> iterator = tree.iterator();
		BinarySearchTreeNodePayload<String> leftPayload = iterator.next();
		assertEquals(leftKey, leftPayload.getKey());
		assertEquals(leftValue, leftPayload.getValue());		
		BinarySearchTreeNodePayload<String> payload = iterator.next();
		assertEquals(key, payload.getKey());
		assertEquals(value, payload.getValue());
	}

	@Test
	public void delete_rootWithLeftChild() {
		BinarySearchCoreTree<String> tree = new BinarySearchCoreTree<String>();
		Integer key = 3;
		String value = "value";
		tree.put(key, value);
		Integer leftKey = 2;
		String leftValue = "leftValue";
		tree.put(leftKey, leftValue);
		tree.delete(key);
		assertEquals(1, tree.size());
		Iterator<BinarySearchTreeNodePayload<String>> iterator = tree.iterator();
		BinarySearchTreeNodePayload<String> payload = iterator.next();
		assertEquals(leftKey, payload.getKey());
		assertEquals(leftValue, payload.getValue());
	}

	@Test
	public void delete_rootWithRightChild() {
		BinarySearchCoreTree<String> tree = new BinarySearchCoreTree<String>();
		Integer key = 3;
		String value = "value";
		tree.put(key, value);
		Integer rightKey = 5;
		String rightValue = "rightValue";
		tree.put(rightKey, rightValue);
		tree.delete(key);
		Iterator<BinarySearchTreeNodePayload<String>> iterator = tree.iterator();
		BinarySearchTreeNodePayload<String> rightPayload = iterator.next();
		assertEquals(rightKey, rightPayload.getKey());
		assertEquals(rightValue, rightPayload.getValue());
	}
	
	@Test
	public void delete_rootWithBothChildren() {
		BinarySearchCoreTree<String> tree = new BinarySearchCoreTree<String>();
		Integer key = 3;
		String value = "value";
		tree.put(key, value);
		Integer leftKey = 2;
		String leftValue = "leftValue";
		tree.put(leftKey, leftValue);
		Integer rightKey = 5;
		String rightValue = "rightValue";
		tree.put(rightKey, rightValue);		
		tree.delete(key);
		assertEquals(2, tree.size());
		Iterator<BinarySearchTreeNodePayload<String>> iterator = tree.iterator();
		BinarySearchTreeNodePayload<String> leftPayload = iterator.next();
		assertEquals(leftKey, leftPayload.getKey());
		assertEquals(leftValue, leftPayload.getValue());
		BinarySearchTreeNodePayload<String> rightPayload = iterator.next();
		assertEquals(rightKey, rightPayload.getKey());
		assertEquals(rightValue, rightPayload.getValue());
	}
	
	@Test
	public void delete_leafFromRootAndTwoMoreLevels() {
		BinarySearchCoreTree<String> tree = new BinarySearchCoreTree<String>();
		Integer key = 3;
		String value = "value";
		tree.put(key, value);
		Integer leftKey = 2;
		String leftValue = "leftValue";
		tree.put(leftKey, leftValue);
		Integer rightKey = 5;
		String rightValue = "rightValue";
		tree.put(rightKey, rightValue);
		Integer leafKey = 1;
		String leafValue = "leafValue";
		tree.put(leafKey, leafValue);
		tree.delete(leafKey);
		assertEquals(3, tree.size());
		Iterator<BinarySearchTreeNodePayload<String>> iterator = tree.iterator();
		BinarySearchTreeNodePayload<String> leftPayload = iterator.next();
		assertEquals(leftKey, leftPayload.getKey());
		assertEquals(leftValue, leftPayload.getValue());		
		BinarySearchTreeNodePayload<String> payload = iterator.next();
		assertEquals(key, payload.getKey());
		assertEquals(value, payload.getValue());
		BinarySearchTreeNodePayload<String> rightPayload = iterator.next();
		assertEquals(rightKey, rightPayload.getKey());
		assertEquals(rightValue, rightPayload.getValue());		
	}

	@Test
	public void delete_leftChildFromRootAndTwoMoreLevels() {
		BinarySearchCoreTree<String> tree = new BinarySearchCoreTree<String>();
		Integer key = 3;
		String value = "value";
		tree.put(key, value);
		Integer leftKey = 2;
		String leftValue = "leftValue";
		tree.put(leftKey, leftValue);
		Integer rightKey = 5;
		String rightValue = "rightValue";
		tree.put(rightKey, rightValue);
		Integer leafKey = 1;
		String leafValue = "leafValue";
		tree.put(leafKey, leafValue);
		tree.delete(leftKey);
		assertEquals(3, tree.size());
		Iterator<BinarySearchTreeNodePayload<String>> iterator = tree.iterator();
		BinarySearchTreeNodePayload<String> leafPayload = iterator.next();
		assertEquals(leafKey, leafPayload.getKey());
		assertEquals(leafValue, leafPayload.getValue());		
		BinarySearchTreeNodePayload<String> payload = iterator.next();
		assertEquals(key, payload.getKey());
		assertEquals(value, payload.getValue());
		BinarySearchTreeNodePayload<String> rightPayload = iterator.next();
		assertEquals(rightKey, rightPayload.getKey());
		assertEquals(rightValue, rightPayload.getValue());		
	}

	@Test
	public void delete_rightChildFromRootAndTwoMoreLevels() {
		BinarySearchCoreTree<String> tree = new BinarySearchCoreTree<String>();
		Integer key = 3;
		String value = "value";
		tree.put(key, value);
		Integer leftKey = 2;
		String leftValue = "leftValue";
		tree.put(leftKey, leftValue);
		Integer rightKey = 5;
		String rightValue = "rightValue";
		tree.put(rightKey, rightValue);
		Integer leafKey = 10;
		String leafValue = "leafValue";
		tree.put(leafKey, leafValue);
		tree.delete(rightKey);
		assertEquals(3, tree.size());
		Iterator<BinarySearchTreeNodePayload<String>> iterator = tree.iterator();
		BinarySearchTreeNodePayload<String> leftPayload = iterator.next();
		assertEquals(leftKey, leftPayload.getKey());
		assertEquals(leftValue, leftPayload.getValue());		
		BinarySearchTreeNodePayload<String> payload = iterator.next();
		assertEquals(key, payload.getKey());
		assertEquals(value, payload.getValue());
		BinarySearchTreeNodePayload<String> leafPayload = iterator.next();
		assertEquals(leafKey, leafPayload.getKey());
		assertEquals(leafValue, leafPayload.getValue());		
	}

	@Test
	public void delete_internalNodeFromRootAndThreeMoreLevels() {
		BinarySearchCoreTree<String> tree = new BinarySearchCoreTree<String>();
		Integer key = 44;
		String value = "value";
		tree.put(key, value);
		Integer leftKey = 17;
		String leftValue = "leftValue";
		tree.put(leftKey, leftValue);
		Integer rightKey = 88;
		String rightValue = "rightValue";
		tree.put(rightKey, rightValue);
		Integer rightInternalKey = 65;
		String rightInternalValue = "rightInternalValue";
		tree.put(rightInternalKey, rightInternalValue);
		Integer lastLeafKey = 82;
		String lastLeafValue = "lastLeafValue";
		tree.put(lastLeafKey, lastLeafValue);
		tree.delete(rightKey);
		assertEquals(4, tree.size());
		Iterator<BinarySearchTreeNodePayload<String>> iterator = tree.iterator();
		BinarySearchTreeNodePayload<String> leftPayload = iterator.next();
		assertEquals(leftKey, leftPayload.getKey());
		assertEquals(leftValue, leftPayload.getValue());		
		BinarySearchTreeNodePayload<String> payload = iterator.next();
		assertEquals(key, payload.getKey());
		assertEquals(value, payload.getValue());
		BinarySearchTreeNodePayload<String> rightInternalPayload = iterator.next();
		assertEquals(rightInternalKey, rightInternalPayload.getKey());
		assertEquals(rightInternalValue, rightInternalPayload.getValue());		
		BinarySearchTreeNodePayload<String> lastLeafPayload = iterator.next();
		assertEquals(lastLeafKey, lastLeafPayload.getKey());
		assertEquals(lastLeafValue, lastLeafPayload.getValue());
	}

}
