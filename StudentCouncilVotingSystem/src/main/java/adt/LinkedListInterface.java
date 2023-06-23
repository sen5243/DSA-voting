/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adt;

import java.util.Iterator;

/**
 *
 * @author Human
 * @param <T>
 */
public interface LinkedListInterface<T> {
    
    //This method adds an entry to the end of the list
    public boolean add (T newEntry);
    
    //This method adds an entry at specified position
    public boolean add (int newPosition, T newEntry);
    
    //This method removes an entry at specified position
    public T remove (int givenPosition);
    
    //This method will remove all entries from the list
    public void clear();
    
    //This method will replace an entry at specified position with a new entry
    public boolean replace (int givenPosition, T newEntry);
    
    //This method will retrieve an entry at specified position
    public T getEntry(int givenPosition);
    
    //This method checks whether the list contains an entry
    public boolean contains(T anEntry);
    
    //This method will get the current number of entries in the list
    public int getNumberOfEntries();
    
    //This method will check if the list is empty
    public boolean isEmpty();

    //This method return iterator over the elements in the list
    public Iterator<T> getIterator();
    
    //This method will return the index of anEntry in the list; otherwise return -1
    public int indexOf(T anEntry);
}
