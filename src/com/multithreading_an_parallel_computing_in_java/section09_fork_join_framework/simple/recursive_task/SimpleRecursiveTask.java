package com.multithreading_an_parallel_computing_in_java.section09_fork_join_framework.simple.recursive_task;

import java.util.concurrent.RecursiveTask;

/**
 * Created by sofia on 2018-11-24.
 */

/**
 * Sources:
 * Udemy: Multithreading and Concurrent Programming in Java
 */
public class SimpleRecursiveTask extends RecursiveTask<Integer> {

    private int simulatedWork;

    public SimpleRecursiveTask(int simulatedWork) {
        this.simulatedWork = simulatedWork;
    }

    @Override
    public Integer compute() {
        if (simulatedWork > 100) {
            System.out.println("Parallel execution ... "+simulatedWork);

            SimpleRecursiveTask task1 = new SimpleRecursiveTask(simulatedWork/2);
            SimpleRecursiveTask task2 = new SimpleRecursiveTask(simulatedWork/2);

            task1.fork();
            task2.fork();

            int result = 0;

            result += task1.join();
            result += task2.join();

            return result;

        } else {
            System.out.println("Sequential execution ... "+simulatedWork);
            return 2*simulatedWork;
        }
    }

}
