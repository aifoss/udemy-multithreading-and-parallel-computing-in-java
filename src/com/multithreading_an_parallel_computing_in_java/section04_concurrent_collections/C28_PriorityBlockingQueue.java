package com.multithreading_an_parallel_computing_in_java.section04_concurrent_collections;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;

/**
 * Created by sofia on 2018-11-24.
 */

/**
 * Sources:
 * Udemy: Multithreading and Concurrent Programming in Java
 */
public class C28_PriorityBlockingQueue {

    static class PriorityProducer implements Runnable {

        private BlockingQueue<Person> queue;

        public PriorityProducer(BlockingQueue<Person> queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            try {
                queue.put(new Person(10, "Tom"));
                queue.put(new Person(20, "Sam"));
                queue.put(new Person(30, "John"));
                Thread.sleep(1000);
                queue.put(new Person(40, "Paul"));
                Thread.sleep(1000);
                queue.put(new Person(50, "James"));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static class PriorityConsumer implements Runnable {

        private BlockingQueue<Person> queue;

        public PriorityConsumer(BlockingQueue<Person> queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(5000);
                System.out.println(queue.take());
                Thread.sleep(1000);
                System.out.println(queue.take());
                Thread.sleep(1000);
                System.out.println(queue.take());
                System.out.println(queue.take());
                System.out.println(queue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static class Person implements Comparable<Person> {

        private int age;
        private String name;

        public Person(int age, String name) {
            this.age = age;
            this.name = name;
        }

        @Override
        public int compareTo(Person other) {
            return name.compareTo(other.name);
        }

        @Override
        public String toString() {
            return name+" ("+age+")";
        }
    }


    public static void main(String[] args) {
        BlockingQueue<Person> queue = new PriorityBlockingQueue<>();

        new Thread(new PriorityProducer(queue)).start();
        new Thread(new PriorityConsumer(queue)).start();
    }

}
