package org.example;

public class HouseRobber198 {
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
}
