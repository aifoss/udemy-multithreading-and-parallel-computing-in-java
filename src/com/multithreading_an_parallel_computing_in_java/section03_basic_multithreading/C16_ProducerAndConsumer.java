package com.multithreading_an_parallel_computing_in_java.section03_basic_multithreading;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sofia on 2018-11-22.
 */

/**
 * Sources:
 * Udemy: Multithreading and Concurrent Programming in Java
 */
public class C16_ProducerAndConsumer {

    static class Producer {

        private static final int LIMIT = 5;

        private final Object lock;
        private final List<Integer> list;
        private int value = 0;

        public Producer(Object lock, List<Integer> list) {
            this.lock = lock;
            this.list = list;
        }

        public void produce() throws InterruptedException {
            synchronized (lock) {
                while (true) {
                    if (list.size() == LIMIT) {
                        System.out.println("Waiting for items to be removed from the list");
                        lock.wait();
                    } else {
                        System.out.println("Producing "+value);
                        list.add(value++);
                        lock.notify();
                    }

                    Thread.sleep(500);
                }
            }
        }

    }

    static class Consumer {

        private static final int BOTTOM = 0;

        private final Object lock;
        private final List<Integer> list;

        public Consumer(Object lock, List<Integer> list) {
            this.lock = lock;
            this.list = list;
        }

        public void consume() throws InterruptedException {
            synchronized (lock) {
                while (true) {
                    if (list.size() == BOTTOM) {
                        System.out.println("Waiting for items to be added to the list");
                        lock.wait();
                    } else {
                        int value = list.remove(list.size()-1);
                        System.out.println("Consuming "+value);
                        lock.notify();
                    }

                    Thread.sleep(500);
                }
            }
        }

    }


    public static void main(String[] args) {
        Object lock = new Object();
        List<Integer> list = new ArrayList<>();

        Producer producer = new Producer(lock, list);
        Consumer consumer = new Consumer(lock, list);

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    producer.produce();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    consumer.consume();
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
