package com.github.nicolapiccolo.trees.binarysearch;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class BinarySearchTreeNodeTest {
	@Test
	public void setPayload() {
		Integer key = 3;
		String value = "value";
		BinarySearchTreeNode<String> node = new BinarySearchTreeNode<String>(key, value);
		assertTrue(value == node.getPayload());
	}
	@Test
	public void getPayload() {
		Integer key = 3;
		String value = "value";
		BinarySearchTreeNode<String> node = new BinarySearchTreeNode<String>(key, value);
		assertTrue(value == node.getPayload());
	}	
	@Test
	public void isRoot_justRoot_true() {
		Integer key = 3;
		String value = "value";
		BinarySearchTreeNode<String> node = new BinarySearchTreeNode<String>(key, value);
		assertTrue(node.isRoot());
	}
	@Test
	public void isRoot_leafNode_false() {
		Integer key = 3;
		String value = "value";
		BinarySearchTreeNode<String> root = new BinarySearchTreeNode<String>(key, value);
		Integer leafKey = 6;
		String leaveValue = "leaveValue";
		BinarySearchTreeNode<String> leftChild = new BinarySearchTreeNode<String>(leafKey, leaveValue);
		root.setLeftChild(leftChild);
		assertFalse(leftChild.isRoot());
	}
	@Test
	public void getParent() {
		Integer key = 3;
		String value = "value";
		BinarySearchTreeNode<String> root = new BinarySearchTreeNode<String>(key, value);
		Integer leafKey = 6;
		String leaveValue = "leaveValue";
		BinarySearchTreeNode<String> leftChild = new BinarySearchTreeNode<String>(leafKey, leaveValue);
		root.setLeftChild(leftChild);
		assertTrue(root == leftChild.getParent());
	}
	@Test
	public void getLeftChild() {
		Integer key = 3;
		String value = "value";
		BinarySearchTreeNode<String> root = new BinarySearchTreeNode<String>(key, value);
		Integer leafKey = 6;
		String leaveValue = "leaveValue";
		BinarySearchTreeNode<String> leftChild = new BinarySearchTreeNode<String>(leafKey, leaveValue);
		root.setLeftChild(leftChild);
		assertTrue(leftChild == root.getLeftChild());
	}
	@Test
	public void hasLeftChild() {
		Integer key = 3;
		String value = "value";
		BinarySearchTreeNode<String> root = new BinarySearchTreeNode<String>(key, value);
		Integer leafKey = 6;
		String leaveValue = "leaveValue";
		BinarySearchTreeNode<String> leftChild = new BinarySearchTreeNode<String>(leafKey, leaveValue);
		root.setLeftChild(leftChild);
		assertTrue(root.hasLeftChild());
	}	
	@Test
	public void resetLeftChild() {
		Integer key = 3;
		String value = "value";
		BinarySearchTreeNode<String> root = new BinarySearchTreeNode<String>(key, value);
		Integer leafKey = 6;
		String leaveValue = "leaveValue";
		BinarySearchTreeNode<String> leftChild = new BinarySearchTreeNode<String>(leafKey, leaveValue);
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
		BinarySearchTreeNode<String> root = new BinarySearchTreeNode<String>(key, value);
		Integer leafKey = 6;
		String leaveValue = "leaveValue";
		BinarySearchTreeNode<String> rightChild = new BinarySearchTreeNode<String>(leafKey, leaveValue);
		root.setRightChild(rightChild);
		assertTrue(rightChild == root.getRightChild());
	}
	@Test
	public void hasRightChild() {
		Integer key = 3;
		String value = "value";
		BinarySearchTreeNode<String> root = new BinarySearchTreeNode<String>(key, value);
		Integer leafKey = 6;
		String leaveValue = "leaveValue";
		BinarySearchTreeNode<String> rightChild = new BinarySearchTreeNode<String>(leafKey, leaveValue);
		assertFalse(root.hasRightChild());
		root.setRightChild(rightChild);
		assertTrue(root.hasRightChild());
	}	
	@Test
	public void resetRightChild() {
		Integer key = 3;
		String value = "value";
		BinarySearchTreeNode<String> root = new BinarySearchTreeNode<String>(key, value);
		Integer leafKey = 6;
		String leaveValue = "leaveValue";
		BinarySearchTreeNode<String> rightChild = new BinarySearchTreeNode<String>(leafKey, leaveValue);
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
		BinarySearchTreeNode<String> root = new BinarySearchTreeNode<String>(key, value);
		Integer leafKey = 6;
		String leaveValue = "leaveValue";
		BinarySearchTreeNode<String> leftChild = new BinarySearchTreeNode<String>(leafKey, leaveValue);
		root.setLeftChild(leftChild);
		assertTrue(root.hasChildren());
	}
	@Test
	public void hasChildren_rightChild() {
		Integer key = 3;
		String value = "value";
		BinarySearchTreeNode<String> root = new BinarySearchTreeNode<String>(key, value);
		Integer leafKey = 6;
		String leaveValue = "leaveValue";
		BinarySearchTreeNode<String> rightChild = new BinarySearchTreeNode<String>(leafKey, leaveValue);
		assertFalse(root.hasRightChild());
		root.setRightChild(rightChild);
		assertTrue(root.hasChildren());
	}
	@Test
	public void isDeleted() {
		Integer key = 3;
		String value = "value";
		BinarySearchTreeNode<String> root = new BinarySearchTreeNode<String>(key, value);
		assertFalse(root.isDeleted());
		root.setIsDeleted();
		assertTrue(root.isDeleted());
		root.resetIsDeleted();
		assertFalse(root.isDeleted());
	}
}
