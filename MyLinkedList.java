import java.util.*;

public class MyLinkedList <E> implements Iterable <E> {
	
	//Class representation of a single node
	//each node has a previous and next //pointer, as well as, 
	//some data.
	private static class DListNode<E>{
    //Instance variables in inner class.
		private E data;
		private DListNode <E> previous;
		private DListNode <E> next;
		
		public DListNode( E data, DListNode <E> previous, DListNode <E> next) {

			this.data = data;
			this.previous = previous;
			this.next = next;
		}
		//getters
		public E getData() {
			return data;
		}
		public DListNode <E> getNext(){
			return next;
		}
		public DListNode <E> getPrevious(){
			return previous;
		}
    //setters
		public void setData(E data) {
			this.data = data;
		}
		public void setPrevious( DListNode <E> previous) {
			this.previous = previous;
		}
		public void setNext(DListNode <E> next) {
			this.next = next;
		}
	}//end of DListNode Class
	

  //Declaring first and last node
  //as instance varibales.
	private DListNode <E> head;
	private DListNode <E> tail;
	private int numElements;
	
  //constructor
	public MyLinkedList() {
		head = null;
		tail = null;
		numElements = 0;
	}

  //checks if the list is empty
  //sets the next value of temp to head
  //sets head previous to temp
  //make temp the head to make front of list.
	public void addFirst(E e) {
		
		DListNode <E> temp = new DListNode<>(e,null,null);
		//If the LinkedList is empty makes a singleton node.
		if(head == null) {
			head = temp;
			tail = temp;
		}
		else {
			temp.setNext(head);
			head.setPrevious(temp);
			head = temp;
		}
		numElements++;
	}
  //checks if the list is empty
  //sets the previous value of temp to tail
  //sets tail next to temp
  //make temp the tail to make end of list.
	public void addLast(E e) {
		DListNode <E> temp = new DListNode<>(e,null,null);
		
		if(head == null) {
			head = temp;
			tail = temp;
		}
		else {
			temp.setPrevious(tail);
			tail.setNext(temp);
			tail = temp;
			
		}
		numElements++;
	}
	//Checks list is not empty.
  //gets value at the first node.
	public E getFirst() {
		if(head == null) {
			throw new NoSuchElementException();
		}
		return head.getData();
	}
  //Checks list is not empty.
  //gets value at the last node.
	public E getLast() {
		if(head == null) {
			throw new NoSuchElementException();
		}
		return tail.getData();
	}
	//Checks list is not empty.
  //Sets a temp variable to data at head.
  //sets head to next value in list.
  //Removes pointer to previous.
  //Returns removed value.
	public E removeFirst() {
		
		if(head == null) {
			throw new NoSuchElementException();
		}
		E answer = head.getData();
		head = head.getNext();
		
		if(head != null) {
			head.setPrevious(null);
		}
		numElements --;
		return answer; 
		
	}
	//Checks list is not empty.
  //Sets a temp variable to data at tail.
  //set tail to previous node in list.
  //Removes pointer to next bode.
  //Returns removed value.
	public E removeLast() {
		
		if(head == null) {
			throw new NoSuchElementException();
		}
		E answer = tail.getData();
		tail = tail.getPrevious();
		if(tail != null) {
			tail.setNext(null);
		}
		numElements --;
		return answer;
		
	}
	//Checks if the index exists in the list.
  //Checks if index is first or last.
  //Iterates to index and changes the 
  //pointers before and after to point
  //to the new node.
	public void add(int index, E element) {
		
		if(isOutOfBounds(index)) {
			throw new IndexOutOfBoundsException();
		}
		
		if(index == 0) {
			addFirst(element);
		}
		else if(index == size()-1) {
			addLast(element);
		}
		else {
			DListNode <E> pointer = marchToIndex(index - 1);
			DListNode <E> newNode = new DListNode<>(element, pointer, pointer.getNext());
			pointer.getNext().setPrevious(newNode);
			pointer.setNext(newNode);
			numElements++;
		}	
	}
	//Sets node pointers to null.
	public void clear() {
		head = null;
		tail = null;
	}
	//Checks if list contains an object by
  //calling if there is an indexOf this object.
	public boolean contains(Object obj) {
		return indexOf(obj) != 1;
	}
	//Checks if two LinkedLists are the same.
  //First checks they are the same object,
  //then iterates through lists and compares value
  //at each index.
  //Returns true if equal.
	public boolean equals(Object obj) {
		if(!(obj instanceof MyLinkedList)) {
			return false;
		}
		MyLinkedList <E> another = (MyLinkedList<E>) obj;
		if(another.size() != size()) {
			return false;
		}
		
		DListNode <E> iter = head;
		DListNode <E> anotherIter = another.head;
		while(iter != null) {
			if(!(iter.getData().equals(anotherIter.getData()))){
				return false;
			}
			iter = iter.getNext();
			anotherIter = anotherIter.getNext();
		}
		return true;
	}
	//Gets data at a certain index by iterating
  //through list.
	public E get(int index) {
		if(isOutOfBounds(index)) {
			throw new IndexOutOfBoundsException();
		}
		return marchToIndex(index).getData();
	}
	//Sets data at a certain index by iterating
  //through and calling a setter.
  //returns old value at index. 
	public E set(int index, E elements) {
		if(isOutOfBounds(index)) {
			throw new IndexOutOfBoundsException();
		}
		DListNode <E> iter = marchToIndex(index);
		E old = iter.getData();
		iter.setData(elements);
		return old;
	}
	//Iterates through list to return the index
  //of a certain object. 
  //Returns -1 if object not in the list. 
	public int indexOf (Object obj) {
		int index = 0;
		
		DListNode <E> iter = head;
		while(iter != null) {
			if(iter.getData().equals(obj)) {
				return index;
			}
			index++;
			iter = iter.getNext();
		}
		return -1;
	}
	//Checks if list size is zero.
	public boolean isEmpty() {
		return size() == 0;
	}
	
