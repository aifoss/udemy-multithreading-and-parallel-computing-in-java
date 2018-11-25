package com.multithreading_an_parallel_computing_in_java.section08_parallel_algorithms.mergesort;

import com.multithreading_an_parallel_computing_in_java.util.ArrayUtil;

import java.util.Random;

/**
 * Created by sofia on 2018-11-24.
 */

/**
 * Sources:
 * Udemy: Multithreading and Concurrent Programming in Java
 */
public class MergeSortApp {

    public static Random random = new Random();

    public static void main(String[] args) {
        int numThreads = Runtime.getRuntime().availableProcessors();

        System.out.println("Number of threads: "+numThreads);
        System.out.println();

        int numItems = random.nextInt(100000000);

        System.out.println("Number of items: "+numItems);
        System.out.println();

        int[] numbers = ArrayUtil.createRandomArray(numItems);
        MergeSort mergeSort = new MergeSort(numbers);

        long startTime = System.currentTimeMillis();
        mergeSort.parallelMergeSort(0, numbers.length-1, numThreads);
        long endTime = System.currentTimeMillis();

        System.out.printf("Time taken for parallel: %d", endTime-startTime);
        System.out.println();

        startTime = System.currentTimeMillis();
        mergeSort.mergeSort(0, numbers.length-1);
        endTime = System.currentTimeMillis();

        System.out.printf("Time taken for sequential: %d", endTime-startTime);
        System.out.println();
    }

}
