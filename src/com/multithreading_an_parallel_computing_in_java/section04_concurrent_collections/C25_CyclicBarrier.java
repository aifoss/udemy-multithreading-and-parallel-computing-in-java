package com.multithreading_an_parallel_computing_in_java.section04_concurrent_collections;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by sofia on 2018-11-24.
 */

/**
 * Sources:
 * Udemy: Multithreading and Concurrent Programming in Java
 */
public class C25_CyclicBarrier {

    static class BarrierWorker implements Runnable {

        private int id;
        private Random random;
        private CyclicBarrier barrier;

        public BarrierWorker(int id, CyclicBarrier barrier) {
            this.id = id;
            this.random = new Random();
            this.barrier = barrier;
        }

        @Override
        public void run() {
            doWork();
        }

        private void doWork() {
            System.out.println("Thread with id "+id+" starts working");

            try {
                Thread.sleep(random.nextInt(3000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("Thread with id "+id+" finished");

            try {
                barrier.await();
                System.out.println("After await");
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
        }

        @Override
        public String toString() {
            return ""+id;
        }
    }


    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(5);

        CyclicBarrier barrier = new CyclicBarrier(5, new Runnable() {
            @Override
            public void run() {
                System.out.println("All the tasks are finished");
            }
        });

        for (int i = 0; i < 5; i++) {
            executorService.execute(new BarrierWorker(i+1, barrier));
        }

        executorService.shutdown();
    }

}
