package org.example;

public class MinimumArrayEnd3133 {
    class Solution {
        public long minEnd(int n, int x) {
            long muskToCreteResult = 0;
            int xMovedCurrent = x;
            n--;
            //form mask
            for (int i = 1; i < 63; i++) {
                long underMusk = xMovedCurrent & 0b0000000000000000000000000000000000000000000000000000000000000001l;
                xMovedCurrent >>= 1;
                if (underMusk == 0) {
                    if ((n & 0b0000000000000000000000000000000000000000000000000000000000000001l) != 0) {
                        muskToCreteResult |= 0b0100000000000000000000000000000000000000000000000000000000000000l;
                    }
                    n >>= 1;
                }
                muskToCreteResult >>= 1;
            }
            return muskToCreteResult | x;
        }
    }
}
