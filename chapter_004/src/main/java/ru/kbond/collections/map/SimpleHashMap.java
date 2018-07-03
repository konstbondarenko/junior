package ru.kbond.collections.map;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * A collection of type Map based on a hash table.
 * Contains key value pairs. Does not store the null keys.
 * An iterator is supported.
 *
 *
 * @author kbondarenko
 * @since 11.03.2018
 * @version 1
 */
public class SimpleHashMap<K, V> implements Iterable<K> {
    /**
     * Default initial capacity.
     */
    static final int DEFAULT_INITIAL_CAPACITY = 16;
    /**
     * The element of map.
     */
    private static class Node<K, V> {
        K key;
        V value;
        /**
         * Constructor.
         *
         * @param key  the key.
         * @param value  value to insert.
         */
        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
        /**
         * Getter.
         */
        public K getKey() {
            return key;
        }
        /**
         * Getter.
         */
        public V getValue() {
            return value;
        }
    }
    /**
     * The array buffer into which the elements of the SimpleHashMap are stored.
     * The capacity of the SimpleHashMap is the length of this array buffer.
     *
     * @param table  array objects.
     */
    private Object[] table;
    /**
     * Constructor an empty container with an initial capacity of sixteen.
     */
    public SimpleHashMap() {
        this.table = new Object[DEFAULT_INITIAL_CAPACITY];
    }
    /**
     * Constructor an empty container with the specified initial capacity.
     *
     * @param initialCapacity  the initial capacity of the container.
     */
    public SimpleHashMap(int initialCapacity) {
        this.table = new Object[initialCapacity];
    }
    /**
     * The size of the HashContainer (the number of elements it contains).
     */
    private int size;
    /**
     * Returns the number of elements in this container.
     *
     * @return the number of elements in this container.
     */
    public int size() {
        return size;
    }
    /**
     * The number of times this container has been <i>structurally modified</i>.
     * Structural modifications are those that change the size of the
     * container, or otherwise perturb it in such a fashion that iterations in
     * progress may yield incorrect results.
     *
     * <p>This field is used by the iterator implementation
     * returned by the {@code iterator} methods. If the value of
     * this field changes unexpectedly, the iterator
     * will throw a {@code ConcurrentModificationException} in
     * response to the {@code next}. This provides <i>fail-fast</i>
     * behavior, rather than non-deterministic behavior in
     * the face of concurrent modification during iteration.
     *
     * @param modCount  number of collection changes.
     */
    private int modCount = 0;
    /**
     * Increases container capacity when filling.
     */
    private void increaseCapacity() {
        int oldCapacity = this.table.length;
        int newCapacity = oldCapacity * 2;
        reHashing(newCapacity);
    }
    /**
     * Redistributes old items due to the change in the size of the base array.
     *
     * @param newCapacity  the size of the new array.
     */
    private void reHashing(int newCapacity) {
        Object[] newValue = new Object[newCapacity];
        Node<K, V> tmp;
        for (int i = 0; i < table.length; i++) {
            if (table[i] != null) {
                tmp = (Node<K, V>) table[i];
                int h = hash(tmp.getKey().hashCode());
                newValue[position(h, newValue.length)] = table[i];
            }
        }
        table = newValue;
    }
    /**
     * Appends the specified element to this container.
     * Important! Does not accept null.
     *
     * @param key  key with which the specified value is to be associated.
     * @param value  value to be associated with the specified key.
     * @return <tt>true</tt> if the key is not contained in the map.
     */
    public boolean insert(K key, V value) {
        boolean checkInsert = false;
        Node<K, V> newNode = new Node<>(key, value);
        if (key == null) {
            throw new NullPointerException("The object must not be null");
        }
        if (!contains(key)) {
            if (size == table.length) {
                increaseCapacity();
            }
            int h = hash(key.hashCode());
            table[position(h, table.length)] = newNode;
            this.size++;
            this.modCount++;
            checkInsert = true;
        }
        return checkInsert;
    }
    /**
     * Returns the value to which the specified key is mapped,
     * or {@code null} if this map contains no mapping for the key.
     *
     * @param key  specified key is mapped.
     * @return  the value to which the specified key is mapped.
     */
    public V get(K key) {
        Node<K, V> tmp;
        V v = null;
        if (contains(key)) {
            int h = hash(key.hashCode());
            tmp = (Node<K, V>) table[position(h, table.length)];
            v = tmp.getValue();
        }
        return v;
    }
    /**
     * Delete the mapping for the specified key from this map if present..
     *
     * @param key  key to be deleted.
     */
    public boolean delete(K key) {
        boolean checkDelete = false;
        if (contains(key)) {
            int h = hash(key.hashCode());
            table[position(h, table.length)] = null;
            checkDelete = true;
            this.size--;
            this.modCount++;
        }
        return checkDelete;
    }
    /**
     * Returns <tt>true</tt> if this map contains the specified key.
     *
     * @param key  element whose presence in this map is to be tested.
     * @return <tt>true</tt> if this map contains the specified key.
     */
    public boolean contains(K key) {
        boolean checkContains = false;
        Node<K, V> tmp;
        if (key != null) {
            int h = hash(key.hashCode());
            if (table[position(h, table.length)] != null) {
                tmp = (Node<K, V>) table[position(h, table.length)];
                if (tmp.getKey().equals(key)) {
                    checkContains = true;
                }
            }
        }
        return checkContains;
    }
    /**
     * Method calculation hash code for element.
     *
     * @param h  hash code element.
     * @return  number to calculate the index
     *         of an element in an array.
     */
    private int hash(int h) {
        h ^= (h >>> 20) ^ (h >>> 12);
        return h ^ (h >>> 7) ^ (h >>> 4);
    }
    /**
     * Determine the position in the array for the element.
     *
     * @param hash  hash element.
     * @param length  length array.
     */
    private int position(int hash, int length) {
        return Math.abs(hash % length);
    }
    /**
     * Returns an iterator over the elements in this container in proper sequence.
     *
     * @return an iterator over the elements in this container in proper sequence.
     */
    @Override
    public Iterator<K> iterator() {
        return new SimpleHashMapIterator<K>(table);
    }

    /**
     * Iterator.
     */
    private class SimpleHashMapIterator<K> implements Iterator<K> {
        /**
         * The field storing array.
         *
         * @param objects  array objects.
         */
        private final Object[] objects;
        /**
         * The field element index.
         *
         * @param index  element index.
         */
        private int index = 0;
        /**
         * The modCount value that the iterator believes that the backing
         * container should have. If this expectation is violated, the iterator
         * has detected concurrent modification.
         */
        private int expectedModCount = modCount;
        /**
         * Constructor.
         *
         * @param objects array.
         */
        public SimpleHashMapIterator(final Object[] objects) {
            this.objects = objects;
        }
        /**
         * Method returns {@code true} if the iteration has more elements.
         *
         * @return {@code true} if the iteration has more elements.
         */
        @Override
        public boolean hasNext() {
            return index < objects.length;
        }
        /**
         * Method returns the next element in the iteration.
         *
         * @return the next element in the iteration.
         * @throws NoSuchElementException if the iteration has no more elements.
         * @throws ConcurrentModificationException this exception will be thrown
         *         out if the container undergoes a change after the iterator is created
         */
        @Override
        public K next() {
            checkForComodification();
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Node<K, V> tmp = (Node<K, V>) objects[index++];
            return tmp.getKey();
        }
        /**
         * The method checks whether the collection was changed during iteration.
         */
        final void checkForComodification() {
            if (modCount != expectedModCount) {
                throw new ConcurrentModificationException();
            }
        }
    }
}
