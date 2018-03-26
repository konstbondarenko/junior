package ru.kbond.generic.storage;

import ru.kbond.generic.AbstractStore;
import ru.kbond.generic.SimpleArray;
import ru.kbond.generic.models.User;

/**
 * Class container for storing objects User.
 *
 * @author kbondarenko
 * @since 20.02.2018
 * @version 1
 */
public class UserStore extends AbstractStore<User> {
    /**
     * Constructor.
     *
     * @param simpleArray  container for storing objects User.
     */
    public UserStore(SimpleArray<User> simpleArray) {
        super(simpleArray);
    }
}
