package org.example;

import java.util.HashSet;

public class LongestConsecutiveSequence128 {
    public int longestConsecutive(int[] nums) {

        int length = nums.length;
        if (nums.length == 0) {
            return 0;
        }
        HashSet<Integer> hashSet = new HashSet<>(length);
        for (int num : nums) {
            hashSet.add(num);
        }
        int maxSequence = 1;
        int currentSequence = 1;
        for (int i = 0; i < length; i++) {
            int num = nums[i];
            int pivot = num - 1;
            while (hashSet.contains(pivot)) {
                currentSequence++;
                hashSet.remove(pivot);
                pivot--;
            }
            pivot = num + 1;
            while (hashSet.contains(pivot)) {
                currentSequence++;
                hashSet.remove(pivot);
                pivot++;
            }
            if (currentSequence > maxSequence) {
                maxSequence = currentSequence;
            }
            currentSequence = 1;
        }
        return maxSequence;
    }
}
