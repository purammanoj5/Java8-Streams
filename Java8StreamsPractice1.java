package com.practice.java.javapractice.javapractice;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Java8StreamsPractice1 {

	public static void main(String[] args) {
//		Given a list of integers, find out all the numbers starting with 1 using Stream functions
		List<Integer> myList = Arrays.asList(10,25,8,49,15,98,32);
		myList.stream().filter(n -> String.valueOf(n).startsWith("1")).forEach(System.out::println);
		System.out.println("------------------");
		
//		find duplicate elements in a given integers list in java using Stream functions
		List<Integer> myList2 = Arrays.asList(10,25,8,49,15,98,32,15,25);
		myList2.stream().filter(n -> myList2.indexOf(n) != myList2.lastIndexOf(n)).distinct().forEach(System.out::println);
//		or
		Set<Integer> set = new HashSet<>();
        myList2.stream()
              .filter(n -> !set.add(n))
              .forEach(System.out::println);
        System.out.println("------------------");
        
//      find the first element of the list using Stream functions
		myList2.stream().findFirst().ifPresent(System.out::println);
		System.out.println("------------------");
		
//		find the maximum value element present in it using Stream functions
		myList2.stream().max(Integer::compare).ifPresent(System.out::println);
//		or
		int max = myList2.stream().max(Comparator.naturalOrder()).get();
		System.out.println(max);
		System.out.println("------------------");
		
//		find the first non-repeated character in it using Stream functions
		String input = "Java articles are Awesome";
		input.chars()
			 .mapToObj(c -> Character.toLowerCase((char) c))
			 .filter(c -> input.indexOf(c) == input.lastIndexOf(c))
			 .findFirst()
			 .ifPresent(System.out::println);
		System.out.println("------------------");
		
//		find the first repeated character in it using Stream functions
		input.chars()
			 .mapToObj(ch -> Character.toLowerCase((char) ch))
			 .filter(ch -> input.indexOf(ch) != input.lastIndexOf(ch))
			 .findFirst()
			 .ifPresent(System.out::println);
		System.out.println("------------------");
		
//		Given a list of integers, sort all the values present in it in descending order using Stream functions
		myList2.stream().sorted(Comparator.reverseOrder()).forEach(System.out::println);
		myList2.stream().sorted(Collections.reverseOrder()).forEach(System.out::println);
		System.out.println("------------------");
		
//		Given an integer array nums, return true if any value appears at least twice in the array, 
//				and return false if every element is distinct.
		int[] nums = {1,2,3,4,5,6,7,8,9,10};
		boolean hasDuplicates = Arrays.stream(nums).boxed().collect(Collectors.toSet()).size() < nums.length;
		System.out.println(hasDuplicates);
		
		Set<Integer> setData = new HashSet<>();
        boolean anyMatch = Arrays.stream(nums).anyMatch(num -> !setData.add(num));
        System.out.println(anyMatch);
        System.out.println("------------------");
        
        String name = "Manoj Puram";
        Map<Character,Long> nameMap = name.chars().mapToObj(c -> Character.toLowerCase( (char) c) )
        		.filter(c -> !Character.isWhitespace(c)).collect(Collectors.groupingBy(Function.identity(),Collectors.counting()));
        for(Character cha:nameMap.keySet()) {
        	System.out.println(cha+":"+nameMap.get(cha));
        }
}
}
