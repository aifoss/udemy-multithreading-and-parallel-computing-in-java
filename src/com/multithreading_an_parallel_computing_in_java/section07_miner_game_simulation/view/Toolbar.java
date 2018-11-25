package com.multithreading_an_parallel_computing_in_java.section07_miner_game_simulation.view;

import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by sofia on 2018-11-24.
 */

/**
 * Sources:
 * Udemy: Multithreading and Concurrent Programming in Java
 */
public class Toolbar extends JPanel implements ActionListener {

    private static final long serialVersionUID = 1L;

    private JButton startButton;
    private JButton pauseButton;
    private JButton stopButton;

    private ButtonListener listener;

    public Toolbar() {
        setLayout(new FlowLayout(FlowLayout.CENTER));
        initVariables();

        add(startButton);
        add(pauseButton);
        add(stopButton);
    }

    private void initVariables() {
        this.startButton = new JButton("Start");
        this.pauseButton = new JButton("Pause");
        this.stopButton = new JButton("Stop");

        this.startButton.addActionListener(this);
        this.pauseButton.addActionListener(this);
        this.stopButton.addActionListener(this);
    }

    public void setButtonListener(ButtonListener listener) {
        this.listener = listener;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if (this.listener != null) {
            if (event.getSource() == this.startButton) {
                this.listener.startClicked();
            } else if (event.getSource() == this.pauseButton) {
                this.listener.pauseClicked();
            } else {
                this.listener.stopClicked();
            }
        }
    }

}
