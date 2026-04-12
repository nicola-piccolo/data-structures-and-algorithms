package com.github.nicolapiccolo.trees.binarysearch.avl;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

import com.github.nicolapiccolo.trees.binarysearch.BinarySearchTreeNode;

public class BinarySearchAVLRebalancedNodeHeightUpdateOperatorTest {
	@Test
	public void updateHeightsFrom_onlyRoot() {
		BinarySearchAVLRebalancedNodeHeightUpdateOperator<String> operator = new BinarySearchAVLRebalancedNodeHeightUpdateOperator<String>();
		Map<Integer, Integer> nodeHeights = new HashMap<Integer, Integer>();		
		Integer key = 3;
		String value = "value";
		BinarySearchTreeNode<String> rootNode = new BinarySearchTreeNode<String>(key, value);
		nodeHeights.put(key, 1);
		Integer originalHeight = 100;
		operator.updateHeightsFrom(rootNode, originalHeight, nodeHeights);
		assertTrue(nodeHeights.containsKey(key));
		assertTrue(nodeHeights.get(key) == originalHeight);
	}
	@Test
	public void updateHeightsFrom_leftChild() {
		BinarySearchAVLRebalancedNodeHeightUpdateOperator<String> operator = new BinarySearchAVLRebalancedNodeHeightUpdateOperator<String>();
		Map<Integer, Integer> nodeHeights = new HashMap<Integer, Integer>();		
		Integer key = 3;
		String value = "value";
		BinarySearchTreeNode<String> rootNode = new BinarySearchTreeNode<String>(key, value);
		nodeHeights.put(key, 2);
		Integer leftKey = 2;
		String leftValue = "leftValue";
		BinarySearchTreeNode<String> leftChild = new BinarySearchTreeNode<String>(leftKey, leftValue);
		rootNode.setLeftChild(leftChild);
		nodeHeights.put(leftKey, 2);
		Integer originalHeight = 100;
		operator.updateHeightsFrom(rootNode, originalHeight, nodeHeights);
		assertTrue(nodeHeights.containsKey(key));
		assertTrue(nodeHeights.get(key) == originalHeight);
		assertTrue(nodeHeights.containsKey(leftKey));
		assertTrue(nodeHeights.get(leftKey) == originalHeight-1);
	}
	@Test
	public void updateHeightsFrom_rightChild() {
		BinarySearchAVLRebalancedNodeHeightUpdateOperator<String> operator = new BinarySearchAVLRebalancedNodeHeightUpdateOperator<String>();
		Map<Integer, Integer> nodeHeights = new HashMap<Integer, Integer>();				
		Integer key = 3;
		String value = "value";
		BinarySearchTreeNode<String> rootNode = new BinarySearchTreeNode<String>(key, value);
		nodeHeights.put(key, 2);
		Integer rightKey = 4;
		String rightValue = "rightValue";
		BinarySearchTreeNode<String> rightChild = new BinarySearchTreeNode<String>(rightKey, rightValue);
		rootNode.setRightChild(rightChild);
		nodeHeights.put(rightKey, 2);
		Integer originalHeight = 100;
		operator.updateHeightsFrom(rootNode, originalHeight, nodeHeights);
		assertTrue(nodeHeights.containsKey(key));
		assertTrue(nodeHeights.get(key) == originalHeight);
		assertTrue(nodeHeights.containsKey(rightKey));
		assertTrue(nodeHeights.get(rightKey) == originalHeight-1);
	}
	@Test
	public void updateHeightsFrom_leftAndRightChildren() {
		BinarySearchAVLRebalancedNodeHeightUpdateOperator<String> operator = new BinarySearchAVLRebalancedNodeHeightUpdateOperator<String>();
		Map<Integer, Integer> nodeHeights = new HashMap<Integer, Integer>();						
		Integer key = 3;
		String value = "value";
		BinarySearchTreeNode<String> rootNode = new BinarySearchTreeNode<String>(key, value);
		nodeHeights.put(key, 2);
		Integer leftKey = 2;
		String leftValue = "leftValue";
		BinarySearchTreeNode<String> leftChild = new BinarySearchTreeNode<String>(leftKey, leftValue);
		rootNode.setLeftChild(leftChild);
		nodeHeights.put(leftKey, 2);
		Integer rightKey = 4;
		String rightValue = "rightValue";
		BinarySearchTreeNode<String> rightChild = new BinarySearchTreeNode<String>(rightKey, rightValue);
		rootNode.setRightChild(rightChild);
		nodeHeights.put(rightKey, 2);
		Integer originalHeight = 100;
		operator.updateHeightsFrom(rootNode, originalHeight, nodeHeights);
		assertTrue(nodeHeights.containsKey(key));
		assertTrue(nodeHeights.get(key) == originalHeight);
		assertTrue(nodeHeights.containsKey(leftKey));
		assertTrue(nodeHeights.get(leftKey) == originalHeight-1);
		assertTrue(nodeHeights.containsKey(rightKey));
		assertTrue(nodeHeights.get(rightKey) == originalHeight-1);
	}
	@Test
	public void updateHeightsFrom_leftAndRightGrandchild() {
		BinarySearchAVLRebalancedNodeHeightUpdateOperator<String> operator = new BinarySearchAVLRebalancedNodeHeightUpdateOperator<String>();
		Map<Integer, Integer> nodeHeights = new HashMap<Integer, Integer>();						
		Integer key = 3;
		String value = "value";
		BinarySearchTreeNode<String> rootNode = new BinarySearchTreeNode<String>(key, value);
		nodeHeights.put(key, 3);
		Integer leftKey = 2;
		String leftValue = "leftValue";
		BinarySearchTreeNode<String> leftChild = new BinarySearchTreeNode<String>(leftKey, leftValue);
		rootNode.setLeftChild(leftChild);
		nodeHeights.put(leftKey, 3);
		Integer rightKey = 4;
		String rightValue = "rightValue";
		BinarySearchTreeNode<String> rightChild = new BinarySearchTreeNode<String>(rightKey, rightValue);
		rootNode.setRightChild(rightChild);
		nodeHeights.put(rightKey, 3);
		Integer leftGrandchildKey = 1;
		String leftGrandchildValue = "leftGrandchildValue";
		BinarySearchTreeNode<String> leftGrandchild = new BinarySearchTreeNode<String>(leftGrandchildKey, leftGrandchildValue);
		leftChild.setLeftChild(leftGrandchild);
		nodeHeights.put(leftGrandchildKey, 2);
		Integer rightGrandchildKey = 100;
		String rightGrandchildValue = "rightGrandchildValue";
		BinarySearchTreeNode<String> rightGrandchild = new BinarySearchTreeNode<String>(rightGrandchildKey, rightGrandchildValue);
		rightChild.setRightChild(rightGrandchild);
		nodeHeights.put(rightGrandchildKey, 2);
		Integer originalHeight = 100;
		operator.updateHeightsFrom(rootNode, originalHeight, nodeHeights);
		assertTrue(nodeHeights.containsKey(key));
		assertTrue(nodeHeights.get(key) == originalHeight);
		assertTrue(nodeHeights.containsKey(leftKey));
		assertTrue(nodeHeights.get(leftKey) == originalHeight-1);
		assertTrue(nodeHeights.containsKey(rightKey));
		assertTrue(nodeHeights.get(rightKey) == originalHeight-1);
		assertTrue(nodeHeights.containsKey(leftGrandchildKey));
		assertTrue(nodeHeights.get(leftGrandchildKey) == originalHeight-2);
		assertTrue(nodeHeights.containsKey(rightGrandchildKey));
		assertTrue(nodeHeights.get(rightGrandchildKey) == originalHeight-2);
	}
}
