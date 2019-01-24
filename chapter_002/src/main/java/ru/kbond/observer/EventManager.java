package ru.kbond.observer;

import ru.kbond.observer.listener.EventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Менеджер подписок и оповещений.
 *
 * @author kbondarenko
 * @version 1
 * @since 23.01.2019
 */
public class EventManager {
    private List<EventListener> listeners = new ArrayList<>();

    public void subscribe(EventListener listener) {
        this.listeners.add(listener);
    }

    public void unsubscribe(EventListener listener) {
        this.listeners.remove(listener);
    }

    public void notify(String eventType, String msg) {
        for (EventListener listener : this.listeners) {
            listener.update(eventType, msg);
        }
    }
}