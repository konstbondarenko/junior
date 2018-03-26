package ru.kbond.generic.storage;

import ru.kbond.generic.AbstractStore;
import ru.kbond.generic.SimpleArray;
import ru.kbond.generic.models.Role;

/**
 * Class container for storing objects Role.
 *
 * @author kbondarenko
 * @since 20.02.2018
 * @version 1
 */
public class RoleStore extends AbstractStore<Role> {
    /**
     * Constructor.
     *
     * @param simpleArray  container for storing objects Role.
     */
    public RoleStore(SimpleArray<Role> simpleArray) {
        super(simpleArray);
    }
}
