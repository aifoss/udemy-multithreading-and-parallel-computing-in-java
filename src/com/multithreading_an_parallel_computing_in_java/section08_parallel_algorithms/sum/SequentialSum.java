package com.multithreading_an_parallel_computing_in_java.section08_parallel_algorithms.sum;

/**
 * Created by sofia on 2018-11-24.
 */

/**
 * Sources:
 * Udemy: Multithreading and Concurrent Programming in Java
 */
public class SequentialSum {

    public long sum(int[] nums) {
        long total = 0;
        for (int i = 0; i < nums.length; i++) {
            total += nums[i];
        }
        return total;
    }

}
