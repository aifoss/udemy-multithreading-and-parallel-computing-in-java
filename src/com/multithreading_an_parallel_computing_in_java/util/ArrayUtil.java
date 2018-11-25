package com.multithreading_an_parallel_computing_in_java.util;

import java.util.Random;

/**
 * Created by sofia on 2018-11-24.
 */

/**
 * Sources:
 * Udemy: Multithreading and Concurrent Programming in Java
 */
public class ArrayUtil {

    static Random random = new Random();

    public static int[] createRandomArray(int numItems) {
        return createRandomArray(numItems, numItems);
    }

    public static int[] createRandomArray(int numItems, int limit) {
        int[] a = new int[numItems];
        for (int i = 0; i < numItems; i++) {
            a[i] = random.nextInt(limit);
        }
        return a;
    }

}
