package com.github.nicolapiccolo.trees.binarysearch;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Optional;

import org.junit.Test;

public class BinarySearchCoreTreeTest {

	@Test
	public void size_emptyTree_zero() {
		BinarySearchCoreTree<String> tree = new BinarySearchCoreTree<String>();
		assertEquals(0, tree.size());
	}

	@Test
	public void iterateWith_emptyTree() {
		BinarySearchCoreTree<String> tree = new BinarySearchCoreTree<String>();
		assertEquals(0, tree.size());
		BinarySearchTreeInOrderIterator<String> iterator = new BinarySearchTreeInOrderIterator<String>();
		tree.iterateWith(iterator);
		assertFalse(iterator.hasNext());
	}

	@Test
	public void put_root() {
		BinarySearchCoreTree<String> tree = new BinarySearchCoreTree<String>();
		Integer key = 3;
		String value = "value";
		tree.put(key, value);
		assertEquals(1, tree.size());
		BinarySearchTreeInOrderIterator<String> iterator = new BinarySearchTreeInOrderIterator<String>();
		tree.iterateWith(iterator);
		String rootPayload = iterator.next();
		assertEquals(value, rootPayload);
	}
	
	@Test
	public void put_rootWithCounter() {
		int deletedNodesPercentage = 10;
		int deletedNodesCountThreshold = 10;
		BinarySearchCoreTree<String> tree = new BinarySearchCoreTree<String>(deletedNodesPercentage, deletedNodesCountThreshold);
		Integer key = 3;
		String value = "value";
		tree.put(key, value);
		assertEquals(1, tree.size());
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
		BinarySearchTreeInOrderIterator<String> iterator = new BinarySearchTreeInOrderIterator<String>();
		tree.iterateWith(iterator);
		String rootPayload = iterator.next();
		assertEquals(anotherValue, rootPayload);
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
		BinarySearchTreeInOrderIterator<String> iterator = new BinarySearchTreeInOrderIterator<String>();
		tree.iterateWith(iterator);
		String nodePayload = iterator.next();
		assertEquals(leftValue, nodePayload);
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
		BinarySearchTreeInOrderIterator<String> iterator = new BinarySearchTreeInOrderIterator<String>();
		tree.iterateWith(iterator);
		String nodePayload = iterator.next();
		assertEquals(value, nodePayload);
		String rightNodePayload = iterator.next();
		assertEquals(rightValue, rightNodePayload);
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
		BinarySearchTreeInOrderIterator<String> iterator = new BinarySearchTreeInOrderIterator<String>();
		tree.iterateWith(iterator);
		String leftNodePayload = iterator.next();
		assertEquals(leftValue, leftNodePayload);		
		String nodePayload = iterator.next();
		assertEquals(value, nodePayload);
		String rightPayload = iterator.next();
		assertEquals(rightValue, rightPayload);
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
		BinarySearchTreeInOrderIterator<String> iterator = new BinarySearchTreeInOrderIterator<String>();
		tree.iterateWith(iterator);
		String leafPayload = iterator.next();
		assertEquals(leafValue, leafPayload);		
	}
	
	@Test
	public void put_withPostProcessor() {
		BinarySearchCoreTree<String> tree = new BinarySearchCoreTree<String>();
		MockBinarySearchTreePutPostProcessor postProcessor = new MockBinarySearchTreePutPostProcessor();
		tree.setPutPostProcessor(postProcessor);
		Integer key = 3;
		String value = "value";
		tree.put(key, value);
		assertEquals(1, tree.size());
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
		BinarySearchTreeInOrderIterator<String> iterator = new BinarySearchTreeInOrderIterator<String>();
		tree.iterateWith(iterator);
		String payload = iterator.next();
		assertEquals(value, payload);
		String rightPayload = iterator.next();
		assertEquals(rightValue, rightPayload);
	}
	
