package com.multithreading_an_parallel_computing_in_java.section04_concurrent_collections;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by sofia on 2018-11-24.
 */

/**
 * Sources:
 * Udemy: Multithreading and Concurrent Programming in Java
 */
public class C24_CountDownLatch {

    static class LatchWorker implements Runnable {

        private int id;
        private CountDownLatch latch;

        public LatchWorker(int id, CountDownLatch latch) {
            this.id = id;
            this.latch = latch;
        }

        @Override
        public void run() {
            doWork();
            latch.countDown();
        }

        private void doWork() {
            System.out.println("Thread with id "+id+" starts working");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    public static void main(String[] args) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        CountDownLatch latch = new CountDownLatch(5);
        for (int i = 0; i < 5; i++) {
            executorService.execute(new LatchWorker(i+1, latch));
        }

        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("All the prerequisites are done ...");

        executorService.shutdown();
    }

}
