package ru.kbond.multithreading.bomberman;

public class InvalidStepException extends RuntimeException {

    public InvalidStepException(String msg) {
        super(msg);
    }
}
