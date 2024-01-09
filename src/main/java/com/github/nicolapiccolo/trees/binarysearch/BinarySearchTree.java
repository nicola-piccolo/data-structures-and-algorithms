package com.github.nicolapiccolo.trees.binarysearch;

import java.util.Optional;

public interface BinarySearchTree<V> {
	int size();
	Optional<V> get(Integer key);
	void put(Integer key, V value);
	void delete(Integer key);
}
