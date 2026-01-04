package org.example;

import java.util.Arrays;
import java.util.Comparator;

public class NonOverlappingIntervals435 {
    public int eraseOverlapIntervals(int[][] intervals) {
        if (intervals == null || intervals.length == 0) {
            return 0;
        }
        // 1. Сортировка по ключу
        Arrays.sort(intervals, Comparator.comparingInt(interval -> interval[1]));
        int removedCount = 0;
        int lastIntervalEnd = Integer.MIN_VALUE;

        for (int[] currentInterval : intervals) {
            int currentStart = currentInterval[0];
            int currentEnd = currentInterval[1];
            // если пересечение есть — удаляем текущий интервал
            if (currentStart < lastIntervalEnd) {
                removedCount++;
            } else {
                // обновляем конец последнего оставленного интервала
                lastIntervalEnd = currentEnd;
            }
        }
        return removedCount;
    }
}
