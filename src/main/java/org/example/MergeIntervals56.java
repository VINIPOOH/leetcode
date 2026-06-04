package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class MergeIntervals56 {

    public int[][] merge(int[][] intervals) {
        //        Merge Intervals требует сортировки по start, потому что мы расширяем текущий интервал слева направо.
        //Сортировка по end может нарушить порядок слияния: новые интервалы, начинающиеся раньше, чем предыдущий интервал в отсортированном массиве, будут проверены слишком поздно → merge будет неправильным.
        Arrays.sort(intervals, Comparator.comparingInt(value -> value[0]));

        ArrayList<int[]> merged = new ArrayList<>();
        for (int i = 0; i < intervals.length - 1; i++) {
            if (intervals[i][1] < intervals[i + 1][0]) {
                merged.addLast(intervals[i]);
            } else {
                intervals[i + 1][0] = intervals[i][0];
                intervals[i + 1][1] = Math.max(intervals[i][1], intervals[i + 1][1]);
            }
        }
        merged.addLast(intervals[intervals.length - 1]);

        return merged.toArray(new int[merged.size()][]);
    }
}
