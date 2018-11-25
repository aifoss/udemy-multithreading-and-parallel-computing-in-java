package com.multithreading_an_parallel_computing_in_java.section09_fork_join_framework.application.mergesort;

import java.util.Arrays;
import java.util.concurrent.RecursiveAction;

/**
 * Created by sofia on 2018-11-24.
 */

/**
 * Sources:
 * Udemy: Multithreading and Concurrent Programming in Java
 */
public class ParallelMergeSort extends RecursiveAction {

    private int[] nums;

    public ParallelMergeSort(int[] nums) {
        this.nums = nums;
    }

    @Override
    public void compute() {
        if (nums.length <= MergeSortApp.THRESHOLD) {
            SequentialMergeSort.mergeSort(nums);
            return;
        }

        int mid = nums.length / 2;

        int[] left = Arrays.copyOfRange(nums, 0, mid);
        int[] right = Arrays.copyOfRange(nums, mid, nums.length);

        ParallelMergeSort action1 = new ParallelMergeSort(left);
        ParallelMergeSort action2 = new ParallelMergeSort(right);

        invokeAll(action1, action2);

        SequentialMergeSort.merge(left, right, nums);
    }

}
