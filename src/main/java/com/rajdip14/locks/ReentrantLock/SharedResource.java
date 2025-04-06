package com.rajdip14.locks.ReentrantLock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * --- Synchronized is simple but limited. ReentrantLock is more complex but gives you more control.
 * - ReentrantLock-
 *  1. Explict locking (lock() and unlock())
 *  2. Interruptible lock available (lockInterruptibly())
 *  3. Try lock with timeout (tryLock(long, TimeUnit))
 *  4. Fairness Policy, locks acquired in FCFS basis if made fair, by default unfair
 *                      (make it fair with constructor, new ReentrantLock(true))
 *  5. Multiple condition variables via Condition objects
 *  6. If the same thread holds the lock, it can acquire it again without blocking.
 *
 *  https://medium.com/@AlexanderObregon/javas-reentrantlock-lock-method-explained-41ace8b9eec7
 */

public class SharedResource {

    boolean isAvailable = false;
    ReentrantLock lock = new ReentrantLock();

    public void producer() {

        try {

            lock.lock();
            System.out.println("Lock acquired by: " + Thread.currentThread().getName());
            isAvailable = true;
            Thread.sleep(4000);

        } catch (Exception _) {

        } finally {
            System.out.println("Lock released by: " + Thread.currentThread().getName());
            lock.unlock();
        }
    }
}
