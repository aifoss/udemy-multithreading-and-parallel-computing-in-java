package com.multithreading_an_parallel_computing_in_java.section03_basic_multithreading;

/**
 * Created by sofia on 2018-11-22.
 */

/**
 * Sources:
 * Udemy: Multithreading and Concurrent Programming in Java
 */
public class C11_Volatile {

    static class VolatileWorker implements Runnable {

        private volatile boolean isTerminated = false;

        @Override
        public void run() {
            while (!isTerminated) {
                System.out.println("Hello from worker class ...");

                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        public boolean isTerminated() {
            return isTerminated;
        }

        public void setTerminated(boolean terminated) {
            isTerminated = terminated;
        }

    }


    public static void main(String[] args) {
        VolatileWorker worker = new VolatileWorker();
        Thread t1 = new Thread(worker);
        t1.start();

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        worker.setTerminated(true);

        System.out.println("Finished");
    }

}
