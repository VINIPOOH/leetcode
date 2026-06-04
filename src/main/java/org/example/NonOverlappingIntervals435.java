package org.example;

import java.util.Arrays;
import java.util.Comparator;

public class NonOverlappingIntervals435 {
    public int eraseOverlapIntervals(int[][] intervals) {
        if (intervals == null || intervals.length == 0) {
            return 0;
        }
        // 2️⃣ Почему сортируем по end
        //Если отсортировать по концу, мы всегда берём первый интервал, который заканчивается раньше, и он оставляет максимум пространства для следующих интервалов.
        //Идея жадного алгоритма:
        //Сортируем по end.
        //Берём первый интервал.
        //Следующий интервал:
        //Если start < lastEnd → пересечение → удаляем (увеличиваем removedCount).
        //Иначе → нет пересечения → обновляем lastEnd = currentEnd.
        //Почему это минимизирует удаление?
        //Чем раньше заканчивается интервал, тем больше шанс вписать следующие интервалы без пересечения.
        //Если сортировать по start, нужно проверять все возможные варианты — не будет гарантии минимального удаления.
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


    //сложность Н квадрат
    public int eraseOverlapIntervalsDP(int[][] intervals) {
        if (intervals.length == 0) return 0;

        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0])); // сортировка по start
        int n = intervals.length;
        int[] dp = new int[n];//количество не пересечений до интервала и.
        Arrays.fill(dp, 1); // каждый интервал сам по себе

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (intervals[j][1] <= intervals[i][0]) { // не пересекается
                    dp[i] = Math.max(dp[i], dp[j] + 1);//количество не пересечений равно либо текущему значению, либо максимальному количеству не пересечений
                    // элемента перед +1
                }
            }
        }

        int maxNonOverlap = 0;
        for (int x : dp) maxNonOverlap = Math.max(maxNonOverlap, x);

        return n - maxNonOverlap; // минимальное количество удалений
    }
}
