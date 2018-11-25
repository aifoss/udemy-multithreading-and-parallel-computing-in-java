package com.multithreading_an_parallel_computing_in_java.section06_student_library_simulation;

/**
 * Created by sofia on 2018-11-24.
 */

/**
 * Sources:
 * Udemy: Multithreading and Concurrent Programming in Java
 */
public class Record {

    private long timestamp;
    private int studentId;

    public Record(long timestamp, int studentId) {
        this.timestamp = timestamp;
        this.studentId = studentId;
    }

    public int getStudentId() {
        return studentId;
    }

    @Override
    public String toString() {
        return timestamp+": student"+studentId;
    }

}
