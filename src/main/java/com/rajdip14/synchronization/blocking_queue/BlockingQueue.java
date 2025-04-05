package com.rajdip14.synchronization.blocking_queue;

import java.util.LinkedList;
import java.util.Queue;

/**
 * A blocking queue simplifies the implementation of the Producer-Consumer problem by providing built-in methods for
 * thread-safe addition and removal of items, eliminating the need to manually handle synchronization with wait() and notifyAll().
 * In Java, the java.util.concurrent package provides the BlockingQueue interface and its implementations
 * like ArrayBlockingQueue or LinkedBlockingQueue.
 */

public class BlockingQueue {
    private final Queue<Integer> q;

    private final int capacity;

    public BlockingQueue(int cap) {
        q = new LinkedList<>();
        capacity = cap;
    }

    public boolean add(int item) {
        synchronized (q) {
            while(q.size() == capacity) {
                try {
                    q.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            q.add(item);
            q.notifyAll();
            return true;
        }
    }

    public int remove() {
        synchronized (q) {
            while(q.isEmpty()) {
                try {
                    q.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            int element = q.poll();
            q.notifyAll();
            return element;
        }
    }
}
