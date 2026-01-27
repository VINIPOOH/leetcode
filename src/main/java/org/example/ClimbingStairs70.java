package org.example;

public class ClimbingStairs70 {
    public int climbStairs(int n) {
        if (n <= 2) {
            return n;
        }

        int prev = 1; // ways(1)
        int curr = 2; // ways(2)

        for (int i = 3; i <= n; i++) {
            int next = prev + curr;
            prev = curr;
            curr = next;
        }
        return curr;
    }
}
