package ru.kbond.observer;

/**
 * Субъект логгер.
 *
 * @author kbondarenko
 * @version 1
 * @since 23.01.2019
 */
public class Logger {
    public EventManager events;

    public Logger() {
        this.events = new EventManager();
    }

    public void info(String msg) {
        this.events.notify("info: ", msg);
    }

    public void error(String msg) {
        this.events.notify("error: ", msg);
    }
}