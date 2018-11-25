package com.multithreading_an_parallel_computing_in_java.section05_dining_philosopher_problem;

import java.util.Random;

/**
 * Created by sofia on 2018-11-24.
 */

/**
 * Sources:
 * Udemy: Multithreading and Concurrent Programming in Java
 */
public class Philosopher implements Runnable {

    private int id;
    private Chopstick leftChopstick;
    private Chopstick rightChopstick;
    private Random random;
    private int eatingCounter;
    private volatile boolean isFull = false;

    public Philosopher(int id, Chopstick leftChopstick, Chopstick rightChopstick) {
        this.id = id;
        this.leftChopstick = leftChopstick;
        this.rightChopstick = rightChopstick;
        this.random = new Random();
    }

    @Override
    public void run() {
        try {
            while (!isFull) {
                think();

                if (leftChopstick.pickUp(this, Side.LEFT)) {
                    if (rightChopstick.pickUp(this, Side.RIGHT)) {
                        eat();
                        rightChopstick.putDown(this, Side.RIGHT);
                    }
                    leftChopstick.putDown(this, Side.LEFT);
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    private void think() throws InterruptedException {
        System.out.println(this+" is thinking ...");
        Thread.sleep(random.nextInt(1000));
    }

    private void eat() throws InterruptedException {
        System.out.println(this+" is eating ...");
        eatingCounter++;
        Thread.sleep(random.nextInt(1000));
    }

    public int getCounter() {
        return eatingCounter;
    }

    public void setFull(boolean isFull) {
        this.isFull = isFull;
    }

    @Override
    public String toString() {
        return "philosopher"+id;
    }

}
