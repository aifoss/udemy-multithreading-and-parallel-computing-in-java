package com.multithreading_an_parallel_computing_in_java.section09_fork_join_framework.simple.recursive_action;

import java.util.concurrent.RecursiveAction;

/**
 * Created by sofia on 2018-11-24.
 */

/**
 * Sources:
 * Udemy: Multithreading and Concurrent Programming in Java
 */
public class SimpleRecursiveAction extends RecursiveAction {

    private int simulatedWork;

    public SimpleRecursiveAction(int simulatedWork) {
        this.simulatedWork = simulatedWork;
    }

    @Override
    public void compute() {
        if (simulatedWork > 100) {
            System.out.println("Parallel execution and split task ... "+simulatedWork);

            SimpleRecursiveAction action1 = new SimpleRecursiveAction(simulatedWork/2);
            SimpleRecursiveAction action2 = new SimpleRecursiveAction(simulatedWork/2);

            action1.fork();
            action2.fork();

        } else {
            System.out.println("Sequential execution ... "+simulatedWork);
            System.out.println("Done");
        }
    }

}
