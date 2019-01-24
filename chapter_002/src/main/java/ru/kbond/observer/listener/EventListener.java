package ru.kbond.observer.listener;

/**
 * Интерфейс слушателя.
 *
 * @author kbondarenko
 * @version 1
 * @since 23.01.2019
 */
public interface EventListener {
    void update(String eventType, String data);
}
