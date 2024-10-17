package main.java.model;


public class CustomHashSet<T> implements Container {
    private final static int DEFAULT_CAPACITY = 10;
    private final double LOAD_FACTOR = 0.75;

    private T[] set;
    private int capacity = 0;
    private int filled = 0;

    public CustomHashSet() {
        this(DEFAULT_CAPACITY);
    }

    public CustomHashSet(int capacity) {
        set = (T[]) new Object[capacity];
        this.capacity = capacity;
    }

    private int getCapacity() {
        return capacity;
    }

    public void put(Object obj) {
        if (!this.contains(obj)) {
            if (this.filled >= capacity * LOAD_FACTOR) {
                this.increaseCapacity();
            }
                set[filled++] = (T) obj;
        }
    }

    public void put(Object obj, int position) {
        if (!this.contains(obj) & position >= 1) {
            if (this.filled >= (int)(capacity * LOAD_FACTOR)) {
                this.increaseCapacity();
            }
            for (int i = filled; i > position - 1; i--) {
                set[i] = set[i - 1];
            }

            set[position - 1] = (T) obj;
            filled++;
        }
    }


    public boolean contains(Object obj) {
        boolean isContain = false;
        Iteratorable iterator = this.getIterator();

        while (iterator.hasNext()) {
            Object item = iterator.iterate();
            if (item.hashCode() == obj.hashCode()) {
                if (item.equals(obj)) {
                    isContain = true;
                    break;
                }
            }
        }

        return isContain;
    }

    private void increaseCapacity() {
        int newCapacity = 2 * capacity;
        Object[] newSet = new Object[newCapacity];

        for (int i = 0; i < capacity; i++) {
            newSet[i] = set[i];
        }

        set = (T[]) newSet;
        capacity = newCapacity;

    }

    public void deleteItemFromPosition(int position) {
        if (position >= 0 &
                (position - 1) < filled) {
            for (int i = position - 1; i <= filled; i++) {
                set[i] = set [i+1];
            }
            filled--;
        }
    }

    @Override
    public Iteratorable getIterator() {
        return new CustomHashSetIterator();
    }

    @Override
    public String toString() {


        String result = "HashSet capacity: " + capacity + '\n' +
                "HashSet filled spots: " + filled + '\n' +
                "HashSet entries: ";
        for (Iteratorable iterator = this.getIterator(); iterator.hasNext();) {
            result += iterator.iterate().toString();
            result += " ";
        }
        return result + '\n';
    }



    private class CustomHashSetIterator implements Iteratorable {
        private int index = 0;


        @Override
        public boolean hasNext() {
            return index < filled;
        }

        @Override
        public Object iterate() {
            if (this.hasNext()) {
                return set[index++];
            }
            return null;
        }
    }
}
