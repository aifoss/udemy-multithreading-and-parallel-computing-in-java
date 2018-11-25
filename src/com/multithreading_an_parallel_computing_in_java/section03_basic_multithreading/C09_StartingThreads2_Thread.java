package com.multithreading_an_parallel_computing_in_java.section03_basic_multithreading;

/**
 * Created by sofia on 2018-11-22.
 */

/**
 * Sources:
 * Udemy: Multithreading and Concurrent Programming in Java
 */
public class C09_StartingThreads2_Thread {

    static class ThreadRunner extends Thread {

        public ThreadRunner(String name) {
            super(name);
        }

        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                System.out.println(getName()+": "+i);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

    }


    public static void main(String[] args) {
        ThreadRunner t1 = new ThreadRunner("Runner1");
        ThreadRunner t2 = new ThreadRunner("Runner2");

        t1.start();
        t2.start();
    }

}

