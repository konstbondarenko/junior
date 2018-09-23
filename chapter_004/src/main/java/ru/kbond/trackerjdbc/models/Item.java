package ru.kbond.trackerjdbc.models;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Objects;

/**
 * Class Item - класс для создания объектов типа item.
 *
 * @author kbondarenko
 * @version 1
 * @since 22.05.2017
 */
public class Item {

    private int id;
    /**
     * Поле хранящее имя.
     *
     * @param name - имя.
     */
    private String name;
    /**
     * Поле хранящее описание.
     *
     * @param desc - описание.
     */
    private String desc;
    /**
     * Поле хранящее дату создания.
     *
     * @param created - дата создания.
     */
    private long created;

    /**
     * Конструктор.
     *
     * @param name    - имя.
     * @param desc    - описание.
     * @param created - дата создания.
     */
    public Item(String name, String desc, long created) {
        this.name = name;
        this.desc = desc;
        this.created = created;
    }

    public void setId(int id) {
        this.id = id;
    }

    /**
     * Setter.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Setter.
     */
    public void setDesc(String desc) {
        this.desc = desc;
    }

    /**
     * Setter.
     */
    public void setCreated(long created) {
        this.created = created;
    }

    public int getId() {
        return id;
    }

    /**
     * Getter.
     */
    public String getName() {
        return name;
    }

    /**
     * Getter.
     */
    public String getDesc() {
        return desc;
    }

    /**
     * Getter.
     */
    public long getCreated() {
        return created;
    }

    /**
     * To string.
     */
    @Override
    public String toString() {
        return "id: " + id + ", name: " + name + '\''
                + ", description: " + desc + '\''
                + ", date created: " + new SimpleDateFormat("dd-MM-YYYY").format(new Date(created))
                + '}';
    }

    /**
     * Equals.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Item item = (Item) o;
        return id == item.id
                &&
                created == item.created;
    }

    /**
     * HashCode.
     */
    @Override
    public int hashCode() {
        return Objects.hash(id, created);
    }
}
