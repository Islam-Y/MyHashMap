import static org.junit.jupiter.api.Assertions.*;

import com.javarush.MyHashMap;
import org.junit.jupiter.api.Test;
import java.util.Set;
import java.util.Collection;

public class MyHashMapTest {

    @org.junit.Test
    public void testPutAndGet() {
        MyHashMap<Integer, String> map = new MyHashMap<>();
        map.put(1, "One");
        map.put(2, "Two");
        map.put(3, "Three");

        assertEquals("One", map.get(1));
        assertEquals("Two", map.get(2));
        assertEquals("Three", map.get(3));
        // Проверяем, что для отсутствующего ключа возвращается null
        assertNull(map.get(4));
    }

    @Test
    public void testRemove() {
        MyHashMap<Integer, String> map = new MyHashMap<>();
        map.put(1, "One");
        map.put(2, "Two");
        map.put(3, "Three");

        // Удаляем элемент и проверяем, что он корректно возвращается
        assertEquals("Two", map.remove(2));
        // После удаления элемент недоступен
        assertNull(map.get(2));
        // Проверяем, что размер уменьшился
        assertEquals(2, map.size());

        // Попытка удалить несуществующий ключ должна вернуть null
        assertNull(map.remove(100));
    }

    @Test
    public void testKeySet() {
        MyHashMap<String, Integer> map = new MyHashMap<>();
        map.put("A", 1);
        map.put("B", 2);
        map.put("C", 3);

        Set<String> keys = map.keySet();
        assertTrue(keys.contains("A"));
        assertTrue(keys.contains("B"));
        assertTrue(keys.contains("C"));
        assertEquals(3, keys.size());
    }

    @Test
    public void testValues() {
        MyHashMap<String, Integer> map = new MyHashMap<>();
        map.put("A", 1);
        map.put("B", 2);
        map.put("C", 3);

        Collection<Integer> values = map.values();
        assertTrue(values.contains(1));
        assertTrue(values.contains(2));
        assertTrue(values.contains(3));
        assertEquals(3, values.size());
    }

    @Test
    public void testLoopAddAndRemove() {
        MyHashMap<Integer, Integer> map = new MyHashMap<>();

        // Добавляем 1000 элементов
        for (int i = 0; i < 1000; i++) {
            map.put(i, i * 10);
        }
        assertEquals(1000, map.size());

        // Проверяем, что для каждого ключа возвращается корректное значение
        for (int i = 0; i < 1000; i++) {
            assertEquals(i * 10, map.get(i));
        }

        // Удаляем 1000 элементов
        for (int i = 0; i < 1000; i++) {
            assertEquals(i * 10, map.remove(i));
        }
        // После удаления таблица должна быть пуста
        assertEquals(0, map.size());
    }
}
