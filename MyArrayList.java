import java.util.*;
public class MyArrayList<E> implements Iterable <E>{
	
  //inner class to iterate through array
  public class ArrayIterator<E> implements Iterator <E>{
    private int index;
    public ArrayIterator(){
      index = 0;
    }
    public E next(){
      index++;
      return (E) array[index-1];
    }
    public boolean hasNext(){
      return index < numElements;
    }
    public void remove(){
      throw new UnsupportedOperationException();
    }
  }
  //instance variables
  private E[] array;
  private int numElements;

  //constructor
  public MyArrayList(){

    //array of object cast to E since Java doesnt know until runtime what E is
    array = (E[]) new Object [10];
    numElements = 0;
  }

  //methods
  public int size(){
    return numElements;
  }
  //Returns true if add was successful 
  //adds item to end of array
  public boolean add( E item){
    if(numElements == array.length)
    	//method to grow array size
        grow();
    array [numElements] = item;
    numElements++;
    return true;
  }
  //Doubles the size of the array.
  private void grow(){
    E[] temp = (E[])new Object[2 * array.length];
    for (int i =0; i < array.length; i++){
       temp[i] = array[i];
    }
    array = temp;
  }
  //Returns value at certain index.
  public E get(int index){
    if(index < 0 || index >= size())
        throw new IndexOutOfBoundsException();
    return array[index];
  }
  //Sets new value at index, returns old value.
  public E set( int index, E item){
    if ( index < 0 || index >= size())
        throw new IndexOutOfBoundsException();
    E old = array[index];
    array[index] = item;
    return old;
  }
  //Returns index of certain value. Returns -1 if value not found.
  public int indexOf( Object o){
    for (int i =0; i < size(); i++){
        if (array[i].equals(0))
          return i;
    }
    return -1;
  }
  //Checks if array contains an Object by calling indexOf.
  public boolean contains(Object o){
    return indexOf(o)!= -1;
  }
//Removes value at index, returns old value and decreases element size.
  public E remove(int index){

    if(index < 0 || index >= size())
        throw new IndexOutOfBoundsException();
    E old = array[index];
    for( int i = index; i <size()-1; i++){
        array[i]= array[i+1];
        numElements--;
    }
    return old;
  }
 //Finds index of desired object and removes (if object exists).
  public boolean remove(Object o){
    int index = indexOf(o);
    if(index == -1)
        return false;
    remove(index);
    return true;
  }
  //Returns a sublist ArrayList consisting of desired values.
  public MyArrayList<E> subList (int from, int to){
    MyArrayList<E> temp;
    temp= new MyArrayList< >();
    for( int i= from; i<to; i++){
        temp.add(get(i));
    }
    return temp;
  }
  //Returns address of object that knows how to iterate.
  public Iterator <E> iterator(){
    return new ArrayIterator<E>();
  }
}