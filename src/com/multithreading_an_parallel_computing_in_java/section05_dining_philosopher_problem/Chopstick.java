package com.multithreading_an_parallel_computing_in_java.section05_dining_philosopher_problem;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by sofia on 2018-11-24.
 */

/**
 * Sources:
 * Udemy: Multithreading and Concurrent Programming in Java
 */
public class Chopstick {

    private int id;
    private Lock lock;

    public Chopstick(int id) {
        this.id = id;
        this.lock = new ReentrantLock();
    }

    public boolean pickUp(Philosopher philosopher, Side side) throws InterruptedException {
        if (lock.tryLock(10, TimeUnit.MILLISECONDS)) {
            System.out.println(philosopher+" picked up "+ side +" "+this);
            return true;
        }

        return false;
    }

    public void putDown(Philosopher philosopher, Side side) {
        lock.unlock();
        System.out.println(philosopher+" put down "+this);
    }

    @Override
    public String toString() {
        return "chopstick"+id;
    }

}
