package com.multithreading_an_parallel_computing_in_java.section06_student_library_simulation;

import java.util.ArrayList;
import java.util.List;
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
public class Book {

    private int id;
    private Lock lock;
    private List<Record> records;

    public Book(int id) {
        this.id = id;
        this.lock = new ReentrantLock();
        this.records = new ArrayList<>();
    }

    public void read(Student student) throws InterruptedException {
        if (lock.tryLock(1, TimeUnit.SECONDS)) {
            System.out.println(student + " starts reading " + this);

            Thread.sleep(2000);
            lock.unlock();

            System.out.println(student + " finished reading " + this);

            records.add(new Record(System.currentTimeMillis(), student.getId()));
        }
    }

    public boolean alreadyReadBy(int studentId) {
        for (int i = 0; i < records.size(); i++) {
            if (records.get(i).getStudentId() == studentId) {
                return true;
            }
        }
        return false;
    }

    public List<Record> getRecords() {
        return records;
    }

    @Override
    public String toString() {
        return "book"+this.id;
    }

}
