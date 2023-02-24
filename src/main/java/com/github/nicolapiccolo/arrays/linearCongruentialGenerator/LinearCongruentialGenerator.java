package com.github.nicolapiccolo.arrays.linearCongruentialGenerator;

public class LinearCongruentialGenerator {
	public double generateRandomNumberFrom(LinearCongruentialGeneratorParametersDto dto, int numberOfIterations) {
		double randomNumber = dto.seed;
		for(int iterationNumber = 1; iterationNumber <= numberOfIterations; iterationNumber++) {
			randomNumber = this.getNextRandomNumberValueFrom(dto, randomNumber);
		}
		return randomNumber;
	}
	private double getNextRandomNumberValueFrom(LinearCongruentialGeneratorParametersDto dto, double currentRandomNumber) {
		return (dto.multiplier * currentRandomNumber + dto.increment) % dto.modulus;
	}
}
