package com.multithreading_an_parallel_computing_in_java.section06_student_library_simulation;

import java.util.Random;

import static com.multithreading_an_parallel_computing_in_java.section06_student_library_simulation.Constants.NUMBER_OF_BOOKS;

/**
 * Created by sofia on 2018-11-24.
 */

/**
 * Sources:
 * Udemy: Multithreading and Concurrent Programming in Java
 */
public class Student implements Runnable {

    private int id;
    private Book[] books;

    public Student(int id, Book[] books) {
        this.id = id;
        this.books = books;
    }

    @Override
    public void run() {
        System.out.println(this+" started running");

        Random random = new Random();

        while (!readAllBooks()) {
            int bookId = random.nextInt(NUMBER_OF_BOOKS);
            Book book = books[bookId];

            if (alreadyReadBook(book)) {
                System.out.println(this+" already read "+book+", not reading it again");
                continue;
            }

            try {
                book.read(this);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean readAllBooks() {
        for (int i = 0; i < NUMBER_OF_BOOKS; i++) {
            if (!alreadyReadBook(books[i])) {
                return false;
            }
        }
        return true;
    }

    public boolean alreadyReadBook(Book book) {
        return book.alreadyReadBy(this.id);
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "student"+id;
    }

}
