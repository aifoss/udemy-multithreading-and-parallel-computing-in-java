package com.multithreading_an_parallel_computing_in_java.section08_parallel_algorithms.sum;

import com.multithreading_an_parallel_computing_in_java.util.ArrayUtil;

import java.util.Random;

/**
 * Created by sofia on 2018-11-24.
 */

/**
 * Sources:
 * Udemy: Multithreading and Concurrent Programming in Java
 */
public class SumApp {

    public static void main(String[] args) {
        int numThreads = Runtime.getRuntime().availableProcessors();

        System.out.println("Number of threads: "+numThreads);
        System.out.println();

        Random random = new Random();
        int numItems = random.nextInt(100000000);

        System.out.println("Number of items: "+numItems);
        System.out.println();

        int[] numbers = ArrayUtil.createRandomArray(numItems);

        SequentialSum sequentialSum = new SequentialSum();
        long startTime = System.currentTimeMillis();
        long sum = sequentialSum.sum(numbers);
        long endTime = System.currentTimeMillis();

        System.out.println("Sum = "+sum);
        System.out.printf("Time taken for sequential sum: %d", endTime-startTime);
        System.out.println();
        System.out.println();

        ParallelSum parallelSum = new ParallelSum(numThreads);
        startTime = System.currentTimeMillis();
        sum = parallelSum.sum(numbers);
        endTime = System.currentTimeMillis();

        System.out.println("Sum = "+sum);
        System.out.printf("Time taken for parallel sum: %d", endTime-startTime);
        System.out.println();
    }

}
