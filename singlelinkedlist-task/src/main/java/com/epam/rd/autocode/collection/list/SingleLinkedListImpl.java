package com.epam.rd.autocode.collection.list;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;

public class SingleLinkedListImpl implements List {

    private Node head;

    private static class Node {
        Object data;
        Node next;

        Node(Object data) {
            this.data = data;
        }

        Node(Object data, Node next) {
            this.data = data;
            this.next = next;
        }

        @Override
        public String toString() {
            return "[" + data + ']';
        }
    }

    public SingleLinkedListImpl() {
        head = new Node(0, null);
    }

    @Override
    public void clear() {
        head.next = null;
        head.data = 0;
    }

    @Override
    public int size() {
        return (int) head.data;
    }

    @Override
    public boolean add(Object el) {
        if (el == null) throw new NullPointerException();
        head.next = new Node(el, head.next);
        head.data = (int) head.data + 1;
        return true;
    }

    @Override
    public Optional<Object> remove(Object el) {
        if (el == null) throw new NullPointerException();
        Node prev = head;
        Node curr = head.next;
        while (curr != null) {
            if (Objects.equals(curr.data, el)) {
                prev.next = curr.next;
                head.data = (int) head.data - 1;
                return Optional.of(el);
            }
            prev = curr;
            curr = curr.next;
        }
        return Optional.empty();
    }

    @Override
    public Object get(int index) {
        if (index < 0 || index >= size()) throw new IndexOutOfBoundsException();
        Node curr = head.next;
        for (int i = 0; i < index; i++) {
            curr = curr.next;
        }
        return curr.data;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        Node curr = head.next;
        while (curr != null) {
            sb.append(curr.data);
            if (curr.next != null) {
                sb.append(", ");
            }
            curr = curr.next;
        }
        sb.append("]");
        return sb.toString();
    }

    @Override
    public Iterator<Object> iterator() {
        return new Iterator<Object>() {
            private Node current = head.next;
            private Node previous = head;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public Object next() {
                if (!hasNext()) throw new NoSuchElementException();
                Object data = current.data;
                previous = current;
                current = current.next;
                return data;
            }

            @Override
            public void remove() {
                if (previous == head) throw new IllegalStateException();
                Node curr = head;
                while (curr.next != previous) {
                    curr = curr.next;
                }
                curr.next = previous.next;
                head.data = (int) head.data - 1;
                previous = head;
            }
        };
    }
}
