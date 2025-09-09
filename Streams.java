package com.practice.java.javapractice.javapractice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Comparator;
import java.util.HashMap;
import java.util.stream.Collectors;
import java.util.Optional;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.springframework.cache.annotation.EnableCaching;

public class Streams {
	public static void main(String[] args) {
//    	Primitive Array
		int[] primitiveArray = { 1, 2, 3, 4 };
//      Object Array
		Integer[] objectArray = { 10, 20, 30, 40 };

		final IntStream intStream = Arrays.stream(primitiveArray); // primitive array to stream
		intStream.forEach(System.out::println); 
		IntStream.of(primitiveArray).forEach(System.out::println); // we have only IntStream, LongStream, DoubleStream
		
		final Stream<Integer> integerStream = Stream.of(objectArray); // non-primitive array to stream
		integerStream.forEach(System.out::println);
		Arrays.stream(objectArray).forEach(System.out::println); // non-primitive array to stream

		List<Integer> integerList = Arrays.asList(5, 6, 7, 8);

		Collectors.toList();
		Arrays.stream(primitiveArray).boxed().collect(Collectors.toList()).forEach(System.out::println); // primitive
																											// array to
																											// list
		Arrays.asList(objectArray).forEach(System.out::println); // non-primitive array to list
		integerList.forEach(System.out::println);

		integerList.stream().forEach(System.out::println); // list to stream

		List<Integer> values = Arrays.asList(11, 12, 13, 14, 14);
		
		//intermediate operations
		values.stream().filter(n -> n % 2 == 0).forEach(System.out::println);
		values.stream().map(n -> n * 2).forEach(System.out::println);
		values.stream().limit(3).forEach(System.out::println);
		values.stream().skip(1).forEach(System.out::println);
		values.stream().distinct().forEach(System.out::println);
		List<Integer> val=values.stream().peek(n->System.out.println("num:"+n)).collect(Collectors.toList());
		System.out.println("val:" + val);
		
		List<List<String>> listOfLists = Arrays.asList(
	            Arrays.asList("Java", "Spring"),
	            Arrays.asList("JUnit", "Mockito"),
	            Arrays.asList("Docker", "Kubernetes")
	        );

		List<String> flatList = listOfLists.stream()
	                        .flatMap(list -> list.stream())
	                        .collect(Collectors.toList());
		System.out.println(flatList); // printing list directly with sysout
		
		List<Integer> numbers = Arrays.asList(5, 3, 1, 4, 2);

		numbers.stream().sorted(Comparator.reverseOrder()).forEach(System.out::println);
		numbers.stream().sorted().forEach(System.out::println);

		List<String> words = Arrays.asList("apple", "banana", "kiwi", "cherry");
		List<String> sortedWordsByLength = words.stream().sorted(Comparator.comparingInt(String::length).reversed())
				.toList();
		sortedWordsByLength.forEach(System.out::println);
		
		//terminal operations
		final boolean b = numbers.stream().anyMatch(number -> number % 2 == 0);
		System.out.println("b:" + b);

		Optional<Integer> number=numbers.stream().findFirst();
		System.out.println("number:" + number.get()); //to get int from Optional<Integer>
		
		final long count = numbers.stream().count();
		System.out.println(count);

		final int sum = numbers.stream().mapToInt(Integer::intValue).sum();
		System.out.println(sum);

		final Optional<Integer> max = numbers.stream().max(Comparator.naturalOrder());
		System.out.println(max.get());

		String concatenatedLastNames = words.stream().collect(Collectors.joining("],[", "[", "]"));
		System.out.println(concatenatedLastNames);
		System.out.println(words);

		List<String> result = Stream.of("Java", "Spring", "JUnit")
				.collect(ArrayList::new, // Supplier: Creates a new	result container.
						 List::add, // Accumulator: Adds elements to the container.
						 List::addAll // Combiner: Merges two containers (used in parallel streams).
						);
		result.forEach(System.out::println); // printing list using forEach


		HashMap<Integer, String> empMap = new HashMap<>();
		List<Employee> empList= new ArrayList<>();
		Employee emp1 = new Employee();
		Employee emp2 = new Employee();
		Employee emp3 = new Employee();
		
		emp1.setEmpId(1);
		emp1.setEmpName("Manoj");
		empList.add(emp1);
		empMap.put(emp1.getEmpId(), emp1.getEmpName());
		
		emp1.setEmpId(2);		   // after setting empId to 2 for emp1, it will also change in empList
		emp1.setEmpName("Vamsi");  // after setting empName to Vamsi for emp1, it will also change in empList
//		empList.add(emp1);
		empMap.put(emp1.getEmpId(), emp1.getEmpName()); //but in map it will create a new entry as key is different
		
		emp2.setEmpId(3);
		emp2.setEmpName("Rohit");
//		empList.add(emp2);
		empMap.put(emp2.getEmpId(), emp2.getEmpName());
		
		emp2.setEmpId(5);
		emp2.setEmpName("Virat");
		empList.add(emp2);
		empMap.put(emp2.getEmpId(), emp2.getEmpName());
		
		emp3.setEmpId(3);
		emp3.setEmpName("Dhoni");
		empList.add(emp3);
		
		System.out.println(empMap);
		empList.forEach(System.out::println);
		
		 Map<String, List<Employee>> employeesByDepartment =
	                empList.stream()
	                .collect(Collectors.groupingBy(Employee::getEmpName));
		 
		 Map<Boolean, List<Employee>> employeesById =
	                empList.stream()
	                .collect(Collectors.partitioningBy(emp->emp.getEmpId()>2));
		 
		 employeesByDepartment.forEach((empName, employeeList) -> {
	            System.out.println("EmpName: " + empName);
	            employeeList.forEach(System.out::println);
	        });
		 
		 System.out.println("EmpId > 2");
		 employeesById.get(true).forEach(System.out::println);
		 System.out.println("EmpId <= 2");
		 employeesById.get(false).forEach(System.out::println);
	}

}

class Employee {

	private static int n = 1;
	private int empId;
	private String empName;

	public int getEmpId() {
		return empId;
	}

	public void setEmpId(int empId) {
		this.empId = n++;
		System.out.println(empId);
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	
	@Override
	public String toString() {
		return "Employee [empId=" + empId + ", empName=" + empName + "]";
	}

	Employee() {
//    	System.out.println("Obj");
	};

	Employee(int empId, String empName) {
		this.empId = n++;
		this.empName = empName;
	}


}


