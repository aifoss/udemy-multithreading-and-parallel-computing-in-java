package com.multithreading_an_parallel_computing_in_java.section09_fork_join_framework.application.maxfinding;

/**
 * Created by sofia on 2018-11-24.
 */

/**
 * Sources:
 * Udemy: Multithreading and Concurrent Programming in Java
 */
public class SequentialMaxFinder {

    public int findMax(int[] nums, int high) {
        int max = nums[0];

        for (int i = 1; i <= high; i++) {
            if (nums[i] > max) {
                max = nums[i];
            }
        }

        return max;
    }

}
