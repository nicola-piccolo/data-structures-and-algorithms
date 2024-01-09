package com.github.nicolapiccolo.trees.binarysearch;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class BinarySearchTreeNodeTest {
	@Test
	public void setPayload() {
		Integer key = 3;
		String value = "value";
		BinarySearchTreeNodePayload<String> payload = new BinarySearchTreeNodePayload<String>(key, value);
		BinarySearchTreeNode<String> node = new BinarySearchTreeNode<String>(payload);
		assertTrue(payload == node.getPayload());
		Integer newKey = 6;
		String newValue = "newValue";
		BinarySearchTreeNodePayload<String> newPayload = new BinarySearchTreeNodePayload<String>(newKey, newValue);
		node.setPayload(newPayload);
		assertTrue(newPayload == node.getPayload());
	}
	@Test
	public void getPayload() {
		Integer key = 3;
		String value = "value";
		BinarySearchTreeNodePayload<String> payload = new BinarySearchTreeNodePayload<String>(key, value);
		BinarySearchTreeNode<String> node = new BinarySearchTreeNode<String>(payload);
		assertTrue(payload == node.getPayload());
	}	
	@Test
	public void isRoot_justRoot_true() {
		Integer key = 3;
		String value = "value";
		BinarySearchTreeNodePayload<String> payload = new BinarySearchTreeNodePayload<String>(key, value);
		BinarySearchTreeNode<String> node = new BinarySearchTreeNode<String>(payload);
		assertTrue(node.isRoot());
	}
	@Test
	public void isRoot_leafNode_false() {
		Integer key = 3;
		String value = "value";
		BinarySearchTreeNodePayload<String> payload = new BinarySearchTreeNodePayload<String>(key, value);
		BinarySearchTreeNode<String> root = new BinarySearchTreeNode<String>(payload);
		Integer leafKey = 6;
		String leaveValue = "leaveValue";
		BinarySearchTreeNodePayload<String> leafPayload = new BinarySearchTreeNodePayload<String>(leafKey, leaveValue);
		BinarySearchTreeNode<String> leftChild = new BinarySearchTreeNode<String>(leafPayload);
		root.setLeftChild(leftChild);
		assertFalse(leftChild.isRoot());
	}
	@Test
	public void getParent() {
		Integer key = 3;
		String value = "value";
		BinarySearchTreeNodePayload<String> payload = new BinarySearchTreeNodePayload<String>(key, value);
		BinarySearchTreeNode<String> root = new BinarySearchTreeNode<String>(payload);
		Integer leafKey = 6;
		String leaveValue = "leaveValue";
		BinarySearchTreeNodePayload<String> leafPayload = new BinarySearchTreeNodePayload<String>(leafKey, leaveValue);
		BinarySearchTreeNode<String> leftChild = new BinarySearchTreeNode<String>(leafPayload);
		root.setLeftChild(leftChild);
		assertTrue(root == leftChild.getParent());
	}
	@Test
	public void getLeftChild() {
		Integer key = 3;
		String value = "value";
		BinarySearchTreeNodePayload<String> payload = new BinarySearchTreeNodePayload<String>(key, value);
		BinarySearchTreeNode<String> root = new BinarySearchTreeNode<String>(payload);
		Integer leafKey = 6;
		String leaveValue = "leaveValue";
		BinarySearchTreeNodePayload<String> leafPayload = new BinarySearchTreeNodePayload<String>(leafKey, leaveValue);
		BinarySearchTreeNode<String> leftChild = new BinarySearchTreeNode<String>(leafPayload);
		root.setLeftChild(leftChild);
		assertTrue(leftChild == root.getLeftChild());
	}
	@Test
	public void hasLeftChild() {
		Integer key = 3;
		String value = "value";
		BinarySearchTreeNodePayload<String> payload = new BinarySearchTreeNodePayload<String>(key, value);
		BinarySearchTreeNode<String> root = new BinarySearchTreeNode<String>(payload);
		Integer leafKey = 6;
		String leaveValue = "leaveValue";
		BinarySearchTreeNodePayload<String> leafPayload = new BinarySearchTreeNodePayload<String>(leafKey, leaveValue);
		BinarySearchTreeNode<String> leftChild = new BinarySearchTreeNode<String>(leafPayload);
		root.setLeftChild(leftChild);
		assertTrue(root.hasLeftChild());
	}	
	@Test
	public void resetLeftChild() {
		Integer key = 3;
		String value = "value";
		BinarySearchTreeNodePayload<String> payload = new BinarySearchTreeNodePayload<String>(key, value);
		BinarySearchTreeNode<String> root = new BinarySearchTreeNode<String>(payload);
		Integer leafKey = 6;
		String leaveValue = "leaveValue";
		BinarySearchTreeNodePayload<String> leafPayload = new BinarySearchTreeNodePayload<String>(leafKey, leaveValue);
		BinarySearchTreeNode<String> leftChild = new BinarySearchTreeNode<String>(leafPayload);
		assertFalse(root.hasLeftChild());
		root.setLeftChild(leftChild);
		assertTrue(root.hasLeftChild());
		root.resetLeftChild();
		assertFalse(root.hasLeftChild());
	}
	@Test
	public void getRightChild() {
		Integer key = 3;
		String value = "value";
		BinarySearchTreeNodePayload<String> payload = new BinarySearchTreeNodePayload<String>(key, value);
		BinarySearchTreeNode<String> root = new BinarySearchTreeNode<String>(payload);
		Integer leafKey = 6;
		String leaveValue = "leaveValue";
		BinarySearchTreeNodePayload<String> leafPayload = new BinarySearchTreeNodePayload<String>(leafKey, leaveValue);
		BinarySearchTreeNode<String> rightChild = new BinarySearchTreeNode<String>(leafPayload);
		root.setRightChild(rightChild);
		assertTrue(rightChild == root.getRightChild());
	}
	@Test
	public void hasRightChild() {
		Integer key = 3;
		String value = "value";
		BinarySearchTreeNodePayload<String> payload = new BinarySearchTreeNodePayload<String>(key, value);
		BinarySearchTreeNode<String> root = new BinarySearchTreeNode<String>(payload);
		Integer leafKey = 6;
		String leaveValue = "leaveValue";
		BinarySearchTreeNodePayload<String> leafPayload = new BinarySearchTreeNodePayload<String>(leafKey, leaveValue);
		BinarySearchTreeNode<String> rightChild = new BinarySearchTreeNode<String>(leafPayload);
		assertFalse(root.hasRightChild());
		root.setRightChild(rightChild);
		assertTrue(root.hasRightChild());
	}	
	@Test
	public void resetRightChild() {
		Integer key = 3;
		String value = "value";
		BinarySearchTreeNodePayload<String> payload = new BinarySearchTreeNodePayload<String>(key, value);
		BinarySearchTreeNode<String> root = new BinarySearchTreeNode<String>(payload);
		Integer leafKey = 6;
		String leaveValue = "leaveValue";
		BinarySearchTreeNodePayload<String> leafPayload = new BinarySearchTreeNodePayload<String>(leafKey, leaveValue);
		BinarySearchTreeNode<String> rightChild = new BinarySearchTreeNode<String>(leafPayload);
		assertFalse(root.hasRightChild());
		root.setRightChild(rightChild);
		assertTrue(root.hasRightChild());
		root.resetRightChild();
		assertFalse(root.hasRightChild());
	}
	@Test
	public void hasChildren_leftChild() {
		Integer key = 3;
		String value = "value";
		BinarySearchTreeNodePayload<String> payload = new BinarySearchTreeNodePayload<String>(key, value);
		BinarySearchTreeNode<String> root = new BinarySearchTreeNode<String>(payload);
		Integer leafKey = 6;
		String leaveValue = "leaveValue";
		BinarySearchTreeNodePayload<String> leafPayload = new BinarySearchTreeNodePayload<String>(leafKey, leaveValue);
		BinarySearchTreeNode<String> leftChild = new BinarySearchTreeNode<String>(leafPayload);
		root.setLeftChild(leftChild);
		assertTrue(root.hasChildren());
	}
	@Test
	public void hasChildren_rightChild() {
		Integer key = 3;
		String value = "value";
		BinarySearchTreeNodePayload<String> payload = new BinarySearchTreeNodePayload<String>(key, value);
		BinarySearchTreeNode<String> root = new BinarySearchTreeNode<String>(payload);
		Integer leafKey = 6;
		String leaveValue = "leaveValue";
		BinarySearchTreeNodePayload<String> leafPayload = new BinarySearchTreeNodePayload<String>(leafKey, leaveValue);
		BinarySearchTreeNode<String> rightChild = new BinarySearchTreeNode<String>(leafPayload);
		assertFalse(root.hasRightChild());
		root.setRightChild(rightChild);
		assertTrue(root.hasChildren());
	}
}
