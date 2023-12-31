package adt;

public interface ListInterface<T> {

    public boolean add(T newEntry);

    public boolean add(int newPosition, T newEntry);

    public T remove(int givenPosition);

    public void clear();

    public boolean replace(int givenPosition, T newEntry);

    public T getEntry(int givenPosition);

    public boolean contains(T anEntry);

    public int getNumberOfEntries();

    public boolean isEmpty();

    public boolean isFull();

    public int size();

    public <T> T[] toArray(T[] newArray);

    public <T extends Comparable<T>> void bubbleSort(T[] a, int n);

    public <T extends Comparable<T>> void reversedBubbleSort(T[] a, int n);

}
