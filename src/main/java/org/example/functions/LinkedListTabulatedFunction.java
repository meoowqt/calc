package org.example.functions;

public class LinkedListTabulatedFunction extends AbstractTabulatedFunction implements Insertable, Removable {
    private Node head;
    private int count = 0;

    public LinkedListTabulatedFunction(double[] xValues, double[] yValues) {
        for (int i = 0; i < xValues.length; i++) {
            this.addNode(xValues[i], yValues[i]);
        }
    }

    public LinkedListTabulatedFunction(MathFunction source, double xFrom, double xTo, int count) {
        double step = (xTo - xFrom) / (count - 1);
        for (int i = 0; i < count; i++) {
            addNode(xFrom, source.apply(xFrom));
            xFrom += step;
        }
    }

    @Override
    public void insert(double x, double y) {
        if (indexOfX(x) != -1) {
            setY(indexOfX(x), y);
            return;
        }
        if (head == null) {
            addNode(x, y);
        } else {
            Node node = new Node();
            node.x = x;
            node.y = y;

            if (head.prev.x < x) {
                node.next = head;
                node.prev = head.prev;
                head.prev.next = node;
                head.prev = node;
            } else if (head.x > x) {
                head.next.prev = head;
                node.next = head;
                node.prev = head.prev;
                head.prev.next = node;
                head = node;
            } else if (floorIndexOfX(x) == count) {
                node.next = head;
                node.prev = head.prev;
                head.prev = node;
                head.prev.next = node;
                head.prev.prev = node;

            } else {
                int ind = floorIndexOfX(x);
                Node check = getNode(ind);
                node.next = check.next;
                node.prev = check;
                check.next = node;
            }
        }
        count++;
    }

    @Override
    public void remove(int index) {
        if (index == 0) {
            head.next.prev = head.prev;
            head.prev.next = head.next;
            head = head.next;
        } else {
            Node delete = getNode(index);
            Node prevDelete = getNode(index - 1);
            prevDelete.next = delete.next;
            delete.next.prev = prevDelete;
        }
        count--;
    }

    protected static class Node {
        public Node next;
        public Node prev;
        public double x;
        public double y;
    }

    public void addNode(double x, double y) {
        Node node = new Node();
        node.x = x;
        node.y = y;
        if (head == null) {
            head = node;
            node.prev = node;
            node.next = node;
        } else {
            Node last = head.prev;
            node.prev = last;
            node.next = head;
            last.next = node;
            head.prev = node;
        }
        count++;
    }

    Node getNode(int index) {
        Node indexNode;
        if (index < count / 2.) {
            indexNode = head;
            for (int i = 0; i <= count / 2.; i++) {
                if (i == index) {
                    return indexNode;
                } else {
                    indexNode = indexNode.next;
                }
            }
        } else {
            indexNode = head.prev;
            for (int i = count - 1; i >= count / 2.; i--) {
                if (i == index) {
                    return indexNode;
                } else {
                    indexNode = indexNode.prev;
                }
            }

        }
        return indexNode;
    }

    @Override
    protected int floorIndexOfX(double x) {
        Node indexNode = head;
        for (int i = 0; i < count; i++) {
            if (indexNode.x < x) {
                indexNode = indexNode.next;
            } else {
                if (i == 0) {
                    return 0;
                }
                return i - 1;
            }
        }
        return getCount();
    }

    @Override
    protected double extrapolateLeft(double x) {
        return interpolate(x, head.x, head.next.x, head.y, head.next.y);
    }

    @Override
    protected double extrapolateRight(double x) {
        return interpolate(x, head.prev.prev.x, head.prev.x, head.prev.prev.y, head.prev.y);
    }

    @Override
    protected double interpolate(double x, int floorIndex) {
        Node leftNode = getNode(floorIndex);
        assert leftNode != null;
        Node rightNode = leftNode.next;
        return interpolate(x, leftNode.x, rightNode.x, leftNode.y, rightNode.y);
    }

    @Override
    public int getCount() {
        return count;
    }

    @Override
    public double getX(int index) {
        return getNode(index).x;
    }

    @Override
    public double getY(int index) {
        return getNode(index).y;
    }

    @Override
    public void setY(int index, double value) {
        getNode(index).y = value;
    }

    @Override
    public int indexOfX(double x) {
        Node indexNode = head;
        for (int i = 0; i < count; i++) {
            if (indexNode.x == x) {
                return i;
            } else {
                indexNode = indexNode.next;
            }
        }
        return -1;
    }

    @Override
    public int indexOfY(double y) {
        Node indexNode = head;
        for (int i = 0; i < count; i++) {
            if (indexNode.y == y) {
                return i;
            } else {
                indexNode = indexNode.next;
            }
        }
        return -1;
    }

    @Override
    public double leftBound() {
        return head.x;
    }

    @Override
    public double rightBound() {
        return head.prev.x;
    }
}
