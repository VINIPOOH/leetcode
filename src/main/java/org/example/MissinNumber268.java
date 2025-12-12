package org.example;

public class MissinNumber268 {
    public static void main(String[] args) {
        new MissinNumber268().missingNumber(new int[]{9,6,4,2,3,5,7,0,1});
    }

    public int missingNumber(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            int curentNode = nums[i];
            if (nums[i] == nums.length) {
                nums[i] = -1;
            } else if (nums[i] != i) {
                int pivot = nums[curentNode];
                nums[i] = -1;
                nums[curentNode] = curentNode;
                while (pivot != nums.length && pivot != -1 && pivot != nums[pivot]) {
                    curentNode = pivot;
                    pivot = nums[curentNode];
                    nums[curentNode] = curentNode;
                }
            }
        }
        for (int i = 0; i < nums.length; i++) {
            System.out.println(nums[i]);
        }
        for (int i = 0; i < nums.length; i++) {

            if (nums[i] != i) {
                return i;
            }
        }
        return nums.length;
    }
}
