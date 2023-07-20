package data.utils;

public class Test {
	

	    public static int solution(int[] A) {
	        // write your code in Java SE 8
	    	int i=1;
	    	boolean found=false;
	        for(; i<A.length+1;i++)
	        {
	            for(int j=0;j<A.length;j++)
	            {
	            	if(A[j]==i)
	            		break;
	            	else if(A[j]!=i && j==A.length-1)
	            		found=true;
	            		
	            }
	            if(found)
	            	break;
	        }
	        //if(found)
	        	return i;
	        //return i+1;
	    }
	public static void main(String[] args) {
//		int[] A= {1, 3, 6, 4, 1, 2};
//		System.out.println(solution(A));
		
		String x="Hello world!";
		String y=x;
		x=x.replace("Hello", "Ciao");
		
		System.out.println(x);
		System.out.println(y);
		
		
	}
	    

}
