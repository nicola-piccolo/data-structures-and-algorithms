package com.github.nicolapiccolo.trees.binarysearch.avl;

import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.github.nicolapiccolo.trees.binarysearch.BinarySearchTreeNode;

public class BinarySearchAVLBottomUpHeightUpdateOperatorTest {
	@Test
	public void updateHeightsFrom_onlyRoot() {
		BinarySearchAVLBottomUpHeightUpdateOperator<String> operator = new BinarySearchAVLBottomUpHeightUpdateOperator<String>();
		Map<Integer, Integer> nodeHeights = new HashMap<Integer, Integer>();		
		Integer key = 3;
		String value = "value";
		BinarySearchTreeNode<String> rootNode = new BinarySearchTreeNode<String>(key, value);
		operator.updateHeightsFrom(rootNode, nodeHeights);
		assertTrue(nodeHeights.containsKey(key));
		assertTrue(nodeHeights.get(key) == 1);
	}
	@Test
	public void updateHeightsFrom_leftChild() {
		BinarySearchAVLBottomUpHeightUpdateOperator<String> operator = new BinarySearchAVLBottomUpHeightUpdateOperator<String>();
		Map<Integer, Integer> nodeHeights = new HashMap<Integer, Integer>();		
		Integer key = 3;
		String value = "value";
		BinarySearchTreeNode<String> rootNode = new BinarySearchTreeNode<String>(key, value);
		operator.updateHeightsFrom(rootNode, nodeHeights);
		Integer leftKey = 2;
		String leftValue = "leftValue";
		BinarySearchTreeNode<String> leftChild = new BinarySearchTreeNode<String>(leftKey, leftValue);
		rootNode.setLeftChild(leftChild);
		operator.updateHeightsFrom(leftChild, nodeHeights);
		assertTrue(nodeHeights.containsKey(key));
		assertTrue(nodeHeights.get(key) == 2);
		assertTrue(nodeHeights.containsKey(leftKey));
		assertTrue(nodeHeights.get(leftKey) == 1);
	}
	@Test
	public void updateHeightsFrom_rightChild() {
		BinarySearchAVLBottomUpHeightUpdateOperator<String> operator = new BinarySearchAVLBottomUpHeightUpdateOperator<String>();
		Map<Integer, Integer> nodeHeights = new HashMap<Integer, Integer>();				
		Integer key = 3;
		String value = "value";
		BinarySearchTreeNode<String> rootNode = new BinarySearchTreeNode<String>(key, value);
		operator.updateHeightsFrom(rootNode, nodeHeights);
		Integer rightKey = 4;
		String rightValue = "rightValue";
		BinarySearchTreeNode<String> rightChild = new BinarySearchTreeNode<String>(rightKey, rightValue);
		rootNode.setRightChild(rightChild);
		operator.updateHeightsFrom(rightChild, nodeHeights);
		assertTrue(nodeHeights.containsKey(key));
		assertTrue(nodeHeights.get(key) == 2);
		assertTrue(nodeHeights.containsKey(rightKey));
		assertTrue(nodeHeights.get(rightKey) == 1);
	}
	@Test
	public void updateHeightsFrom_leftAndRightChildren() {
		BinarySearchAVLBottomUpHeightUpdateOperator<String> operator = new BinarySearchAVLBottomUpHeightUpdateOperator<String>();
		Map<Integer, Integer> nodeHeights = new HashMap<Integer, Integer>();						
		Integer key = 3;
		String value = "value";
		BinarySearchTreeNode<String> rootNode = new BinarySearchTreeNode<String>(key, value);
		operator.updateHeightsFrom(rootNode, nodeHeights);
		Integer leftKey = 2;
		String leftValue = "leftValue";
		BinarySearchTreeNode<String> leftChild = new BinarySearchTreeNode<String>(leftKey, leftValue);
		rootNode.setLeftChild(leftChild);
		operator.updateHeightsFrom(leftChild, nodeHeights);
		Integer rightKey = 4;
		String rightValue = "rightValue";
		BinarySearchTreeNode<String> rightChild = new BinarySearchTreeNode<String>(rightKey, rightValue);
		rootNode.setRightChild(rightChild);
		operator.updateHeightsFrom(rightChild, nodeHeights);
		assertTrue(nodeHeights.containsKey(key));
		assertTrue(nodeHeights.get(key) == 2);
		assertTrue(nodeHeights.containsKey(leftKey));
		assertTrue(nodeHeights.get(leftKey) == 1);
		assertTrue(nodeHeights.containsKey(rightKey));
		assertTrue(nodeHeights.get(rightKey) == 1);
	}
	@Test
	public void updateHeightsFrom_leftAndRightGrandchild() {
		BinarySearchAVLBottomUpHeightUpdateOperator<String> operator = new BinarySearchAVLBottomUpHeightUpdateOperator<String>();
		Map<Integer, Integer> nodeHeights = new HashMap<Integer, Integer>();						
		Integer key = 3;
		String value = "value";
		BinarySearchTreeNode<String> rootNode = new BinarySearchTreeNode<String>(key, value);
		operator.updateHeightsFrom(rootNode, nodeHeights);
		Integer leftKey = 2;
		String leftValue = "leftValue";
		BinarySearchTreeNode<String> leftChild = new BinarySearchTreeNode<String>(leftKey, leftValue);
		rootNode.setLeftChild(leftChild);
		operator.updateHeightsFrom(leftChild, nodeHeights);
		Integer rightKey = 4;
		String rightValue = "rightValue";
		BinarySearchTreeNode<String> rightChild = new BinarySearchTreeNode<String>(rightKey, rightValue);
		rootNode.setRightChild(rightChild);
		operator.updateHeightsFrom(rightChild, nodeHeights);
		Integer leftGrandchildKey = 1;
		String leftGrandchildValue = "leftGrandchildValue";
		BinarySearchTreeNode<String> leftGrandchild = new BinarySearchTreeNode<String>(leftGrandchildKey, leftGrandchildValue);
		leftChild.setLeftChild(leftGrandchild);
		operator.updateHeightsFrom(leftGrandchild, nodeHeights);
		Integer rightGrandchildKey = 100;
		String rightGrandchildValue = "rightGrandchildValue";
		BinarySearchTreeNode<String> rightGrandchild = new BinarySearchTreeNode<String>(rightGrandchildKey, rightGrandchildValue);
		rightChild.setRightChild(rightGrandchild);
		operator.updateHeightsFrom(rightGrandchild, nodeHeights);		
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
	public void updateHeightsFrom_leftChildAndRightGrandchild() {
		BinarySearchAVLBottomUpHeightUpdateOperator<String> operator = new BinarySearchAVLBottomUpHeightUpdateOperator<String>();
		Map<Integer, Integer> nodeHeights = new HashMap<Integer, Integer>();						
		Integer key = 100;
		String value = "value";
		BinarySearchTreeNode<String> rootNode = new BinarySearchTreeNode<String>(key, value);
		operator.updateHeightsFrom(rootNode, nodeHeights);
		Integer leftKey = 2;
		String leftValue = "leftValue";
		BinarySearchTreeNode<String> leftChild = new BinarySearchTreeNode<String>(leftKey, leftValue);
		rootNode.setLeftChild(leftChild);
		operator.updateHeightsFrom(leftChild, nodeHeights);
		Integer rightGrandchildKey = 10;
		String rightGrandchildValue = "rightGrandchildValue";
		BinarySearchTreeNode<String> rightGrandchild = new BinarySearchTreeNode<String>(rightGrandchildKey, rightGrandchildValue);
		leftChild.setRightChild(rightGrandchild);
		operator.updateHeightsFrom(rightGrandchild, nodeHeights);		
		assertTrue(nodeHeights.containsKey(key));
		assertTrue(nodeHeights.get(key) == 2);
		assertTrue(nodeHeights.containsKey(leftKey));
		assertTrue(nodeHeights.get(leftKey) == 2);
		assertTrue(nodeHeights.containsKey(rightGrandchildKey));
		assertTrue(nodeHeights.get(rightGrandchildKey) == 1);

	}
}
