package com.github.nicolapiccolo.arrays.linearCongruentialGenerator;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class LinearCongruentialGeneratorTest {
	@Test
	public void oneIterationWithNoIncrement() {
		int multiplier = 1;
		int increment = 0;
		int modulus = 8;
		int seed = 1;
		int numberOfIterations = 1;
		LinearCongruentialGeneratorParametersDto dto = this.buildDtoFrom(multiplier, increment, modulus, seed);
		LinearCongruentialGenerator generator = new LinearCongruentialGenerator();
		double result = generator.generateRandomNumberFrom(dto, numberOfIterations);
		double expectedResult = (multiplier * seed + increment) % modulus;
		assertTrue(expectedResult == result);
	}
	private LinearCongruentialGeneratorParametersDto buildDtoFrom(int multiplier, int increment, int modulus, int seed) {
		LinearCongruentialGeneratorParametersDto dto = new LinearCongruentialGeneratorParametersDto();
		dto.multiplier = multiplier;
		dto.increment = increment;
		dto.modulus = modulus;
		dto.seed = seed;
		return dto;
	}
	@Test
	public void twoIterationsWithNoIncrement() {
		int multiplier = 1;
		int increment = 0;
		int modulus = 8;
		int seed = 1;
		int numberOfIterations = 2;
		LinearCongruentialGeneratorParametersDto dto = this.buildDtoFrom(multiplier, increment, modulus, seed);
		LinearCongruentialGenerator generator = new LinearCongruentialGenerator();
		double result = generator.generateRandomNumberFrom(dto, numberOfIterations);
		double expectedResult = (multiplier * ((multiplier * seed + increment) % modulus) + increment) % modulus;
		assertTrue(expectedResult == result);
	}
	@Test
	public void oneIterationWithIncrement() {
		int multiplier = 1;
		int increment = 1;
		int modulus = 8;
		int seed = 1;
		int numberOfIterations = 1;
		LinearCongruentialGeneratorParametersDto dto = this.buildDtoFrom(multiplier, increment, modulus, seed);
		LinearCongruentialGenerator generator = new LinearCongruentialGenerator();
		double result = generator.generateRandomNumberFrom(dto, numberOfIterations);
		double expectedResult = (multiplier * seed + increment) % modulus;
		assertTrue(expectedResult == result);
	}
	@Test
	public void twoIterationsWithIncrement() {
		int multiplier = 1;
		int increment = 1;
		int modulus = 8;
		int seed = 1;
		int numberOfIterations = 2;
		LinearCongruentialGeneratorParametersDto dto = this.buildDtoFrom(multiplier, increment, modulus, seed);
		LinearCongruentialGenerator generator = new LinearCongruentialGenerator();
		double result = generator.generateRandomNumberFrom(dto, numberOfIterations);
		double expectedResult = (multiplier * ((multiplier * seed + increment) % modulus) + increment) % modulus;
		assertTrue(expectedResult == result);
	}
}
