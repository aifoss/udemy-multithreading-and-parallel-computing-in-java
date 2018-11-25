package com.multithreading_an_parallel_computing_in_java.section03_basic_multithreading;

/**
 * Created by sofia on 2018-11-22.
 */

/**
 * Sources:
 * Udemy: Multithreading and Concurrent Programming in Java
 */
public class C15_WaitAndNotify {

    static class Processor {

        public void produce() throws InterruptedException {
            synchronized (this) {
                System.out.println("In produce method ...");
                wait();
                System.out.println("Again in produce method ...");
            }
        }

        public void consume() throws InterruptedException {
            Thread.sleep(1000);

            synchronized (this) {
                System.out.println("In consumer method ...");
                notify();
                Thread.sleep(3000);
            }
        }

    }


    public static void main(String[] args) {
        Processor processor = new Processor();

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    processor.produce();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    processor.consume();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
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
    }

}
