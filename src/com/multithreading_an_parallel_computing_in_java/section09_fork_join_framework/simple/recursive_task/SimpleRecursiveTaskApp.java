package com.multithreading_an_parallel_computing_in_java.section09_fork_join_framework.simple.recursive_task;

import java.util.concurrent.ForkJoinPool;

/**
 * Created by sofia on 2018-11-24.
 */

/**
 * Sources:
 * Udemy: Multithreading and Concurrent Programming in Java
 */
public class SimpleRecursiveTaskApp {

    public static void main(String[] args) {
        ForkJoinPool pool = new ForkJoinPool(Runtime.getRuntime().availableProcessors());

        SimpleRecursiveTask simpleRecursiveTask = new SimpleRecursiveTask(20);
        int result = pool.invoke(simpleRecursiveTask);
        System.out.println(result);

        simpleRecursiveTask = new SimpleRecursiveTask(200);
        result = pool.invoke(simpleRecursiveTask);
        System.out.println(result);
    }

}
