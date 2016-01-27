/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package redblacktreetesterproject;

/**
 * The Queue is a first in first out data structure. This Queue holds Java
 * Object references. It grows dynamically as long as memory is available.
 *
 * @author YutingFan
 */
public class Queue {

    private int capacityUnit;
    private int capacity;
    private int count;
    private int front;
    private int rear;
    private RedBlackNode[] nodeArray;

    /**
     * Create an empty queue that lives in a small array. Theta(1) for no best
     * case or worse case.
     */
    Queue() {
        capacityUnit = 1;
        capacity = capacityUnit * 10;
        this.nodeArray = new RedBlackNode[capacity];
        count = front = rear = 0;
    }

    /**
     * Object method removes and returns reference in front of queue. Theta(1)
     * for no best case or worse case.
     *
     * @return object in front of queue
     */
    public RedBlackNode deQueue() {
        RedBlackNode result;
        if (this.isEmpty()) {
            throw new RuntimeException("Queue is empty!");
        }
        result = nodeArray[front];
        count--;
        front = (front + 1) % capacity;
        return result;
    }

    /**
     * Add an object reference to the rear of the queue
     *
     * @param x is an object to be added to the rear of the queue
     */
    public void enQueue(RedBlackNode x) {
        if (this.isFull()) {
            capacityUnit++;
            capacity = capacityUnit * 10;
        }
        count++;
        nodeArray[rear] = x;
        rear = (rear + 1) % capacity;
    }

    /**
     * Method getFront returns the front of the queue without removing it.
     * Theta(1) for no best case or worse case.
     *
     * @return the queue front without removal
     */
    public int getFront() {
        return front;
    }

    /**
     * Boolean method returns true on empty queue, false otherwise. Theta(1) for
     * no best case or worse case.
     *
     * @return Returns true if queue is empty
     */
    public boolean isEmpty() {
        return (count == 0);
    }

    /**
     * Boolean method returns true if queue is currently at capacity, false
     * otherwise. Theta(1) for no best case or worse case.
     *
     * @return Returns true if queue is at current capacity
     */
    public boolean isFull() {
        return (count == capacity);
    }

    /**
     * The toString method returns a String representation of the current queue
     * contents. Theta(n) for no best case or worse case.
     *
     * @return a string representation of the queue. It shows the front of the
     * queue first. It then shows the second and third and so on.
     */
    @Override
    public String toString() {
        String result = "";
        for (int i = 0; i < nodeArray.length && nodeArray[i] != null; i++) {
            result += nodeArray[i] + "\n";
        }

        return result;
    }

    /**
     * main is for testing the queue routines.
     *
     * @param a Command line parameters are not used
     */
    public static void main(String[] a) {

    }
}
