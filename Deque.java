import java.util.Iterator;
import java.util.NoSuchElementException;
//import static System.out;

public class Deque<Item> implements Iterable<Item> { //code for the methods in 
	//iterable MUST be provided
	private Node first;
	private Node oldfirst;
	private Node last;
	private Node oldlast;
	private class Node{
		Item item;
		Node next;
      Node previous;
	}

	public Deque() {
		first=null;
		last=null;
		oldlast=null;
		oldfirst=null;
	}


   private class ListIterator implements Iterator<Item>{//the interface iterator has 3 methods
      private Node current=first;
      public boolean hasNext(){return current!=null;}
      public void remove(){
          throw new java.lang.UnsupportedOperationException("remove is not supported");
      }
      public Item next(){
         if (!this.hasNext()){
            throw new java.util.NoSuchElementException("there is no next");
         }         
         Item item=current.item;
         current = current.next;
         return item;
      }
   }


	public boolean isEmpty(){
		return first==null;
	}           // is the deque empty?
	public int size(){

		if (this.isEmpty())return 0;

		int size=0;
		Node node=first;
		while (node != null){
			size+=1;
         node=node.next;
		}
		return size;
	}                  
	public void addFirst(Item item){
      if (item==null){
         throw new java.lang.NullPointerException("adding null value to first");
      }

      boolean firstEntry=false;
      if (this.isEmpty())firstEntry=true;      
		oldfirst=first;
		first=new Node();
		first.next=oldfirst;
      first.previous=null;
		first.item=item;
      //check if the list has only one element
      if (firstEntry){
         oldlast=first;
      }
	}    // insert the item at the front

	public void addLast(Item item){
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
	}
	public Item removeFirst(){
      if (this.isEmpty()){
         throw new  java.util.NoSuchElementException("the list is empty");
      }
		oldfirst=first;
   	first=first.next;
   	oldfirst.next=null;
   	return oldfirst.item;
	}
	public Item removeLast() {
      if (this.isEmpty()){
         throw new  java.util.NoSuchElementException("the list is empty");
      }      
      Node node=first;
      if (node.next==null){
         //only one element!
         Item retItem = first.item;
         first=null;
         last=null;
         return retItem;
      }
      else{
         //while(node.next.next!=null){
         //   node=node.next;
         //}
         //Item retItem=last.item;
         last.item=null;
         last.next=null;
         last=oldlast;
         last.next=null;
         return last.item;
      }
	}

	public Iterator<Item> iterator(){//hast to return Iterator because implements iterable
      return new ListIterator();
   }
	   // return an iterator over items in order from front to end


   public static void main (String[] args) {
      Deque<Integer> mydeque = new Deque<Integer>();
   }
}













