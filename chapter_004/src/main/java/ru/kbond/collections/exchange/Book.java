package ru.kbond.collections.exchange;

import java.util.Objects;

/**
 * Book.
 *
 * @author kbondarenko
 * @since 25.03.2018
 * @version 1
 */
public class Book {
    /**
     * Identification number.
     */
    private int id;
    /**
     * Security identifier.
     */
    private String book;
    /**
     * Application type.
     */
    private String type;
    /**
     * To buy or sell.
     */
    private String action;
    /**
     * The price to buy or sell.
     */
    private int price;
    /**
     * Number of shares in the application.
     */
    private int volume;
    /**
     * Constructor.
     *
     * @param id  identification number.
     * @param book  security identifier.
     * @param type  application type.
     * @param action  bid or ask.
     * @param price  price to buy or sell.
     * @param volume  number of shares in the application.
     */
    public Book(int id, String book, String type, String action, int price, int volume) {
        this.id = id;
        this.book = book;
        this.type = type;
        this.action = action;
        this.price = price;
        this.volume = volume;
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
    public String getBook() {
        return book;
    }
    /**
     * Getter.
     */
    public String getType() {
        return type;
    }
    /**
     * Getter.
     */
    public String getAction() {
        return action;
    }
    /**
     * Getter.
     */
    public int getPrice() {
        return price;
    }
    /**
     * Getter.
     */
    public int getVolume() {
        return volume;
    }
    /**
     * Setter.
     */
    public void setVolume(int volume) {
        this.volume = volume;
    }
    /**
     * ToString.
     */
    @Override
    public String toString() {
        return "Book { "
                +
                "id = " + id
                +
                ", book = '" + book + '\''
                +
                ", type = '" + type + '\''
                +
                ", action = '" + action + '\''
                +
                ", price = " + price
                +
                ", volume = " + volume
                +
                '}';
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
        Book book1 = (Book) o;
        return id == book1.id
                &&
                Objects.equals(book, book1.book);
    }
    /**
     * HashCode.
     */
    @Override
    public int hashCode() {
        return Objects.hash(id, book);
    }
}
