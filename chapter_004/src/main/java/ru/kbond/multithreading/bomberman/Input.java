package ru.kbond.multithreading.bomberman;

/**
 * The interface for implementing various input methods.
 *
 * @author kbondarenko
 * @version 1
 * @since 09.07.2018
 */
public interface Input {
    /**
     * The method that implements the input of coordinates.
     *
     * @param question received string.
     */
    int ask(String question);
}
