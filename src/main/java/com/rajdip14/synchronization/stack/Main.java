package com.rajdip14.synchronization.stack;

public class Main {
    public static void main(String[] args) {
        Stack stack = new Stack(6);

        //Testing a thread safe Stack
        new Thread(() -> {
        	int counter = 0;
        	while(++counter < 10)
        		System.out.println("Pushed: " + stack.push(100));
        }, "Pushed").start();

        new Thread(() -> {
        	int counter = 0;
        	while(++counter < 10)
        		System.out.println("Popped: " + stack.pop());
        }, "Popper").start();
    }
}
