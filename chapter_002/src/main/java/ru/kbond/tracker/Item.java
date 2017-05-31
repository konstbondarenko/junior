package ru.kbond.tracker;

import java.util.Arrays;

/**
 * Class Item - класс для создания объектов типа item.
 * @author kbondarenko
 * @since 22.05.2017
 * @version 1
 */
public class Item {
    /**
     * Поле хранящее id.
     * @param id - номер.
     */
    private String id;
    /**
     * Поле хранящее имя.
     * @param name - имя.
     */
    private String name;
    /**
     * Поле хранящее описание.
     * @param desc - описание.
     */
    private String desc;
    /**
     * Поле хранящее дату создания.
     * @param created - дата создания.
     */
    private long created;
    /**
     * Поле хранящее комментарии.
     * @param comments - комментарии.
     */
    private String[] comments;
    /**
     * Конструктор.
     * @param id - номер.
     * @param name - имя.
     * @param desc - описание.
     * @param created - дата создания.
     * @param comments - комментарии.
     */
    public Item(String id, String name, String desc, long created, String[] comments) {
        this.id = id;
        this.name = name;
        this.desc = desc;
        this.created = created;
        this.comments = comments;
    }
    /**
     * Метод переопределяющий "toString".
     * @return - результат.
     */
    @Override
    public String toString() {
        return "id: " + this.id
                + "; name: " + this.name
                + "; description: " + this.desc
                + "; date created: " + this.created
                + "; comments: " + Arrays.toString(this.comments);
    }
    /**
     * Setter.
     * @param id - номер.
     */
    public void setId(String id) {
        this.id = id;
    }
    /**
     * Setter.
     * @param name - имя.
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * Setter.
     * @param desc - описание.
     */
    public void setDesc(String desc) {
        this.desc = desc;
    }
    /**
     * Setter.
     * @param created - дата создания.
     */
    public void setCreated(long created) {
        this.created = created;
    }
    /**
     * Setter.
     * @param comments - комментарии.
     */
    public void setComments(String[] comments) {
        this.comments = comments;
    }
    /**
     * Getter.
     * @return - результат.
     */
    public String getId() {
        return id;
    }
    /**
     * Getter.
     * @return - результат.
     */
    public String getName() {
        return name;
    }
    /**
     * Getter.
     * @return - результат.
     */
    public String getDesc() {
        return desc;
    }
    /**
     * Getter.
     * @return - результат.
     */
    public long getCreated() {
        return created;
    }
    /**
     * Getter.
     * @return - результат.
     */
    public String[] getComments() {
        return comments;
    }
}
