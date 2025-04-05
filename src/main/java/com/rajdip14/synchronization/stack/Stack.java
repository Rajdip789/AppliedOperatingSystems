package com.rajdip14.synchronization.stack;

/**
 * Thread safe stack implementation
 */
public class Stack {
    private final int[] array;
    private int stackTop;
    final Object lock;

    public Stack(int capacity) {
        array = new int[capacity];
        stackTop = -1;
        lock = new Object();
    }

    // If we make the method as synchronized then whole method body acts as a synchronized block
    // using the reference of current object as lock object. (ex synchronized (this) {} )
    // And so the lock object is also same for all methods
    // By using a lock object we get more control, can use different locks for diff. operations, can make a
    // part of the method synchronized instead the whole method
    public boolean push(int element) {
        synchronized (lock) {
            if(isFull()) return false;

            ++stackTop;

            try { Thread.sleep(1000); } catch (Exception _) { }

            array[stackTop] = element;
            return true;
        }
    }

    public int pop() {
        synchronized (lock) {
            if(isEmpty()) return Integer.MIN_VALUE;

            int obj = array[stackTop];

            try { Thread.sleep(1000); } catch (Exception _) { }

            stackTop--;
            return obj;
        }
    }

    public boolean isEmpty() {
        return stackTop < 0;
    }

    public boolean isFull() {
        return stackTop >= array.length - 1;
    }
}
