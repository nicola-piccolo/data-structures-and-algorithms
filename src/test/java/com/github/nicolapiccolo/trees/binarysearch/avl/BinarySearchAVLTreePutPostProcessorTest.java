package com.github.nicolapiccolo.trees.binarysearch.avl;

import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.github.nicolapiccolo.trees.binarysearch.BinarySearchTreeNode;

public class BinarySearchAVLTreePutPostProcessorTest {
	@Test
	public void processWith_onlyRoot() {
		Map<Integer, Integer> nodeHeights = new HashMap<Integer, Integer>();
		BinarySearchAVLTreePutPostProcessor<String> operator = new BinarySearchAVLTreePutPostProcessor<String>(nodeHeights);
		Integer key = 3;
		String value = "value";
		BinarySearchTreeNode<String> rootNode = new BinarySearchTreeNode<String>(key, value);
		operator.processWith(key, rootNode);
		assertTrue(nodeHeights.containsKey(key));
		assertTrue(nodeHeights.get(key) == 1);
	}
	@Test
	public void processWith_leftChild() {
		Map<Integer, Integer> nodeHeights = new HashMap<Integer, Integer>();
		BinarySearchAVLTreePutPostProcessor<String> operator = new BinarySearchAVLTreePutPostProcessor<String>(nodeHeights);		
		Integer key = 3;
		String value = "value";
		BinarySearchTreeNode<String> rootNode = new BinarySearchTreeNode<String>(key, value);
		operator.processWith(key, rootNode);
		Integer leftKey = 2;
		String leftValue = "leftValue";
		BinarySearchTreeNode<String> leftChild = new BinarySearchTreeNode<String>(leftKey, leftValue);
		rootNode.setLeftChild(leftChild);
		operator.processWith(leftKey, rootNode);
		assertTrue(nodeHeights.containsKey(key));
		assertTrue(nodeHeights.get(key) == 2);
		assertTrue(nodeHeights.containsKey(leftKey));
		assertTrue(nodeHeights.get(leftKey) == 1);
	}
	@Test
	public void processWith_rightChild() {
		Map<Integer, Integer> nodeHeights = new HashMap<Integer, Integer>();
		BinarySearchAVLTreePutPostProcessor<String> operator = new BinarySearchAVLTreePutPostProcessor<String>(nodeHeights);				
		Integer key = 3;
		String value = "value";
		BinarySearchTreeNode<String> rootNode = new BinarySearchTreeNode<String>(key, value);
		operator.processWith(key, rootNode);
		Integer rightKey = 4;
		String rightValue = "rightValue";
		BinarySearchTreeNode<String> rightChild = new BinarySearchTreeNode<String>(rightKey, rightValue);
		rootNode.setRightChild(rightChild);
		operator.processWith(rightKey, rootNode);
		assertTrue(nodeHeights.containsKey(key));
		assertTrue(nodeHeights.get(key) == 2);
		assertTrue(nodeHeights.containsKey(rightKey));
		assertTrue(nodeHeights.get(rightKey) == 1);
	}
	@Test
	public void processWith_leftAndRightChildren() {
		Map<Integer, Integer> nodeHeights = new HashMap<Integer, Integer>();
		BinarySearchAVLTreePutPostProcessor<String> operator = new BinarySearchAVLTreePutPostProcessor<String>(nodeHeights);						
		Integer key = 3;
		String value = "value";
		BinarySearchTreeNode<String> rootNode = new BinarySearchTreeNode<String>(key, value);
		operator.processWith(key, rootNode);
		Integer leftKey = 2;
		String leftValue = "leftValue";
		BinarySearchTreeNode<String> leftChild = new BinarySearchTreeNode<String>(leftKey, leftValue);
		rootNode.setLeftChild(leftChild);
		operator.processWith(leftKey, rootNode);
		Integer rightKey = 4;
		String rightValue = "rightValue";
		BinarySearchTreeNode<String> rightChild = new BinarySearchTreeNode<String>(rightKey, rightValue);
		rootNode.setRightChild(rightChild);
		operator.processWith(rightKey, rootNode);
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
		BinarySearchAVLTreePutPostProcessor<String> operator = new BinarySearchAVLTreePutPostProcessor<String>(nodeHeights);						
		Integer key = 3;
		String value = "value";
		BinarySearchTreeNode<String> rootNode = new BinarySearchTreeNode<String>(key, value);
		operator.processWith(key, rootNode);
		Integer leftKey = 2;
		String leftValue = "leftValue";
		BinarySearchTreeNode<String> leftChild = new BinarySearchTreeNode<String>(leftKey, leftValue);
		rootNode.setLeftChild(leftChild);
		operator.processWith(leftKey, rootNode);
		Integer rightKey = 4;
		String rightValue = "rightValue";
		BinarySearchTreeNode<String> rightChild = new BinarySearchTreeNode<String>(rightKey, rightValue);
		rootNode.setRightChild(rightChild);
		operator.processWith(rightKey, rootNode);
		Integer leftGrandchildKey = 1;
		String leftGrandchildValue = "leftGrandchildValue";
		BinarySearchTreeNode<String> leftGrandchild = new BinarySearchTreeNode<String>(leftGrandchildKey, leftGrandchildValue);
		leftChild.setLeftChild(leftGrandchild);
		operator.processWith(leftGrandchildKey, rootNode);
		Integer rightGrandchildKey = 100;
		String rightGrandchildValue = "rightGrandchildValue";
		BinarySearchTreeNode<String> rightGrandchild = new BinarySearchTreeNode<String>(rightGrandchildKey, rightGrandchildValue);
		rightChild.setRightChild(rightGrandchild);
		operator.processWith(rightGrandchildKey, rootNode);
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
	public void processWith_unbalancedLeftSubtree() {
		Map<Integer, Integer> nodeHeights = new HashMap<Integer, Integer>();
		BinarySearchAVLTreePutPostProcessor<String> operator = new BinarySearchAVLTreePutPostProcessor<String>(nodeHeights);						
		Integer key = 100;
		String value = "value";
		BinarySearchTreeNode<String> rootNode = new BinarySearchTreeNode<String>(key, value);
		operator.processWith(key, rootNode);
		Integer firstLeftKey = 20;
		String firstLeftValue = "firstLeftValue";
		BinarySearchTreeNode<String> firstLeftChild = new BinarySearchTreeNode<String>(firstLeftKey, firstLeftValue);
		rootNode.setLeftChild(firstLeftChild);
		operator.processWith(firstLeftKey, rootNode);
		Integer secondLeftKey = 30;
		String secondLeftValue = "secondLeftValue";
		BinarySearchTreeNode<String> secondLeftChild = new BinarySearchTreeNode<String>(secondLeftKey, secondLeftValue);
		firstLeftChild.setRightChild(secondLeftChild);
		operator.processWith(secondLeftKey, rootNode);
		assertTrue(nodeHeights.containsKey(secondLeftKey));
		assertTrue(nodeHeights.get(secondLeftKey) == 2);
		assertTrue(nodeHeights.containsKey(firstLeftKey));
		assertTrue(nodeHeights.get(firstLeftKey) == 1);
		assertTrue(nodeHeights.containsKey(key));
		assertTrue(nodeHeights.get(key) == 1);
	}
	@Test
	public void processWith_unbalancedRightSubtree() {
		Map<Integer, Integer> nodeHeights = new HashMap<Integer, Integer>();
		BinarySearchAVLTreePutPostProcessor<String> operator = new BinarySearchAVLTreePutPostProcessor<String>(nodeHeights);						
		Integer key = 10;
		String value = "value";
		BinarySearchTreeNode<String> rootNode = new BinarySearchTreeNode<String>(key, value);
		operator.processWith(key, rootNode);
		Integer firstRightKey = 20;
		String firstRightValue = "firstRightValue";
		BinarySearchTreeNode<String> firstRightChild = new BinarySearchTreeNode<String>(firstRightKey, firstRightValue);
		rootNode.setRightChild(firstRightChild);
		operator.processWith(firstRightKey, rootNode);
		Integer secondRightKey = 15;
		String secondRightValue = "secondRightValue";
		BinarySearchTreeNode<String> secondRightChild = new BinarySearchTreeNode<String>(secondRightKey, secondRightValue);
		firstRightChild.setLeftChild(secondRightChild);
		operator.processWith(secondRightKey, rootNode);
		assertTrue(nodeHeights.containsKey(secondRightKey));
		assertTrue(nodeHeights.get(secondRightKey) == 2);
		assertTrue(nodeHeights.containsKey(firstRightKey));
		assertTrue(nodeHeights.get(firstRightKey) == 1);
		assertTrue(nodeHeights.containsKey(key));
		assertTrue(nodeHeights.get(key) == 1);
	}
}
