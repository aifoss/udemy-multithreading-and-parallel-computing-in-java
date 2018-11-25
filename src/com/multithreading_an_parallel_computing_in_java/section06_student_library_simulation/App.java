package com.multithreading_an_parallel_computing_in_java.section06_student_library_simulation;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static com.multithreading_an_parallel_computing_in_java.section06_student_library_simulation.Constants.NUMBER_OF_BOOKS;
import static com.multithreading_an_parallel_computing_in_java.section06_student_library_simulation.Constants.NUMBER_OF_STUDENTS;

/**
 * Created by sofia on 2018-11-24.
 */

/**
 * Sources:
 * Udemy: Multithreading and Concurrent Programming in Java
 */
public class App {

    public static void main(String[] args) {
        Book[] books = new Book[NUMBER_OF_BOOKS];
        Student[] students = new Student[NUMBER_OF_STUDENTS];

        for (int i = 0; i < NUMBER_OF_BOOKS; i++) {
            books[i] = new Book(i);
        }
        for (int i = 0; i < NUMBER_OF_STUDENTS; i++) {
            students[i] = new Student(i, books);
        }

        ExecutorService executorService = Executors.newFixedThreadPool(NUMBER_OF_STUDENTS);

        try {
            for (int i = 0; i < NUMBER_OF_STUDENTS; i++) {
                executorService.execute(students[i]);
            }

        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            executorService.shutdown();

            while (!executorService.isTerminated()) {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            for (int i = 0; i < NUMBER_OF_BOOKS; i++) {
                System.out.println(books[i].getRecords());
            }

            System.out.println("All books have been read by each student");
        }
    }

}
