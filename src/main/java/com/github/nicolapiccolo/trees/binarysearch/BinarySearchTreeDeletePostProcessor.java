package com.github.nicolapiccolo.trees.binarysearch;

public interface BinarySearchTreeDeletePostProcessor<V> {

	void processWith(BinarySearchTreeNode<V> root);
}