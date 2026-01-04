package org.example.utils;

public class MatrixUtils {

    //отражение относительно главной диагонали
    public static void transpose(int[][] matrix) {
        int length = matrix.length;

        for (int rov = 0; rov < length; rov++) {
            for (int col = rov + 1; col < length; col++) {
                int temp = matrix[rov][col];
                matrix[rov][col] = matrix[col][rov];
                matrix[col][rov] = temp;
            }
        }
    }

    //отражение по второстепенной диагонали
    public static void reflectSecondaryDiagonal(int[][] matrix) {
        int length = matrix.length;
        // Проходим только по верхней части относительно диагонали
        for (int row = 0; row < length; row++) {
            for (int col = 0; col < length - row; col++) {
                int temp = matrix[row][col];
                matrix[row][col] = matrix[length - 1 - col][length - 1 - row];
                matrix[length - 1 - col][length - 1 - row] = temp;
            }
        }
    }


    public static void flipHorizontally(int[][] matrix) {
        int length = matrix.length;

        for (int rov = 0; rov < length / 2; rov++) {
            int[] temp = matrix[rov];
            matrix[rov] = matrix[length - 1 - rov];
            matrix[length - 1 - rov] = temp;
        }
    }

    public static void flipVertically(int[][] matrix) {
        int length = matrix.length;

        for (int rov = 0; rov < length; rov++) {
            for (int col = 0; col < length / 2; col++) {
                int temp = matrix[rov][col];
                matrix[rov][col] = matrix[rov][length - 1 - col];
                matrix[rov][length - 1 - col] = temp;
            }
        }
    }

    public static void siftRowLeft(int[][] matrix, int rowIndex, int steps){
        int length = matrix[rowIndex].length;
        steps = steps % length;      // берем длину строки
        steps = length - steps;
        shiftRowRight(matrix, rowIndex, steps);
    }

    // Сдвиг строки rowIndex на steps шагов вправо
    public static void shiftRowRight(int[][] matrix, int rowIndex, int steps) {
        int length = matrix[rowIndex].length;
        steps = steps % length; // на случай steps > length
        if (steps == 0) return;

        // Разделяем на две части и разворачиваем каждую
        reverseRowPart(matrix, rowIndex, 0, length - steps - 1);
        reverseRowPart(matrix, rowIndex, length - steps, length - 1);
        // Разворачиваем всю строку
        reverseRowPart(matrix, rowIndex, 0, length - 1);
    }

    // Сдвиг столбца colIndex на steps шагов вниз
    public static void shiftColumnDown(int[][] matrix, int colIndex, int steps) {
        int length = matrix.length;
        steps = steps % length;
        if (steps == 0) return;

        // Разделяем на две части и разворачиваем каждую
        reverseColumnPart(matrix, colIndex, 0, length - steps - 1);
        reverseColumnPart(matrix, colIndex, length - steps, length - 1);
        // Разворачиваем весь столбец
        reverseColumnPart(matrix, colIndex, 0, length - 1);
    }

    private static void reverseColumnPart(int[][] matrix, int colIndex, int top, int bottom) {
        while (top < bottom) {
            int temp = matrix[top][colIndex];
            matrix[top][colIndex] = matrix[bottom][colIndex];
            matrix[bottom][colIndex] = temp;
            top++;
            bottom--;
        }
    }

    // Вспомогательные методы
    private static void reverseRowPart(int[][] matrix, int rowIndex, int left, int right) {
        while (left < right) {
            int temp = matrix[rowIndex][left];
            matrix[rowIndex][left] = matrix[rowIndex][right];
            matrix[rowIndex][right] = temp;
            left++;
            right--;
        }
    }

}
