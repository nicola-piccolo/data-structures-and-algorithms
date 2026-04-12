package com.github.nicolapiccolo.maps.sortedhashtables;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import com.github.nicolapiccolo.trees.binarysearch.BinarySearchTree;
import com.github.nicolapiccolo.trees.binarysearch.BinarySearchTreeInOrderIterator;
import com.github.nicolapiccolo.trees.binarysearch.BinarySearchTreeIterator;
import com.github.nicolapiccolo.trees.binarysearch.avl.BinarySearchAVLTreeFactory;

public class SortedHashTable<V> {
	private static final int DELETED_NODES_PERCENTAGE = 20;
	private static final int DELETED_NODES_COUNT_THRESHOLD = 100;
	private final BinarySearchTree<SortedHashTableEntry<V>> tree;
	private final List<Integer> sortedKeys;

	public SortedHashTable() {
		BinarySearchAVLTreeFactory<SortedHashTableEntry<V>> factory = new BinarySearchAVLTreeFactory<>();
		this.tree = factory.create(DELETED_NODES_PERCENTAGE, DELETED_NODES_COUNT_THRESHOLD);
		this.sortedKeys = new ArrayList<>();
	}

	public int size() {
		return this.tree.size();
	}

	public boolean isEmpty() {
		return this.tree.size() == 0;
	}

	public void put(Integer key, V value) {
		if (key == null) {
			throw new IllegalArgumentException("Null key provided!");
		}
		Optional<SortedHashTableEntry<V>> existing = this.tree.get(key);
		if (existing.isPresent()) {
			existing.get().setValue(value);
		} else {
			this.tree.put(key, new SortedHashTableEntry<>(value));
			this.insertKeyInSortedOrder(key);
		}
	}

	private void insertKeyInSortedOrder(Integer key) {
		int insertionIndex = this.findInsertionIndex(key);
		this.sortedKeys.add(insertionIndex, key);
	}

	private int findInsertionIndex(Integer key) {
		int low = 0;
		int high = this.sortedKeys.size();
		while (low < high) {
			int mid = (low + high) / 2;
			if (this.sortedKeys.get(mid) < key) {
				low = mid + 1;
			} else {
				high = mid;
			}
		}
		return low;
	}

	public Optional<V> get(Integer key) {
		if (key == null) {
			throw new IllegalArgumentException("Null key provided!");
		}
		Optional<SortedHashTableEntry<V>> entry = this.tree.get(key);
		return entry.map(SortedHashTableEntry::getValue);
	}

	public void delete(Integer key) {
		if (key == null) {
			throw new IllegalArgumentException("Null key provided!");
		}
		Optional<SortedHashTableEntry<V>> existing = this.tree.get(key);
		if (existing.isPresent()) {
			this.tree.delete(key);
			this.removeKeyFromSortedList(key);
		}
	}

	private void removeKeyFromSortedList(Integer key) {
		int index = this.findInsertionIndex(key);
		if (index < this.sortedKeys.size() && this.sortedKeys.get(index).equals(key)) {
			this.sortedKeys.remove(index);
		}
	}

	public Optional<Integer> firstKey() {
		if (this.isEmpty()) {
			return Optional.empty();
		}
		return Optional.of(this.sortedKeys.get(0));
	}

	public Optional<Integer> lastKey() {
		if (this.isEmpty()) {
			return Optional.empty();
		}
		return Optional.of(this.sortedKeys.get(this.sortedKeys.size() - 1));
	}

	public Optional<Integer> floorKey(Integer key) {
		if (key == null) {
			throw new IllegalArgumentException("Null key provided!");
		}
		int index = this.findInsertionIndex(key);
		if (index < this.sortedKeys.size() && this.sortedKeys.get(index).equals(key)) {
			return Optional.of(key);
		}
		if (index == 0) {
			return Optional.empty();
		}
		return Optional.of(this.sortedKeys.get(index - 1));
	}

	public Optional<Integer> ceilingKey(Integer key) {
		if (key == null) {
			throw new IllegalArgumentException("Null key provided!");
		}
		int index = this.findInsertionIndex(key);
		if (index >= this.sortedKeys.size()) {
			return Optional.empty();
		}
		return Optional.of(this.sortedKeys.get(index));
	}

	public Iterator<Integer> iterator() {
		return this.sortedKeys.iterator();
	}
}
