package com.github.nicolapiccolo.trees.binarysearch;

import java.util.Iterator;

public interface BinarySearchTreeIterator<V> extends Iterator<V> {
	void initializeWith(BinarySearchTreeNode<V> root);
}
