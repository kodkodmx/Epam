package com.epam.rd.autocode.dllist;

import java.util.Optional;

public class DoublyLinkedListImpl implements DoublyLinkedList {

    private Node head;
    private Node tail;

    private static class Node {
        Object element;
        Node next;
        Node prev;

        Node(Object element, Node prev, Node next) {
            this.element = element;
            this.prev = prev;
            this.next = next;
        }
    }

    @Override
    public boolean addFirst(Object element) {
        if (element == null) {
            return false;
        }
        Node newNode = new Node(element, null, head);
        if (head == null) {
            tail = newNode;
        } else {
            head.prev = newNode;
        }
        head = newNode;
        return true;
    }

    @Override
    public boolean addLast(Object element) {
        if (element == null) {
            return false;
        }
        Node newNode = new Node(element, tail, null);
        if (tail == null) {
            head = newNode;
        } else {
            tail.next = newNode;
        }
        tail = newNode;
        return true;
    }

    @Override
    public void delete(int index) {
        Node current = getNode(index);
        if (current.prev != null) {
            current.prev.next = current.next;
        } else {
            head = current.next;
        }
        if (current.next != null) {
            current.next.prev = current.prev;
        } else {
            tail = current.prev;
        }
    }

    @Override
    public Optional<Object> remove(Object element) {
        if (element == null) {
            return Optional.empty();
        }
        Node current = head;
        while (current != null) {
            if (current.element.equals(element)) {
                if (current.prev != null) {
                    current.prev.next = current.next;
                } else {
                    head = current.next;
                }
                if (current.next != null) {
                    current.next.prev = current.prev;
                } else {
                    tail = current.prev;
                }
                return Optional.of(current.element);
            }
            current = current.next;
        }
        return Optional.empty();
    }

    @Override
    public boolean set(int index, Object element) {
        if (element == null) {
            return false;
        }
        Node current = getNode(index);
        current.element = element;
        return true;
    }

    @Override
    public int size() {
        int count = 0;
        Node current = head;
        while (current != null) {
            count++;
            current = current.next;
        }
        return count;
    }

    @Override
    public Object[] toArray() {
        Object[] array = new Object[size()];
        Node current = head;
        int index = 0;
        while (current != null) {
            array[index++] = current.element;
            current = current.next;
        }
        return array;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        Node current = head;
        while (current != null) {
            result.append(current.element.toString());
            if (current.next != null) {
                result.append(" ");
            }
            current = current.next;
        }
        return result.toString();
    }

    private Node getNode(int index) {
        if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException("Index out of range");
        }
        Node current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current;
    }
}
