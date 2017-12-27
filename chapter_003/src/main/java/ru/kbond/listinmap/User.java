package ru.kbond.listinmap;

/**
 * Class storing user data.
 * @author kbondarenko
 * @since 27.12.2017
 * @version 1
 */
public class User {
    /**
     * The field storing the user id.
     * @param age - user age.
     */
    private int id;
    /**
     * The field storing the name user.
     * @param name - user name.
     */
    private String name;
    /**
     * The field storing the user city.
     * @param city - user city.
     */
    private String city;
    /**
     * Constructor.
     * @param id - user id.
     * @param name - user name.
     * @param city - user city.
     */
    public User(int id, String name, String city) {
        this.id = id;
        this.name = name;
        this.city = city;
    }
    /**
     * Getter.
     * @return getId - user id.
     */
    public int getId() {
        return id;
    }
}
