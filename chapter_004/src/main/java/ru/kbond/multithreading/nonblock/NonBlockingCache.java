package ru.kbond.multithreading.nonblock;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Non-blocking cache.
 *
 * @author kbondarenko
 * @version 1
 * @since 19.06.2018
 */
public class NonBlockingCache {
    private ConcurrentHashMap<Integer, Base> map = new ConcurrentHashMap<>();

    /**
     * The method adds the model to the collection.
     *
     * @param model model.
     * @return {@code true} if model is added.
     */
    public boolean add(Base model) {
        return this.map.putIfAbsent(model.getId(), model) == null;
    }

    /**
     * The method updates the model data.
     * Checks if the version of the file has been
     * changed by another thread, if so it will
     * throw an OptimisticException.
     *
     * @param key         key of the object to be updated.
     * @param updateModel data that needs to be changed in the model.
     * @return {@code true} if model is update.
     */
    public boolean update(int key, Base updateModel) {
        final boolean[] success = {false};
        map.computeIfPresent(key, (k, v) -> {
            if (v.getVersion() != updateModel.getVersion()) {
                throw new OptimisticException("Concurrent modification");
            }
            updateModel.updateVersion();
            success[0] = true;
            return updateModel;
        });
        return success[0];
    }

    /**
     * Removes the model by key.
     *
     * @param key key of the object to be removed.
     * @return {@code true} if model is deleted.
     */
    public boolean delete(int key) {
        final boolean[] success = {false};
        map.computeIfPresent(key, (k, v) -> {
            success[0] = true;
            return null;
        });
        return success[0];
    }

    /**
     * Returns the model by key.
     *
     * @param key the key of the received object.
     * @return model by key
     */
    public Base get(int key) {
        return map.get(key);
    }
}
