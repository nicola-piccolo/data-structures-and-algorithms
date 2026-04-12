package com.github.nicolapiccolo.trees.binarysearch;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class BinarySearchTreeDeleteRebuildPostProcessorTest {

	@Test
	public void processWith_justRoot_returnsRoot() {
		BinarySearchTreeDeleteRebuildPostProcessor<String> postProcessor = new BinarySearchTreeDeleteRebuildPostProcessor<String>();
		BinarySearchTreeNode<String> root = new BinarySearchTreeNode<String>(100,"root");
		postProcessor.processWith(root);
		BinarySearchTreeNode<String> newRoot = postProcessor.getNewRoot();
		assertTrue(newRoot.getKey().equals(root.getKey()));
	}
	
	@Test
	public void processWith_rootAndDeletedChild_returnsRoot() {
		BinarySearchTreeDeleteRebuildPostProcessor<String> postProcessor = new BinarySearchTreeDeleteRebuildPostProcessor<String>();
		BinarySearchTreeNode<String> root = new BinarySearchTreeNode<String>(100,"root");
		BinarySearchTreeNode<String> child = new BinarySearchTreeNode<String>(10,"child");
		root.setLeftChild(child);
		child.setIsDeleted();
		postProcessor.processWith(root);
		BinarySearchTreeNode<String> newRoot = postProcessor.getNewRoot();
		assertTrue(newRoot.getKey().equals(root.getKey()));
	}

	@Test
	public void processWith_deletedRootAndChild_returnsChild() {
		BinarySearchTreeDeleteRebuildPostProcessor<String> postProcessor = new BinarySearchTreeDeleteRebuildPostProcessor<String>();
		BinarySearchTreeNode<String> root = new BinarySearchTreeNode<String>(100,"root");
		BinarySearchTreeNode<String> child = new BinarySearchTreeNode<String>(10,"child");
		root.setLeftChild(child);
		root.setIsDeleted();
		postProcessor.processWith(root);
		BinarySearchTreeNode<String> newRoot = postProcessor.getNewRoot();
		assertTrue(newRoot.getKey().equals(child.getKey()));
	}
	
	@Test
	public void processWith_linearTree_returnsBlaancedTree() {
		BinarySearchTreeDeleteRebuildPostProcessor<String> postProcessor = new BinarySearchTreeDeleteRebuildPostProcessor<String>();
		BinarySearchTreeNode<String> root = new BinarySearchTreeNode<String>(1,"root");
		BinarySearchTreeNode<String> firstDescendant = new BinarySearchTreeNode<String>(2,"firstDescendant");
		root.setRightChild(firstDescendant);
		BinarySearchTreeNode<String> secondDescendant = new BinarySearchTreeNode<String>(3,"secondDescendant");
		firstDescendant.setRightChild(secondDescendant);
		BinarySearchTreeNode<String> thirdDescendant = new BinarySearchTreeNode<String>(4,"thirdDescendant");
		secondDescendant.setRightChild(thirdDescendant);
		postProcessor.processWith(root);
		BinarySearchTreeNode<String> newRoot = postProcessor.getNewRoot();
		assertTrue(newRoot.getKey().equals(firstDescendant.getKey()));
		assertTrue(newRoot.getLeftChild().getKey().equals(root.getKey()));
		assertTrue(newRoot.getRightChild().getKey().equals(secondDescendant.getKey()));
		assertTrue(newRoot.getRightChild().getRightChild().getKey().equals(thirdDescendant.getKey()));
	}
}
