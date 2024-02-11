package com.github.nicolapiccolo.trees.binarysearch;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.junit.Test;

public class BinarySearchDeleteOperatorTest {

	@Test
	public void deleteRoot_rootWithLeftChild() {
		Integer key = 3;
		String value = "value";		
		BinarySearchTreeNodePayload<String> rootPayload = new BinarySearchTreeNodePayload<String>(key, value);
		BinarySearchTreeNode<String> rootNode = new BinarySearchTreeNode<String>(rootPayload);
		Integer leftKey = 2;
		String leftValue = "leftValue";
		BinarySearchTreeNodePayload<String> leftChildPayload = new BinarySearchTreeNodePayload<String>(leftKey, leftValue);
		BinarySearchTreeNode<String> leftChild = new BinarySearchTreeNode<String>(leftChildPayload);
		rootNode.setLeftChild(leftChild);
		BinarySearchDeleteOperator<String> deleteOperator = new BinarySearchDeleteOperator<String>();
		BinarySearchTreeNode<String> newRootNode = deleteOperator.deleteRoot(rootNode);
		BinarySearchTreeNodePayload<String> payload = newRootNode.getPayload();
		assertEquals(leftKey, payload.getKey());
		assertEquals(leftValue, payload.getValue());
	}

	@Test
	public void delete_rootWithRightChild() {
		Integer key = 3;
		String value = "value";		
		BinarySearchTreeNodePayload<String> rootPayload = new BinarySearchTreeNodePayload<String>(key, value);
		BinarySearchTreeNode<String> rootNode = new BinarySearchTreeNode<String>(rootPayload);
		Integer rightKey = 5;
		String rightValue = "rightValue";
		BinarySearchTreeNodePayload<String> rightChildPayload = new BinarySearchTreeNodePayload<String>(rightKey, rightValue);
		BinarySearchTreeNode<String> rightChild = new BinarySearchTreeNode<String>(rightChildPayload);
		rootNode.setRightChild(rightChild);
		BinarySearchDeleteOperator<String> deleteOperator = new BinarySearchDeleteOperator<String>();
		BinarySearchTreeNode<String> newRootNode = deleteOperator.deleteRoot(rootNode);
		BinarySearchTreeNodePayload<String> payload = newRootNode.getPayload();
		assertEquals(rightKey, payload.getKey());
		assertEquals(rightValue, payload.getValue());
	}
	
	@Test
	public void delete_rootWithBothChildren() {
		Integer key = 3;
		String value = "value";
		BinarySearchTreeNodePayload<String> rootPayload = new BinarySearchTreeNodePayload<String>(key, value);
		BinarySearchTreeNode<String> rootNode = new BinarySearchTreeNode<String>(rootPayload);
		Integer leftKey = 2;
		String leftValue = "leftValue";
		BinarySearchTreeNodePayload<String> leftChildPayload = new BinarySearchTreeNodePayload<String>(leftKey, leftValue);
		BinarySearchTreeNode<String> leftChild = new BinarySearchTreeNode<String>(leftChildPayload);
		rootNode.setLeftChild(leftChild);
		Integer rightKey = 5;
		String rightValue = "rightValue";
		BinarySearchTreeNodePayload<String> rightChildPayload = new BinarySearchTreeNodePayload<String>(rightKey, rightValue);
		BinarySearchTreeNode<String> rightChild = new BinarySearchTreeNode<String>(rightChildPayload);
		rootNode.setRightChild(rightChild);
		BinarySearchDeleteOperator<String> deleteOperator = new BinarySearchDeleteOperator<String>();
		BinarySearchTreeNode<String> newRootNode = deleteOperator.deleteRoot(rootNode);
		BinarySearchTreeNodePayload<String> payload = newRootNode.getPayload();
		assertEquals(leftKey, payload.getKey());
		assertEquals(leftValue, payload.getValue());
	}
	
