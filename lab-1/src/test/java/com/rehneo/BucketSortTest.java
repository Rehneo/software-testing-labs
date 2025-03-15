package com.rehneo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

public class BucketSortTest {
    @Test
    @DisplayName("Сортировка случайного массива из 1000000 элементов")
    void testSortLongArray() {
        int[] expected = new int[1000000];
        int[] actual = new int[1000000];
        Random random = new Random();
        for (int i = 0; i < 1000000; i++) {
            expected[i] = random.nextInt();
            actual[i] = expected[i];
        }
        Arrays.sort(expected);
        BucketSort.sort(actual);
        assertArrayEquals(expected, actual);
    }

    @Test
    @DisplayName("Сортировка пустого массива")
    void testSortEmptyArray() {
        int[] array = {};
        BucketSort.sort(array);
        assertArrayEquals(new int[]{}, array);
    }

    @Test
    @DisplayName("Сортировка массива из одного элемента")
    void testSortSingleElementArray() {
        int[] array = {5};
        BucketSort.sort(array);
        assertArrayEquals(new int[]{5}, array);
    }

    @Test
    @DisplayName("Сортировка массива из двух элементов")
    void testSortTwoElementArray() {
        int[] array = {8, 5};
        BucketSort.sort(array);
        assertArrayEquals(new int[]{5, 8}, array);
    }

    @Test
    @DisplayName("Сортировка инвертированного массива")
    void testSortInvertedArray() {
        int[] array = {10, 9, 8, 7, 6, 5, 4, 3, 2, 1};
        BucketSort.sort(array);
        assertArrayEquals(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}, array);
    }

    @Test
    @DisplayName("Сортировка массива из одинаковых элементов")
    void testSortIdenticalArray() {
        int[] array = {3, 3, 3, 3, 3};
        BucketSort.sort(array);
        assertArrayEquals(new int[]{3, 3, 3, 3, 3}, array);
    }

    @Test
    @DisplayName("Сортировка уже отсортированного массива")
    void testSortAlreadySortedArray() {
        int[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        BucketSort.sort(array);
        assertArrayEquals(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}, array);
    }

    @Test
    @DisplayName("Сортировка null массива")
    void testNullArray() {
        assertThrowsExactly(
                NullPointerException.class,
                () -> BucketSort.sort(null)
        );
    }
}
