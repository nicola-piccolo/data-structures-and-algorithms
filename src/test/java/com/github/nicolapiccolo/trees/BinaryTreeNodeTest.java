package com.github.nicolapiccolo.trees;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class BinaryTreeNodeTest {
	@Test
	public void setValue() {
		Integer initialValue = 3;
		BinaryTreeNode parent = null;
		BinaryTreeNode node = new BinaryTreeNode(initialValue, parent);
		assertTrue(initialValue == node.getValue());
		Integer newValue = 5;
		node.setValue(newValue);
		assertTrue(newValue == node.getValue());
	}
	@Test
	public void getValue() {
		Integer value = 3;
		BinaryTreeNode parent = null;
		BinaryTreeNode node = new BinaryTreeNode(value, parent);
		assertTrue(value == node.getValue());
	}
	@Test
	public void isRoot_justRoot_true() {
		Integer value = 3;
		BinaryTreeNode parent = null;
		BinaryTreeNode node = new BinaryTreeNode(value, parent);
		assertTrue(value == node.getValue());
		assertTrue(node.isRoot());
	}
	@Test
	public void isRoot_leafNode_false() {
		Integer value = 3;
		BinaryTreeNode parent = null;
		BinaryTreeNode root = new BinaryTreeNode(value, parent);
		BinaryTreeNode leftChild = new BinaryTreeNode(value, root);
		root.setLeftChild(leftChild);
		assertFalse(leftChild.isRoot());
	}
	@Test
	public void getParent() {
		Integer value = 3;
		BinaryTreeNode parent = null;
		BinaryTreeNode root = new BinaryTreeNode(value, parent);
		BinaryTreeNode leftChild = new BinaryTreeNode(value, root);
		root.setLeftChild(leftChild);
		assertTrue(root == leftChild.getParent());
	}
	@Test
	public void getLeftChild() {
		Integer value = 3;
		BinaryTreeNode parent = null;
		BinaryTreeNode root = new BinaryTreeNode(value, parent);
		BinaryTreeNode leftChild = new BinaryTreeNode(value, root);
		root.setLeftChild(leftChild);
		assertTrue(leftChild == root.getLeftChild());
	}
	@Test
	public void hasLeftChild() {
		Integer value = 3;
		BinaryTreeNode parent = null;
		BinaryTreeNode root = new BinaryTreeNode(value, parent);
		BinaryTreeNode leftChild = new BinaryTreeNode(value, root);
		assertFalse(root.hasLeftChild());
		root.setLeftChild(leftChild);
		assertTrue(root.hasLeftChild());
	}	
	@Test
	public void getRightChild() {
		Integer value = 3;
		BinaryTreeNode parent = null;
		BinaryTreeNode root = new BinaryTreeNode(value, parent);
		BinaryTreeNode rightChild = new BinaryTreeNode(value, root);
		root.setRightChild(rightChild);
		assertTrue(rightChild == root.getRightChild());
	}
	@Test
	public void hasRightChild() {
		Integer value = 3;
		BinaryTreeNode parent = null;
		BinaryTreeNode root = new BinaryTreeNode(value, parent);
		BinaryTreeNode rightChild = new BinaryTreeNode(value, root);
		assertFalse(root.hasRightChild());
		root.setRightChild(rightChild);
		assertTrue(root.hasRightChild());
	}	
}
