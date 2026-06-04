package org.example;

public class HouseRobber198 {

    //аналогично дп с оптимизацией по памяти без массива Space-optimized DP
    public int rob(int[] nums) {
        if (nums.length == 1){
            return nums[0];
        }

        //хранит сколько максимально можно иметь к предыдущему элементу
        int prevSum = nums[0];//максимальная сума к текущему элементу
        int currentSum = Math.max(nums[0], nums[1]);

        for (int i = 2; i < nums.length; i++) {
            int newCurrentSum = Math.max(currentSum, prevSum + nums[i]);
            prevSum = currentSum;
            currentSum = newCurrentSum;
        }
        return Math.max(prevSum, currentSum);
    }


    public int robDP(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }

        int[] dp = new int[nums.length];

        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);

        for (int i = 2; i < nums.length; i++) {
            dp[i] = Math.max(dp[i - 1], nums[i] + dp[i - 2]);
        }

        return dp[nums.length - 1];
    }

}
