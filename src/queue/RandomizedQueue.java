package queue;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Random;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private static final int STANDARD_INITIAL_CAPACITY = 16;
    private static final Random random = new Random();
    private Item[] array;
    private int size = 0;

    private class RandomizedQueueIterator<T> implements Iterator<T> {

        Object[] queue;
        private int currentIndex = 0;
        private int length;

        public RandomizedQueueIterator() {
            length = size;
            queue = Arrays.copyOf(array, length);
            shuffle(queue);
        }

        @Override
        public boolean hasNext() {
            return currentIndex < length;
        }

        @Override
        public T next() {
            if (!hasNext())
                throw new NoSuchElementException();
            return (T) queue[currentIndex++];
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

        private void shuffle(Object[] array) {
            int r;
            for (int i = 1; i < array.length; i++) {
                r = random.nextInt(i);
                swap(array, i, r);
            }
        }

        private void swap(Object[] array, int i, int j) {
            Object tmp = array[i];
            array[i] = array[j];
            array[j] = tmp;
        }
    }

    public RandomizedQueue() {
        array = (Item[]) new Object[STANDARD_INITIAL_CAPACITY];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    private void resize() {
        if (size == array.length) {
            array = Arrays.copyOf(array, 2 * array.length);
        } else if (size < array.length / 3) {
            array = Arrays.copyOf(array, array.length / 2);
        }
    }

    public void enqueue(Item item) {
        if (item == null)
            throw new NullPointerException();
        array[size++] = item;
        resize();
    }

    public Item dequeue() {
        if (size == 0) throw new NoSuchElementException();
        int index = random.nextInt(size);
        Item tmp = array[index];
        array[index] = array[--size];
        resize();
        return tmp;
    }

    public Item sample() {
        if (size == 0) throw new NoSuchElementException();
        int index = random.nextInt(size);
        return array[index];
    }

    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator();
    }

    public static void main(String[] args) {
    }
}