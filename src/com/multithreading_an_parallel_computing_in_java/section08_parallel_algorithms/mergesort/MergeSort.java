package com.multithreading_an_parallel_computing_in_java.section08_parallel_algorithms.mergesort;

/**
 * Created by sofia on 2018-11-24.
 */

/**
 * Sources:
 * Udemy: Multithreading and Concurrent Programming in Java
 */
public class MergeSort {

    private int[] nums;
    private int[] temp;

    public MergeSort(int[] nums) {
        this.nums = nums;
        this.temp = new int[nums.length];
    }

    public void parallelMergeSort(int low, int high, int numThreads) {
        if (numThreads <= 1) {
            mergeSort(low, high);
            return;
        }

        int mid = low + ((high-low)/2);

        Thread leftSorter = getParallelSorter(low, mid, numThreads);
        Thread rightSorter = getParallelSorter(mid+1, high, numThreads);

        leftSorter.start();
        rightSorter.start();

        try {
            leftSorter.join();
            rightSorter.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        merge(low, mid, high);
    }

    private Thread getParallelSorter(int low, int high, int numThreads) {
        return new Thread() {
            @Override
            public void run() {
                parallelMergeSort(low, high, numThreads/2);
            }
        };
    }

    public void mergeSort(int low, int high) {
        if (low >= high) {
            return;
        }

        int mid = low + ((high-low)/2);

        mergeSort(low, mid);
        mergeSort(mid+1, high);
        merge(low, mid, high);
    }

    public void merge(int low, int mid, int high) {
        for (int i = low; i <= high; i++) {
            temp[i] = nums[i];
        }

        int i = low;
        int j = mid+1;
        int k = low;

        while (i <= mid && j <= high) {
            if (temp[i] <= temp[j]) {
                nums[k++] = temp[i++];
            } else {
                nums[k++] = temp[j++];
            }
        }

        while (i <= mid) {
            nums[k++] = temp[i++];
        }
        while (j <= high) {
            nums[k++] = temp[j++];
        }
    }

    public void showResult() {
        for (int i = 0; i < nums.length; i++) {
            System.out.print(nums[i]+" ");
        }
        System.out.println();
    }

}
