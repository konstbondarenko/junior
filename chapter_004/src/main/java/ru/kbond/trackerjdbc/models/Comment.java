package ru.kbond.trackerjdbc.models;

import java.sql.Date;
import java.text.SimpleDateFormat;

/**
 * Class Comment - класс для создания объектов типа Comment.
 *
 * @author kbondarenko
 * @version 1
 * @since 20.09.2018
 */
public class Comment {
    private String text;
    private long created;
    private int id;

    /**
     * Constructor.
     *
     * @param text    - текст комментария.
     * @param created - дата создания.
     */
    public Comment(String text, long created) {
        this.text = text;
        this.created = created;
    }

    /**
     * Setter.
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * Setter.
     */
    public void setCreated(long created) {
        this.created = created;
    }

    /**
     * Setter.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Getter.
     */
    public int getId() {
        return id;
    }

    /**
     * Getter.
     */
    public String getText() {
        return text;
    }

    /**
     * To string.
     */
    @Override
    public String toString() {
        return "id: "
                + id
                + ", text = '"
                + text
                + '\''
                + ", date created: "
                + new SimpleDateFormat("dd-MM-YYYY").format(new Date(created))
                + '}';
    }
}
