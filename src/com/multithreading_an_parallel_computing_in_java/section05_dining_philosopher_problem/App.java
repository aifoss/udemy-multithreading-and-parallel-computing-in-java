package com.multithreading_an_parallel_computing_in_java.section05_dining_philosopher_problem;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static com.multithreading_an_parallel_computing_in_java.section05_dining_philosopher_problem.Constants.NUMBER_OF_CHOPSTICKS;
import static com.multithreading_an_parallel_computing_in_java.section05_dining_philosopher_problem.Constants.NUMBER_OF_PHILOSOPHERS;
import static com.multithreading_an_parallel_computing_in_java.section05_dining_philosopher_problem.Constants.SIMULATION_RUNNING_TIME;

/**
 * Created by sofia on 2018-11-24.
 */

/**
 * Sources:
 * Udemy: Multithreading and Concurrent Programming in Java
 */
public class App {

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = null;
        Philosopher[] philosophers = null;

        try {
            philosophers = new Philosopher[NUMBER_OF_PHILOSOPHERS];
            Chopstick[] chopsticks = new Chopstick[NUMBER_OF_CHOPSTICKS];

            for (int i = 0; i < NUMBER_OF_CHOPSTICKS; i++) {
                chopsticks[i] = new Chopstick(i);
            }

            executorService = Executors.newFixedThreadPool(NUMBER_OF_PHILOSOPHERS);

            for (int i = 0; i < NUMBER_OF_PHILOSOPHERS; i++) {
                philosophers[i] = new Philosopher(i, chopsticks[i], chopsticks[(i+1) % NUMBER_OF_CHOPSTICKS]);
                executorService.execute(philosophers[i]);
            }

            Thread.sleep(SIMULATION_RUNNING_TIME);

            for (Philosopher p : philosophers) {
                p.setFull(true);
            }

        } finally {
            executorService.shutdown();

            while (!executorService.isTerminated()) {
                Thread.sleep(1000);
            }

            for (Philosopher p : philosophers) {
                System.out.println(p+" has eaten "+p.getCounter()+" times");
            }
        }
    }

}
