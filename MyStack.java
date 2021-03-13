
//Stack interface declaring methods
public interfae MyStack <E> {
  public void push (E e);
  public E pop();
  public E peek();
  public boolean isEmpty();
}
//Stack implementation.
import java.util.*;
public class AStack <E> implements MyStack <E>{
  private ArrayList <E> list;
//constructor
  public AStack(){
    list = newArrayList<>();
  }
//Stack is last-in-last-out so adds to end
  public void push(E e){
    list.add(e);
  }
//Returns last thing added to stack.
  public E peek(){
    if(isEmpty()){
      throw new NoSuchElementException();
    }
    return list.get(list.size()-1);
  }
//Returns and removes last thing added to stack.
  public E pop(){
    if(isEmpty()){
      throw new NoSuchElementException();
    }
    return list.remove(list.size()-1);
  }
//Checks if there are elements in the stack
  public boolean isEmpty(){
    return list.size() == 0;
  }

}