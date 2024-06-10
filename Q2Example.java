public class Q2Example {
	  	
	public static void main(String[] args) {
		
		int a= 10;
	  	double b=0.5;
	 	double c=a+b*10;
	 	double d=b-c/10;
	 	double e=c%d-1;
	 	String[] array= {"ben","jerry"};
	 	boolean correct = true ; 
	 	System.out.println(array[1]);
	 	System.out.println(correct);
	 	System.out.println(isEven(1));
	 	System.out.println("The num is: " + e);
	 	
	 	if (a > b || a>= b) {
	 		System.out.println(a + " is bigger then or the same as " + b);
	 	}else if(a < b && a<= b){
	 		System.out.println(a + " is bigger then or the same as " + b);
	 	}
	 	else {
	 		System.out.println("This was simply to test keywords and double operands");
	 	}
	 	
	 	for(int i=10; i>1; i--){
            System.out.println("The value of i is: "+i);
        }
	 	
	 	int i = 0;
	 	while (i < 5) {
	 	  System.out.println(i);
	 	  i++;
	 	  continue;
	 	}
	 	int t = 0;
	 	System.out.println(t);
	 }	
	
	
	public static boolean isDigit(int t) {
		
		switch (t) {
		case(0): return true ; 
		case(1): return true ; 
		case(2): return true ; 
		case(3): return true ;
		case(4): return true ; 
		case(5): return true ;
		case(6): return true ; 
		case(7): return true ; 
		case(8): return true ; 
		case(9): return true ;
		default: return false;}
		
		}
	
	public static boolean isEven(int z) {
		if((z%2)==0 && ((z%2)!= 1) || (z/2)== 0) {
			return true;
		}
		 else {
		 	return false;		 		
		}
		 	   
	}
	
}

