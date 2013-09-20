
import java.util.Iterator;

public class Subset {
   	public static void main(String[] args){

		RandomizedQueue<String> theQueue= new RandomizedQueue<String>();	

   	   	int size =  Integer.parseInt(args[0]);
   	   	String a="";
   	   	while(!StdIn.isEmpty()) { 
   	   		a=StdIn.readString();

   	   		theQueue.enqueue(a);
   	   	}
		Iterator<String> myInt = theQueue.iterator();
		for (int i=0;i<size;++i){
	    	System.out.println(myInt.next());
		}

   	}


}