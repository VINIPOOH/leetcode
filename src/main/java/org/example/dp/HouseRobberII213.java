package org.example.dp;

public class HouseRobberII213 {

    public int rob(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }
        if (nums.length == 2) {
            return Math.max(nums[0], nums[1]);
        }
        return Math.max(getRobersonNotCircleStreetWithOutArray(nums, 0, nums.length - 1), getRobersonNotCircleStreetWithOutArray(nums, 1, nums.length));
    }

    private static int getRobersonNotCircleStreet(int[] nums, int start, int finis) {
        int[] dp = new int[finis];
        dp[start] = nums[start];
        dp[start + 1] = Math.max(nums[start], nums[start + 1]);
        for (int i = 2; i < finis; i++) {
            int withNewHouseSum = nums[i] + dp[i - 2];
            dp[i] = Math.max(withNewHouseSum, dp[i - 1]);
        }
        return dp[dp.length - 1];
    }

    //dp но без масива. ибо нам достаточно предыдущего и пред предыдущего
    private static int getRobersonNotCircleStreetWithOutArray(int[] nums, int start, int finis) {
        int prev2 = 0; // dp[i-2]
        int prev1 = 0; // dp[i-1]

        for (int i = start; i < finis; i++) {
            int take = nums[i] + prev2;
            int skip = prev1;
            int curr = Math.max(take, skip);

            prev2 = prev1;
            prev1 = curr;
        }

        return prev1;
    }
}
