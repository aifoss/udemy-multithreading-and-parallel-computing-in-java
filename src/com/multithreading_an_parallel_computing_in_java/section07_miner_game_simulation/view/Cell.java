package com.multithreading_an_parallel_computing_in_java.section07_miner_game_simulation.view;

import com.multithreading_an_parallel_computing_in_java.section07_miner_game_simulation.constants.State;

import javax.swing.JPanel;
import java.awt.GridLayout;
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
public class Cell extends JPanel {

    private static final long serialVersionUID = 1L;

    private int id;
    private Lock lock;
    private State state;
    private boolean hasBomb;

    public Cell(int id) {
        initVariables(id);
        setLayout(new GridLayout());
    }

    private void initVariables(int id) {
        this.id = id;
        this.lock = new ReentrantLock();
        this.state = State.CLEAR;
        this.hasBomb = false;
    }

    public void lock() {
        try {
            lock.tryLock(10, TimeUnit.HOURS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void unlock() {
        lock.unlock();
    }

    public boolean hasBomb() {
        return hasBomb;
    }

    public void setHasBomb(boolean hasBomb) {
        this.hasBomb = hasBomb;
    }

    public void setState(State state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return ""+this.id+"-"+state+"-"+hasBomb;
    }

}
