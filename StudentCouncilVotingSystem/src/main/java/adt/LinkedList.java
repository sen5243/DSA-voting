/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adt;

import java.io.Serializable;
import java.util.Iterator;

/**
 *
 * @author Human
 * @param <T>
 */
public class LinkedList<T> implements LinkedListInterface<T>, Serializable {

    private Node firstNode;
    private int numberOfEntries;

    public LinkedList() {
        clear();
    }

    @Override
    public boolean add(T newEntry) {
        // create the new node
        Node newNode = new Node(newEntry);          

        if (isEmpty()) {
            firstNode = newNode;
        // add to end of nonempty list
        } else {
                  
            Node currentNode = firstNode;
        // traverse linked list with pointer pointing to the current node  
        // while have not reached the last node
            while (currentNode.next != null) {      
                currentNode = currentNode.next;
            }
            // make last node reference new node
            currentNode.next = newNode;
        }
        numberOfEntries++;
        return true;
    }

    @Override
    public boolean add(int newPosition, T newEntry) {
        boolean isSuccessful = true;

        if ((newPosition >= 1) && (newPosition <= numberOfEntries + 1)) {
            Node newNode = new Node(newEntry);
            // case 1: add to beginning of list
            if (isEmpty() || (newPosition == 1)) {              
                newNode.next = firstNode;
                firstNode = newNode;
            // case 2: list is not empty and newPosition > 1
            } else {						
                Node nodeBefore = firstNode;
                for (int i = 1; i < newPosition - 1; ++i) {
                    // advance nodeBefore to its next node
                    nodeBefore = nodeBefore.next;		
                }
                // make new node point to current node at newPosition
                newNode.next = nodeBefore.next;
                // make the node before point to the new node
                nodeBefore.next = newNode;                      
            }
            numberOfEntries++;
        } else {
            isSuccessful = false;
        }
        return isSuccessful;
    }

    @Override
    public T remove(int givenPosition) {
        // return value
        T result = null;                                        

        if ((givenPosition >= 1) && (givenPosition <= numberOfEntries)) {
            // case 1: remove first entry
            if (givenPosition == 1) {
                // save entry to be removed
                result = firstNode.data;                        
                firstNode = firstNode.next;
            // case 2: givenPosition > 1
            } else {                                            
                Node nodeBefore = firstNode;
                for (int i = 1; i < givenPosition - 1; ++i) {
                    // advance nodeBefore to its next node
                    nodeBefore = nodeBefore.next;		
                }
                // save entry to be removed
                result = nodeBefore.next.data;  
                // make node before point to node after the
                // one to be deleted (to disconnect node from chain)
                nodeBefore.next = nodeBefore.next.next;     	
            } 							
            numberOfEntries--;
        }
        // return removed entry, or null if operation fails
        return result;                                          
    }

    @Override
    public final void clear() {
        firstNode = null;
        numberOfEntries = 0;
    }

    @Override
    public boolean replace(int givenPosition, T newEntry) {
        boolean isSuccessful = true;

        if ((givenPosition >= 1) && (givenPosition <= numberOfEntries)) {
            Node currentNode = firstNode;
            for (int i = 0; i < givenPosition - 1; ++i) {
                // advance currentNode to next node
                currentNode = currentNode.next;		
            }
            // currentNode is pointing to the node at givenPosition
            currentNode.data = newEntry;	
        } else {
            isSuccessful = false;
        }
        return isSuccessful;
    }

    @Override
    public T getEntry(int givenPosition) {
        T result = null;

        if ((givenPosition >= 1) && (givenPosition <= numberOfEntries)) {
            Node currentNode = firstNode;
            for (int i = 0; i < givenPosition - 1; ++i) {
                // advance currentNode to next node
                currentNode = currentNode.next;		
            }
            // currentNode is pointing to the node at givenPosition
            result = currentNode.data;	
        }
        return result;
    }

    @Override
    public boolean contains(T anEntry) {
        boolean found = false;
        Node currentNode = firstNode;

        while (!found && (currentNode != null)) {
            if (anEntry.equals(currentNode.data)) {
                found = true;
            } else {
                currentNode = currentNode.next;
            }
        }
        return found;
    }

    @Override
    public int getNumberOfEntries() {
        return numberOfEntries;
    }

    @Override
    public boolean isEmpty() {
        boolean result;

        result = numberOfEntries == 0;

        return result;
    }

    @Override
    public int indexOf(T anEntry) {
        int index = 1;
        Node currentNode = firstNode;
        while (currentNode != null) {
            if (anEntry.toString().equals(currentNode.data.toString())) {
                return index;
            }
            index++;
            currentNode = currentNode.next;
        }
        return -1;
    }

    @Override
    public Iterator<T> getIterator() {
        return new LinkedListIterator();
    }

    private class LinkedListIterator implements Iterator<T> {

        private Node currentNode;

        public LinkedListIterator() {
            currentNode = firstNode;
        }

        @Override
        public boolean hasNext() {
            return currentNode != null;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                System.out.println("No next!");
            }
            T data = currentNode.data;
            currentNode = currentNode.next;
            return data;
        }
    }

    private class Node implements Serializable {

        private T data;
        private Node next;

        private Node(T data) {
            this.data = data;
            this.next = null;
        }

        private Node(T data, Node next) {
            this.data = data;
            this.next = next;
        }
    }

}