	@Test
	public void delete_alreadyDeletedLeftChild() {
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
		tree.delete(leftKey);
		BinarySearchTreeInOrderIterator<String> iterator = new BinarySearchTreeInOrderIterator<String>();
		tree.iterateWith(iterator);
		String payload = iterator.next();
		assertEquals(value, payload);
		String rightPayload = iterator.next();
		assertEquals(rightValue, rightPayload);
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
		BinarySearchTreeInOrderIterator<String> iterator = new BinarySearchTreeInOrderIterator<String>();
		tree.iterateWith(iterator);
		String leftPayload = iterator.next();
		assertEquals(leftValue, leftPayload);
		String payload = iterator.next();
		assertEquals(value, payload);
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
		BinarySearchTreeInOrderIterator<String> iterator = new BinarySearchTreeInOrderIterator<String>();
		tree.iterateWith(iterator);
		String payload = iterator.next();
		assertEquals(leftValue, payload);
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
		BinarySearchTreeInOrderIterator<String> iterator = new BinarySearchTreeInOrderIterator<String>();
		tree.iterateWith(iterator);
		String rightPayload = iterator.next();
		assertEquals(rightValue, rightPayload);
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
		BinarySearchTreeInOrderIterator<String> iterator = new BinarySearchTreeInOrderIterator<String>();
		tree.iterateWith(iterator);
		String leftPayload = iterator.next();
		assertEquals(leftValue, leftPayload);
		String rightPayload = iterator.next();
		assertEquals(rightValue, rightPayload);
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
		BinarySearchTreeInOrderIterator<String> iterator = new BinarySearchTreeInOrderIterator<String>();
		tree.iterateWith(iterator);
		String leftPayload = iterator.next();
		assertEquals(leftValue, leftPayload);
		String payload = iterator.next();
		assertEquals(value, payload);
		String rightPayload = iterator.next();
		assertEquals(rightValue, rightPayload);		
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
		BinarySearchTreeInOrderIterator<String> iterator = new BinarySearchTreeInOrderIterator<String>();
		tree.iterateWith(iterator);
		String leafPayload = iterator.next();
		assertEquals(leafValue, leafPayload);
		String payload = iterator.next();
		assertEquals(value, payload);
		String rightPayload = iterator.next();
		assertEquals(rightValue, rightPayload);		
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
		BinarySearchTreeInOrderIterator<String> iterator = new BinarySearchTreeInOrderIterator<String>();
		tree.iterateWith(iterator);
		String leftPayload = iterator.next();
		assertEquals(leftValue, leftPayload);
		String payload = iterator.next();
		assertEquals(value, payload);
		String leafPayload = iterator.next();
		assertEquals(leafValue, leafPayload);		
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
		BinarySearchTreeInOrderIterator<String> iterator = new BinarySearchTreeInOrderIterator<String>();
		tree.iterateWith(iterator);
		String leftPayload = iterator.next();
		assertEquals(leftValue, leftPayload);
		String payload = iterator.next();
		assertEquals(value, payload);
		String rightInternalPayload = iterator.next();
		assertEquals(rightInternalValue, rightInternalPayload);	
		String lastLeafPayload = iterator.next();
		assertEquals(lastLeafValue, lastLeafPayload);
	}
	
	@Test
	public void delete_rootWithBothChildrenBeyondThreshold_rebuild() {
		int deletedNodesPercentage = 10;
		int deletedNodesCountThreshold = 1;
		BinarySearchCoreTree<String> tree = new BinarySearchCoreTree<String>(deletedNodesPercentage, deletedNodesCountThreshold);
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
		BinarySearchTreeInOrderIterator<String> iterator = new BinarySearchTreeInOrderIterator<String>();
		tree.iterateWith(iterator);
		String leftPayload = iterator.next();
		assertEquals(leftValue, leftPayload);
		String rightPayload = iterator.next();
		assertEquals(rightValue, rightPayload);
	}
	
	@Test
	public void delete_rootWithBothChildrenBeyondThreshold_noRebuild() {
		int deletedNodesPercentage = 100;
		int deletedNodesCountThreshold = 1;
		BinarySearchCoreTree<String> tree = new BinarySearchCoreTree<String>(deletedNodesPercentage, deletedNodesCountThreshold);
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
		BinarySearchTreeInOrderIterator<String> iterator = new BinarySearchTreeInOrderIterator<String>();
		tree.iterateWith(iterator);
		String leftPayload = iterator.next();
		assertEquals(leftValue, leftPayload);
		String rightPayload = iterator.next();
		assertEquals(rightValue, rightPayload);
	}
	
	@Test
	public void delete_rootWithBothChildrenAndPostProcessor() {
		MockBinarySearchTreeDeletePostProcessor postProcessor = new MockBinarySearchTreeDeletePostProcessor();
		int deletedNodesPercentage = 10;
		int deletedNodesCountThreshold = 1;
		BinarySearchCoreTree<String> tree = new BinarySearchCoreTree<String>(deletedNodesPercentage, deletedNodesCountThreshold);
		tree.setDeletePostProcessor(postProcessor);
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
		BinarySearchTreeInOrderIterator<String> iterator = new BinarySearchTreeInOrderIterator<String>();
		tree.iterateWith(iterator);
		String leftPayload = iterator.next();
		assertEquals(leftValue, leftPayload);
		String rightPayload = iterator.next();
		assertEquals(rightValue, rightPayload);
	}
}
