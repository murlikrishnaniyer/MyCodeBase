package jdk18;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Anagram {

	public static void main(String[] args) {
		String s1 = "RaceCar";
		String s2 = "CarRace";
		
		s1=Stream.of(s1.split("")).map(String::toUpperCase).sorted().collect(Collectors.joining());
		System.out.println("Anagram.main()"+s1);
		s2=Stream.of(s2.split("")).map(String::toUpperCase).sorted().collect(Collectors.joining());
		System.out.println("Anagram.main()"+s2);
		
		String abc = "RaceCar";

		String spl="";

        // The regex [,\\.\\s] splits the string by
        // commas (,), spaces (\\s), and periods (\\.)
        //String regex = "[,\\s\\.]";

        // using split() method
        String[] arr = abc.split(spl);

        // Print each element of the resulting array
        for (String sr : arr) {
            System.out.println("SR  "+ sr);
        }
    }
		
		

	

}
