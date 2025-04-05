package com.rajdip14.synchronization.deadlock;

public class Main {
    public static void main(String[] args) {

        String lock1 = "good", lock2 = "boy";

        // This is an example of deadlock where each have acquired different lock and waiting for the other
        // To avoid deadlock, locks need to acquired in the same order by both the threads.

        Thread thread1 = new Thread(() -> {
            synchronized (lock1) {
                try {
                    System.out.println("Lock1 acquired by t1");
                    Thread.sleep(1000);
                } catch (InterruptedException _) {}

                synchronized (lock2) {
                    System.out.println("Lock2 acquired by t1");
                }
            }
        }, "Thread1");

        Thread thread2 = new Thread(() -> {
            synchronized (lock2) {
                try {
                    System.out.println("Lock2 acquired by t2");
                    Thread.sleep(1000);
                } catch (InterruptedException _) {}

                synchronized (lock1) {
                    System.out.println("Lock1 acquired by t2");
                }
            }
        }, "Thread2");

        thread1.start();
        thread2.start();
    }
}
