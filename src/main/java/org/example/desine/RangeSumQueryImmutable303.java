package org.example.desine;

public class RangeSumQueryImmutable303 {


    class NumArray {

        private final int[] prefix;

        public NumArray(int[] nums) {
            prefix = new int[nums.length + 1];//на один дольше что бы не делать иф в сум ренж. Если нужно вернуть массив включая первый до райта.

            for (int i = 0; i < nums.length; i++) {
                prefix[i + 1] = prefix[i] + nums[i];
            }
        }

        public int sumRange(int left, int right) {
            return prefix[right + 1] - prefix[left];
        }
    }
}
