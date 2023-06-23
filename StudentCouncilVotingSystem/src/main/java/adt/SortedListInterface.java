/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package adt;

import java.util.Iterator;

/**
 *
 * @author Dextor
 * @param <T>
 */
public interface SortedListInterface<T extends Comparable<T>> {

    /**
     * Task: Adds a new entry to the sorted list in its proper order.
     *
     * @param newEntry the object to be added as a new entry
     * @return true if the addition is successful
     */
    public boolean add(T newEntry);

    /**
     * Task: Removes a specified entry from the sorted list.
     *
     * @param anEntry the object to be removed
     * @return true if anEntry was located and removed
     */
    public boolean remove(T anEntry);

    public boolean contains(T anEntry);

    public void clear();

    public T getEntry(int givenPosition);
    
    public int getNumberOfEntries();

    public boolean isEmpty();

    Iterator<T> getIterator();
}
