package ru.kbond.generic.storage;

import ru.kbond.generic.AbstractStore;
import ru.kbond.generic.Base;
import ru.kbond.generic.SimpleArray;

/**
 * Class container for storing objects Role.
 *
 * @author kbondarenko
 * @since 20.02.2018
 * @version 1
 */
public class RoleStore<T extends Base> extends AbstractStore<T> {
    /**
     * Constructor.
     *
     * @param simpleArray  container for storing objects Role.
     */
    public RoleStore(SimpleArray<T> simpleArray) {
        super(simpleArray);
    }
}
