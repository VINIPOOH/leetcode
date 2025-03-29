package org.example;

import java.util.HashMap;

public class TwoSum1 {
    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> numersMap = new HashMap<>();
        //can skip array range chech since it is gurantied that answer exists
        for (int i = 0; ; i++) {
            int currentNum = nums[i];
            int secondNum = target - currentNum;
            if (numersMap.containsKey(secondNum)) {
                return new int[] { i, numersMap.get(secondNum) };
            }
            numersMap.put(currentNum, i);
        }
    }
}
