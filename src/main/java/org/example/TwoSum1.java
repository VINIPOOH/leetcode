package org.example;

import java.util.HashMap;

public class TwoSum1 {
    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> numbersMap = new HashMap<>();
        //can skip array range check since it is guaranteed that answer exists
        for (int i = 0; ; i++) {
            int currentNum = nums[i];
            int secondNum = target - currentNum;
            if (numbersMap.containsKey(secondNum)) {
                return new int[] { i, numbersMap.get(secondNum) };
            }
            numbersMap.put(currentNum, i);
        }
    }
}
