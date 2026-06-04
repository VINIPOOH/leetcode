package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LongestIncreasingSubsequence300 {

    //бинарный поиск н лог н.
    public int lengthOfLIS1(int[] nums) {
        //Хранит минимальные значения для цепочки последовательности из К элементов. Каждый индекс это длина последовательности
        List<Integer> tails = new ArrayList<>();
        for (int x : nums) {

            //бинарным поиском находим индекс для вставки
            int left = getIndexToInsert(x, tails);
            //если позиция в ставки за пределами добавляем новый
            //нашли увеличение количества К.
            //хвост всегда или улучается, или удлиняется
            if (left == tails.size()) {
                tails.add(x);
            } else { // улучшаем (уменьшаем) хвост
                tails.set(left, x);
            }
        }
        return tails.size();
    }

    private static int getIndexToInsert(int x, List<Integer> tails) {
        int left = 0;
        int right = tails.size();
        // бинарный поиск первого элемента >= x
        while (left < right) {
            int mid = (left + right) / 2;
            if (tails.get(mid) < x) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }

    //написанное мной классическое, но с конца те же н квадрат
    public int lengthOfLIS(int[] nums) {
        //лучшая длина хвоста с заданного элемента
        int[] lengthes = new int[nums.length];
        for (int i = nums.length - 1; i >= 0; i--) {
            int currentMaxLenght = 0;
            //Ищем самый длинный хвост за элементом
            for (int j = i + 1; j < nums.length; j++) {
                if (lengthes[j] > currentMaxLenght && nums[j] > nums[i]) {
                    currentMaxLenght = lengthes[j];
                }
            }
            lengthes[i] = currentMaxLenght + 1;
        }
        return Arrays.stream(lengthes).max().getAsInt();
    }

    //classic solution
    //вычисляем максимумы последовательно сложность н квадрат.
    public int lengthOfLIClassic(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];

        // каждая сама по себе длина 1
        Arrays.fill(dp, 1);

        int result = 1;

        for (int i = 0; i < n; i++) {

            for (int j = 0; j < i; j++) {

                // можем продолжить последовательность
                if (nums[j] < nums[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }

            result = Math.max(result, dp[i]);
        }

        return result;
    }
}
