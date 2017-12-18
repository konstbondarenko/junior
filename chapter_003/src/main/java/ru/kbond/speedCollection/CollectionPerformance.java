package ru.kbond.speedCollection;

import java.util.*;

/**
 * Class CollectionPerformance - calculating the speed of adding and removing items from collections.
 * @author kbondarenko
 * @since 18.12.2017
 * @version 1
 */
public class CollectionPerformance {
    /**
     * The method calculation of time to add items to the collection.
     * @param collection - accepted collection.
     * @param amount - number of elements.
     * @return - time of operation.
     */
    public long add(Collection<String> collection, int amount) {
        long start, end;

        start = System.currentTimeMillis();
        for (int i = 0; i < amount; i++) {
            collection.add(String.valueOf(i));
        }
        end = System.currentTimeMillis();
        return end - start;
    }
    /**
     * The method calculation of time to delete items in the collection.
     * @param collection - accepted collection.
     * @param amount - number of elements.
     * @return - time of operation.
     */
    public  long delete(Collection<String> collection, int amount) {
        long start, end;
        for (int i = 0; i < amount; i++) {
            collection.add(String.valueOf(i));
        }
        start = System.currentTimeMillis();
        for (int i = 0; i < amount / 2; i++) {
            collection.remove(String.valueOf(i));
        }
        end = System.currentTimeMillis();
        return end - start;
    }
}
