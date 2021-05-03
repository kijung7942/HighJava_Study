import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		        
		
				int A = sc.nextInt(), B = sc.nextInt();
		    	
		    	System.out.println(B%10*A);
		    	System.out.println((B%100-B%10)/10*A);
		    	System.out.println((B-B%100)/100*A);
				
		    	System.out.println(A*B);

	}

}