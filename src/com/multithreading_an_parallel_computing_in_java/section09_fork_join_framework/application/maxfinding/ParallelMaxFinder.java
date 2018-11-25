package com.multithreading_an_parallel_computing_in_java.section09_fork_join_framework.application.maxfinding;

import java.util.concurrent.RecursiveTask;

/**
 * Created by sofia on 2018-11-24.
 */

/**
 * Sources:
 * Udemy: Multithreading and Concurrent Programming in Java
 */
public class ParallelMaxFinder extends RecursiveTask<Integer> {

    private int[] nums;
    private int low;
    private int high;

    public ParallelMaxFinder(int[] nums, int low, int high) {
        this.nums = nums;
        this.low = low;
        this.high = high;
    }

    @Override
    public Integer compute() {
        if (high-low < MaxFindingApp.THRESHOLD) {
            return sequentialMaxFind();

        } else {
            int mid = low + ((high-low)/2);

            ParallelMaxFinder task1 = new ParallelMaxFinder(nums, low, mid);
            ParallelMaxFinder task2 = new ParallelMaxFinder(nums, mid+1, high);

            invokeAll(task1, task2);

            return Math.max(task1.join(), task2.join());
        }
    }

    private int sequentialMaxFind() {
        int max = nums[low];

        for (int i = low+1; i <= high; i++) {
            if (nums[i] > max) {
                max = nums[i];
            }
        }

        return max;
    }

}
