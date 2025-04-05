package com.rajdip14.synchronization.producer_consumer;

public class ProducerConsumer {
    public static void main(String[] args) {
        SharedResource sharedBuffer = new SharedResource(6);

        Thread producerThread = new Thread(() -> {
            try {
                for (int i = 1; i <= 12; i++) {
                    sharedBuffer.produce(i);
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        });

        Thread consumerThread = new Thread(() -> {
            try {
                for (int i = 1; i <= 12; i++) {
                    sharedBuffer.consume();
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        });

        producerThread.start();
        consumerThread.start();
    }
}
