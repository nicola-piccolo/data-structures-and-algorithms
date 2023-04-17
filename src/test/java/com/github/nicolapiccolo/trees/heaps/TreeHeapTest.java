package com.github.nicolapiccolo.trees.heaps;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import com.github.nicolapiccolo.trees.BinaryTreeIterator;
import com.github.nicolapiccolo.trees.BinaryTreePreorderIterator;

public class TreeHeapTest {
	@Test
	public void insert_root() {
		Integer initialValue = 3;
		TreeHeap heap = new TreeHeap();
		heap.insert(initialValue);
		BinaryTreeIterator iterator = new BinaryTreePreorderIterator();
		List<Integer> values = heap.visitWith(iterator);
		assertTrue(values.get(0) == initialValue);
	}
	
	@Test
	public void insert_rootAndSmallerLeaf() {
		Integer rootValue = 3;
		TreeHeap heap = new TreeHeap();
		heap.insert(rootValue);
		Integer leafValue = 1;
		heap.insert(leafValue);
		BinaryTreeIterator iterator = new BinaryTreePreorderIterator();
		List<Integer> values = heap.visitWith(iterator);
		assertTrue(values.get(0) == leafValue);
		assertTrue(values.get(1) == rootValue);
	}
	
	@Test
	public void insert_rootAndLargerLeaf() {
		Integer rootValue = 3;
		TreeHeap heap = new TreeHeap();
		heap.insert(rootValue);
		Integer leafValue = 5;
		heap.insert(leafValue);
		BinaryTreeIterator iterator = new BinaryTreePreorderIterator();
		List<Integer> values = heap.visitWith(iterator);
		assertTrue(values.get(0) == rootValue);
		assertTrue(values.get(1) == leafValue);
	}
	
	@Test
	public void insert_rootAndTwoSmallerLeaves() {
		Integer rootValue = 3;
		TreeHeap heap = new TreeHeap();
		heap.insert(rootValue);
		Integer firstLeafValue = 2;
		heap.insert(firstLeafValue);
		Integer secondLeafValue = 1;
		heap.insert(secondLeafValue);
		BinaryTreeIterator iterator = new BinaryTreePreorderIterator();
		List<Integer> values = heap.visitWith(iterator);
		assertTrue(values.get(0) == secondLeafValue);
		assertTrue(values.get(1) == rootValue);
		assertTrue(values.get(2) == firstLeafValue);
	}
	
	@Test
	public void insert_addLeaveToCompleteTreeWithoutSwap() {
		Integer rootValue = 3;
		TreeHeap heap = new TreeHeap();
		heap.insert(rootValue);
		Integer firstLeafValue = 2;
		heap.insert(firstLeafValue);
		Integer secondLeafValue = 1;
		heap.insert(secondLeafValue);
		Integer lastLeafValue = 100;
		heap.insert(lastLeafValue);
		BinaryTreeIterator iterator = new BinaryTreePreorderIterator();
		List<Integer> values = heap.visitWith(iterator);
		assertTrue(values.get(0) == secondLeafValue);
		assertTrue(values.get(1) == rootValue);
		assertTrue(values.get(2) == lastLeafValue);
		assertTrue(values.get(3) == firstLeafValue);
	}
	
	@Test
	public void insert_addLeaveToCompleteTreeWithSwap() {
		Integer rootValue = 3;
		TreeHeap heap = new TreeHeap();
		heap.insert(rootValue);
		Integer firstLeafValue = 2;
		heap.insert(firstLeafValue);
		Integer secondLeafValue = 1;
		heap.insert(secondLeafValue);
		Integer lastLeafValue = -1;
		heap.insert(lastLeafValue);
		BinaryTreeIterator iterator = new BinaryTreePreorderIterator();
		List<Integer> values = heap.visitWith(iterator);
		assertTrue(values.get(0) == lastLeafValue);
		assertTrue(values.get(1) == secondLeafValue);
		assertTrue(values.get(2) == rootValue);
		assertTrue(values.get(3) == firstLeafValue);
	}
}
