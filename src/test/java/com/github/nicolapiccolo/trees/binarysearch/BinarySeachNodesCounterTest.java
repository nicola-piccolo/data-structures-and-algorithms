package com.github.nicolapiccolo.trees.binarysearch;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class BinarySeachNodesCounterTest {

	@Test
	public void isEmpty_justConstructed_true() {
		BinarySeachNodesCounter counter = new BinarySeachNodesCounter();
		assertTrue(counter.isEmpty());
	}
	
	@Test
	public void initializeSizeToOne() {
		BinarySeachNodesCounter counter = new BinarySeachNodesCounter();
		assertTrue(counter.isEmpty());
		counter.initializeSizeToOne();
		assertFalse(counter.isEmpty());
	}
	
	@Test
	public void getSize_justConstructed_zero() {
		BinarySeachNodesCounter counter = new BinarySeachNodesCounter();
		assertTrue(counter.getSize() == 0);
	}
	
	@Test
	public void getSize_initializeSizeToOne_one() {
		BinarySeachNodesCounter counter = new BinarySeachNodesCounter();
		counter.initializeSizeToOne();
		assertTrue(counter.getSize() == 1);
	}
	
	@Test
	public void decreaseSize_justConstructed_noop() {
		BinarySeachNodesCounter counter = new BinarySeachNodesCounter();
		counter.decreaseSize();
		assertTrue(counter.isEmpty());
	}
	
	@Test
	public void decreaseSize_initializeSizeToOne_empty() {
		BinarySeachNodesCounter counter = new BinarySeachNodesCounter();
		counter.initializeSizeToOne();
		counter.decreaseSize();
		assertTrue(counter.isEmpty());
	}
	
	@Test
	public void resetSize_justConstructed_noop() {
		BinarySeachNodesCounter counter = new BinarySeachNodesCounter();
		counter.resetSize();
		assertTrue(counter.isEmpty());
	}	
	
	@Test
	public void resetSize_initializeSizeToOne_empty() {
		BinarySeachNodesCounter counter = new BinarySeachNodesCounter();
		counter.initializeSizeToOne();
		counter.resetSize();
		assertTrue(counter.isEmpty());
	}
	
	@Test
	public void updateSizeOnPut_isNew_increaseSize() {
		BinarySeachNodesCounter counter = new BinarySeachNodesCounter();
		boolean isNewKey = true;
		boolean isMatchingNodeDeleted = false;
		counter.updateSizeOnPut(isNewKey, isMatchingNodeDeleted);
		assertFalse(counter.isEmpty());
	}	
	
	@Test
	public void updateSizeOnPut_isNotNewButMatchingDeletedNode_increaseSize() {
		BinarySeachNodesCounter counter = new BinarySeachNodesCounter();
		boolean isNewKey = false;
		boolean isMatchingNodeDeleted = true;
		counter.updateSizeOnPut(isNewKey, isMatchingNodeDeleted);
		assertFalse(counter.isEmpty());
	}
	
	@Test
	public void updateSizeOnPut_isNotNewAndNotMatchingDeletedNode_noSizeIncrease() {
		BinarySeachNodesCounter counter = new BinarySeachNodesCounter();
		boolean isNewKey = false;
		boolean isMatchingNodeDeleted = false;
		counter.updateSizeOnPut(isNewKey, isMatchingNodeDeleted);
		assertTrue(counter.isEmpty());
	}
	
	@Test
	public void hasDeletedNodesCountPassedThreshold_justConstructed_false() {
		BinarySeachNodesCounter counter = new BinarySeachNodesCounter();
		assertFalse(counter.hasDeletedNodesCountPassedThreshold());
	}
	
	@Test
	public void hasDeletedNodesCountPassedThreshold_initializeSize_false() {
		BinarySeachNodesCounter counter = new BinarySeachNodesCounter();
		counter.initializeSizeToOne();
		assertFalse(counter.hasDeletedNodesCountPassedThreshold());
	}
	
	@Test
	public void hasDeletedNodesCountPassedThreshold_initializeSizeWithThresholdSetToOne_true() {
		int deletedNodesPercentageThreshold = 80;
		int deletedNodesCountThreshold = 1;
		BinarySeachNodesCounter counter = new BinarySeachNodesCounter(deletedNodesPercentageThreshold, deletedNodesCountThreshold);
		counter.initializeSizeToOne();
		counter.decreaseSize();
		assertTrue(counter.hasDeletedNodesCountPassedThreshold());
	}
	
	@Test
	public void hasDeletedNodesPercentagePassedThreshold_justConstructed_false() {
		BinarySeachNodesCounter counter = new BinarySeachNodesCounter();
		assertFalse(counter.hasDeletedNodesPercentagePassedThreshold());
	}
	
	@Test
	public void hasDeletedNodesPercentagePassedThreshold_initializeSize_false() {
		BinarySeachNodesCounter counter = new BinarySeachNodesCounter();
		counter.initializeSizeToOne();
		assertFalse(counter.hasDeletedNodesPercentagePassedThreshold());
	}
	
	@Test
	public void hasDeletedNodesPercentagePassedThreshold_initializeSizeWithPercentageSetToFifty_false() {
		int deletedNodesPercentageThreshold = 50;
		int deletedNodesCountThreshold = 1;
		BinarySeachNodesCounter counter = new BinarySeachNodesCounter(deletedNodesPercentageThreshold, deletedNodesCountThreshold);
		counter.initializeSizeToOne();
		counter.decreaseSize();
		assertTrue(counter.hasDeletedNodesPercentagePassedThreshold());
	}
}
