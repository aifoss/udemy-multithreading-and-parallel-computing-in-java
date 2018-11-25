package com.multithreading_an_parallel_computing_in_java.section07_miner_game_simulation.workers;

import com.multithreading_an_parallel_computing_in_java.section07_miner_game_simulation.constants.Constants;
import com.multithreading_an_parallel_computing_in_java.section07_miner_game_simulation.view.Board;

import java.util.Random;

/**
 * Created by sofia on 2018-11-24.
 */

/**
 * Sources:
 * Udemy: Multithreading and Concurrent Programming in Java
 */
public class MineSweeper implements Runnable {

    private int id;
    private Board board;
    private volatile boolean sweeperRunning;

    public MineSweeper(int id, Board board) {
        this.id = id;
        this.board = board;
        this.sweeperRunning = true;
    }

    @Override
    public void run() {
        Random random = new Random();

        while (sweeperRunning) {
            if (Thread.currentThread().isInterrupted()) {
                return;
            }

            int index = random.nextInt(Constants.BOARD_ROWS*Constants.BOARD_COLUMNS);

            if (board.sweepMine(index)) {
                System.out.println(this+" swept mine at index "+index);
                System.out.println("numberOfMines = "+board.getNumberOfMines());
            }

            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void stop() {
        setSweeperRunning(false);
    }

    public void setSweeperRunning(boolean sweeperRunning) {
        this.sweeperRunning = sweeperRunning;
    }

    @Override
    public String toString() {
        return "MineSweeper"+id;
    }

}
