package com.multithreading_an_parallel_computing_in_java.section03_basic_multithreading;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by sofia on 2018-11-23.
 */

/**
 * Sources:
 * Udemy: Multithreading and Concurrent Programming in Java
 */
public class C23_CallableAndFuture {

    static class CallableProcessor implements Callable<String> {

        private int id;

        public CallableProcessor(int id) {
            this.id = id;
        }

        @Override
        public String call() throws Exception {
            Thread.sleep(1000);
            return Thread.currentThread().getName()+": id: "+id;
        }
    }


    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        List<Future<String>> results = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            Future<String> result = executorService.submit(new CallableProcessor(i));
            results.add(result);
        }

        for (Future<String> result : results) {
            try {
                System.out.println(result.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }

        executorService.shutdown();
    }

}
