package com.github.nicolapiccolo.trees.binarysearch.avl;

import java.util.HashMap;
import java.util.Map;

import com.github.nicolapiccolo.trees.binarysearch.BinarySearchCoreTree;
import com.github.nicolapiccolo.trees.binarysearch.BinarySearchTree;
import com.github.nicolapiccolo.trees.binarysearch.BinarySearchTreeDeletePostProcessor;
import com.github.nicolapiccolo.trees.binarysearch.BinarySearchTreePutPostProcessor;

public class BinarySearchAVLTreeFactory<V> {
	
	public BinarySearchTree<V> create(int deletedNodesPercentage, int deletedNodesCountThreshold){
		BinarySearchCoreTree<V> binarySearchtree = new BinarySearchCoreTree<V>(deletedNodesPercentage, deletedNodesCountThreshold);
		Map<Integer, Integer> nodeHeights = new HashMap<Integer, Integer>();
		BinarySearchTreePutPostProcessor<V> putPostProcessor = new BinarySearchAVLTreePutPostProcessor<V>(nodeHeights);
		binarySearchtree.setPutPostProcessor(putPostProcessor);
		BinarySearchTreeDeletePostProcessor<V> deletepostProcessor = new BinarySearchTreeDeleteHeightRebuildPostProcessor<V>(nodeHeights);
		binarySearchtree.setDeletePostProcessor(deletepostProcessor);
		return binarySearchtree;
	}

}
