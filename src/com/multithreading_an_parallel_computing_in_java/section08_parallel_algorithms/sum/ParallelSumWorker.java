package com.multithreading_an_parallel_computing_in_java.section08_parallel_algorithms.sum;

/**
 * Created by sofia on 2018-11-24.
 */

/**
 * Sources:
 * Udemy: Multithreading and Concurrent Programming in Java
 */
public class ParallelSumWorker extends Thread {

    private int[] nums;
    private int low;
    private int high;
    private long partialSum;

    public ParallelSumWorker(int[] nums, int low, int high) {
        this.nums = nums;
        this.low = low;
        this.high = high;
    }

    @Override
    public void run() {
        partialSum = 0;

        for (int i = low; i < high; i++) {
            partialSum += nums[i];
        }

    }

    public long getPartialSum() {
        return partialSum;
    }

}
