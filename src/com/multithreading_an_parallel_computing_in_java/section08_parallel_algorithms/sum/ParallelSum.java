package com.multithreading_an_parallel_computing_in_java.section08_parallel_algorithms.sum;

/**
 * Created by sofia on 2018-11-24.
 */

/**
 * Sources:
 * Udemy: Multithreading and Concurrent Programming in Java
 */
public class ParallelSum {

    private int numThreads;
    private ParallelSumWorker[] sums;

    public ParallelSum(int numThreads) {
        this.numThreads = numThreads;
        this.sums = new ParallelSumWorker[numThreads];
    }

    public long sum(int[] nums) {
        int step = (int) Math.ceil(nums.length*1.0/numThreads);

        for (int i = 0; i < numThreads; i++) {
            sums[i] = new ParallelSumWorker(nums, i*step, Math.min((i+1)*step, nums.length));
            sums[i].start();
        }

        try {
            for (ParallelSumWorker worker : sums) {
                worker.join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        long total = 0;

        for (ParallelSumWorker worker : sums) {
            total += worker.getPartialSum();
        }

        return total;
    }

}
