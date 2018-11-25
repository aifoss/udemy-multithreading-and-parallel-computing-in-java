package com.multithreading_an_parallel_computing_in_java.section03_basic_multithreading;

/**
 * Created by sofia on 2018-11-22.
 */

/**
 * Sources:
 * Udemy: Multithreading and Concurrent Programming in Java
 */
public class C07_SequentialProcessing {

    static class SequentialRunner {

        private String name;

        public SequentialRunner(String name) {
            this.name = name;
        }

        public void startRunning() {
            for (int i = 0; i < 10; i++) {
                System.out.println(name+": "+i);
            }
        }
    }


    public static void main(String[] args) {
        SequentialRunner runner1 = new SequentialRunner("Runner1");
        SequentialRunner runner2 = new SequentialRunner("Runner2");

        runner1.startRunning();
        runner2.startRunning();
    }

}