  //Returns iterator for the list.
	public Iterator <E> iterator(){
		return new MyLinkedListIterator();
	}
	//Gets the last index of an obhect 
  //by starting at the end of the list
  //and going backwards. 
	public int lastIndexOf(Object obj) {
		
		int index = size() -1;
		DListNode <E> iter = tail;
		
		while(iter != null) {
			if(iter.getData().equals(obj)) {
				return index;
			}
			index--;
			iter = iter.getPrevious();
		}
		
		return -1;
	}
	//Removes a node at an index.
  //Checks if last or first node.
  //Sets the previous of node to next node.
  //Sets previous of next to previous of the node.
	public E remove(int index) {
		if(isOutOfBounds(index)) {
			throw new IndexOutOfBoundsException();
		}
		if(index == 0) {
			return removeFirst();
		}
		else if( index == size()-1 ) {
			return removeLast();
		}
		else {
			
			DListNode <E> iter = marchToIndex(index);
			E old = iter.getData();
			iter.getPrevious().setNext(iter.getNext());
			iter.getNext().setPrevious(iter.getPrevious());
			numElements--;
			return old;
		}
	}
	//Removes a node of a certain object.
  //Checks list contains object then removes.
  //Returns false if object not in the list.
	public boolean remove(Object obj) {

		int index = indexOf(obj);
		if(index == -1) {
			return false;
		}
		else {
			remove(index);
			return true;
		}
	}
	//Returns number of elements in the list. 
	public int size() {
		return numElements;
	}
	//Overrides toString, which originally prints address
  //by printing the list values.
	public String toString() {
		
		StringBuilder sb = new StringBuilder();
		sb.append("[ ");
		for(E e: this) {
			sb.append( e + " ");
		}
		sb.append(" ]");
		return sb.toString();
		
	}
	//Checks if index is in the bounds of the list. 
	public boolean isOutOfBounds(int index) {
		return index < 0 || index >= size();
	}
	//Goes to the next node until the desired
  //index is reached. 
	private DListNode <E> marchToIndex(int index){
		
		if(isOutOfBounds(index)) {
			throw new IndexOutOfBoundsException();
		}
		int count = 0;
		DListNode <E> temp = head;
		while(count < index) {
			count++;
			temp = temp.getNext();
		}
		
		return temp;
	}
	//Iterator which can move and get to next node and
  //sees if list has a next value.
	private class MyLinkedListIterator implements Iterator <E>{
		
		private DListNode <E> pointer;
		
		public MyLinkedListIterator() {
			pointer = head;
		}
		
		public E next() {
			E old = pointer.getData();
			pointer = pointer.getNext();
			return old;
		}
		
		public boolean hasNext() {
			return pointer != null;
		}
		
		public void remove() {
			throw new UnsupportedOperationException();
		}
		
	}
	
	public static void main(String[] args) {
		MyLinkedList <Integer> aList = new MyLinkedList<> ();
		
		for( int i = 0; i < 10; i++) {
			aList.addFirst(i+1);
		}
		aList.toString;
	}

}
