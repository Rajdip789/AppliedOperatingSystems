package com.rajdip14.synchronization.blocking_queue;

public class Main {
    public static void main( String[] args) {
        BlockingQueue bQueue = new BlockingQueue(6);

        //Testing BlockingQueue in multithreaded environment

        new Thread(() -> {
        	int counter = 0;
        	while(++counter < 10)
        		System.out.println("Added: " + bQueue.add(10));
        }, "Adder").start();

        new Thread(() -> {
        	int counter = 0;
        	while(++counter < 10)
        		System.out.println("Removed: " + bQueue.remove());
        }, "Remover").start();
    }
}
