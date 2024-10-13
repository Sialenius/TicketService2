package model;

import java.util.Arrays;

public class CustomArrayList<T> implements Printable{
    private final static int DEFAULT_CAPACITY = 10;

    private T[] list;
    private int size = 0;

    public CustomArrayList() {
        this(DEFAULT_CAPACITY);
    }

    public CustomArrayList(int capacity) {
        list = (T[]) new Object[capacity];

    }

    public T[] getArray() {
        return list;
    }

    public int getSize() {
        return size;
    }


    private boolean checkAvailableSpots() {
        boolean isAvailable = true;
        if (list.length == size-1) {
            isAvailable = false;
        }
        return isAvailable;
    }

    private void increaseCapacity() {
        int newSize = size * 2;
        T[] newList = (T[]) new Object[newSize];
        for (int i = 0; i < list.length; i++) {
            newList[i] = list[i];
        }
        list = newList;
    }

    public void put(T obj) {
        if (!checkAvailableSpots()) {
            increaseCapacity();
        }
        list[size++] = obj;
    }

    public T get(int index) {
        if (index < size) {
            return list[index];
        } else {
            throw new IllegalArgumentException ("Wrong item's index.");
        }
    }

    public void delete(int index) {
        if (index >= size) {
            throw new IllegalArgumentException("Wrong item's index");
        }
        else if (index == size - 1) {
                size--;
            } else {
            for (int i = index; i < size - 1; i++) {
                list[i] = list[i+1];
            }
            size--;
        }
    }


    public boolean equals(CustomArrayList<T> secondList) {
        boolean isEqual = true;

        if (secondList == null |
                this.getSize() != secondList.getSize()) {
            isEqual = false;

        } else {
            for (int i = 0; i < this.size; i++) {
                if (!this.get(i).equals(secondList.get(i))) {
                    isEqual = false;
                    break;
                }
            }
        }
        return isEqual;
    }

    @Override
    public int hashCode() {
        int result = 1;

        result += (int) 31 * Arrays.hashCode(list);
        result += 31 * size;

        return result;
    }

    @Override
    public String toString() {
        String returnValue = "";
        for (int i = 0; i < size; i++) {
            returnValue += list[i];
            returnValue += ' ';
        }
        return returnValue;
    }
}
