package com.multithreading_an_parallel_computing_in_java.section09_fork_join_framework.application.mergesort;

import java.util.Arrays;

/**
 * Created by sofia on 2018-11-24.
 */

/**
 * Sources:
 * Udemy: Multithreading and Concurrent Programming in Java
 */
public class SequentialMergeSort {

    public void doMergeSort(int[] nums) {
        mergeSort(nums);
    }

    public static void mergeSort(int[] nums) {
        if (nums.length <= 1) {
            return;
        }

        int mid = nums.length / 2;

        int[] left = Arrays.copyOfRange(nums, 0, mid);
        int[] right = Arrays.copyOfRange(nums, mid, nums.length);

        mergeSort(left);
        mergeSort(right);

        merge(left, right, nums);
    }

    public static void merge(int[] left, int[] right, int[] nums) {
        int i = 0;
        int j = 0;
        int k = 0;

        while (i < left.length && j < right.length) {
            if (left[i] <= right[j]) {
                nums[k++] = left[i++];
            } else {
                nums[k++] = right[j++];
            }
        }

        while (i < left.length) {
            nums[k++] = left[i++];
        }
        while (j < right.length) {
            nums[k++] = right[j++];
        }
    }

}
