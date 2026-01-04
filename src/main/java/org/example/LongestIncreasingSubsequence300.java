package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LongestIncreasingSubsequence300 {

    //optimized
    public int lengthOfLIS1(int[] nums) {
        List<Integer> tails = new ArrayList<>();
        for (int x : nums) {

            //бинарным поиском находим индекс для вставки
            int left = getIndexToInsert(x, tails);
            //если позиция в ставки за пределами добавляем новый
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

    //classic solution
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
}
