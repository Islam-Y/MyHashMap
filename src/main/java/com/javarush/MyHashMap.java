package com.javarush;



import java.util.*;

/**
 * Простая реализация хеш-таблицы (HashMap) с использованием метода цепочек для разрешения коллизий.
 *
 * @param <K> тип ключа
 * @param <V> тип значения
 */
public class MyHashMap<K, V> {

    /**
         * Внутренний класс, представляющий запись (узел) в хеш-таблице.
         */
        private static class Entry<K, V> {
            K key;
            V value;
            MyHashMap.Entry<K, V> next;

            Entry(K key, V value, MyHashMap.Entry<K, V> next) {
                this.key = key;
                this.value = value;
                this.next = next;
            }
        }

        private MyHashMap.Entry<K, V>[] table;
        private int size;
        private static final int INITIAL_CAPACITY = 16;

        /**
         * Конструктор по умолчанию, инициализирующий хеш-таблицу с начальной емкостью.
         */
    public MyHashMap() {
            table = new MyHashMap.Entry[INITIAL_CAPACITY];
        }

        /**
         * Вычисляет хеш ключа, ограничивая его в пределах размера таблицы.
         * @param key ключ
         * @return индекс в массиве
         */
        private int hash(K key) {
//        int h;
//        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);

            return (key == null) ? 0 : Math.abs(key.hashCode() % table.length);
        }

        /**
         * Добавляет или обновляет элемент в хеш-таблице.
         * @param key ключ
         * @param value значение
         */
        public void put(K key, V value) {
            int index = hash(key);
            MyHashMap.Entry<K, V> newEntry = new MyHashMap.Entry<>(key, value, null);

            if (table[index] == null) {
            table[index] = newEntry;
        } else {
            Entry<K, V> current = table[index];
            while (true) {
                // Если ключ уже есть, обновляем значение
                if (current.key.equals(key)) {
                    current.value = value;
                    return;
                }
                // Если достигли конца цепочки, добавляем новый узел
                if (current.next == null) {
                    current.next = newEntry;
                    break;
                }
                current = current.next;
            }
        }
        size++;
    }

    /**
     * Возвращает значение, соответствующее переданному ключу.
     * @param key ключ
     * @return значение или null, если ключ не найден
     */
    public V get(K key) {
        int index = hash(key);
        Entry<K, V> current = table[index];
        while (current != null) {
            if (current.key.equals(key)) {
                return current.value;
            }
            current = current.next;
        }
        return null;
    }

    /**
     * Удаляет элемент по ключу.
     * @param key ключ
     * @return удаленное значение или null, если ключ не найден
     */
    public V remove(K key) {
        int index = hash(key);
        Entry<K, V> current = table[index];
        Entry<K, V> prev = null;

        while (current != null) {
            if ((current.key == null && key == null) ||
                    (current.key != null && current.key.equals(key))) {
                V removedValue = current.value;
                if (prev == null) {
                    table[index] = current.next;
                } else {
                    prev.next = current.next;
                }
                size--;
                return removedValue;
            }
            prev = current;
            current = current.next;
        }
        return null;
    }

    /**
     * Возвращает множество ключей, содержащихся в таблице.
     * @return множество ключей
     */
    public Set<K> keySet() {
        Set<K> keys = new HashSet<>();
        for (Entry<K, V> entry : table) {
            while (entry != null) {
                keys.add(entry.key);
                entry = entry.next;
            }
        }
        return keys;
    }

    /**
     * Возвращает коллекцию значений, содержащихся в таблице.
     * @return коллекция значений
     */
    public Collection<V> values() {
        List<V> values = new ArrayList<>();
        for (Entry<K, V> entry : table) {
            while (entry != null) {
                values.add(entry.value);
                entry = entry.next;
            }
        }
        return values;
    }

    /**
     * Возвращает количество элементов в хеш-таблице.
     * @return количество элементов
     */
    public int size() {
        return size;
    }
}

