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
public class MineLayer implements Runnable {

    private int id;
    private Board board;
    private volatile boolean layerRunning;

    public MineLayer(int id, Board board) {
        this.id = id;
        this.board = board;
        this.layerRunning = true;
    }

    @Override
    public void run() {
        Random random = new Random();

        while (layerRunning) {
            if (Thread.currentThread().isInterrupted()) {
                return;
            }

            int index = random.nextInt(Constants.BOARD_ROWS*Constants.BOARD_COLUMNS);

            if (board.setMine(index)) {
                System.out.println(this+" set mine at index "+index);
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
        setLayerRunning(false);
    }

    public void setLayerRunning(boolean layerRunning) {
        this.layerRunning = layerRunning;
    }

    @Override
    public String toString() {
        return "MineLayer"+id;
    }

}
