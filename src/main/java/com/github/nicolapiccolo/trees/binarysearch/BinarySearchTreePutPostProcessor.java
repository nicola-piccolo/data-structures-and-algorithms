package com.github.nicolapiccolo.trees.binarysearch;

public interface BinarySearchTreePutPostProcessor<V> {
	
	void processWith(Integer key, BinarySearchTreeNode<V> root);
}
