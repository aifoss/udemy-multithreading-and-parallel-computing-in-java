package com.multithreading_an_parallel_computing_in_java.section03_basic_multithreading;

import static com.multithreading_an_parallel_computing_in_java.section03_basic_multithreading.C09_StartingThreads2_Thread.ThreadRunner;

/**
 * Created by sofia on 2018-11-22.
 */

/**
 * Sources:
 * Udemy: Multithreading and Concurrent Programming in Java
 */
public class C10_Join {

    public static void main(String[] args) {
        ThreadRunner t1 = new ThreadRunner("Runner1");
        ThreadRunner t2 = new ThreadRunner("Runner2");

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Finished tasks");
    }

}
