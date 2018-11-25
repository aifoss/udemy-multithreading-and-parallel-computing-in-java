package com.multithreading_an_parallel_computing_in_java.section09_fork_join_framework.application.mergesort;

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
public class MergeSortApp {

    static int THRESHOLD = 10;

    public static void main(String[] args) {
        Random random = new Random();

        int limit = 1000;
        int numItems = random.nextInt(1000);
        int numThreads = Runtime.getRuntime().availableProcessors();
        THRESHOLD = numItems / numThreads;

        System.out.println("numItems = "+numItems);
        System.out.println();

        int[] nums1 = ArrayUtil.createRandomArray(numItems, limit);
        int[] nums2 = Arrays.copyOf(nums1, numItems);

        System.out.println(Arrays.toString(nums1));
        System.out.println();

        SequentialMergeSort sequentialMergeSort = new SequentialMergeSort();
        long startTime = System.currentTimeMillis();
        sequentialMergeSort.doMergeSort(nums1);
        long endTime = System.currentTimeMillis();

        System.out.println("Sequential approach: "+(endTime-startTime)+" (ms)");
        System.out.println(Arrays.toString(nums1));
        System.out.println();

        ForkJoinPool pool = new ForkJoinPool(numThreads);

        ParallelMergeSort parallelMergeSort = new ParallelMergeSort(nums2);
        startTime = System.currentTimeMillis();
        pool.invoke(parallelMergeSort);
        endTime = System.currentTimeMillis();

        System.out.println("Parallel approach: "+(endTime-startTime)+" (ms)");
        System.out.println(Arrays.toString(nums2));
        System.out.println();
    }

}
