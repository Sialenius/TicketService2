package main.java.model;

import java.util.Arrays;

public class CustomArrayList<T> {
    private final static int DEFAULT_CAPACITY = 10;

    private T[] list;
    private int capacity;
    private int filled = 0;

    public CustomArrayList() {
        this(DEFAULT_CAPACITY);
    }

    public CustomArrayList(int capacity) {
        list = (T[]) new Object[capacity];
        this.capacity = capacity;

    }

    public T[] getArray() {
        return list;
    }

    public int getFilledSpots() {
        return filled;
    }


    private boolean checkAvailableSpots() {
        boolean isAvailable = true;
        if (capacity == filled) {
            isAvailable = false;
        }
        return isAvailable;
    }

    private void increaseCapacity() {
        int newCapacity = capacity * 2;
        T[] newList = (T[]) new Object[newCapacity];
        for (int i = 0; i < list.length; i++) {
            newList[i] = list[i];
        }
        list = newList;
        capacity = newCapacity;
    }

    public void put(T obj) {
        if (!checkAvailableSpots()) {
            increaseCapacity();
        }
        list[filled++] = obj;
    }

    public T get(int index) {
        if (index < filled) {
            return list[index];
        } else {
            throw new IllegalArgumentException ("Wrong item's index.");
        }
    }

    public void delete(int index) {
        if (index >= filled) {
            throw new IllegalArgumentException("Wrong item's index");
        }
        else if (index == filled - 1) {
                filled--;
            } else {
            for (int i = index; i < filled - 1; i++) {
                list[i] = list[i+1];
            }
            filled--;
        }
    }

    @Override
    public boolean equals(Object obj) {
        boolean isEqual = true;

        CustomArrayList<T> testList = (CustomArrayList<T>) obj;

        if (testList == null |
                this.getFilledSpots() != testList.getFilledSpots()) {
            isEqual = false;

        } else {
            for (int i = 0; i < this.filled; i++) {
                if (!this.get(i).equals(testList.get(i))) {
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
        result += 31 * filled;

        return result;
    }

    @Override
    public String toString() {
        String returnValue = "";
        for (int i = 0; i < filled; i++) {
            returnValue += list[i];
            returnValue += ' ';
        }
        return returnValue;
    }
}
