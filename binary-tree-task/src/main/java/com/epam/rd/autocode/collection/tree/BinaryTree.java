package com.epam.rd.autocode.collection.tree;

import java.util.Optional;

/**
 * Binary Search Tree.<br>
 * This class uses the natural ordering to compare elements.<br>
 * This implementation does not provide any balancing.
 *
 * @author D. Kolesnikov, Y. Mishcheriakov
 */
public class BinaryTree {

    private static final String INDENT = "-~-";
    private static final String EOL = System.lineSeparator();

    private Node root;
    private int size;

    private static final class Node {
        Integer e;
        Node left;
        Node right;

        Node(Integer e) {
            this.e = e;
        }

        public String toString() {
            return "Node[" + e + "]";
        }
    }

    /**
     * Creates an empty binary tree.
     */
    public BinaryTree() {
        super();
    }

    /**
     * Creates an empty binary tree and adds all distinct elements.
     *
     * @param elements the elements to add.
     * @throws NullPointerException if the parameter is {@code null}
     *                              or contains {@code null} elements.
     */
    public BinaryTree(Integer... elements) {
        if (elements == null) {
            throw new NullPointerException("Elements cannot be null");
        }
        for (Integer element : elements) {
            if (element == null) {
                throw new NullPointerException("Element cannot be null");
            }
            add(element);
        }
    }

    /**
     * Adds an element to the tree.
     * If there is an element in the tree that,
     * using the compareTo method, is equal to the
     * element being added, then the addition does
     * not occur and the method returns false,
     * otherwise the element is included in the tree
     * and the method returns true.
     *
     * @param element the element to add.
     * @return {@code true} if the element was added (@code false} otherwise.
     * @throws NullPointerException if the parameter is {@code null}.
     */
    public boolean add(Integer element) {
        if (element == null) {
            throw new NullPointerException("Element cannot be null");
        }
        if (root == null) {
            root = new Node(element);
            size++;
            return true;
        }
        return addRecursive(root, element);
    }

    private boolean addRecursive(Node node, Integer element) {
        if (element.equals(node.e)) {
            return false;
        }
        if (element < node.e) {
            if (node.left == null) {
                node.left = new Node(element);
                size++;
                return true;
            } else {
                return addRecursive(node.left, element);
            }
        } else {
            if (node.right == null) {
                node.right = new Node(element);
                size++;
                return true;
            } else {
                return addRecursive(node.right, element);
            }
        }
    }

    /**
     * Adds all non-existing in the tree elements to this tree.
     *
     * @param ar the elements to add.
     * @throws NullPointerException if the parameter is {@code null}
     *                              or contains {@code null} elements.
     */
    public void addAll(Integer... ar) {
        if (ar == null) {
            throw new NullPointerException("Elements cannot be null");
        }
        for (Integer element : ar) {
            if (element == null) {
                throw new NullPointerException("Element cannot be null");
            }
            add(element);
        }
    }

    /**
     * @return a string representation of the tree
     * that composed using natural ordering.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append('[');
        toStringHelper(root, sb);
        if (sb.length() > 1) { // Verifica si hay algo después de '['
            sb.setLength(sb.length() - 2); // Elimina la última coma y espacio
        }
        sb.append(']');
        return sb.toString();
    }
    
    private void toStringHelper(Node node, StringBuilder sb) {
        if (node != null) {
            toStringHelper(node.left, sb);
            sb.append(node.e).append(", ");
            toStringHelper(node.right, sb);
        }
    }

    /**
     * Removes the specified element from this tree if
     * it exists in this tree.
     *
     * @param element an element to remove.
     * @return {@code true} if the element was removed, otherwise {@code false}.
     * @throws NullPointerException if the parameter is {@code null}.
     */
    public Optional<Integer> remove(Integer element) {
        if (element == null) {
            throw new NullPointerException("Element cannot be null");
        }
        Result result = new Result();
        root = removeRecursive(root, element, result);
        if (result.found) {
            size--;
            return Optional.of(element);
        }
        return Optional.empty();
    }

    private Node removeRecursive(Node node, Integer element, Result result) {
        if (node == null) {
            return null;
        }
        if (element.equals(node.e)) {
            result.found = true;
            if (node.left == null && node.right == null) {
                return null;
            }
            if (node.left == null) {
                return node.right;
            }
            if (node.right == null) {
                return node.left;
            }
            Integer minValue = findMinValue(node.right);
            node.e = minValue;
            node.right = removeRecursive(node.right, minValue, result);
            return node;
        }
        if (element < node.e) {
            node.left = removeRecursive(node.left, element, result);
        } else {
            node.right = removeRecursive(node.right, element, result);
        }
        return node;
    }

    private Integer findMinValue(Node node) {
        while (node.left != null) {
            node = node.left;
        }
        return node.e;
    }

    /**
     * Returns the size of the tree.
     *
     * @return the number of elements in the tree.
     */
    public int size() {
        return size;
    }

    /**
     * The helper method for you.<br>
     * Creates a 'tree' string representation of the tree.<br>
     * If the sequence of elements `[3, 1, 2, 5, 6, 4, 0]`,
     * in the specified order, was added to the tree,
     * then the following representation is expected:
     * <pre>
     *      7
     *   6
     *     5
     * 4
     *     2
     *   1
     *     0
     * </pre>
     * '4' is the root of this tree, '0' is the most left leaf,
     * and '7' is the most right leaf of the tree.
     *
     * @return a 'tree' string representation of the tree.
     */
    String asTreeString() {
        StringBuilder sb = new StringBuilder();
        asTreeString(sb, root, 0);
        return sb.toString();
    }

    private void asTreeString(StringBuilder sb, Node node, int k) {
        if (node == null) {
            return;
        }
        asTreeString(sb, node.right, k + 1);
        sb.append(INDENT.repeat(k));
        sb.append(String.format("%3s", node.e)).append(EOL);
        asTreeString(sb, node.left, k + 1);
    }

    private static class Result {
        boolean found = false;
    }
}
