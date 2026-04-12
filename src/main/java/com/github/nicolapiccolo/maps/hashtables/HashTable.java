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
		if(key == null) {
			throw new IllegalArgumentException("Null key provided");
		}
		this.doPut(key, value);
		this.resizeHashTableIfLoadFactorExceeded();
	}
	
	private void doPut(K key, V value) {
		int index = this.getIndexFrom(key);
		this.insertIntoBucketArray(index, key, value);
	}
	
	private int getIndexFrom(K key) {
		String keyToHash = key.toString();
		int hash = this.hashCode.hashCodeOf(keyToHash);
		return this.compressor.compress(hash, this.compressorParameters);
	}
	
	private void insertIntoBucketArray(int index, K key, V value) {
		if(this.bucketArray[index] == null) {
			this.bucketArray[index] = new ArrayList<>(); 
		}
		List<HashTableEntry<K, V>> bucket = this.bucketArray[index];
		for(HashTableEntry<K, V> entry : bucket) {
			if(entry.key().equals(key)) {
				entry.setValue(value);
				return;
			}
		}
		bucket.add(new HashTableEntry<>(key, value));
		this.entriesCount++;
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
					this.doPut(entry.key(), entry.value());
				}				
			}
		}
	}
	
	public V get(K key) {
		if(key == null) {
			throw new IllegalArgumentException("Null key provided");
		}
		return this.doGet(key);
	}
	
	private V doGet(K key) {
		int index = this.getIndexFrom(key);
		return this.findValueWith(index, key);
	}
	
	private V findValueWith(int index, K key) {
		List<HashTableEntry<K, V>> bucket = this.bucketArray[index];
		if(bucket == null) {
			return null;
		}
		int entryIndex = this.findEntryIndexWith(key, bucket);
		if(entryIndex < 0) {
			return null;
		}
		return bucket.get(entryIndex).value();
	}
	
	private int findEntryIndexWith(K key, List<HashTableEntry<K, V>> bucket) {
		for(int entryIndex = 0; entryIndex < bucket.size(); entryIndex++) {
			if(bucket.get(entryIndex).key().equals(key)) {
				return entryIndex;
			}
		}
		return -1;
	}
	
	public void delete(K key) {
		if(key == null) {
			throw new IllegalArgumentException("Null key provided");
		}
		this.doDelete(key);
	}
	
	private void doDelete(K key) {
		int index = this.getIndexFrom(key);
		this.deleteEntryWith(index, key);
	}
	
	private void deleteEntryWith(int index, K key) {
		List<HashTableEntry<K, V>> bucket = this.bucketArray[index];
		if(bucket != null) {
			int indexToRemove = this.findEntryIndexWith(key, bucket);
			if(indexToRemove >= 0) {
				bucket.remove(indexToRemove);
				this.entriesCount--;
			}
		}
	}
}
