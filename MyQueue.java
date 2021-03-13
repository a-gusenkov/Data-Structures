//interface for a queue
public interface MyQueue <E>{
  public E peek();
  public E poll();
  public boolean add (E e);
  public boolean isEmpty();
}
//Implementation of queue.
import java.util.*
public class LQueue <E> implements MyQueue <E>{
  //Declared LinkedList object to 
  //represents a queue.
  private LinkedList <E> list;
  public LQueue(){
    list = new LinkedList<>();
  }
  //Queue is first-in-first-out
  //so when you add it adds to
  //the back of the queue.
  public boolean add(E e){
    list.addLast(e);
    return true;
  }
  //Checks if queue is empty.
  public boolean isEmpty(){
    return list.isEmpty();

  }
  //Returns data at first node of //queue.
  public E peek(){
    if(isEmpty){
      throw new NoSuchElementException();
    }
    return list.getFirst();
  }
  //Removes first elements in queue.
  public E poll(){
    if(isEmpty()){
      throw new NoSuchElementException();
    }

    return list.removeFirst();
  }
  //Overrides toString to print list
  //representation of queue.
  public static <E> string toString (MyQueue <E> queue){
    StringBuilder sb = newStringBuilder();
    MyQueue <E> temp = new LQueue<>();

    while(!queue.isEmpty()){
      sb.append(queue.peek() + " ");
      temp.add(queue.poll());
    }
    while(!temp.isEmpty()){
      queue.add(temp.poll());
    }
    return sb.toString();
  }
  //main
  public static void main(String[] args) {
    //checks queue methods
    MyQueue<Character> queue = new LQueue<>();
    for(char ch = 'a'; ch <= 'f'; ch++){
      queue.add(ch);
    }
    System.out.println(toString(queue));
    for(int i = 0; i < 3; i++){
      queue.poll();
    }
    System.out.println(toString(queue));
  }
}