	@Test
	public void delete_leafFromRootAndTwoMoreLevels() {
		Integer key = 3;
		String value = "value";
		BinarySearchTreeNodePayload<String> rootPayload = new BinarySearchTreeNodePayload<String>(key, value);
		BinarySearchTreeNode<String> rootNode = new BinarySearchTreeNode<String>(rootPayload);
		Integer leftKey = 2;
		String leftValue = "leftValue";
		BinarySearchTreeNodePayload<String> leftChildPayload = new BinarySearchTreeNodePayload<String>(leftKey, leftValue);
		BinarySearchTreeNode<String> leftChild = new BinarySearchTreeNode<String>(leftChildPayload);
		rootNode.setLeftChild(leftChild);
		Integer rightKey = 5;
		String rightValue = "rightValue";
		BinarySearchTreeNodePayload<String> rightChildPayload = new BinarySearchTreeNodePayload<String>(rightKey, rightValue);
		BinarySearchTreeNode<String> rightChild = new BinarySearchTreeNode<String>(rightChildPayload);
		rootNode.setRightChild(rightChild);
		Integer leafKey = 1;
		String leafValue = "leafValue";
		BinarySearchTreeNodePayload<String> leafPayload = new BinarySearchTreeNodePayload<String>(leafKey, leafValue);
		BinarySearchTreeNode<String> leaf = new BinarySearchTreeNode<String>(leafPayload);
		leftChild.setLeftChild(leaf);
		BinarySearchDeleteOperator<String> deleteOperator = new BinarySearchDeleteOperator<String>();
		deleteOperator.delete(leaf);
		assertFalse(leftChild.hasLeftChild());		
	}

	@Test
	public void delete_leftChildFromRootAndTwoMoreLevels() {
		Integer key = 3;
		String value = "value";
		BinarySearchTreeNodePayload<String> rootPayload = new BinarySearchTreeNodePayload<String>(key, value);
		BinarySearchTreeNode<String> rootNode = new BinarySearchTreeNode<String>(rootPayload);
		Integer leftKey = 2;
		String leftValue = "leftValue";
		BinarySearchTreeNodePayload<String> leftChildPayload = new BinarySearchTreeNodePayload<String>(leftKey, leftValue);
		BinarySearchTreeNode<String> leftChild = new BinarySearchTreeNode<String>(leftChildPayload);
		rootNode.setLeftChild(leftChild);
		Integer rightKey = 5;
		String rightValue = "rightValue";
		BinarySearchTreeNodePayload<String> rightChildPayload = new BinarySearchTreeNodePayload<String>(rightKey, rightValue);
		BinarySearchTreeNode<String> rightChild = new BinarySearchTreeNode<String>(rightChildPayload);
		rootNode.setRightChild(rightChild);
		Integer leafKey = 1;
		String leafValue = "leafValue";
		BinarySearchTreeNodePayload<String> leafPayload = new BinarySearchTreeNodePayload<String>(leafKey, leafValue);
		BinarySearchTreeNode<String> leaf = new BinarySearchTreeNode<String>(leafPayload);
		leftChild.setLeftChild(leaf);
		BinarySearchDeleteOperator<String> deleteOperator = new BinarySearchDeleteOperator<String>();
		deleteOperator.delete(leftChild);
		BinarySearchTreeNode<String> newLeftChild = rootNode.getLeftChild();
		BinarySearchTreeNodePayload<String> newLeftPayload = newLeftChild.getPayload();
		assertEquals(leafKey, newLeftPayload.getKey());
		assertEquals(leafValue, newLeftPayload.getValue());		
	}

