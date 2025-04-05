package com.rajdip14.synchronization.deadlock;

class A {
    synchronized void foo(B b) {
        String name = Thread.currentThread().getName();
        System.out.println(name + " entered A.foo");
        try {
            Thread.sleep(1000);
        } catch(Exception e) {
            System.out.println("A Interrupted");
        }
        System.out.println(name + " trying to call B.last()");
        b.last();
    }
    synchronized void last() {
        System.out.println("Inside A.last");
    }
}

class B {
    synchronized void bar(A a) {
        String name = Thread.currentThread().getName();
        System.out.println(name + " entered B.bar");
        try {
            Thread.sleep(1000);
        } catch(Exception e) {
            System.out.println("B Interrupted");
        }
        System.out.println(name + " trying to call A.last()");
        a.last();
    }

    synchronized void last() {
        System.out.println("Inside A.last");
    }
}

public class Deadlock implements Runnable {
    private final A a = new A();
    private final B b = new B();

    public static void main(String[] args) {
        new Deadlock().start();
    }

    void start() {
        Thread.currentThread().setName("MainThread");

        Thread t = new Thread(this, "RacingThread");
        t.start();

        a.foo(b); // Main thread holds lock on 'a'
        System.out.println("Back in main thread");
    }

    @Override
    public void run() {
        b.bar(a); // Racing thread holds lock on 'b'
        System.out.println("Back in racing thread");
    }
}
