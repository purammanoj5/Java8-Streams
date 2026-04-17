package com.practice.java.javapractice.javapractice;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Java8StreamsPractice {

//	private static Stream<Employe> ;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Basic stream problems");
		
//		print only even numbers from the list of numbers.
		List<Integer> numList = Arrays.asList(1, 2, 15, 4, 13);
		List<Integer> evenNumList = numList.stream().filter(n -> n % 2 == 0).collect(Collectors.toList());
//		or
		evenNumList = numList.stream().filter(n -> n % 2 == 0).toList();
		evenNumList.forEach(System.out::println);
		
		int[] arr = {10,15,8,49,25,98,32};
		Map<Boolean,Long> evenMap = Arrays.stream(arr).boxed()
				.collect(Collectors.partitioningBy(n -> n%2==0, Collectors.counting()));
		for(Boolean key: evenMap.keySet()) {
			System.out.println("evenMap");
			System.out.println(key+" : "+evenMap.get(key));
		}
		
//		print square of numbers for the numbers list
		evenNumList.stream().map(n -> n * n).forEach(System.out::println);
		System.out.println();
		
//		print square of numbers for even numbers and print odd numbers as it is in the given list
		List<Integer> evenSquareList = numList.stream().map((n) -> {
																	  if (n % 2 == 0) {
																		  return n * n;
																	  } else {
																		  return n;
																	  }
																   }).collect(Collectors.toList());
		System.out.println(evenSquareList+"\n");
		
//		find the smallest number that is greater than 10 in the list
		Optional<Integer> number = numList.stream().filter(n -> n>10).sorted().findFirst();
		System.out.println(number.get()+"\n");
		
//		find the count of numbers greater than 3 in the list
		long c = numList.stream().filter(n -> n>3).count();
		System.out.println(c+"\n");
		
//		find the sum of integers in list
		int s = numList.stream().mapToInt(Integer::intValue).sum();
		System.out.println(s+"\n");
		
//		find the max number in the list
		Optional<Integer> m = numList.stream().max(Comparator.naturalOrder());
		System.out.println(m.get());
		int max = numList.stream().distinct().sorted(Comparator.reverseOrder()).findFirst().orElse(0);
		System.out.println(max);
		max = numList.stream().reduce(0, (a,b) -> Integer.max(a, b));
//		or
		max = numList.stream().reduce(0, Integer::max);
		System.out.println(max);
		Optional<Integer> maxi = numList.stream().reduce((a,b) -> Integer.max(a, b));
		System.out.println(maxi.get());
//		or
		max = numList.stream().reduce(Integer::max).orElseThrow();
		System.out.println(max);
		max = numList.stream().mapToInt(Integer::intValue).max().orElseThrow();
		System.out.println(max+"\n");
		
//		print only first 3 numbers of list
		numList.stream().mapToInt(Integer::intValue).limit(3).forEach(System.out::println);
		
		final int x = 1;
		int y = 2;
		int z = x + y;
		final int e = x + y;
		System.out.println("\n"+z+" "+e+"\n");
		
//		sum of squares of even numbers in the list
		int evenSquareSum = numList.stream().filter(n -> n%2==0).map(n -> n*n).mapToInt(Integer::intValue).sum();
		System.out.println(evenSquareSum);
		evenSquareSum = numList.stream().filter(n -> n%2==0).map(n -> n*n).reduce(0, (a,b)-> a+b);
		System.out.println(evenSquareSum+"\n");
		
		
		
		
		System.out.println("Easy stream problems");
		
//		remove duplicates from list
		List<Integer> list = Arrays.asList(1, 2, 2, 15, 4, 13);
		List<Integer> distinctList = list.stream().distinct().collect(Collectors.toList());
		for(int n:distinctList) {
			System.out.println(n+"\n");
		}
		
//		average of all numbers in the list
		OptionalDouble avg = list.stream().mapToInt(Integer::intValue).average();
		System.out.println(avg.getAsDouble());
		double avrg = list.stream().mapToInt(Integer::intValue).average().orElse(0.0);
		System.out.println(avrg+"\n");
		
//		sort in asc
		List<Integer> ascList = list.stream().sorted().toList();
		System.out.println(ascList);
		
//		sort in desc
		List<Integer> descList = list.stream().sorted(Comparator.reverseOrder()).toList();
		System.out.println(descList+"\n");
		
//		count the number of words startes with a
		List<String> words = Arrays.asList("apple", "banana", "kiwi", "cherry", "avacado", "berry");
		long l = words.stream().filter(n->n.startsWith("a")).count();
		System.out.println(l+"\n");
		
//		combine all strings in a list of strings
		String combinedStr = words.stream().reduce((a,b) -> a+","+b).orElse(null);
		System.out.println(combinedStr);
		combinedStr = words.stream().collect(Collectors.joining(", ","[","]"));
		System.out.println(combinedStr+"\n");
		
//		check if all are greater than zero
		boolean b = list.stream().allMatch(n -> n>0);
		System.out.println(b+"\n");
		
//		check if any element in the list is divided by 3
		b = list.stream().anyMatch(n -> n%3==0);
		System.out.println(b+"\n");
		
//		flatten the list of list
		List<List<String>> listOfLists = Arrays.asList(
	            Arrays.asList("Java", "Spring"),
	            Arrays.asList("JUnit", "Mockito"),
	            Arrays.asList("Docker", "Kubernetes")
	        );
		System.out.println(listOfLists+"\n"); 
		List<String> flatList = listOfLists.stream()
	                        .flatMap(List::stream)
	                        .collect(Collectors.toList());
		System.out.println(flatList+"\n"); 
		
//		find the first non empty string in the list
		List<String> word = Arrays.asList("", "banana", "kiwi", "cherry", "avacado", "berry");
		String str = word.stream().filter(n -> !n.isEmpty()).findFirst().orElse(null);
		System.out.println(str+"\n"); 
		
//		2nd largest number in the list
		list.stream().distinct().sorted(Comparator.reverseOrder()).skip(1).findFirst()
		.ifPresent(num->System.out.println(num));
		
		
		
		System.out.println("Intermediate questions"); 
		
//		sort empList based on salary
		List<Employe> empList = Arrays.asList(
	        		new Employe(1,"Manoj",50000,"IT"),
	        		new Employe(2,"Vamsi",60000,"IT"),
	        		new Employe(3,"Manish",30000,"HR")
	        		);
//		Employe emp = new Employe();
		List<Employe> sortedList = empList.stream().sorted(Comparator.comparing(Employe::getEmpSalary)).toList();
		System.out.println(sortedList);
		
//		salary avg
		double avgSal = empList.stream().mapToInt(Employe::getEmpSalary).average().orElse(0.0);
		System.out.println("avg sal: "+avgSal);
		
		empList.stream().filter(emp -> emp.getEmpSalary()>50000).forEach(System.out::println);
//		divide odd numbers and even numbers in the list
		List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);
		Map<Boolean,List<Integer>> map = numbers.stream().collect(Collectors.partitioningBy(n -> n%2==0));
		System.out.println(map);
		System.out.println("Even numbers:"+map.get(true));
		System.out.println("Odd numbers:"+map.get(false));
		
