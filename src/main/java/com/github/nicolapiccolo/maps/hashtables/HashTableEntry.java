package com.github.nicolapiccolo.maps.hashtables;

public class HashTableEntry<K, V> {
	private final K key;
	private V value;

	public HashTableEntry(K key, V value) {
		this.key = key;
		this.value = value;
	}

	public K key() {
		return this.key;
	}

	public V value() {
		return this.value;
	}

	public void setValue(V value) {
		this.value = value;
	}
}
