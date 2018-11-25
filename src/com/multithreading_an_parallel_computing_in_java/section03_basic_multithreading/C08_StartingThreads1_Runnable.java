package com.multithreading_an_parallel_computing_in_java.section03_basic_multithreading;

/**
 * Created by sofia on 2018-11-22.
 */

/**
 * Sources:
 * Udemy: Multithreading and Concurrent Programming in Java
 */
public class C08_StartingThreads1_Runnable {

    static class RunnableRunner implements Runnable {

        private String name;

        public RunnableRunner(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                System.out.println(name+": "+i);
            }
        }

    }


    public static void main(String[] args) {
        Thread t1 = new Thread(new RunnableRunner("Runner1"));
        Thread t2 = new Thread(new RunnableRunner("Runner2"));

        t1.start();
        t2.start();
    }

}
