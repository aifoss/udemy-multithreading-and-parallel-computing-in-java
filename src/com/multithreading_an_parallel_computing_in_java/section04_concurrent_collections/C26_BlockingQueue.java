package com.multithreading_an_parallel_computing_in_java.section04_concurrent_collections;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Created by sofia on 2018-11-24.
 */

/**
 * Sources:
 * Udemy: Multithreading and Concurrent Programming in Java
 */
public class C26_BlockingQueue {

    static class BlockingQueueProducer implements Runnable {

        private BlockingQueue<Integer> queue;

        public BlockingQueueProducer(BlockingQueue<Integer> queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            int counter = 0;

            while (true) {
                try {
                    int item = ++counter;
                    System.out.println("Putting item to the queue: "+item);
                    queue.put(item);
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static class BlockingQueueConsumer implements Runnable {

        private BlockingQueue<Integer> queue;

        public BlockingQueueConsumer(BlockingQueue<Integer> queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            while (true) {
                try {
                    int item = queue.take();
                    System.out.println("\tTaking item from the queue: "+item);
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    public static void main(String[] args) {
        BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(10);

        BlockingQueueProducer producer = new BlockingQueueProducer(queue);
        BlockingQueueConsumer consumer = new BlockingQueueConsumer(queue);

        new Thread(producer).start();
        new Thread(consumer).start();
    }

}
