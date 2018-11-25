package com.multithreading_an_parallel_computing_in_java.section04_concurrent_collections;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * Created by sofia on 2018-11-24.
 */

/**
 * Sources:
 * Udemy: Multithreading and Concurrent Programming in Java
 */
public class C27_DelayQueue {

    static class DelayedWorker implements Delayed {

        private long duration;
        private String message;

        public DelayedWorker(long duration, String message) {
            this.duration = System.currentTimeMillis() + duration;
            this.message = message;
        }

        @Override
        public int compareTo(Delayed other) {
            return Long.compare(this.duration, ((DelayedWorker) other).duration);
        }

        @Override
        public long getDelay(TimeUnit unit) {
            return unit.convert(duration-System.currentTimeMillis(), TimeUnit.MILLISECONDS);
        }

        @Override
        public String toString() {
            return message;
        }
    }


    public static void main(String[] args) {
        BlockingQueue<DelayedWorker> queue = new DelayQueue<>();

        try {
            queue.put(new DelayedWorker(1000, "This is the first message"));
            queue.put(new DelayedWorker(2000, "This is the second message"));
            queue.put(new DelayedWorker(3000, "This is the third message"));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        while (!queue.isEmpty()) {
            try {
                System.out.println(queue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
