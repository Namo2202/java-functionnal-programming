package com.in28minutes.functional;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class MethodReferencesRunner {

	public static void main(String[] args) {
		List.of("Ant", "Bat", "Cat", "Dog", "Elephant").stream().map(s -> s.length())
				.forEach(s -> System.out.println(s));

		List.of("Ant", "Bat", "Cat", "Dog", "Elephant").stream().map(String::length).forEach(System.out::println);

		Integer max = List.of(23, 45, 67, 34).stream().filter(n -> n % 2 == 0).max((n1, n2) -> Integer.compare(n1, n2))
				.orElse(0);
		System.out.println(max);

		Integer max2 = List.of(23, 45, 67, 34).stream().filter(MethodReferencesRunner::isEven).max(Integer::compare)
				.orElse(0);
		System.out.println(max2);

		List<Integer> numbers = List.of(4, 6, 8, 13, 3, 15);
		int sum = numbers.stream().reduce(0, (num1, num2) -> num1 + num2);
		System.out.println(sum);

		System.out.println(filterOddNumbers(List.of(-3, -2, -1, 0, 1, 2)));

		System.out.println(getCubesOfFirstNNumbers(0));

		System.out.println(getCourseNameCharacterCount(List.of("Math", "English", "History", "Physics")));

		System.out.println(sumOfSquares(List.of(1, 2, 3)));

		System.out.println(findMaxEvenNumber(List.of(23, 45, 67, 12, 34, 56, 78)));
	}

	public static Boolean isEven(Integer i) {
		return i % 2 == 0;
	}

	public static Boolean isOdd(Integer i) {
		return i % 2 != 0;
	}

	public static Integer cubes(Integer i) {
		return i * i * i;
	}

	public static Long squares(long i) {
		return i * i;
	}

	public static List<Integer> filterOddNumbers(List<Integer> numbers) {
		LinkedList<Integer> oddNumbers = new LinkedList<>(numbers);

		return oddNumbers.stream().filter(MethodReferencesRunner::isOdd).collect(Collectors.toList());
	}

	public static List<Integer> getCubesOfFirstNNumbers(int n) {
		ArrayList<Integer> cubesNumbers = new ArrayList<>();
		if (n == 0) {
			return cubesNumbers;
		}

		cubesNumbers = (ArrayList<Integer>) IntStream.range(1, n + 1).map(MethodReferencesRunner::cubes).boxed()
				.collect(Collectors.toList());

		return cubesNumbers;

	}

	public static List<Integer> getCourseNameCharacterCount(List<String> courses) {
		if (courses == null || courses.size() == 0) {
			return List.of();
		}

		return courses.stream().map(String::length).collect(Collectors.toList());

	}

	public static long sumOfSquares(List<Integer> numbers) {
		if (numbers == null) {
			return 0;
		}

		return numbers.stream().mapToLong(MethodReferencesRunner::squares).sum();
	}

	public static int findMaxEvenNumber(List<Integer> numbers) {
		if (numbers == null || numbers.size() == 0 || numbers.stream().allMatch(MethodReferencesRunner::isOdd)) {
			return 0;
		}

		return numbers.stream().filter(MethodReferencesRunner::isEven).max(Integer::compare).orElse(0);
	}

}
