package com.multithreading_an_parallel_computing_in_java.section03_basic_multithreading;

/**
 * Created by sofia on 2018-11-22.
 */

/**
 * Sources:
 * Udemy: Multithreading and Concurrent Programming in Java
 */
public class C14_SynchronizedBlocks {

    private static int count1 = 0;
    private static int count2 = 0;

    private static Object lock1 = new Object();
    private static Object lock2 = new Object();

    public static void add() {
        synchronized (lock1) {
            count1++;
        }
    }

    public static void addAgain() {
        synchronized (lock2) {
            count2++;
        }
    }

    public static void compute() {
        for (int i = 0; i < 100; i++) {
            add();
            addAgain();
        }
    }


    public static void main(String[] args) {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                compute();
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                compute();
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

        System.out.println("count1 = "+count1);
        System.out.println("count2 = "+count2);
    }

}
