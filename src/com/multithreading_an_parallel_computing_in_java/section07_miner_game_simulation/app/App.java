package com.multithreading_an_parallel_computing_in_java.section07_miner_game_simulation.app;

import com.multithreading_an_parallel_computing_in_java.section07_miner_game_simulation.view.MainFrame;

import javax.swing.UIManager;
import javax.swing.SwingUtilities;
import javax.swing.UnsupportedLookAndFeelException;

/**
 * Created by sofia on 2018-11-24.
 */

/**
 * Sources:
 * Udemy: Multithreading and Concurrent Programming in Java
 */
public class App {

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainFrame();
            }
        });
    }

}
