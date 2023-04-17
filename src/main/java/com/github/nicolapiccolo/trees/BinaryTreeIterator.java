package com.github.nicolapiccolo.trees;

import java.util.Iterator;

public interface BinaryTreeIterator extends Iterator<Integer> {
	void initializeWith(BinaryTreeNode root);
}
