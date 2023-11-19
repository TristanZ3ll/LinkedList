
import java.util.Iterator;
import java.util.ListIterator;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Collection;

public class Exercise24_03 {
  public static void main(String[] args) {
    new Exercise24_03();
  }
  
  public Exercise24_03() {
    TwoWayLinkedList<Double> list = new TwoWayLinkedList<>();
    System.out.print("Enter five numbers: ");
    Scanner input = new Scanner(System.in);
    double[] v = new double[5];
    for (int i = 0; i < 5; i++) 
      v[i] = input.nextDouble();

    list.add(0,v[1]);
    
    list.add(1,v[2]);
    
    list.add(2,v[3]);
    
    list.add(3,v[4]);
    
    list.add(0, v[0]);
    
    list.add(2, 10.55);
    
    list.remove(3);

    java.util.ListIterator<Double> iterator1 = list.listIterator();
    System.out.print("The list in forward order: ");
    while (iterator1.hasNext())
      System.out.print(iterator1.next() + " ");

    java.util.ListIterator<Double> iterator2 = list.listIterator(list.size() - 1);
    System.out.print("\nThe list in backward order: ");
    while (iterator2.hasPrevious())
      System.out.print(iterator2.previous() + " ");
  }
} 

interface MyList<E> extends java.util.Collection<E> {
  /** Add a new element at the specified index in this list */
  public void add(int index, E e);

  /** Return the element from this list at the specified index */
  public E get(int index);

  /** Return the index of the first matching element in this list.
   *  Return -1 if no match. */
  public int indexOf(Object e);

  /** Return the index of the last matching element in this list
   *  Return -1 if no match. */
  public int lastIndexOf(E e);

  /** Remove the element at the specified position in this list
   *  Shift any subsequent elements to the left.
   *  Return the element that was removed from the list. */
  public E remove(int index);

  /** Replace the element at the specified position in this list
   *  with the specified element and returns the new set. */
  public E set(int index, E e);
  
  @Override /** Add a new element at the end of this list */
  public default boolean add(E e) {
    add(size(), e);
    return true;
  }

  @Override /** Return true if this list contains no elements */
  public default boolean isEmpty() {
    return size() == 0;
  }

  @Override /** Remove the first occurrence of the element e 
   *  from this list. Shift any subsequent elements to the left.
   *  Return true if the element is removed. */
  public default boolean remove(Object e) {
    if (indexOf(e) >= 0) {
      remove(indexOf(e));
      return true;
    }
    else
      return false;
  }

  @Override
  public default boolean containsAll(Collection<?> c) {
    // Left as an exercise
    return true;
  }

  @Override
  public default boolean addAll(Collection<? extends E> c) {
    // Left as an exercise
    return true;
  }

  @Override
  public default boolean removeAll(Collection<?> c) {
    return true;
  }

  @Override
  public default boolean retainAll(Collection<?> c) {
      return true;
  }
    

  @Override
  public default Object[] toArray() {
    // Left as an exercise
    return null;
  }

  @Override
  public default <T> T[] toArray(T[] array) {
    // Left as an exercise
    return null;
  }
}

class TwoWayLinkedList<E> implements MyList<E> {
  private Node<E> head, tail;
  private int size = 0;

  /** Create a default list */
  public TwoWayLinkedList() {
  }

  /** Create a list from an array of objects */
  public TwoWayLinkedList(E[] objects) {
    for (E e : objects)
      add(e);
  }


   @Override //remove the element at the specified position in this list return element that was removed
    public E remove(int index){
        Node<E> current= head;
        if (index > size || index < 0){
            return null;
        }
        for(int i = 0; i < index; i++){
            current = current.next;
        }
        
        (current.next).previous = current.previous;
        (current.previous).next = current.next;
        size--;
        return current.element;
        

    }
  /** Return the head element in the list */
  public E getFirst() {
    if (size == 0) {
      return null;
    } else {
      return head.element;
    }
  }

  /** Return the last element in the list */
  public E getLast() {
    if (size == 0) {
      return null;
    } else {
      return tail.element;
    }
  }

  @Override
  public String toString() {
    StringBuilder result = new StringBuilder("[");

    Node<E> current = head;
    for (int i = 0; i < size; i++) {
      result.append(current.element);
      current = current.next;
      if (current != null) {
        result.append(", "); // Separate two elements with a comma
      } else {
        result.append("]"); // Insert the closing ] in the string
      }
    }

    return result.toString();
  }

  /** Clear the list */
  public void clear() {
    head = tail = null;
  }

  public boolean contains(Object e){
        Node<E> current = head;
        for(int i =0; i < size; i++){
            if(current.element == e){
                return true;
            }else{
                current = current.next;
            }
            
        }
        return false;
        
    }

