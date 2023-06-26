package com.github.nicolapiccolo.arrays.heaps;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class ArrayHeapTest {
	@Test
	public void insert_root() {
		Integer initialValue = 3;
		Integer capacity = 20;
		ArrayHeap heap = new ArrayHeap(capacity);
		heap.insert(initialValue);
		Integer[] nodes = heap.getNodes();
		assertTrue(nodes[0] == initialValue);
	}
	
	@Test
	public void insert_rootAndSmallerLeaf() {
		Integer rootValue = 3;
		Integer capacity = 20;
		ArrayHeap heap = new ArrayHeap(capacity);
		heap.insert(rootValue);
		Integer leafValue = 1;
		heap.insert(leafValue);
		Integer[] nodes = heap.getNodes();
		assertTrue(nodes[0] == leafValue);
		assertTrue(nodes[1] == rootValue);
	}
	
	@Test
	public void insert_rootAndLargerLeaf() {
		Integer rootValue = 3;
		Integer capacity = 20;
		ArrayHeap heap = new ArrayHeap(capacity);
		heap.insert(rootValue);
		Integer leafValue = 5;
		heap.insert(leafValue);
		Integer[] nodes = heap.getNodes();
		assertTrue(nodes[0] == rootValue);
		assertTrue(nodes[1] == leafValue);
	}
	
	@Test
	public void insert_rootAndTwoSmallerLeaves() {
		Integer rootValue = 3;
		Integer capacity = 20;
		ArrayHeap heap = new ArrayHeap(capacity);
		heap.insert(rootValue);
		Integer firstLeafValue = 2;
		heap.insert(firstLeafValue);
		Integer secondLeafValue = 1;
		heap.insert(secondLeafValue);
		Integer[] nodes = heap.getNodes();
		assertTrue(nodes[0] == secondLeafValue);
		assertTrue(nodes[1] == rootValue);
		assertTrue(nodes[2] == firstLeafValue);
	}
	
	@Test
	public void insert_addLeaveToCompleteTreeWithoutSwap() {
		Integer rootValue = 3;
		Integer capacity = 20;
		ArrayHeap heap = new ArrayHeap(capacity);
		heap.insert(rootValue);
		Integer firstLeafValue = 2;
		heap.insert(firstLeafValue);
		Integer secondLeafValue = 1;
		heap.insert(secondLeafValue);
		Integer lastLeafValue = 100;
		heap.insert(lastLeafValue);
		Integer[] nodes = heap.getNodes();
		assertTrue(nodes[0] == secondLeafValue);
		assertTrue(nodes[1] == rootValue);
		assertTrue(nodes[2] == firstLeafValue);
		assertTrue(nodes[3] == lastLeafValue);
	}
	
	@Test
	public void insert_addLeaveToCompleteTreeWithSwap() {
		Integer rootValue = 3;
		Integer capacity = 20;
		ArrayHeap heap = new ArrayHeap(capacity);
		heap.insert(rootValue);
		Integer firstLeafValue = 2;
		heap.insert(firstLeafValue);
		Integer secondLeafValue = 1;
		heap.insert(secondLeafValue);
		Integer lastLeafValue = -1;
		heap.insert(lastLeafValue);
		Integer[] nodes = heap.getNodes();
		assertTrue(nodes[0] == lastLeafValue);
		assertTrue(nodes[1] == secondLeafValue);
		assertTrue(nodes[2] == firstLeafValue);
		assertTrue(nodes[3] == rootValue);
	}
	
	@Test(expected = RuntimeException.class)
	public void insert_overflowCapacity_throwsException() {
		Integer capacity = 1;
		ArrayHeap heap = new ArrayHeap(capacity);
		Integer value = 1;
		heap.insert(value);
		heap.insert(value);
	}
	
	@Test(expected = RuntimeException.class)
	public void removeRoot_emptyHeap_throwsException() {
		Integer capacity = 20;
		ArrayHeap heap = new ArrayHeap(capacity);
		heap.removeRoot();
	}
	
	@Test
	public void removeRoot_justRoot() {
		Integer rootValue = 3;
		Integer capacity = 20;
		ArrayHeap heap = new ArrayHeap(capacity);
		heap.insert(rootValue);
		assertTrue(heap.removeRoot() == rootValue);
	}
	
	@Test
	public void removeRoot_rootAndLeftChild() {
		Integer rootValue = 3;
		Integer capacity = 20;
		ArrayHeap heap = new ArrayHeap(capacity);
		heap.insert(rootValue);
		Integer firstLeafValue = 2;
		heap.insert(firstLeafValue);
		assertTrue(heap.removeRoot() == firstLeafValue);
		assertTrue(heap.removeRoot() == rootValue);
	}
	
	@Test
	public void removeRoot_rootAndTwoChildren() {
		Integer rootValue = 3;
		Integer capacity = 20;
		ArrayHeap heap = new ArrayHeap(capacity);
		heap.insert(rootValue);
		Integer firstLeafValue = 2;
		heap.insert(firstLeafValue);
		Integer secondLeafValue = 1;
		heap.insert(secondLeafValue);
		assertTrue(heap.removeRoot() == secondLeafValue);
		assertTrue(heap.removeRoot() == firstLeafValue);
		assertTrue(heap.removeRoot() == rootValue);
	}
	
	@Test
	public void removeRoot_rootAndOneGrandchild() {
		Integer rootValue = 3;
		Integer capacity = 20;
		ArrayHeap heap = new ArrayHeap(capacity);
		heap.insert(rootValue);
		Integer firstLeafValue = 2;
		heap.insert(firstLeafValue);
		Integer secondLeafValue = 1;
		heap.insert(secondLeafValue);
		Integer firstGrandchildValue = 0;
		heap.insert(firstGrandchildValue);
		assertTrue(heap.removeRoot() == firstGrandchildValue);
		assertTrue(heap.removeRoot() == secondLeafValue);
		assertTrue(heap.removeRoot() == firstLeafValue);
		assertTrue(heap.removeRoot() == rootValue);
	}
	
	@Test
	public void removeRoot_rootAndTwoGrandchildren() {
		Integer rootValue = 3;
		Integer capacity = 20;
		ArrayHeap heap = new ArrayHeap(capacity);
		heap.insert(rootValue);
		Integer firstLeafValue = 2;
		heap.insert(firstLeafValue);
		Integer secondLeafValue = 1;
		heap.insert(secondLeafValue);
		Integer firstGrandchildValue = 0;
		heap.insert(firstGrandchildValue);
		Integer secondGrandchildValue = -1;
		heap.insert(secondGrandchildValue);
		assertTrue(heap.removeRoot() == secondGrandchildValue);
		assertTrue(heap.removeRoot() == firstGrandchildValue);
		assertTrue(heap.removeRoot() == secondLeafValue);
		assertTrue(heap.removeRoot() == firstLeafValue);
		assertTrue(heap.removeRoot() == rootValue);
	}
	
	@Test
	public void removeRoot_rootAndThreeGrandchildren() {
		Integer rootValue = 3;
		Integer capacity = 20;
		ArrayHeap heap = new ArrayHeap(capacity);
		heap.insert(rootValue);
		Integer firstLeafValue = 2;
		heap.insert(firstLeafValue);
		Integer secondLeafValue = 1;
		heap.insert(secondLeafValue);
		Integer firstGrandchildValue = 0;
		heap.insert(firstGrandchildValue);
		Integer secondGrandchildValue = -1;
		heap.insert(secondGrandchildValue);
		Integer thirdGrandchildValue = -2;
		heap.insert(thirdGrandchildValue);
		assertTrue(heap.removeRoot() == thirdGrandchildValue);
		assertTrue(heap.removeRoot() == secondGrandchildValue);
		assertTrue(heap.removeRoot() == firstGrandchildValue);
		assertTrue(heap.removeRoot() == secondLeafValue);
		assertTrue(heap.removeRoot() == firstLeafValue);
		assertTrue(heap.removeRoot() == rootValue);
	}
	
	@Test
	public void removeRoot_rootAndFourGrandchildren() {
		Integer rootValue = 3;
		Integer capacity = 20;
		ArrayHeap heap = new ArrayHeap(capacity);
		heap.insert(rootValue);
		Integer firstLeafValue = 2;
		heap.insert(firstLeafValue);
		Integer secondLeafValue = 1;
		heap.insert(secondLeafValue);
		Integer firstGrandchildValue = 0;
		heap.insert(firstGrandchildValue);
		Integer secondGrandchildValue = -1;
		heap.insert(secondGrandchildValue);
		Integer thirdGrandchildValue = -2;
		heap.insert(thirdGrandchildValue);
		Integer fourthGrandchildValue = -3;
		heap.insert(fourthGrandchildValue);
		assertTrue(heap.removeRoot() == fourthGrandchildValue);
		assertTrue(heap.removeRoot() == thirdGrandchildValue);
		assertTrue(heap.removeRoot() == secondGrandchildValue);
		assertTrue(heap.removeRoot() == firstGrandchildValue);
		assertTrue(heap.removeRoot() == secondLeafValue);
		assertTrue(heap.removeRoot() == firstLeafValue);
		assertTrue(heap.removeRoot() == rootValue);
	}
	
	@Test
	public void size_empty() {
		Integer capacity = 20;
		ArrayHeap heap = new ArrayHeap(capacity);
		assertTrue(heap.size() == 0);
	}
	
	@Test
	public void size_nonEmpty() {
		Integer initialValue = 3;
		Integer capacity = 20;
		ArrayHeap heap = new ArrayHeap(capacity);
		heap.insert(initialValue);
		assertTrue(heap.size() == 1);
	}
}