	@Test
	public void delete_rightChildFromRootAndTwoMoreLevels() {
		Integer key = 3;
		String value = "value";
		BinarySearchTreeNodePayload<String> rootPayload = new BinarySearchTreeNodePayload<String>(key, value);
		BinarySearchTreeNode<String> rootNode = new BinarySearchTreeNode<String>(rootPayload);
		Integer leftKey = 2;
		String leftValue = "leftValue";
		BinarySearchTreeNodePayload<String> leftChildPayload = new BinarySearchTreeNodePayload<String>(leftKey, leftValue);
		BinarySearchTreeNode<String> leftChild = new BinarySearchTreeNode<String>(leftChildPayload);
		rootNode.setLeftChild(leftChild);
		Integer rightKey = 5;
		String rightValue = "rightValue";
		BinarySearchTreeNodePayload<String> rightChildPayload = new BinarySearchTreeNodePayload<String>(rightKey, rightValue);
		BinarySearchTreeNode<String> rightChild = new BinarySearchTreeNode<String>(rightChildPayload);
		rootNode.setRightChild(rightChild);
		Integer leafKey = 10;
		String leafValue = "leafValue";
		BinarySearchTreeNodePayload<String> leafPayload = new BinarySearchTreeNodePayload<String>(leafKey, leafValue);
		BinarySearchTreeNode<String> leaf = new BinarySearchTreeNode<String>(leafPayload);
		rightChild.setRightChild(leaf);
		BinarySearchDeleteOperator<String> deleteOperator = new BinarySearchDeleteOperator<String>();
		deleteOperator.delete(rightChild);
		BinarySearchTreeNode<String> newRightChild = rootNode.getRightChild();
		BinarySearchTreeNodePayload<String> newRightPayload = newRightChild.getPayload();
		assertEquals(leafKey, newRightPayload.getKey());
		assertEquals(leafValue, newRightPayload.getValue());		
	}

	@Test
	public void delete_internalNodeFromRootAndThreeMoreLevels() {
		Integer key = 44;
		String value = "value";
		BinarySearchTreeNodePayload<String> rootPayload = new BinarySearchTreeNodePayload<String>(key, value);
		BinarySearchTreeNode<String> rootNode = new BinarySearchTreeNode<String>(rootPayload);
		Integer leftKey = 17;
		String leftValue = "leftValue";
		BinarySearchTreeNodePayload<String> leftChildPayload = new BinarySearchTreeNodePayload<String>(leftKey, leftValue);
		BinarySearchTreeNode<String> leftChild = new BinarySearchTreeNode<String>(leftChildPayload);
		rootNode.setLeftChild(leftChild);
		Integer rightKey = 88;
		String rightValue = "rightValue";
		BinarySearchTreeNodePayload<String> rightChildPayload = new BinarySearchTreeNodePayload<String>(rightKey, rightValue);
		BinarySearchTreeNode<String> rightChild = new BinarySearchTreeNode<String>(rightChildPayload);
		rootNode.setRightChild(rightChild);
		Integer leftInternalKey = 65;
		String leftInternalValue = "leftInternalValue";
		BinarySearchTreeNodePayload<String> leftInternalChildPayload = new BinarySearchTreeNodePayload<String>(leftInternalKey, leftInternalValue);
		BinarySearchTreeNode<String> leftInternalChild = new BinarySearchTreeNode<String>(leftInternalChildPayload);
		rightChild.setLeftChild(leftInternalChild);
		Integer rightInternalKey = 97;
		String rightInternalValue = "rightInternalValue";
		BinarySearchTreeNodePayload<String> rightInternalChildPayload = new BinarySearchTreeNodePayload<String>(rightInternalKey, rightInternalValue);
		BinarySearchTreeNode<String> rightInternalChild = new BinarySearchTreeNode<String>(rightInternalChildPayload);
		rightChild.setRightChild(rightInternalChild);
		Integer lastLeafKey = 82;
		String lastLeafValue = "lastLeafValue";
		BinarySearchTreeNodePayload<String> lastLeafPayload = new BinarySearchTreeNodePayload<String>(lastLeafKey, lastLeafValue);
		BinarySearchTreeNode<String> lastLeaf = new BinarySearchTreeNode<String>(lastLeafPayload);
		leftInternalChild.setRightChild(lastLeaf);
		BinarySearchDeleteOperator<String> deleteOperator = new BinarySearchDeleteOperator<String>();
		deleteOperator.delete(rightChild);
		BinarySearchTreeNode<String> newRightChild = rootNode.getRightChild();
		BinarySearchTreeNodePayload<String> newRightPayload = newRightChild.getPayload();
		assertEquals(lastLeafKey, newRightPayload.getKey());
		assertEquals(lastLeafValue, newRightPayload.getValue());		
		assertFalse(rightInternalChild.hasRightChild());
		BinarySearchTreeNode<String> newLeftInternalChild = newRightChild.getLeftChild();
		BinarySearchTreeNodePayload<String> newLeftInternalPaylaod = newLeftInternalChild.getPayload();
		assertEquals(leftInternalKey, newLeftInternalPaylaod.getKey());
		assertEquals(leftInternalValue, newLeftInternalPaylaod.getValue());
	}

