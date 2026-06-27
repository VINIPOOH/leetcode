package org.example;

public class SearchInRotatedSortedArray33 {

    public int search(int[] nums, int target) {
        int left = 0, right = nums.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] == target) return mid;

            // Левая половина отсортирована
            if (nums[left] <= nums[mid]) {
                if (target >= nums[left] && target < nums[mid]) {// есть ли в отсортированной половине
                    right = mid - 1; // таргет слева
                } else {
                    left = mid + 1;  // таргет справа
                }
            }
            // Правая половина отсортирована
            else {
                if (target > nums[mid] && target <= nums[right]) {// есть ли в отсортированной половине
                    left = mid + 1;  // таргет справа
                } else {
                    right = mid - 1; // таргет слева
                }
            }
        }
        return -1; // если не найден
    }
}
