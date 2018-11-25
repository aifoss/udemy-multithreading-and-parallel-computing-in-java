package com.multithreading_an_parallel_computing_in_java.section04_concurrent_collections;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Created by sofia on 2018-11-24.
 */

/**
 * Sources:
 * Udemy: Multithreading and Concurrent Programming in Java
 */
public class C29_ConcurrentHashMap {

    static class ConcurrentMapProducer implements Runnable {

        private ConcurrentMap<String, Integer> map;

        public ConcurrentMapProducer(ConcurrentMap<String, Integer> map) {
            this.map = map;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(1000);
                map.put("E", 5);
                map.put("C", 3);
                Thread.sleep(2000);
                map.put("D", 4);
                Thread.sleep(1000);
                map.put("A", 1);
                map.put("B", 2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static class ConcurrentMapConsumer implements Runnable {

        private ConcurrentMap<String, Integer> map;

        public ConcurrentMapConsumer(ConcurrentMap<String, Integer> map) {
            this.map = map;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(5000);
                System.out.println(map.get("A"));
                Thread.sleep(2000);
                System.out.println(map.get("B"));
                Thread.sleep(1000);
                System.out.println(map.get("C"));
                System.out.println(map.get("D"));
                System.out.println(map.get("E"));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    public static void main(String[] args) {
        ConcurrentMap<String, Integer> map = new ConcurrentHashMap<>();

        new Thread(new ConcurrentMapProducer(map)).start();
        new Thread(new ConcurrentMapConsumer(map)).start();
    }

}
