package com.github.nicolapiccolo.maps;

public class Compressor {
	public int compress(int numberToCompress, CompressorParametersDto dto) {
		if(dto == null) {
			throw new RuntimeException("No compressor parameters provided");
		}
		int compressedNumber = numberToCompress * dto.multiplier;
		compressedNumber += dto.offset;
		compressedNumber %= dto.modulo;
		compressedNumber %= dto.bucketArraySize;
		return compressedNumber;
	}
}
