package jdk18;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;

public class PartitionByOddEvenNos {

	public static void main(String[] args) {
		List<Integer> listOfIntegers = Arrays.asList(71, 18, 42, 21, 67, 32, 95, 14, 56, 87);
		
		Map<Boolean, List<Integer>> oddEvenNumbersMap=listOfIntegers.stream().collect(Collectors.partitioningBy(i->i%2==0));
		
		System.out.println("PartitionByOddEvenNos.main()"+oddEvenNumbersMap);
		
	}

}
