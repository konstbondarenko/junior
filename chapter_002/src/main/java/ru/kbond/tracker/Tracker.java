package ru.kbond.tracker;

import java.util.Random;

/**
 * Class Tracker - обёртка над массивом.
 * @author kbondarenko
 * @since 22.05.2017
 * @version 1
 */
public class Tracker {
    /**
     * Поле хранящее массив заявок.
     * @param items - имя массива.
     */
    private Item[] items = new Item[100];
    /**
     * Поле хранящее номер-позицию для добавления нового элемента в массив.
     * @param position - позиция.
     */
    private int position = 0;
    /**
     * Поле генерирующее случайное число для создания id.
     * @param RN - позиция.
     */
    private static final Random RN = new Random();
    /**
     * Метод добавляет заявку, переданную в аргументах в массив заявок this.items.
     * @param item принимаемый объект.
     * @return результат.
     */
    public Item add(Item item) {
        this.items[position++] = item;
        return item;
    }
    /**
     * Метод для обновления элемента массива.
     * @param item принимаемый объект.
     */
    public void update(Item item) {
        for (int index = 0; index != this.position; index++) {
            if (this.items[index] != null && item.getId().equals(this.items[index].getId())) {
                this.items[index] = item;
                break;
            }
        }
    }
    /**
     * Метод для удаления элемента массива this.items по id.
     * @param id принимаемый номер.
     */
    public void delete(String id) {
        for (int index = 0; index != this.position; index++) {
            if (this.items[index] != null && id.equals(this.items[index].getId())) {
                if (index == this.items.length - 1) {
                    this.items[this.items.length - 1] = null;
                } else {
                    System.arraycopy(this.items, index + 1, this.items, index, this.items.length - 1 - index);
                    if (this.items[this.items.length - 1] != null) {
                        this.items[this.items.length - 1] = null;
                    }
                }
                position--;
                break;
            }
        }
    }
    /**
     * Метод возвращает копию массива this.items без null элементов.
     * @return результат.
     */
    public Item[] findAll() {
        int count = 0;
        for (int index = 0; index != this.items.length; index++) {
            if (this.items[index] != null) {
                count++;
            }
        }
        Item[] result = new Item[count];
        count = 0;
        for (int index = 0; index != this.items.length; index++) {
            if (this.items[index] != null) {
                result[count] = this.items[index];
                count++;
            }
        }
        return result;
    }
    /**
     * Метод копирует в результирующий массив элементы, у которых совпадает name и возвращает его.
     * @param key принимаемое имя.
     * @return результат.
     */
    public Item[] findByName(String key) {
        int count = 0;
        for (int index = 0; index != this.items.length; index++) {
            if (this.items[index] != null && key.equals(this.items[index].getName())) {
                count++;
            }
        }
        Item[] result = new Item[count];
        count = 0;
        for (int index = 0; index != this.items.length; index++) {
            if (this.items[index] != null && key.equals(this.items[index].getName())) {
                result[count] = this.items[index];
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
    public Item[] findById(String id) {
        int count = 0;
        for (int index = 0; index != this.items.length; index++) {
            if (this.items[index] != null && id.equals(this.items[index].getId())) {
                count++;
            }
        }
        Item[] result = new Item[count];
        count = 0;
        for (int index = 0; index != this.items.length; index++) {
            if (this.items[index] != null && id.equals(this.items[index].getId())) {
                result[count] = this.items[index];
                count++;
            }
        }
        return result;
    }
}
