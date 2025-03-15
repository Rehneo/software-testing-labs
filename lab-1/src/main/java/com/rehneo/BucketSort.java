package com.rehneo;

import java.util.ArrayList;
import java.util.List;

public class BucketSort {
    public static void sort(final int[] array) {
        final int N = array.length;
        if (N < 2) {
            return;
        }

        int maxElement = array[0];
        int minElement = array[0];
        for (int i = 1; i < N; i++) {
            if (array[i] > maxElement) {
                maxElement = array[i];
            }
            if (array[i] < minElement) {
                minElement = array[i];
            }
        }

        final List<Integer>[] buckets = new List[N];
        long bucketRange = (long) maxElement - minElement;

        for (int i = 0; i < N; i++) {
            buckets[i] = new ArrayList<>();
        }

        for (int num : array) {
            long range = (long) num - minElement;
            int index = (int) ((((double) range / bucketRange) * (N - 1)));
            buckets[index].add(num);
        }

        int i = 0;
        for (List<Integer> bucket : buckets) {
            insertionSort(bucket);
            for (int num : bucket) {
                array[i++] = num;
            }
        }
    }

    private static void insertionSort(List<Integer> bucket) {
        for (int i = 1; i < bucket.size(); i++) {
            int key = bucket.get(i);
            int j = i - 1;
            while (j >= 0 && bucket.get(j) > key) {
                bucket.set(j + 1, bucket.get(j));
                j--;
            }
            bucket.set(j + 1, key);
        }
    }
}
