package com.github.nicolapiccolo.maps.hashtables;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class HashTableTest {

	@Test(expected = RuntimeException.class)
	public void put_nullKey_throwsException() {
		HashTable<String, Integer> hashTable = new HashTable<String, Integer>();
		hashTable.put(null, null);
	}
	
	@Test
	public void put_singleKey_sizeIsOne() {
		HashTable<String, Integer> hashTable = new HashTable<String, Integer>();
		hashTable.put("", 1);
		assertTrue(hashTable.size() == 1);
		assertTrue(hashTable.loadFactor() > 0);
		assertTrue(hashTable.loadFactor() < hashTable.maxLoadFactor());
	}
	
	@Test
	public void put_twoIdenticalKeys_sizeIsOne() {
		HashTable<String, Integer> hashTable = new HashTable<String, Integer>();
		hashTable.put("", 1);
		assertTrue(hashTable.size() == 1);
		assertTrue(hashTable.loadFactor() > 0);
		assertTrue(hashTable.loadFactor() < hashTable.maxLoadFactor());
		hashTable.put("", 1);
		assertTrue(hashTable.size() == 1);
		assertTrue(hashTable.loadFactor() > 0);
		assertTrue(hashTable.loadFactor() < hashTable.maxLoadFactor());
	}
	
	@Test
	public void put_twoDifferentKeys_sizeIsTwo() {
		HashTable<String, Integer> hashTable = new HashTable<String, Integer>();
		hashTable.put("a", 1);
		assertTrue(hashTable.size() == 1);
		double firstLoadFactor = hashTable.loadFactor(); 
		assertTrue(firstLoadFactor > 0);
		assertTrue(hashTable.loadFactor() < hashTable.maxLoadFactor());
		hashTable.put("b", 1);
		assertTrue(hashTable.size() == 2);
		double secondLoadFactor = hashTable.loadFactor();
		assertTrue(secondLoadFactor > 0);
		assertTrue(hashTable.loadFactor() < hashTable.maxLoadFactor());
		assertTrue(firstLoadFactor < secondLoadFactor);
	}

	@Test
	public void put_multipleKeys_tableIsRestructured() {
		HashTable<String, Integer> hashTable = new HashTable<String, Integer>();
		Integer counter = 1;
		hashTable.put(counter.toString(), counter);
		double previousLoadFactor = hashTable.loadFactor();
		double currentLoadFactor = previousLoadFactor;
		while(currentLoadFactor >= previousLoadFactor) {
			counter++;
			hashTable.put(counter.toString(), counter);
			previousLoadFactor = currentLoadFactor;
			currentLoadFactor = hashTable.loadFactor();
		}
		assertTrue(counter > 2);
	}
	
	@Test(expected = RuntimeException.class)
	public void get_nullKey_throwsException() {
		HashTable<String, Integer> hashTable = new HashTable<String, Integer>();
		hashTable.get(null);
	}
	
	@Test
	public void get_availableKey_returnsValue() {
		HashTable<String, Integer> hashTable = new HashTable<String, Integer>();
		hashTable.put("a", 1);
		assertTrue(hashTable.size() == 1);
		assertTrue(hashTable.get("a") == 1);
	}
	
	@Test
	public void get_unavailableKey_returnsNull() {
		HashTable<String, Integer> hashTable = new HashTable<String, Integer>();
		hashTable.put("a", 1);
		assertTrue(hashTable.size() == 1);
		assertTrue(hashTable.get("b") == null);
	}
	
	@Test(expected = RuntimeException.class)
	public void delete_nullKey_throwsException() {
		HashTable<String, Integer> hashTable = new HashTable<String, Integer>();
		hashTable.delete(null);
	}

	@Test
	public void delete_emptyTable_noChanges() {
		HashTable<String, Integer> hashTable = new HashTable<String, Integer>();
		assertTrue(hashTable.size() == 0);
		hashTable.delete("a");
		assertTrue(hashTable.size() == 0);
	}

	@Test
	public void delete_availableKey_reducesSize() {
		HashTable<String, Integer> hashTable = new HashTable<String, Integer>();
		hashTable.put("a", 1);
		assertTrue(hashTable.size() == 1);
		hashTable.delete("a");
		assertTrue(hashTable.size() == 0);
	}
	
	@Test
	public void delete_unavailableKey_noChanges() {
		HashTable<String, Integer> hashTable = new HashTable<String, Integer>();
		hashTable.put("a", 1);
		assertTrue(hashTable.size() == 1);
		hashTable.delete("b");
		assertTrue(hashTable.size() == 1);
	}
}
