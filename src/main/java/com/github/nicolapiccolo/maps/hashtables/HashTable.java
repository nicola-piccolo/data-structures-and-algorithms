package com.github.nicolapiccolo.maps.hashtables;

import java.util.ArrayList;
import java.util.List;

public class HashTable<K, V> {
	private static final int INITIAL_BUCKET_ARRAY_SIZE = 10;
	private static final double MAX_LOAD_FACTOR = 0.25;
	private List<HashTableEntry<K, V>>[] bucketArray;
	private int entriesCount;
	private CompressorParametersDto compressorParameters;
	private HashCode hashCode;
	private Compressor compressor;
	
	public HashTable() {
		this.initializeHashCodeAndCompressor();
		this.initializeBucketArrayAndParametersDto(INITIAL_BUCKET_ARRAY_SIZE);
	}
	
	private void initializeHashCodeAndCompressor() {
		this.hashCode = new HashCode();
		this.compressor = new Compressor();
	}
	
	@SuppressWarnings("unchecked")
	private void initializeBucketArrayAndParametersDto(int size) {
		this.bucketArray = new List[size];
		this.entriesCount = 0;
		CompressorParametersDtoFactory dtoFactory = new CompressorParametersDtoFactory();
		this.compressorParameters = dtoFactory.getDtoFrom(size);
	}
	
	public int size() {
		return this.entriesCount;
	}
	
	public double loadFactor() {
		return ((double)this.entriesCount)/((double)this.bucketArray.length);
	}
	
	public double maxLoadFactor() {
		return MAX_LOAD_FACTOR;
	}
	
	public void put(K key, V value) {
		if(key==null) {
			throw new RuntimeException("Null key provided");
		}
		this.doPut(key, value);
	}
	
	private void doPut(K key, V value) {
		String keyToHash = key.toString();
		int index = this.getIndexFrom(keyToHash);
		this.insertIntoBucketArray(index, key, value);
		this.resizeHashTableIfLoadFactorExceeded();
	}
	
	private int getIndexFrom(String keyToHash) {
		int hash = this.hashCode.hashCodeOf(keyToHash);
		return this.compressor.compress(hash, this.compressorParameters);
	}
	
	private void insertIntoBucketArray(int index, K key, V value) {
		if(this.bucketArray[index] == null) {
			this.bucketArray[index] = new ArrayList<HashTableEntry<K, V>>(); 
		}
		List<HashTableEntry<K, V>> bucket = this.bucketArray[index];
		boolean hasDuplicates = this.hasDuplicates(key, bucket); 
		if(hasDuplicates) {
			this.replaceEntry(key, value, bucket);
		} else {
			this.insertEntry(key, value, bucket);
		}
		
	}
	
	private boolean hasDuplicates(K key, List<HashTableEntry<K, V>> bucket) {
		for(HashTableEntry<K, V> entry : bucket) {
			if(entry.key.equals(key)) {
				return true;
			}
		}
		return false;
	}
	
	private void replaceEntry(K key, V value, List<HashTableEntry<K, V>> bucket) {
		for(HashTableEntry<K, V> entry : bucket) {
			if(entry.key.equals(key)) {
				entry.value = value;
				break;
			}
		}		
	}
	
	private void insertEntry(K key, V value, List<HashTableEntry<K, V>> bucket) {
		HashTableEntry<K, V> entry = this.buildEntryWith(key, value);
		bucket.add(entry);
		this.entriesCount++;
	}
	
	private HashTableEntry<K, V> buildEntryWith(K key, V value){
		HashTableEntry<K, V> entry = new HashTableEntry<K, V>();
		entry.key = key;
		entry.value = value;
		return entry;
	}
	
	private void resizeHashTableIfLoadFactorExceeded() {
		if(this.loadFactor() >= MAX_LOAD_FACTOR) {
			List<HashTableEntry<K, V>>[] originalBucketArray = this.bucketArray;
			this.initializeBucketArrayAndParametersDto(originalBucketArray.length * 2);
			this.bulkPut(originalBucketArray);
		}		
	}
	
	private void bulkPut(List<HashTableEntry<K, V>>[] originalBucketArray) {
		for(List<HashTableEntry<K, V>> originalBucket : originalBucketArray) {
			if(originalBucket != null) {
				for(HashTableEntry<K, V> entry : originalBucket) {
					this.put(entry.key, entry.value);
				}				
			}
		}
	}
	
	public V get(K key) {
		if(key==null) {
			throw new RuntimeException("Null key provided");
		}
		return this.doGet(key);
	}
	
	private V doGet(K key) {
		String keyToHash = key.toString();
		int index = this.getIndexFrom(keyToHash);
		V value = this.findEntryWith(index, key);
		return value;
	}
	
	private V findEntryWith(int index, K key) {
		List<HashTableEntry<K, V>> bucket = this.bucketArray[index];
		if(bucket == null) {
			return null;
		}
		int entryIndexToReturn = this.findEntryWith(key, bucket);
		if(entryIndexToReturn < 0) {
			return null;
		}
		HashTableEntry<K, V> entry = bucket.get(entryIndexToReturn);
		return entry.value;
	}
	
	private int findEntryWith(K key, List<HashTableEntry<K, V>> bucket) {
		int matchingIndexToRemove = -1;
		for(int entryIndex=0; entryIndex < bucket.size(); entryIndex++) {
			HashTableEntry<K, V> entry = bucket.get(entryIndex);
			if(entry.key.equals(key)) {
				matchingIndexToRemove = entryIndex;
				break;
			}
		}
		return matchingIndexToRemove;
	}
	
	public void delete(K key) {
		if(key==null) {
			throw new RuntimeException("Null key provided");
		}
		this.doDelete(key);
	}
	
	private void doDelete(K key) {
		String keyToHash = key.toString();
		int index = this.getIndexFrom(keyToHash);
		this.deleteEntryWith(index, key);
	}
	
	private void deleteEntryWith(int index, K key) {
		List<HashTableEntry<K, V>> bucket = this.bucketArray[index];
		if(bucket != null) {
			int indexToRemove = this.findEntryWith(key, bucket);
			if(indexToRemove >= 0) {
				bucket.remove(indexToRemove);
				this.entriesCount--;
			}
		}
	}
}