//		group the strings in the list based on their length
		List<String> wordList = Arrays.asList("cat", "mouse", "cat", "wolf", "tom", "jerry");
		Map<Integer,List<String>> wordMap= wordList.stream().collect(Collectors.groupingBy(String::length));
		System.out.println(wordMap);
		
//		print the occurrences of strings in the list
		Map<String, Long> collect = wordList.stream().collect(Collectors.groupingBy(Function.identity(),Collectors.counting()));
		System.out.println(collect);
		
//		print the occurrences of characters in the string
		String st = "Manoj Puramm";
		Map<Character, Long> collect2 = st.chars().mapToObj(ch -> Character.toLowerCase((char) ch))
				.filter(ch -> !Character.isWhitespace(ch))
				.collect(Collectors.groupingBy(Function.identity(),Collectors.counting()));
		System.out.println(collect2);
		
//		calculate avg of salaries of each department
		Map<String, Double> collect3 = empList.stream()
				.collect(Collectors.groupingBy(Employe::getDept,Collectors.averagingDouble(Employe::getEmpSalary)));
		System.out.println(collect3);
		
//		print the max salaried employee details from each department
		Map<String, Optional<Employe>> collect4 = empList.stream()
		.collect(Collectors.groupingBy(Employe::getDept,Collectors.maxBy(Comparator.comparing(Employe::getEmpSalary))));
	    System.out.println("collect4:"+collect4);
