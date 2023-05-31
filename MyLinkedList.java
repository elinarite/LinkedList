package collections.linkedList;

public class MyLinkedList implements Linked {
    private Node last;
    private Node first;
    private int size;

    public MyLinkedList() {
        this.last = null;
        this.first = null;
        this.size = 0;
    }

    private static class Node {
        private Node next;
        private Node previous;
        private int data;

        public Node(int data) {
            this.data = data;
            this.next = null;
            this.previous = null;
        }
    }

    public void add(int data) {
        Node newNode = new Node(data);
        if (last == null) {
            last = newNode;
            first = newNode;
        } else {
            newNode.previous = last;
            last.next = newNode;
            last = newNode;
        }
        size++;
    }

    public void addAt(int index, int data) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index is out of bounds");
        }
        if (index == size) {
            add(data);
        } else {
            Node newNode = new Node(data);
            if (index == 0) {
                newNode.next = first;
                first.previous = newNode;
                first = newNode;
            } else {
                Node current = getNodeByIndex(index);
                Node previos = (current);
                newNode.previous = previos;
                newNode.next = current;
                previos.next = newNode;
                current.previous = newNode;
            }
            size++;
        }
    }

    public void remove(int data) {
        Node current = first;
        while (current != null) {
            if (current.data == data) {
                Node previous = current.previous;
                Node next = current.next;
                if (previous != null) {
                    previous.next = next;
                } else {
                    first = next;
                }
                if (next != null) {
                    next.previous = previous;
                } else {
                    last = previous;
                }
                size--;
                break;
            }
            current = current.next;
        }
    }

    public void removeAt(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index is out of bounds");
        }
        if (index == 0) {
            first = first.next;
            if (first != null) {
                first.previous = null;
            } else {
                last = null;
            }
        } else if (index == size - 1) {
            last = last.previous;
            last.next = null;
        } else {
            Node current = getNodeByIndex(index);
            Node previous = current.previous;
            Node next = current.next;
            previous.next = next;
            next.previous = previous;
        }
        size--;
    }

    public void set(int index, int data) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index is out of bounds");
        }
        Node node = getNodeByIndex(index);
        node.data = data;
    }

    public int get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index is out of bounds");
        }
        Node node = getNodeByIndex(index);
        return node.data;
    }

    public void clear() {
        first = null;
        last = null;
        size = 0;
    }

    public boolean contains(int data) {
        Node current = first;
        while (current != null) {
            if (current.data == data) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    private Node getNodeByIndex(int index) {
        if (index < size / 2) {
            Node current = first;
            for (int i = 0; i < index; i++) {
                current = current.next;
            }
            return current;
        } else {
            Node current = last;
            for (int i = size - 1; i > index; i--) {
                current = current.previous;
            }
            return current;
        }
    }
}