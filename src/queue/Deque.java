package queue;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    private Entry<Item> head = null;
    private int size = 0;

    private static class Entry<Item> {
        private Item value;
        private Entry<Item> prev;
        private Entry<Item> next;

        Entry(Item value) {
            this.value = value;
            this.prev = this;
            this.next = this;
        }

        Entry(Item value, Entry<Item> prev, Entry<Item> next) {
            this.value = value;
            this.prev = prev;
            this.next = next;
        }

        @Override
        public String toString() {
            return "[" + prev.value + "|" + value + "|" + next.value + "]";
        }
    }

    private class DequeIterator implements Iterator {

        private Entry<Item> lastReturned = null;
        private Entry<Item> current = head;

        @Override
        public boolean hasNext() {
            return !isEmpty() && !(current == head && lastReturned == head.prev);
        }

        @Override
        public Item next() {
            if (!hasNext())
                throw new NoSuchElementException();
            lastReturned = current;
            current = current.next;
            return (Item) lastReturned.value;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("The remove() method of iterator hasn't been implemented yet.");
        }
    }

    public Deque() {
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void addFirst(Item item) {
        head = addEntry(item);
    }

    public void addLast(Item item) {
        addEntry(item);
    }

    private Entry<Item> addEntry(Item item) {
        if (item == null)
            throw new NullPointerException("Attemp to add a null element");

        Entry<Item> entry = null;
        if (isEmpty()) {
            entry = new Entry<Item>(item);
            head = entry;
        } else {
            entry = new Entry<Item>(item, head.prev, head);
            head.prev.next = entry;
            head.prev = entry;
        }
        size++;
        return entry;
    }

    public Item removeFirst() {
        if (isEmpty())
            throw new NoSuchElementException("The queue is empty");
        return removeEntry(head);
    }

    public Item removeLast() {
        if (isEmpty())
            throw new NoSuchElementException("The queue is empty");
        return removeEntry(head.prev);
    }

    private Item removeEntry(Entry<Item> entry) {
        Item removed;
        if (size == 1) {
            removed = (Item) head.value;
            head = null;
        } else {
            Entry<Item> prev = entry.prev;
            Entry<Item> next = entry.next;
            prev.next = next;
            next.prev = prev;
            if (entry == head) head = next;
            removed = entry.value;
        }
        size--;
        return removed;
    }

    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    public static void main(String[] args) {
    }
}