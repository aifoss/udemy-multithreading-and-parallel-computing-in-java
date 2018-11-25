package com.multithreading_an_parallel_computing_in_java.section03_basic_multithreading;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by sofia on 2018-11-23.
 */

/**
 * Sources:
 * Udemy: Multithreading and Concurrent Programming in Java
 */
public class C22_Executors {

    static class ExecutorWorker implements Runnable {

        @Override
        public void run() {
            for (int i = 0; i < 3; i++) {
                System.out.println(Thread.currentThread().getName()+": "+i);

                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

    }


    public static void main(String[] args) {
        runCachedThreadPoolExecutor();
//        runFixedThreadPoolExecutor();
//        runSingleThreadExecutor();
    }

    private static void runCachedThreadPoolExecutor() {
        ExecutorService executorService = Executors.newCachedThreadPool();

        for (int i = 0; i < 5; i++) {
            executorService.submit(new ExecutorWorker());
        }

        executorService.shutdown();
    }

    private static void runFixedThreadPoolExecutor() {
        ExecutorService executorService = Executors.newFixedThreadPool(5);

        for (int i = 0; i < 5; i++) {
            executorService.submit(new ExecutorWorker());
        }

        executorService.shutdown();
    }

    private static void runSingleThreadExecutor() {
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        for (int i = 0; i < 5; i++) {
            executorService.submit(new ExecutorWorker());
        }

        executorService.shutdown();
    }

}
