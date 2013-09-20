import java.util.Iterator;
import java.util.NoSuchElementException;
//import static System.out;
import java.util.Random;

public class RandomizedQueue<Item> implements Iterable<Item> { //code for the methods in 
	//iterable MUST be provided
	private Node first;
	private Node oldfirst;
	private Node last;
	private Node oldlast;
   private int queueSize;
   private Random rand;
	private class Node{
		Item item;
		Node next;

	}

	public RandomizedQueue() {
		first=null;
		last=null;
		oldlast=null;
		oldfirst=null;
      queueSize=0;
      rand=new Random();
	}


   private class RandIterator implements Iterator<Item>{//the interface iterator has 3 methods
      private int iterSize=queueSize;
      private Node current=first;
      private RandomizedQueue<Integer> indexes=new RandomizedQueue<Integer>();



      public RandIterator(){
         for (int i=1;i<=iterSize;++i){
            indexes.enqueue(i);
         }
      }
      public boolean hasNext(){
         return !indexes.isEmpty();
      }
      public void remove(){
          throw new java.lang.UnsupportedOperationException("remove is not supported");
      }
      public Item next(){
         if (!this.hasNext()){
            throw new java.util.NoSuchElementException("there is no next");
         }
         
         //get the item:
         int positionToGet=indexes.dequeue();

         //find it:
         boolean notFound=true;

         Item retItem=null;
         Node node=first;

         int el=0;  
         while (notFound){
            el++;
            if(el==positionToGet){
               retItem=node.item;
               notFound=false;
            }
            if(notFound)node=node.next;
         }
         return retItem;         
         
      }
   }


	public boolean isEmpty(){
		return this.size()==0;
	}           // is the deque empty?
	public int size(){
		return queueSize;
	}                  

	public void enqueue(Item item){
      if (item==null){
         throw new java.lang.NullPointerException("adding null value to first");
      }

      boolean firstEntry=false;
      if (this.isEmpty())firstEntry=true;
      oldlast=last;
   	last=new Node();
		last.item=item;
		last.next=null;

      if (firstEntry)first=last;//there is only one element
		if(!firstEntry)oldlast.next=last; //oldlast is null for first entry

      queueSize++;
	}

   public Item sample(){

      if (this.isEmpty()){
         throw new  java.util.NoSuchElementException("the list is empty");
      }
      //select an item at random
      int itemToGo=rand.nextInt(queueSize)+1;

      int el=0;
      boolean notFound=true;
      Item retItem=null;
      Node node=first;

      while (notFound){
         el++;
         if(el==itemToGo){
            retItem=node.item;
            notFound=false;
         }
         if(notFound)node=node.next;
      }
      return retItem;
   }

   public Item dequeue(){

      if (this.isEmpty()){
         throw new  java.util.NoSuchElementException("the list is empty");
      }
      //select an item at random
      int itemToGo=rand.nextInt(queueSize)+1;

      //findme the element:
      int el=0;
      boolean notFound=true;
      Node previous=first;
      Node node=first;

      while (notFound){
         el++;
         if(el==itemToGo){
            notFound=false;
         }

         if(notFound){
            previous=node;
            node=node.next;
         }

      }
      Item retItem=node.item;

      if(itemToGo==1){


         Node oldfirst=first;

         first=first.next;
         oldfirst.item=null;
         oldfirst.next=null;
         oldfirst=null;
      }
      else if(itemToGo==queueSize){
         Node oldlast=last;
         last=previous;
         last.next=null;
         oldlast.item=null;
         oldlast.next=null;
         oldlast=null;
      }
      else{
         //link previous t next
         previous.next=node.next;
         //delete the node         
         node.item=null;
         node.next=null;
         node=null;
      }
      queueSize--;
      return retItem;

   }


	public Iterator<Item> iterator(){//hast to return Iterator because implements iterable
      return new RandIterator();
   }
	   // return an iterator over items in order from front to end


   public static void main (String[] args) {

      RandomizedQueue<Integer> mydeque = new RandomizedQueue<Integer>();     

      for (int i=0;i<300;++i){
         mydeque.enqueue(i);
      }
      //Iterator<Integer> myInt=mydeque.iterator();


      Iterator<Integer> myInt = mydeque.iterator();
      while (myInt.hasNext()){
         //Integer thisInt=myInt.next();
        System.out.println(myInt.next());
      }      


      //mydeque.addLast(10);            

   }
}