	@Test
	public void delete_internalNodeFromRootAndFourMoreLevels() {
		Integer key = 44;
		String value = "value";
		BinarySearchTreeNodePayload<String> rootPayload = new BinarySearchTreeNodePayload<String>(key, value);
		BinarySearchTreeNode<String> rootNode = new BinarySearchTreeNode<String>(rootPayload);
		Integer leftKey = 17;
		String leftValue = "leftValue";
		BinarySearchTreeNodePayload<String> leftChildPayload = new BinarySearchTreeNodePayload<String>(leftKey, leftValue);
		BinarySearchTreeNode<String> leftChild = new BinarySearchTreeNode<String>(leftChildPayload);
		rootNode.setLeftChild(leftChild);
		Integer rightKey = 88;
		String rightValue = "rightValue";
		BinarySearchTreeNodePayload<String> rightChildPayload = new BinarySearchTreeNodePayload<String>(rightKey, rightValue);
		BinarySearchTreeNode<String> rightChild = new BinarySearchTreeNode<String>(rightChildPayload);
		rootNode.setRightChild(rightChild);
		Integer leftInternalKey = 65;
		String leftInternalValue = "leftInternalValue";
		BinarySearchTreeNodePayload<String> leftInternalChildPayload = new BinarySearchTreeNodePayload<String>(leftInternalKey, leftInternalValue);
		BinarySearchTreeNode<String> leftInternalChild = new BinarySearchTreeNode<String>(leftInternalChildPayload);
		rightChild.setLeftChild(leftInternalChild);
		Integer rightInternalKey = 97;
		String rightInternalValue = "rightInternalValue";
		BinarySearchTreeNodePayload<String> rightInternalChildPayload = new BinarySearchTreeNodePayload<String>(rightInternalKey, rightInternalValue);
		BinarySearchTreeNode<String> rightInternalChild = new BinarySearchTreeNode<String>(rightInternalChildPayload);
		rightChild.setRightChild(rightInternalChild);
		Integer oneLevelAboveLeafKey = 82;
		String oneLevelAboveLeafValue = "oneLevelAboveLeafValue";
		BinarySearchTreeNodePayload<String> oneLevelAboveLeafPayload = new BinarySearchTreeNodePayload<String>(oneLevelAboveLeafKey, oneLevelAboveLeafValue);
		BinarySearchTreeNode<String> oneLevelAboveLeaf = new BinarySearchTreeNode<String>(oneLevelAboveLeafPayload);
		leftInternalChild.setRightChild(oneLevelAboveLeaf);
		Integer leafKey = 76;
		String leafValue = "leafValue";
		BinarySearchTreeNodePayload<String> leafPayload = new BinarySearchTreeNodePayload<String>(leafKey, leafValue);
		BinarySearchTreeNode<String> leaf = new BinarySearchTreeNode<String>(leafPayload);
		oneLevelAboveLeaf.setLeftChild(leaf);
		BinarySearchDeleteOperator<String> deleteOperator = new BinarySearchDeleteOperator<String>();
		deleteOperator.delete(rightChild);
		BinarySearchTreeNode<String> newRightChild = rootNode.getRightChild();
		BinarySearchTreeNode<String> newLeftInternalChild = newRightChild.getLeftChild();
		BinarySearchTreeNodePayload<String> newLeftInternalPaylaod = newLeftInternalChild.getPayload();
		assertEquals(leftInternalKey, newLeftInternalPaylaod.getKey());
		assertEquals(leftInternalValue, newLeftInternalPaylaod.getValue());
		BinarySearchTreeNode<String> newLeaf = newLeftInternalChild.getRightChild();
		BinarySearchTreeNodePayload<String> newLeafPayload = newLeaf.getPayload();
		assertEquals(leafKey, newLeafPayload.getKey());
		assertEquals(leafValue, newLeafPayload.getValue());		
	}

}
