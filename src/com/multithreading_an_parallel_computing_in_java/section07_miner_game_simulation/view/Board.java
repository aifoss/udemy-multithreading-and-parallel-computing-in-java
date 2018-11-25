package com.multithreading_an_parallel_computing_in_java.section07_miner_game_simulation.view;

import com.multithreading_an_parallel_computing_in_java.section07_miner_game_simulation.constants.Constants;
import com.multithreading_an_parallel_computing_in_java.section07_miner_game_simulation.constants.State;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.GridLayout;

/**
 * Created by sofia on 2018-11-24.
 */

/**
 * Sources:
 * Udemy: Multithreading and Concurrent Programming in Java
 */
public class Board extends JPanel {

    private static final long serialVersionUID = 1L;

    private Cell[] cells;
    private int size;
    private volatile int numberOfMines;

    public Board() {
        initializeClass();
        setLayout(new GridLayout(Constants.BOARD_ROWS, Constants.BOARD_COLUMNS));
        initializeBoard();
    }

    public synchronized void incrementNumberOfMines() {
        numberOfMines++;
    }

    public synchronized void decrementNumberOfMines() {
        numberOfMines--;
    }

    public boolean setMine(int index) {
        if (cells[index].hasBomb()) return false;

        cells[index].lock();

        cells[index].setHasBomb(true);
        cells[index].setState(State.MINE);
        cells[index].setBackground(Color.RED);

        incrementNumberOfMines();

        cells[index].unlock();

        sleepThread(500);

        return true;
    }

    public boolean sweepMine(int index) {
        if (!cells[index].hasBomb()) return false;

        cells[index].lock();

        cells[index].setHasBomb(false);
        cells[index].setState(State.CLEAR);
        resetCellColor(index);

        decrementNumberOfMines();

        cells[index].unlock();

        sleepThread(500);

        return true;
    }

    public void clearBoard() {
        numberOfMines = 0;

        for (int i = 0; i < size; i++) {
            cells[i].setHasBomb(false);
            resetCellColor(i);
        }
    }

    public int getNumberOfMines() {
        return numberOfMines;
    }

    private void initializeClass() {
        this.size = Constants.BOARD_ROWS * Constants.BOARD_COLUMNS;
        this.cells = new Cell[size];
        this.numberOfMines = 0;
    }

    private void initializeBoard() {
        for (int i = 0; i < size; i++) {
            cells[i] = new Cell(i+1);
            add(cells[i]);
            resetCellColor(i);
        }
    }

    private void resetCellColor(int index) {
        Color color = getOriginalCellColor(index);
        cells[index].setBackground(color);
    }

    private Color getOriginalCellColor(int index) {
        int row = (index / Constants.BOARD_ROWS) % 2;

        if (row == 0) {
            return index % 2 == 0 ? Color.GRAY : Color.WHITE;
        } else {
            return index % 2 == 0 ? Color.WHITE : Color.GRAY;
        }
    }

    private void sleepThread(long duration) {
        try {
            Thread.sleep(duration);
        } catch (InterruptedException e ) {
            e.printStackTrace();
        }
    }

}
