package ru.kbond.collections.generic.storage;

import ru.kbond.collections.generic.AbstractStore;
import ru.kbond.collections.generic.SimpleArray;
import ru.kbond.collections.generic.models.Role;

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
