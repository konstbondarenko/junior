package ru.kbond.multithreading.nonblock;

public class OptimisticException extends RuntimeException {
    public OptimisticException(String msg) {
        super(msg);
    }
}
