package com.multithreading_an_parallel_computing_in_java.section09_fork_join_framework.simple.recursive_action;

import java.util.concurrent.ForkJoinPool;

/**
 * Created by sofia on 2018-11-24.
 */

/**
 * Sources:
 * Udemy: Multithreading and Concurrent Programming in Java
 */
public class SimpleRecursiveActionApp {

    public static void main(String[] args) {
        ForkJoinPool pool = new ForkJoinPool(Runtime.getRuntime().availableProcessors());

        SimpleRecursiveAction simpleRecursiveAction = new SimpleRecursiveAction(20);
        pool.invoke(simpleRecursiveAction);

        simpleRecursiveAction = new SimpleRecursiveAction(200);
        pool.invoke(simpleRecursiveAction);
    }

}
