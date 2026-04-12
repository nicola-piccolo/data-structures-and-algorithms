package com.github.nicolapiccolo.recursion.linearCongruentialGenerator;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class LinearCongruentialGeneratorTest {
	@Test
	public void zeroIterations() {
		int multiplier = 1;
		int increment = 0;
		int modulus = 8;
		int seed = 1;
		int numberOfIterations = 0;
		LinearCongruentialGeneratorParameters parameters = this.buildDtoFrom(multiplier, increment, modulus, seed);
		LinearCongruentialGenerator generator = new LinearCongruentialGenerator();
		double result = generator.generatePseudoRandomNumberFrom(parameters, numberOfIterations);
		assertTrue(seed == result);
	}

	@Test
	public void oneIterationWithNoIncrement() {
		int multiplier = 1;
		int increment = 0;
		int modulus = 8;
		int seed = 1;
		int numberOfIterations = 1;
		LinearCongruentialGeneratorParameters parameters = this.buildDtoFrom(multiplier, increment, modulus, seed);
		LinearCongruentialGenerator generator = new LinearCongruentialGenerator();
		double result = generator.generatePseudoRandomNumberFrom(parameters, numberOfIterations);
		double expectedResult = (multiplier * seed + increment) % modulus;
		assertTrue(expectedResult == result);
	}

	private LinearCongruentialGeneratorParameters buildDtoFrom(int multiplier, int increment, int modulus, int seed) {
		return new LinearCongruentialGeneratorParameters(modulus, multiplier, increment, seed);
	}

	@Test
	public void twoIterationsWithNoIncrement() {
		int multiplier = 1;
		int increment = 0;
		int modulus = 8;
		int seed = 1;
		int numberOfIterations = 2;
		LinearCongruentialGeneratorParameters parameters = this.buildDtoFrom(multiplier, increment, modulus, seed);
		LinearCongruentialGenerator generator = new LinearCongruentialGenerator();
		double result = generator.generatePseudoRandomNumberFrom(parameters, numberOfIterations);
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
		LinearCongruentialGeneratorParameters parameters = this.buildDtoFrom(multiplier, increment, modulus, seed);
		LinearCongruentialGenerator generator = new LinearCongruentialGenerator();
		double result = generator.generatePseudoRandomNumberFrom(parameters, numberOfIterations);
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
		LinearCongruentialGeneratorParameters parameters = this.buildDtoFrom(multiplier, increment, modulus, seed);
		LinearCongruentialGenerator generator = new LinearCongruentialGenerator();
		double result = generator.generatePseudoRandomNumberFrom(parameters, numberOfIterations);
		double expectedResult = (multiplier * ((multiplier * seed + increment) % modulus) + increment) % modulus;
		assertTrue(expectedResult == result);
	}
}
