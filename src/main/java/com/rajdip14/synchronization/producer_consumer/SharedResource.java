package com.rajdip14.synchronization.producer_consumer;

import java.util.LinkedList;
import java.util.Queue;

public class SharedResource {
    private final Queue<Integer> sharedBuffer;
    private final int bufferSize;

    public SharedResource(int bufferSize) {
        sharedBuffer = new LinkedList<>();
        this.bufferSize = bufferSize;
    }

    public synchronized void produce(int item) throws Exception {

        while (sharedBuffer.size() == bufferSize) {
            System.out.println("Buffer is full, Producer is waiting for consumer");
            wait();
        }

        sharedBuffer.add(item);
        System.out.println("Produced: " + item);

        notify(); //Notify the consumer that there are items to consume now
    }

    public synchronized int consume() throws Exception {

        while (sharedBuffer.isEmpty()) {
            System.out.println("Buffer is empty Consumer is waiting for producer");
            wait();
        }

        int item = sharedBuffer.poll();
        System.out.println("Consumed: " + item);

        notify(); //Notify the producer that there is space in the buffer now
        return item;
    }
}
