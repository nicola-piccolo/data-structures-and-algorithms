package com.github.nicolapiccolo.trees.binarysearch.avl;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

import com.github.nicolapiccolo.trees.binarysearch.BinarySearchTreeNode;

public class BinarySearchTreeDeleteHeightRebuildPostProcessorTest {
	@Test
	public void processWith_onlyRoot() {
		Map<Integer, Integer> nodeHeights = new HashMap<Integer, Integer>();
		BinarySearchTreeDeleteHeightRebuildPostProcessor<String> operator = new BinarySearchTreeDeleteHeightRebuildPostProcessor<String>(nodeHeights);
		Integer key = 3;
		String value = "value";
		BinarySearchTreeNode<String> rootNode = new BinarySearchTreeNode<String>(key, value);
		operator.processWith(rootNode);
		assertTrue(nodeHeights.containsKey(key));
		assertTrue(nodeHeights.get(key) == 1);
	}
	@Test
	public void processWith_leftChild() {
		Map<Integer, Integer> nodeHeights = new HashMap<Integer, Integer>();
		BinarySearchTreeDeleteHeightRebuildPostProcessor<String> operator = new BinarySearchTreeDeleteHeightRebuildPostProcessor<String>(nodeHeights);		
		Integer key = 3;
		String value = "value";
		BinarySearchTreeNode<String> rootNode = new BinarySearchTreeNode<String>(key, value);
		Integer leftKey = 2;
		String leftValue = "leftValue";
		BinarySearchTreeNode<String> leftChild = new BinarySearchTreeNode<String>(leftKey, leftValue);
		rootNode.setLeftChild(leftChild);
		operator.processWith(rootNode);
		assertTrue(nodeHeights.containsKey(key));
		assertTrue(nodeHeights.get(key) == 2);
		assertTrue(nodeHeights.containsKey(leftKey));
		assertTrue(nodeHeights.get(leftKey) == 1);
	}
	@Test
	public void processWith_rightChild() {
		Map<Integer, Integer> nodeHeights = new HashMap<Integer, Integer>();
		BinarySearchTreeDeleteHeightRebuildPostProcessor<String> operator = new BinarySearchTreeDeleteHeightRebuildPostProcessor<String>(nodeHeights);		
		Integer key = 3;
		String value = "value";
		BinarySearchTreeNode<String> rootNode = new BinarySearchTreeNode<String>(key, value);
		Integer rightKey = 4;
		String rightValue = "rightValue";
		BinarySearchTreeNode<String> rightChild = new BinarySearchTreeNode<String>(rightKey, rightValue);
		rootNode.setRightChild(rightChild);
		operator.processWith(rootNode);
		assertTrue(nodeHeights.containsKey(key));
		assertTrue(nodeHeights.get(key) == 2);
		assertTrue(nodeHeights.containsKey(rightKey));
		assertTrue(nodeHeights.get(rightKey) == 1);
	}
	@Test
	public void processWith_leftAndRightChildren() {
		Map<Integer, Integer> nodeHeights = new HashMap<Integer, Integer>();
		BinarySearchTreeDeleteHeightRebuildPostProcessor<String> operator = new BinarySearchTreeDeleteHeightRebuildPostProcessor<String>(nodeHeights);		
		Integer key = 3;
		String value = "value";
		BinarySearchTreeNode<String> rootNode = new BinarySearchTreeNode<String>(key, value);
		Integer leftKey = 2;
		String leftValue = "leftValue";
		BinarySearchTreeNode<String> leftChild = new BinarySearchTreeNode<String>(leftKey, leftValue);
		rootNode.setLeftChild(leftChild);
		Integer rightKey = 4;
		String rightValue = "rightValue";
		BinarySearchTreeNode<String> rightChild = new BinarySearchTreeNode<String>(rightKey, rightValue);
		rootNode.setRightChild(rightChild);
		operator.processWith(rootNode);
		assertTrue(nodeHeights.containsKey(key));
		assertTrue(nodeHeights.get(key) == 2);
		assertTrue(nodeHeights.containsKey(leftKey));
		assertTrue(nodeHeights.get(leftKey) == 1);
		assertTrue(nodeHeights.containsKey(rightKey));
		assertTrue(nodeHeights.get(rightKey) == 1);
	}
	@Test
	public void processWith_leftAndRightGrandchild() {
		Map<Integer, Integer> nodeHeights = new HashMap<Integer, Integer>();
		BinarySearchTreeDeleteHeightRebuildPostProcessor<String> operator = new BinarySearchTreeDeleteHeightRebuildPostProcessor<String>(nodeHeights);		
		Integer key = 3;
		String value = "value";
		BinarySearchTreeNode<String> rootNode = new BinarySearchTreeNode<String>(key, value);
		Integer leftKey = 2;
		String leftValue = "leftValue";
		BinarySearchTreeNode<String> leftChild = new BinarySearchTreeNode<String>(leftKey, leftValue);
		rootNode.setLeftChild(leftChild);
		Integer rightKey = 4;
		String rightValue = "rightValue";
		BinarySearchTreeNode<String> rightChild = new BinarySearchTreeNode<String>(rightKey, rightValue);
		rootNode.setRightChild(rightChild);
		Integer leftGrandchildKey = 1;
		String leftGrandchildValue = "leftGrandchildValue";
		BinarySearchTreeNode<String> leftGrandchild = new BinarySearchTreeNode<String>(leftGrandchildKey, leftGrandchildValue);
		leftChild.setLeftChild(leftGrandchild);
		Integer rightGrandchildKey = 100;
		String rightGrandchildValue = "rightGrandchildValue";
		BinarySearchTreeNode<String> rightGrandchild = new BinarySearchTreeNode<String>(rightGrandchildKey, rightGrandchildValue);
		rightChild.setRightChild(rightGrandchild);
		operator.processWith(rootNode);		
		assertTrue(nodeHeights.containsKey(key));
		assertTrue(nodeHeights.get(key) == 3);
		assertTrue(nodeHeights.containsKey(leftKey));
		assertTrue(nodeHeights.get(leftKey) == 2);
		assertTrue(nodeHeights.containsKey(rightKey));
		assertTrue(nodeHeights.get(rightKey) == 2);
		assertTrue(nodeHeights.containsKey(leftGrandchildKey));
		assertTrue(nodeHeights.get(leftGrandchildKey) == 1);
		assertTrue(nodeHeights.containsKey(rightGrandchildKey));
		assertTrue(nodeHeights.get(rightGrandchildKey) == 1);

	}
	@Test
	public void processWith_leftChildAndRightGrandchild() {
		Map<Integer, Integer> nodeHeights = new HashMap<Integer, Integer>();
		BinarySearchTreeDeleteHeightRebuildPostProcessor<String> operator = new BinarySearchTreeDeleteHeightRebuildPostProcessor<String>(nodeHeights);		
		Integer key = 100;
		String value = "value";
		BinarySearchTreeNode<String> rootNode = new BinarySearchTreeNode<String>(key, value);
		Integer leftKey = 2;
		String leftValue = "leftValue";
		BinarySearchTreeNode<String> leftChild = new BinarySearchTreeNode<String>(leftKey, leftValue);
		rootNode.setLeftChild(leftChild);
		Integer rightGrandchildKey = 10;
		String rightGrandchildValue = "rightGrandchildValue";
		BinarySearchTreeNode<String> rightGrandchild = new BinarySearchTreeNode<String>(rightGrandchildKey, rightGrandchildValue);
		leftChild.setRightChild(rightGrandchild);
		operator.processWith(rootNode);	
		assertTrue(nodeHeights.containsKey(key));
		assertTrue(nodeHeights.get(key) == 3);
		assertTrue(nodeHeights.containsKey(leftKey));
		assertTrue(nodeHeights.get(leftKey) == 2);
		assertTrue(nodeHeights.containsKey(rightGrandchildKey));
		assertTrue(nodeHeights.get(rightGrandchildKey) == 1);

	}
}
