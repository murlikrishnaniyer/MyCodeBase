package jdk18;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.IntPredicate;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class ThreeMax3MinNosfromList {

	public static void main(String[] args) {
		List<Integer> listOfIntegers = Arrays.asList(45, 12, 56, 15, 24, 75, 31, 89);
		
		listOfIntegers.stream().sorted(Comparator.naturalOrder()).forEach(p->System.out.println(p));
		
		listOfIntegers.stream().sorted(Comparator.naturalOrder()).limit(3).forEach(p->System.out.println("ThreeMax3MinNosfromList.main()"+p));
		listOfIntegers.stream().sorted(Comparator.reverseOrder()).limit(3).forEach(p->System.out.println("ThreeMax3MinNosfromList.main()"+p));

		
		Optional<Integer> p=listOfIntegers.stream().sorted(Comparator.naturalOrder()).findFirst();
		System.out.println("FirstNosfromList.main()"+p);
		
		Optional<Integer> q=listOfIntegers.stream().sorted(Comparator.reverseOrder()).findFirst();
		System.out.println("FirstNosfromList.main()"+q);
		
		Optional<Integer> r=listOfIntegers.stream().sorted(Comparator.reverseOrder()).skip(2).findFirst();
		System.out.println("3 element.main()"+r);

		
		// Creating an IntStream 
        List<Integer> l = Arrays.asList(1, 2, 3, 4, 5, 6); 
  
        Integer i=2;
        // Stream anyMatch(Predicate predicate) 
        boolean answer = l.stream().anyMatch(x->x==i);
  
        // Displaying the result 
        System.out.println(answer); 
	}

}