//	    or
	    Map<String, Employe> collect6 = empList.stream()
	    		.collect(Collectors.groupingBy(Employe::getDept,
	    				Collectors.collectingAndThen(Collectors.maxBy(Comparator.comparing(Employe::getEmpSalary)), 
	    						Optional::get)));
	    System.out.println("collect6:"+collect6);
	    
//	    print the character which has more occurrences in the string
	    st.chars().mapToObj(ch -> Character.toLowerCase((char) ch))
		.filter(ch -> !Character.isWhitespace(ch))
		.collect(Collectors.groupingBy(Function.identity(),Collectors.counting()))
	    .entrySet().stream().max(Map.Entry.comparingByValue()).ifPresent(en -> System.out.println(en));
	    
//	    print the first non repeated character in the string
	    st.chars().mapToObj(ch -> Character.toLowerCase((char) ch))
		.filter(ch -> !Character.isWhitespace(ch))
		.collect(Collectors.groupingBy(Function.identity(),Collectors.counting()))
		.entrySet().stream().min(Map.Entry.comparingByValue()).ifPresent(en -> System.out.println(en));    
//      or
	    st.chars().mapToObj(ch -> Character.toLowerCase((char) ch))
		.filter(ch -> !Character.isWhitespace(ch))
		.collect(Collectors.groupingBy(Function.identity(),Collectors.counting()))
		.entrySet().stream().filter(n->n.getValue()==1).findFirst().ifPresent(en -> System.out.println(en));
	    
	    st.chars()
        .skip(IntStream.range(0, st.length())
                       .filter(i -> st.charAt(i) == ' ')
                       .map(i -> i + 1) // move to the character after space
                       .findFirst()
                       .orElse(-1)) // skip -1 if space not found
        .findFirst()
        .ifPresent(ch -> System.out.println((char) ch));
	    
	    String revStr = Arrays.stream(st.split(" ")).map(ch -> new StringBuilder(ch).reverse().toString())
	    .collect(Collectors.joining(" "));
	    System.out.println(revStr);
//	    or
	    revStr = Arrays.stream(st.split(" "))
                .map(wrd -> Arrays.stream(wrd.split(""))
                        .reduce("", (rev,ch) -> ch + rev)  //avaj
                )
                .collect(Collectors.joining(" "));
        System.out.println(revStr);
        
        String collect5 = Arrays.stream(st.split(" ")).sorted(Comparator.reverseOrder()).collect(Collectors.joining(" "));
        System.out.println(collect5);
        
        Runnable r;
        Consumer con;
        Serializable sr;
	}
}

class Employe {

	private int empId;
	private String empName;
	private int empSalary;
	private String dept;

	public int getEmpId() {
		return empId;
	}

	public void setEmpId(int empId) {
		this.empId = empId;
		System.out.println(empId);
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public int getEmpSalary() {
		return empSalary;
	}

	public void setEmpSalary(int empSalary) {
		this.empSalary = empSalary;
	}

	public String getDept() {
		return dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}

	public Employe(int empId, String empName, int empSalary, String dept) {
		super();
		this.empId = empId;
		this.empName = empName;
		this.empSalary = empSalary;
		this.dept = dept;
	}

	public Employe() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Employe [empId=" + empId + ", empName=" + empName + ", empSalary=" + empSalary + ", dept=" + dept + "]";
	}
	
}
