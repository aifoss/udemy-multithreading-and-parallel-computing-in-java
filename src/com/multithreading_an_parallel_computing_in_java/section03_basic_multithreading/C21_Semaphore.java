package com.multithreading_an_parallel_computing_in_java.section03_basic_multithreading;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * Created by sofia on 2018-11-23.
 */

/**
 * Sources:
 * Udemy: Multithreading and Concurrent Programming in Java
 */
public class C21_Semaphore {

    enum Downloader {

        INSTANCE;

        private Semaphore semaphore = new Semaphore(3, true);

        public void downloadData() {
            try {
                semaphore.acquire();
                download();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                semaphore.release();
            }
        }

        private void download() {
            System.out.println(Thread.currentThread().getName()+" downloading data from the web ...");

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();

        for (int i = 0; i < 12; i++) {
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    Downloader.INSTANCE.downloadData();
                }
            });
        }

        executorService.shutdown();
    }

}
