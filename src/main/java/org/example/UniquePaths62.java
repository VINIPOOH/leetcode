package org.example;

public class UniquePaths62 {

    public int uniquePaths(int m, int n) {

        int[][] amountOfWays = new int[m][n];

        for (int i = 0; i < n; i++) {
            amountOfWays[0][i] = 1;
        }
        for (int i = 0; i < m; i++) {
            amountOfWays[i][0] = 1;
        }

        for (int startM = 1; startM < m; startM++) {
            for (int startN = 1; startN < n; startN++) {
                amountOfWays[startM][startN] = amountOfWays[startM - 1][startN] + amountOfWays[startM][startN - 1];
            }
        }
        return amountOfWays[m - 1][n - 1];
    }
}
