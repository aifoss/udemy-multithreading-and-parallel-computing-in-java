package com.multithreading_an_parallel_computing_in_java.section07_miner_game_simulation.view;

import com.multithreading_an_parallel_computing_in_java.section07_miner_game_simulation.constants.Constants;
import com.multithreading_an_parallel_computing_in_java.section07_miner_game_simulation.workers.MineLayer;
import com.multithreading_an_parallel_computing_in_java.section07_miner_game_simulation.workers.MineSweeper;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by sofia on 2018-11-24.
 */

/**
 * Sources:
 * Udemy: Multithreading and Concurrent Programming in Java
 */
public class MainFrame extends JFrame implements ButtonListener {

    private static final long serialVersionUID = 1L;

    private Toolbar toolbar;
    private Board board;
    private ExecutorService layerExecutor;
    private ExecutorService sweeperExecutor;
    private MineLayer[] mineLayers;
    private MineSweeper[] mineSweepers;

    public MainFrame() {
        super(Constants.APP_NAME);

        toolbar = new Toolbar();
        board = new Board();

        initializeVariables();

        toolbar.setButtonListener(this);

        add(toolbar, BorderLayout.NORTH);
        add(board, BorderLayout.CENTER);

        setSize(Constants.BOARD_WIDTH, Constants.BOARD_HEIGHT);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void initializeVariables() {
        mineLayers = new MineLayer[Constants.NUMBER_OF_LAYERS];
        mineSweepers = new MineSweeper[Constants.NUMBER_OF_SWEEPERS];
    }

    @Override
    public void startClicked() {
        board.clearBoard();

        System.out.println("========================================================");
        System.out.println("NEW GAME STARTED: "+board.getNumberOfMines());
        System.out.println("========================================================");

        layerExecutor = Executors.newFixedThreadPool(Constants.NUMBER_OF_LAYERS);
        sweeperExecutor = Executors.newFixedThreadPool(Constants.NUMBER_OF_SWEEPERS);

        try {
            startMineWorkers();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            layerExecutor.shutdown();
            sweeperExecutor.shutdown();
        }
    }

    @Override
    public void pauseClicked() {
        stopMineWorkers();

        System.out.println("========================================================");
        System.out.println("GAME PAUSED: "+board.getNumberOfMines());
        System.out.println("========================================================");
    }

    @Override
    public void stopClicked() {
        stopMineWorkers();

        layerExecutor.shutdown();
        sweeperExecutor.shutdown();

        try {
            layerExecutor.awaitTermination(1, TimeUnit.SECONDS);
            sweeperExecutor.awaitTermination(1, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        board.clearBoard();

        System.out.println("========================================================");
        System.out.println("GAME STOPPED: "+board.getNumberOfMines());
        System.out.println("========================================================");
    }

    private void startMineWorkers() {
        int min = Math.min(Constants.NUMBER_OF_LAYERS, Constants.NUMBER_OF_SWEEPERS);

        for (int i = 0; i < min; i++) {
            mineLayers[i] = new MineLayer(i, board);
            layerExecutor.execute(mineLayers[i]);

            mineSweepers[i] = new MineSweeper(i, board);
            sweeperExecutor.execute(mineSweepers[i]);
        }

        for (int i = min; i < Constants.NUMBER_OF_LAYERS; i++) {
            mineLayers[i] = new MineLayer(i, board);
            layerExecutor.execute(mineLayers[i]);
        }
        for (int i = min; i < Constants.NUMBER_OF_SWEEPERS; i++) {
            mineSweepers[i] = new MineSweeper(i, board);
            sweeperExecutor.execute(mineSweepers[i]);
        }
    }

    private void stopMineWorkers() {
        int min = Math.min(Constants.NUMBER_OF_LAYERS, Constants.NUMBER_OF_SWEEPERS);

        for (int i = 0; i < min; i++) {
            mineLayers[i].stop();
            mineSweepers[i].stop();
        }

        for (int i = min; i < Constants.NUMBER_OF_LAYERS; i++) {
            mineLayers[i].stop();
        }
        for (int i = min; i < Constants.NUMBER_OF_SWEEPERS; i++) {
            mineSweepers[i].stop();
        }
    }

}
