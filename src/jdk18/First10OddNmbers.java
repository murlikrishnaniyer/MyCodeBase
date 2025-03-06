package jdk18;

import java.util.stream.Stream;

public class First10OddNmbers {

	public static void main(String[] args) {
		Stream.iterate(0,n->n+1).limit(10).filter(n -> n%2 !=0).forEach(n->System.out.println("First10OddNmbers.main()"+n));
		
		Stream.iterate(0,n->n+1).limit(10).filter(n->n%2==0).forEach(n->System.out.println("nn"+n));

	}

}
