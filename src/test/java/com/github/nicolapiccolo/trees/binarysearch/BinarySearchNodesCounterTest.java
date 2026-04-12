package com.github.nicolapiccolo.trees.binarysearch;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class BinarySearchNodesCounterTest {

	@Test
	public void isEmpty_justConstructed_true() {
		BinarySearchNodesCounter counter = new BinarySearchNodesCounter();
		assertTrue(counter.isEmpty());
	}
	
	@Test
	public void initializeSizeToOne() {
		BinarySearchNodesCounter counter = new BinarySearchNodesCounter();
		assertTrue(counter.isEmpty());
		counter.initializeSizeToOne();
		assertFalse(counter.isEmpty());
	}
	
	@Test
	public void getSize_justConstructed_zero() {
		BinarySearchNodesCounter counter = new BinarySearchNodesCounter();
		assertTrue(counter.getSize() == 0);
	}
	
	@Test
	public void getSize_initializeSizeToOne_one() {
		BinarySearchNodesCounter counter = new BinarySearchNodesCounter();
		counter.initializeSizeToOne();
		assertTrue(counter.getSize() == 1);
	}
	
	@Test
	public void decreaseSize_justConstructed_noop() {
		BinarySearchNodesCounter counter = new BinarySearchNodesCounter();
		counter.decreaseSize();
		assertTrue(counter.isEmpty());
	}
	
	@Test
	public void decreaseSize_initializeSizeToOne_empty() {
		BinarySearchNodesCounter counter = new BinarySearchNodesCounter();
		counter.initializeSizeToOne();
		counter.decreaseSize();
		assertTrue(counter.isEmpty());
	}
	
	@Test
	public void resetSize_justConstructed_noop() {
		BinarySearchNodesCounter counter = new BinarySearchNodesCounter();
		counter.resetSize();
		assertTrue(counter.isEmpty());
	}	
	
	@Test
	public void resetSize_initializeSizeToOne_empty() {
		BinarySearchNodesCounter counter = new BinarySearchNodesCounter();
		counter.initializeSizeToOne();
		counter.resetSize();
		assertTrue(counter.isEmpty());
	}
	
	@Test
	public void updateSizeOnPut_isNew_increaseSize() {
		BinarySearchNodesCounter counter = new BinarySearchNodesCounter();
		boolean isNewKey = true;
		boolean isMatchingNodeDeleted = false;
		counter.updateSizeOnPut(isNewKey, isMatchingNodeDeleted);
		assertFalse(counter.isEmpty());
	}	
	
	@Test
	public void updateSizeOnPut_isNotNewButMatchingDeletedNode_increaseSize() {
		BinarySearchNodesCounter counter = new BinarySearchNodesCounter();
		boolean isNewKey = false;
		boolean isMatchingNodeDeleted = true;
		counter.updateSizeOnPut(isNewKey, isMatchingNodeDeleted);
		assertFalse(counter.isEmpty());
	}
	
	@Test
	public void updateSizeOnPut_isNotNewAndNotMatchingDeletedNode_noSizeIncrease() {
		BinarySearchNodesCounter counter = new BinarySearchNodesCounter();
		boolean isNewKey = false;
		boolean isMatchingNodeDeleted = false;
		counter.updateSizeOnPut(isNewKey, isMatchingNodeDeleted);
		assertTrue(counter.isEmpty());
	}
	
	@Test
	public void hasDeletedNodesCountPassedThreshold_justConstructed_false() {
		BinarySearchNodesCounter counter = new BinarySearchNodesCounter();
		assertFalse(counter.hasDeletedNodesCountPassedThreshold());
	}
	
	@Test
	public void hasDeletedNodesCountPassedThreshold_initializeSize_false() {
		BinarySearchNodesCounter counter = new BinarySearchNodesCounter();
		counter.initializeSizeToOne();
		assertFalse(counter.hasDeletedNodesCountPassedThreshold());
	}
	
	@Test
	public void hasDeletedNodesCountPassedThreshold_initializeSizeWithThresholdSetToOne_true() {
		int deletedNodesPercentageThreshold = 80;
		int deletedNodesCountThreshold = 1;
		BinarySearchNodesCounter counter = new BinarySearchNodesCounter(deletedNodesPercentageThreshold, deletedNodesCountThreshold);
		counter.initializeSizeToOne();
		counter.decreaseSize();
		assertTrue(counter.hasDeletedNodesCountPassedThreshold());
	}
	
	@Test
	public void hasDeletedNodesPercentagePassedThreshold_justConstructed_false() {
		BinarySearchNodesCounter counter = new BinarySearchNodesCounter();
		assertFalse(counter.hasDeletedNodesPercentagePassedThreshold());
	}
	
	@Test
	public void hasDeletedNodesPercentagePassedThreshold_initializeSize_false() {
		BinarySearchNodesCounter counter = new BinarySearchNodesCounter();
		counter.initializeSizeToOne();
		assertFalse(counter.hasDeletedNodesPercentagePassedThreshold());
	}
	
	@Test
	public void hasDeletedNodesPercentagePassedThreshold_initializeSizeWithPercentageSetToFifty_false() {
		int deletedNodesPercentageThreshold = 50;
		int deletedNodesCountThreshold = 1;
		BinarySearchNodesCounter counter = new BinarySearchNodesCounter(deletedNodesPercentageThreshold, deletedNodesCountThreshold);
		counter.initializeSizeToOne();
		counter.decreaseSize();
		assertTrue(counter.hasDeletedNodesPercentagePassedThreshold());
	}
}
