package org.example.desine;

public class RangeSumQuery2DImmutable304 {

    class NumMatrix {

        final int[][] fieldSums;

        public NumMatrix(int[][] matrix) {
            fieldSums = new int[matrix.length + 1][matrix[0].length + 1];
            for (int row = 0; row < matrix.length; row++) {
                for (int col = 0; col < matrix[0].length; col++) {
                    fieldSums[row + 1][col + 1] = fieldSums[row][col + 1] + fieldSums[row + 1][col] - fieldSums[row][col]
                            + matrix[row][col];
                }
            }
        }

        public int sumRegion(int row1, int col1, int row2, int col2) {
            return fieldSums[row2 + 1][col2 + 1] - fieldSums[row1][col2 + 1] - fieldSums[row2 + 1][col1] + fieldSums[row1][col1];
        }
    }
}