  /** Return the element from this list at the specified index */
  public E get(int index) {
    Node<E> current =head;
    for(int i=0; i< index; i++){
      current = current.next;
    }
    return current.element;
  }

  /**
   * Return the index of the head matching element in this list. Return -1 if
   * no match.
   */
  public int indexOf(Object e){
          Node<E> current = head;
          for(int i = 0; i< size; i++){
              if(current.element == e){
                  return i;
              }
          }
          return -1;
      }


  /**
   * Return the index of the last matching element in this list Return -1 if
   * no match.
   */
  public int lastIndexOf(E e){
          Node<E> current = tail;
          for(int i = size; i>0; i--){
              if (current.element == e){
                  return i;
              }
          }
          return -1;
      }
  /**
   * Replace the element at the specified position in this list with the
   * specified element.
   */
    public E set(int index, E e){
          if(index > size || index < 0){
              return null;
          }
          Node<E> current = head;
          for(int i=0;i < index; i++){
              current= current.next;

              }
              current.element = e;
              return current.element;
          }
      //Add an element to the beginning of the list
    public void addFirst(E e){
        Node <E> newNode = new Node<>(e);
        newNode.next = head;
        head = newNode;
        
        size++;
        if (tail == null){
            tail = head;
            tail.next= null;
            head.previous=null;
        }else{
          head.next.previous=head;
        }

    }



    
//add an element to the end of the list
    public void addLast(E e){
        Node<E> newNode = new Node<>(e);
        tail.next = newNode;
        newNode.previous = tail;
        tail = newNode;
        size++;
        if(head == null){
            head=tail;
            tail.next= null;
            head.next=null;
        }else{

        }
    }

 //remove the last node and return object contained

    public E removeLast(){
        Node<E> temp = tail;
        tail=tail.previous;
        tail.next=null;
        if(tail==null){
            head= null;
        }
        size--;
        return temp.element;
    }


    //remove the head node and return the ooject that is contained in the removed list
    public E removeFirst(){
        if (head == null){
            return null;
        }else{
            Node<E> temp = head;
            head = head.next;
            head.previous=null;
            size--;
            if (head==null){
                tail=null;
            }
            return temp.element;

        }
    }
          @Override // add a new element at the speccified index in this list. the index of the head element is zero
    public void add(int index, E e){
      
        if(index == 0){
            addFirst(e);
        }else if(index >= size){
                
                addLast(e);
            }else{
                Node<E> current = head;
                for(int i = 0; i < index; i++){
                    current = current.next;
                }
                Node<E> temp = current.next;
                current.next=new Node<>(e);
                current.next.next = temp;
                temp.previous = current.next;
                temp.previous.previous=current;
                
                size++;
            }
            
        }
    
  private class LinkedListIterator implements java.util.ListIterator<E> {
    private Node<E> current = head; // Current index

    public LinkedListIterator() {
    }
    
    public LinkedListIterator(int index) {
      if (index < 0 || index >= size)
        throw new IndexOutOfBoundsException("Index: " + index + ", Size: "
          + size);
      for (int nextIndex = 0; nextIndex < index; nextIndex++)
        current = current.next;
    }
    
    public void setLast() {
  	current = tail;
    }
    
    @Override
    public boolean hasNext() {
      return (current != null);
    }

    @Override
    public E next() {
      E e = current.element;
      current = current.next;
      return e;
    }

    @Override
    public void remove() {
      int index = indexOf(current);
      for(int i = 0; i < index; i++){
            current = current.next;
        }
        (current.next).previous = current.previous;
        (current.previous).next = current.next.previous;
        size--;
    }

    @Override
    public void add(E e) {
      Node<E> temp = current.next;
      current.next=new Node<>(e);
      current.next.next = temp;
      temp.previous = current.next;
      temp.previous.previous=current;
      size++;
      
    }

    @Override
    public boolean hasPrevious() {
      return current != null;
    }

    @Override
    public int nextIndex() {
      return indexOf(current.next);
    }

    @Override
    public E previous() {
      E e = current.element;
      current = current.previous;
      return e;
    }

    @Override
    public int previousIndex() {
      return indexOf(current.previous);
    }

    @Override
    public void set(E e) {
      current.element = e; 
    }
  }

  private class Node<E> {
    E element;
    Node<E> next;
    Node<E> previous;

    public Node(E e) {
      element = e;
    }
  }

  @Override
  public int size() {
    return size;
  }

  public ListIterator<E> listIterator() {
    return new LinkedListIterator(); 
  }
  
  public ListIterator<E> listIterator(int index) {
    return new LinkedListIterator(index); 
  }

  @Override
  public Iterator<E> iterator() {
    return null;
  }
}