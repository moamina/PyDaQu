package data.dat.parser;

public class Test {
	
	public static void main(String[] args) {
		String str="//@dataNodes.0/@behaviouralElements.5";
		System.out.println( str.split("@behaviouralElements.")[1]);
	}

}
