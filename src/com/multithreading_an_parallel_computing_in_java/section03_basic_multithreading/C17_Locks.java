package com.multithreading_an_parallel_computing_in_java.section03_basic_multithreading;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by sofia on 2018-11-22.
 */

/**
 * Sources:
 * Udemy: Multithreading and Concurrent Programming in Java
 */
public class C17_Locks {

    private static int counter = 0;
    private static Lock lock = new ReentrantLock();

    public static void increment() {
        lock.lock();

        try {
            for (int i = 0; i < 10000; i++) {
                counter++;
            }
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                increment();
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                increment();
            }
        });

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("counter = "+counter);
    }

}
