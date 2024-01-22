package org.example.functions;

import java.util.Arrays;

public class LinkedListTabulatedFunction extends AbstractTabulatedFunction {
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

    private Node getNode(int index) {
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
