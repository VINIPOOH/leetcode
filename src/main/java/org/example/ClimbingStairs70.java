package org.example;

public class ClimbingStairs70 {

    //рекурсивное решение O(2^n) огромная экспоненциальная сложность
    int climbStairs(int n) {
        if (n <= 1) {
            return 1;
        }
        return climbStairs(n - 1) + climbStairs(n - 2);
    }

    //рекурсивное решение с запоминанием сложность н
    //_________________________________________________________
    int climbStairsRecursiveWithMemo(int n) {
        int[] memo = new int[n + 1];
        return dfs(n, memo);
    }

    int dfs(int n, int[] memo) {
        if (n <= 1) return 1;

        if (memo[n] != 0) return memo[n];

        memo[n] = dfs(n - 1, memo) + dfs(n - 2, memo);
        return memo[n];
    }
    //_________________________________________________


    //итеративное решение
    public int climbStairsIterative(int n) {
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

    //канонический ДП
    int climbStairsDP(int n) {
        int[] dp = new int[n + 1];

        dp[0] = 1;
        dp[1] = 1;

        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }

        return dp[n];
    }


}
