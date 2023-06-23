/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package adt;

import java.util.Iterator;

/**
 *
 * @author User
 */
public interface StackInterface<T> {

  /**
   * Task: Adds a new entry to the top of the stack.
   *
   * @param newEntry an object to be added to the stack
   */
  public void push(T newEntry);

  /**
   * Task: Removes and returns the stack's top entry.
   *
   * @return either the object at the top of the stack or, if the stack is empty
   * before the operation, null
   */
  public T pop();

  /**
   * Task: Retrieves the stack's top entry.
   *
   * @return either the object at the top of the stack or null if the stack is
   * empty
   */
  public T peek();

  /**
   * Task: Detects whether the stack is empty.
   *
   * @return true if the stack is empty
   */
  public boolean isEmpty();

  /**
   * Task: Removes all entries from the stack
   */
  public void clear();
  public int topIndex();
  public Iterator<T> getiterator();
  
  
  public T get(int givenPosition); 
  public boolean contains(T entry);
  public int size();
} // end StackInterface

