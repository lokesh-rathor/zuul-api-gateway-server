package com.santander.zuulapigatewayserver;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

public class MyClass {

	public static void main(String[] args) {
		List<Integer> arrival = Arrays.asList(1,1,1,1,4);
		List<Integer> duration = Arrays.asList(10,3,4,6,6);
		Map<Integer, List<Integer>> value= new MyClass().Correctsubject(arrival, duration);
		System.out.println(value);  
		Integer count = (new MyClass().subject(value.get(0), value.get(1)));
		System.out.println(count);

	}

	public Integer subject(List<Integer> arrival, List<Integer> duration) {
		int count = 0;
		ListIterator<Integer> arIterator = arrival.listIterator();

		ListIterator<Integer> durIterator = duration.listIterator();
		int sum = 0;
		int current = count;
		while (arIterator.hasNext() && durIterator.hasNext()) {

			if (current == count) {
				int arr = arIterator.next();
				int durr = durIterator.next();
				sum = arr + durr;
			}
			while (true && arIterator.hasNext()) {
				int durIt = durIterator.next();
				int arIt = arIterator.next();
				if (sum <= arIt) {
					sum = durIt + arIt;
					count++;
					break;
				}
			}
		}

		return count + 1;

	}

	public Map<Integer, List<Integer>> Correctsubject(List<Integer> arrival, List<Integer> duration) {
		
		Map<Integer, List<Integer>> indexesAltered = new HashMap<>();
		List<Integer> exampleArrival=new ArrayList<>();
		List<Integer> exampleDuration=new ArrayList<>();
		
		
		Map<Integer, Integer> indexes = new HashMap<>();
		for (int i = 0; i < arrival.size(); i++) {
			if(indexes.containsKey(arrival.get(i))) {
				if(indexes.get(arrival.get(i))>duration.get(i))
				{
					indexes.put(arrival.get(i), duration.get(i));
				}
			}
			else {
				indexes.put(arrival.get(i), duration.get(i));
			}
			
		}
	
		System.out.println(indexes);
		
		for (Map.Entry<Integer,Integer> entry : indexes.entrySet())  
		{
			exampleArrival.add(entry.getKey());
			exampleDuration.add(entry.getValue());
		}
		
		
		Collections.sort(exampleArrival);
		Collections.sort(exampleDuration);
		indexesAltered.put(0, exampleArrival);
		indexesAltered.put(1, exampleDuration);
		
		return indexesAltered;

		}

}
