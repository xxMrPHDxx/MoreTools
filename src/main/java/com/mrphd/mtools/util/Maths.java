package com.mrphd.mtools.util;

public class Maths {

	public static int min(int...numbers) {
		if(numbers.length < 1) return 0;
		else if(numbers.length == 1) return numbers[0];
		else if(numbers.length == 1) return Math.min(numbers[0], numbers[1]);
		int smallest = Math.min(numbers[0], numbers[1]);
		for(int i=2; i<numbers.length; i++) {
			smallest = Math.min(smallest, numbers[i]);
		}
		return smallest;
	}
	
}
