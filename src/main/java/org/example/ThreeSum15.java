package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ThreeSum15 {
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums); // сортируем массив
        List<List<Integer>> res = new ArrayList<>();

        for (int i = 0; i < nums.length - 2; i++) {
            if (nums[i] > 0) break; // оптимизация: дальше тройки с суммой 0 невозможны
            if (i > 0 && nums[i] == nums[i - 1]) continue; // пропускаем дубликаты уже проверенного первого элемента

            // получаем пары, сумма которых равна -nums[i]
            List<List<Integer>> pairs = twoSum(nums, i + 1, - nums[i]);
            for (List<Integer> pair : pairs) {
                List<Integer> triplet = new ArrayList<>();
                triplet.add(nums[i]);
                triplet.addAll(pair);
                res.add(triplet);
            }
        }

        return res;
    }

    //на отсортированном такой ту сум Эфективнее
    private List<List<Integer>> twoSum(int[] nums, int startIndex, int target) {
        List<List<Integer>> res = new ArrayList<>();
        int left = startIndex;
        int right = nums.length - 1;

        while (left < right) {
            int sum = nums[left] + nums[right];
            if (sum == target) {
                res.add(Arrays.asList(nums[left], nums[right]));

                // пропускаем дубликаты
                int lv = nums[left], rv = nums[right];
                while (left < right && nums[left] == lv) left++;
                while (left < right && nums[right] == rv) right--;

            } else if (sum < target) {
                left++;
            } else {
                right--;
            }
        }
        return res;
    }
}
