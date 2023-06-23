/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adt;

import java.util.Iterator;

/**
 *
 * @author User
 */
public class ArrayStack<T> implements StackInterface<T> {

  private T[] array;
  private int topIndex; // index of top entry
  private static final int DEFAULT_CAPACITY = 50;

  public ArrayStack() {
    this(DEFAULT_CAPACITY);
  }

  public ArrayStack(int initialCapacity) {
    array = (T[]) new Object[initialCapacity];
    topIndex = -1;
  }

  @Override
  public void push(T newEntry) {
    topIndex++;

    if (topIndex < array.length) {
      array[topIndex] = newEntry;
    }
  }

  @Override
  public T peek() {
    T top = null;

    if (!isEmpty()) {
      top = array[topIndex];
    }

    return top;
  } 
  
 

  @Override
  public T pop() {
    T top = null;

    if (!isEmpty()) {
      top = array[topIndex];
      array[topIndex] = null;
      topIndex--;
      
     
    } // end if
    
    return top;
  } 

  @Override
  public boolean isEmpty() {
    return topIndex < 0;
  }
  
  @Override
  public T get(int givenPosition) {  
      T result = null;
      
      if (givenPosition >=0 && givenPosition < array.length){
        result= array[givenPosition];
      }
      return result;
  }
  
  @Override
  public boolean contains(T entry) {
  for (int i = 0; i <= topIndex; i++) {
    if (array[i].equals(entry)) {
      return true;
    }
  }
  return false;
}
  
   @Override
  public int size() {
  return topIndex + 1;
}
  
  

  @Override
  public void clear() {
    topIndex = -1;
  } 
  
  @Override
  public int topIndex() {
    return topIndex;
  } 
  
  
  @Override
  public Iterator<T> getiterator() {
    return new ArrayStackIterator();
  }

  private class ArrayStackIterator implements Iterator<T> {

    private int currentIndex;

    public ArrayStackIterator() {
      currentIndex = topIndex;
    }

    @Override
    public boolean hasNext() {
      return currentIndex >= 0;
    }

    @Override
    public T next() {
      T entry = array[currentIndex];
      currentIndex--;
      return entry;
    }
  
    
  }
  
  
} 
