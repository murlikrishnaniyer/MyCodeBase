package jdk18;

import java.util.Arrays;
import java.util.List;

public class EvenNumber {

	public static void main(String[] args) {
		
		List<Integer> evenNumber=Arrays.asList(2,3,5,6,9,17,16,23);
		
		evenNumber.stream().filter(p->p%2==0).limit(2).forEach(System.out::println);

	}

}
