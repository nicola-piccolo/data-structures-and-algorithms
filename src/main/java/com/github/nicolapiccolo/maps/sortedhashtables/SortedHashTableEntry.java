package com.github.nicolapiccolo.maps.sortedhashtables;

public class SortedHashTableEntry<V> {
	private V value;

	public SortedHashTableEntry(V value) {
		this.value = value;
	}

	public V getValue() {
		return this.value;
	}

	public void setValue(V value) {
		this.value = value;
	}
}
