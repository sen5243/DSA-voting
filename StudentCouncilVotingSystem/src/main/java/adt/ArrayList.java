package adt;

import java.io.Serializable;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayList<T> implements ListInterface<T>, Serializable, Iterable<T> {

    private T[] array;
    private int numberOfEntries;
    private static final int DEFAULT_CAPACITY = 10;

//Initialization of the arrayList
    public ArrayList() {
        this(DEFAULT_CAPACITY);
    }

    public ArrayList(int initialCapacity) {
        numberOfEntries = 0;
        array = (T[]) new Object[initialCapacity];
    }

    @Override
    public Iterator<T> iterator() {
        return new ArrayIterator();
    }

//Iterator to be used for loop the element in the arrayList using for-each loop
    private class ArrayIterator implements Iterator<T> {

        private int tempIndex = 0;

        @Override
        public boolean hasNext() {
            return tempIndex < numberOfEntries;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            T element = array[tempIndex];
            tempIndex++;
            return element;
        }

    }

// The newEntry will be added to the end of the array of the list and double the array size if the array is full while adding it. It is used in many kind of situations including adding candidate in the admin module.
    @Override
    public boolean add(T newEntry) {
        if (isFull()) {
            doubleArray();
        }
        array[numberOfEntries] = newEntry;
        numberOfEntries++;
        return true;
    }

//The newEntry will be added to the specific position of the array of the arrayList, a space will be made at the specified location in the array and the entries at and after the new entry will be moved to the next position using makeRoom() method.
    @Override
    public boolean add(int newPosition, T newEntry) {
        boolean isSuccessful = true;

        if ((newPosition >= 1) && (newPosition <= numberOfEntries + 1)) {
            makeRoom(newPosition);
            array[newPosition - 1] = newEntry;
            numberOfEntries++;
        } else {
            isSuccessful = false;
        }

        return isSuccessful;
    }

//The entry at the specified position will be removed, and the gap after the entry is removed will be covered by moving the entries after the givenPosition to the previous position. The array will be resized and returned after the operation. It is used in the removeCandidate() method in the admin module.
    @Override
    public T remove(int givenPosition) {
        T result = null;

        if ((givenPosition >= 1) && (givenPosition <= numberOfEntries)) {
            result = array[givenPosition - 1];

            if (givenPosition < numberOfEntries) {
                removeGap(givenPosition);
            }

            numberOfEntries--;
        }

        return result;
    }

//Set the numberOfEntries of the arrayList to 0 in order to clear the element of the arrayList. It is used to clear the candidate information  when the new poll is started.
    @Override
    public void clear() {
        numberOfEntries = 0;
    }

//Replace the entry in the givenPosition with newEntry, the position is -1 in order to use it as the index of the array to determine the position and replace the entry in the given index with newEntry by replacing the value. It is used to update the information of the candidate in the admin module.
    @Override
    public boolean replace(int givenPosition, T newEntry) {
        boolean isSuccessful = true;

        if ((givenPosition >= 1) && (givenPosition <= numberOfEntries)) {
            array[givenPosition - 1] = newEntry;
        } else {
            isSuccessful = false;
        }

        return isSuccessful;
    }

// Get entry in the list by using the position given, the position is minus by 1 in order to use it as the index of the array
    @Override
    public T getEntry(int givenPosition) {
        T result = null;

        if ((givenPosition >= 1) && (givenPosition <= numberOfEntries)) {
            result = array[givenPosition - 1];
        }

        return result;
    }

//For searching the entry stored in the arrayList, sequential search is applied in this method by looping through the value one by one and comparing the given entry with the existing entry in the loop using equals method, if the entry is found, the result will be returned as true. This method has been used for the detailedSearch() operation of the admin module with the overridden method of the candidate class.
    @Override
    public boolean contains(T anEntry) { //sequential search
        boolean found = false;
        for (int index = 0; !found && (index < numberOfEntries); index++) {
            if (anEntry.equals(array[index])) {
                found = true;
            }
        }

        return found;
    }

//Get number of entries of the list
    @Override
    public int getNumberOfEntries() {
        return numberOfEntries;
    }

//Check whether the list is empty
    @Override
    public boolean isEmpty() {
        return numberOfEntries == 0;
    }

//Check whether the list is full by checking the numberOfEntries with array length
    @Override
    public boolean isFull() {
        return numberOfEntries == array.length;
    }

//Get the size of the list by returning the number of entries.
    @Override
    public int size() {
        return numberOfEntries;
    }

//toString method to loop through the array and list down the entry of the array as a combined String
    @Override
    public String toString() {
        String outputStr = "";
        for (int index = 0; index < numberOfEntries; ++index) {
            outputStr += array[index] + "\n";
        }

        return outputStr;
    }

//Used for double to size of the array, the entries of the array will be temporarily stored at the oldArray, and the array itself will be assigned with the generic type of array with double of the size. After that, the entries in the oldArray will be assigned again to the array using a for loop.
    private void doubleArray() {
        T[] oldArray = array; // take array to new array
        array = (T[]) new Object[2 * oldArray.length];

        for (int i = 0; i < numberOfEntries; i++) {
            array[i] = oldArray[i];
        }
    }

//Convert the list into an array, the array will be resized if the size of the array is smaller than the arrayList (numberOfEntries), the array will be cleared if there are any entries in the array before adding the entries into it. Arraycopy is used for copying the contents of the array of the arrayList to the newArray.
    @Override
    public <T> T[] toArray(T[] newArray) {
        if (newArray.length < numberOfEntries) {
            System.out.println("The array size is not enough for the arrayList data");
            newArray = (T[]) new Object[2 * array.length];
        } else if (newArray.length > numberOfEntries) {
            System.out.println("The array is cleared for copying data from arrayList");
            newArray[numberOfEntries] = null;
        }

        System.arraycopy(array, 0, newArray, 0, numberOfEntries);
        return newArray;
    }

// Method to swap two entries in the array
    private static <T> void swap(T[] a, int i, int j) {
        T temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

// Method to sort the array in ascending order using bubble sort, by comparing the entries one by one and swapping them if the value of the previous entry is greater than the next entry in the list.
    @Override
    public <T extends Comparable<T>> void bubbleSort(T[] a, int n) {
        boolean sorted = false;
        for (int pass = 1; pass < n && !sorted; pass++) {
            sorted = true;
            for (int index = 0; index < n - pass; index++) {
                // swap adjacent elements if first is greater than second
                System.out.println(index);
                if (a[index].compareTo(a[index + 1]) > 0) {
                    swap(a, index, index + 1); // swap adjacent elements 
                    sorted = false;  // array not sorted because a swap was performed
                }
            }
        }
    }

// Method to sort the array in descending order using bubble sort, by comparing the entries one by one and swapping them if the value of the previous entry is smaller than the next entry in the list.
    @Override
    public <T extends Comparable<T>> void reversedBubbleSort(T[] a, int n) {
        boolean sorted = false;
        for (int pass = 1; pass < n && !sorted; pass++) {
            sorted = true;
            for (int index = 0; index < n - pass; index++) {
                // swap adjacent elements if first is greater than second
                System.out.println(index);
                if (a[index].compareTo(a[index + 1]) < 0) {
                    swap(a, index, index + 1); // swap adjacent elements 
                    sorted = false;  // array not sorted because a swap was performed
                }
            }
        }
    }

// Makes room for a new entry at newPosition, the entries of the newPosition and after the newPosition will be moved to the next position.
    private void makeRoom(int newPosition) {
        int newIndex = newPosition - 1;
        int lastIndex = numberOfEntries - 1;

        // move each entry to next higher index, starting at end of
        // array and continuing until the entry at newIndex is moved
        for (int index = lastIndex; index >= newIndex; index--) {
            array[index + 1] = array[index];
        }
    }

    //Shift the entries after the givenPosition to the previous position in order to fill in the gap and resize the array
    private void removeGap(int givenPosition) {
        // move each entry to next lower position starting at entry after the
        // one removed and continuing until end of array
        int removedIndex = givenPosition - 1;
        int lastIndex = numberOfEntries - 1;

        for (int index = removedIndex; index < lastIndex; index++) {
            array[index] = array[index + 1];
        }
    }
}
