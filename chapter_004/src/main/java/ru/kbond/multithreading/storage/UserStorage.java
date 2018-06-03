package ru.kbond.multithreading.storage;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;
import java.util.HashMap;
import java.util.Map;

/**
 * Class user storage - stores users.
 *
 * @author kbondarenko
 * @since 03.06.2018
 * @version 1
 */
@ThreadSafe
public class UserStorage {
    @GuardedBy("this")
    private final Map<Integer, User> storage = new HashMap<>();
    /**
     * Adds users to storage.
     *
     * @param user  user.
     * @return  {@code true} if item is added.
     */
    public synchronized boolean add(User user) {
        return this.storage.putIfAbsent(user.getId(), user) == null;
    }
    /**
     * Replaces an existing user, the replacement is made by id.
     *
     * @param user  new user.
     * @return  {@code true} if item is updated.
     */
    public synchronized boolean update(User user) {
        boolean success = false;
        if (this.storage.containsKey(user.getId())) {
            this.storage.put(user.getId(), user);
            success = true;
        }
        return success;
    }
    /**
     * Deletes the user, the deletion is done by id.
     *
     * @param user  deleted user.
     * @return  {@code true} if item is delete.
     */
    public synchronized boolean delete(User user) {
        boolean success = false;
        if (this.storage.containsKey(user.getId())) {
            this.storage.remove(user.getId());
            success = true;
        }
        return success;
    }
    /**
     * The method transfers the amount between users by id.
     *
     * @param fromId  sender.
     * @param toId  destination.
     * @param amount  transferable amount.
     * @return  {@code true} if the translation is completed.
     */
    public synchronized boolean transfer(int fromId, int toId, int amount) {
        boolean success = false;
        if (checkTransfer(fromId, toId, amount)) {
            this.storage.get(fromId).setAmount(this.storage.get(fromId).getAmount() - amount);
            this.storage.get(toId).setAmount(this.storage.get(toId).getAmount() + amount);
            success = true;
        }
        return success;
    }
    /**
     * Returns user by id.
     *
     * @param id  id of the desired user.
     * @return  {@code true} if the user is in storage,
     *          otherwise - null.
     */
    public synchronized User getUser(int id) {
        boolean success = false;
        if (this.storage.containsKey(id)) {
            success = true;
        }
        return success ? this.storage.get(id) : null;
    }
    /**
     * Method to check if transfer is possible.
     *
     * @param fromId  sender.
     * @param toId  destination.
     * @param amount  transferable amount.
     * @return  {@code true} if transfer is possible.
     */
    private synchronized boolean checkTransfer(int fromId, int toId, int amount) {
        boolean success = false;
        if (this.storage.containsKey(fromId)
                && this.storage.containsKey(toId)) {
            if (this.storage.get(fromId).getAmount() > 0
                    && amount <= this.storage.get(fromId).getAmount()) {
                success = true;
            }
        }
        return success;
    }
}
