package com.multithreading_an_parallel_computing_in_java.section09_fork_join_framework.application.maxfinding;

import com.multithreading_an_parallel_computing_in_java.util.ArrayUtil;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;

/**
 * Created by sofia on 2018-11-24.
 */

/**
 * Sources:
 * Udemy: Multithreading and Concurrent Programming in Java
 */
public class MaxFindingApp {

    public static int THRESHOLD = 0;

    public static void main(String[] args) {
        Random random = new Random();

        int limit = 1000;
        int numItems = random.nextInt(100000000);
        int numThreads = Runtime.getRuntime().availableProcessors();
        THRESHOLD = numItems / numThreads;

        System.out.println("numItems = "+numItems);
        System.out.println();

        int[] nums = ArrayUtil.createRandomArray(numItems, limit);

        int max = Arrays.stream(nums).max().getAsInt();
        System.out.println("max = "+max);
        System.out.println();

        SequentialMaxFinder sequentialMaxFinder = new SequentialMaxFinder();
        long startTime = System.currentTimeMillis();
        max = sequentialMaxFinder.findMax(nums, numItems-1);
        long endTime = System.currentTimeMillis();

        System.out.println("Sequential approach: "+(endTime-startTime)+" (ms)");
        System.out.println(max);
        System.out.println();

        ForkJoinPool pool = new ForkJoinPool(numThreads);

        ParallelMaxFinder parallelMaxFinder = new ParallelMaxFinder(nums, 0, numItems-1);
        startTime = System.currentTimeMillis();
        max = pool.invoke(parallelMaxFinder);
        endTime = System.currentTimeMillis();

        System.out.println("Parallel approach: "+(endTime-startTime)+" (ms)");
        System.out.println(max);
        System.out.println();
    }

}
