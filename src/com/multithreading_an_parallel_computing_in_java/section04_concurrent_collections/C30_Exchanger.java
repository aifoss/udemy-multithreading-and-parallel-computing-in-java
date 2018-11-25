package com.multithreading_an_parallel_computing_in_java.section04_concurrent_collections;

import java.util.concurrent.Exchanger;

/**
 * Created by sofia on 2018-11-24.
 */

/**
 * Sources:
 * Udemy: Multithreading and Concurrent Programming in Java
 */
public class C30_Exchanger {

    static class FirstThread implements Runnable {

        private int counter;
        private Exchanger<Integer> exchanger;

        public FirstThread(Exchanger<Integer> exchanger) {
            this.exchanger = exchanger;
        }

        @Override
        public void run() {
            while (true) {
                counter = counter+1;
                System.out.println("First thread increments counter: "+counter);

                try {
                    counter = exchanger.exchange(counter);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static class SecondThread implements Runnable {

        private int counter;
        private Exchanger<Integer> exchanger;

        public SecondThread(Exchanger<Integer> exchanger) {
            this.exchanger = exchanger;
        }

        @Override
        public void run() {
            while (true) {
                counter = counter-1;
                System.out.println("Second thread decrements counter: "+counter);

                try {
                    counter = exchanger.exchange(counter);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    public static void main(String[] args) {
        Exchanger<Integer> exchanger = new Exchanger<>();

        new Thread(new FirstThread(exchanger)).start();
        new Thread(new SecondThread(exchanger)).start();
    }

}
