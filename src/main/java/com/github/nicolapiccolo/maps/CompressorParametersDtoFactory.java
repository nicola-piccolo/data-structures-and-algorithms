package com.github.nicolapiccolo.maps;

import java.util.Arrays;

public class CompressorParametersDtoFactory {
	public CompressorParametersDto getDtoFrom(int bucketArraySize) {
		if(bucketArraySize < 2) {
			throw new RuntimeException("Bucket array size must be greater than 1");
		}
		return doGetDtoFrom(bucketArraySize);
	}
	
	private CompressorParametersDto doGetDtoFrom(int bucketArraySize) {
		CompressorParametersDto dto = this.initializeDtoWith(bucketArraySize);
		this.setModulo(dto, bucketArraySize);
		this.setMultiplier(dto);
		this.setOffset(dto);
		return dto;
	}
	
	private CompressorParametersDto initializeDtoWith(int bucketArraySize) {
		CompressorParametersDto dto = new CompressorParametersDto();
		this.setBucketArraySize(dto, bucketArraySize);
		return dto;
	}
	
	private void setBucketArraySize(CompressorParametersDto dto, int bucketArraySize) {
		dto.bucketArraySize = bucketArraySize;
	}
	
	private void setModulo(CompressorParametersDto dto, int bucketArraySize) {
		int modulo = this.findFirstPrimeNumberGreaterThan(bucketArraySize);
		dto.modulo = modulo;
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
	
	private void setMultiplier(CompressorParametersDto dto) {
		dto.multiplier = this.getRandomNumberLessThan(dto.modulo);
	}
	
	private int getRandomNumberLessThan(int limit) {
		return (int)((limit-2) * Math.random() + 1);
	}
	
	private void setOffset(CompressorParametersDto dto) {
		dto.offset = this.getRandomNumberLessThan(dto.modulo);
	}
}
