package ru.kbond.observer.listener;

/**
 * Слушатель выводящий сообщения в лог.
 *
 * @author kbondarenko
 * @version 1
 * @since 23.01.2019
 */
public class ConsoleWriter implements EventListener {
    @Override
    public void update(String eventType, String msg) {
        System.out.println(eventType + msg);
    }
}
