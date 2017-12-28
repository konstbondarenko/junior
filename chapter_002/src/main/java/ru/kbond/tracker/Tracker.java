package ru.kbond.tracker;

import java.util.ArrayList;
import java.util.List;

/**
 * Class Tracker - добавление функционала для List<>.
 * @author kbondarenko
 * @since 22.05.2017
 * @version 1
 */
public class Tracker {
    /**
     * Поле хранящее List<> заявок.
     * @param items - имя List<>.
     */
    private List<Item> items = new ArrayList<>();
    /**
     * Метод добавляет заявку в List<> заявок items.
     * @param item принимаемый объект.
     */
    public void add(Item item) {
        this.items.add(item);
    }
    /**
     * Метод для обновления элемента по id.
     * @param item принимаемый объект.
     */
    public void update(Item item) {
        for (int index = 0; index != items.size(); index++) {
            if (this.items.get(index).getId().equals(item.getId())) {
                this.items.set(index, item);
                break;
            }
        }
    }
    /**
     * Метод для удаления элемента по id.
     * @param id принимаемый номер.
     */
    public void delete(String id) {
        for (int index = 0; index != items.size(); index++) {
            if (this.items.get(index).getId().equals(id)) {
                this.items.remove(index);
                break;
            }
        }
    }
    /**
     * Метод возвращает копию List<> items без null элементов.
     * @return результат.
     */
    public List<Item> findAll() {
        int countElements = 0;
        for (int index = 0; index != this.items.size(); index++) {
            if (this.items.get(index) != null) {
                countElements++;
            }
        }
        List<Item> result = new ArrayList<>(countElements);
        int count = 0;
        for (int index = 0; index != this.items.size(); index++) {
            if (this.items.get(index) != null) {
                result.add(this.items.get(index));
                count++;
            }
        }
        return result;
    }
    /**
     * Метод копирует в результирующий List<> элементы, у которых совпадает name.
     * @param key принимаемое имя.
     * @return результат.
     */
    public List<Item> findByName(String key) {
        int countElName = 0;
        for (int index = 0; index != this.items.size(); index++) {
            if (this.items.get(index) != null && key.equals(this.items.get(index).getName())) {
                countElName++;
            }
        }
        List<Item> result = new ArrayList<>(countElName);
        int count = 0;
        for (int index = 0; index != this.items.size(); index++) {
            if (this.items.get(index) != null && key.equals(this.items.get(index).getName())) {
                result.add(count, this.items.get(index));
                count++;
            }
        }
        return result;
    }
    /**
     * Метод возвращает item по его id.
     * @param id принимаемый номер.
     * @return результат.
     */
    public List<Item> findById(String id) {
        int countElId = 0;
        for (int index = 0; index != this.items.size(); index++) {
            if (this.items.get(index) != null && id.equals(this.items.get(index).getId())) {
                countElId++;
            }
        }
        List<Item> result = new ArrayList<>(countElId);
        int count = 0;
        for (int index = 0; index != this.items.size(); index++) {
            if (this.items.get(index) != null && id.equals(this.items.get(index).getId())) {
                result.add(count, this.items.get(index));
                count++;
            }
        }
        return result;
    }
}
