package org.example;

public class FindMinimumInRotatedSortedArray153 {

    public int findMin(int[] nums) {
        int left = 0;
        int right = nums.length - 1;

        while (left < right) {
            int mid = (left + right) / 2;

            if (nums[mid] > nums[right]) {
                // минимум точно справа
                left = mid + 1;
            } else {
                // минимум либо mid, либо левее
                right = mid;
            }
        }
        return nums[left];
    }
}
