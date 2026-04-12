package com.github.nicolapiccolo.maps.hashtables;

import java.util.Arrays;

public class CompressorParametersDtoFactory {
	public CompressorParametersDto getDtoFrom(int bucketArraySize) {
		if(bucketArraySize < 2) {
			throw new IllegalArgumentException("Bucket array size must be greater than 1");
		}
		return doGetDtoFrom(bucketArraySize);
	}
	
	private CompressorParametersDto doGetDtoFrom(int bucketArraySize) {
		int modulo = this.findFirstPrimeNumberGreaterThan(bucketArraySize);
		int multiplier = this.getRandomNumberLessThan(modulo);
		int offset = this.getRandomNumberLessThan(modulo);
		return new CompressorParametersDto(multiplier, offset, modulo, bucketArraySize);
	}
	
	private int findFirstPrimeNumberGreaterThan(int bucketArraySize) {
		int upperLimit = bucketArraySize * 2;
		boolean[] isPrimeNumberBitMask = this.initializeIsPrimeNumberBitMask(upperLimit);
		this.setAllPrimeNumbersIn(isPrimeNumberBitMask);
		return this.doFindPrimeNumberIn(isPrimeNumberBitMask);
	}
	
	private boolean[] initializeIsPrimeNumberBitMask(int upperLimit) {
		boolean[] isPrimeNumberBitMask = new boolean[upperLimit];
		Arrays.fill(isPrimeNumberBitMask, true);
		return isPrimeNumberBitMask;
	}
	
	private void setAllPrimeNumbersIn(boolean[] isPrimeNumberBitMask){
		int loopUpperBoundary = (int)Math.sqrt(isPrimeNumberBitMask.length);
		for(int currentValue = 2; currentValue <= loopUpperBoundary; currentValue++) {
			for(int currentNonPrimeNumber=currentValue*currentValue; currentNonPrimeNumber<=isPrimeNumberBitMask.length; currentNonPrimeNumber+=currentValue) {
				isPrimeNumberBitMask[currentNonPrimeNumber-1] = false;
			}
		}
	}
	
	private int doFindPrimeNumberIn(boolean[] isPrimeNumberBitMask) {
		int primeNumber = isPrimeNumberBitMask.length/2 + 1;
		for(; primeNumber<=isPrimeNumberBitMask.length; primeNumber++) {
			if(isPrimeNumberBitMask[primeNumber-1]) {
				break;
			}
		}
		return primeNumber;
	}
	
	private int getRandomNumberLessThan(int limit) {
		return (int)((limit-2) * Math.random() + 1);
	}
}
