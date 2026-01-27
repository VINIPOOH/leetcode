package org.example;

public class SetMatrixZeroes73 {
    public void setZeroes(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;

        boolean firstRowZero = false;
        boolean firstColZero = false;

        // Проверка первой строки
        for (int col = 0; col < cols; col++) {
            if (matrix[0][col] == 0) {
                firstRowZero = true;
                break;
            }
        }

        // Проверка первого столбца
        for (int row = 0; row < rows; row++) {
            if (matrix[row][0] == 0) {
                firstColZero = true;
                break;
            }
        }

        // Маркируем строки и столбцы
        for (int row = 1; row < rows; row++) {
            for (int col = 1; col < cols; col++) {
                if (matrix[row][col] == 0) {
                    matrix[row][0] = 0;
                    matrix[0][col] = 0;
                }
            }
        }

        // Обнуляем по маркерам
        for (int row = 1; row<rows; row++){
            if (matrix[row][0] == 0){
                for (int col = 1; col < cols; col++){
                    matrix[row][col] = 0;
                }
            }
        }

        for (int col = 1; col<cols; col++){
            if (matrix[0][col] == 0){
                for (int row = 1; row < rows; row++){
                    matrix[row][col] = 0;
                }
            }
        }

//        for (int row = 1; row < rows; row++) {
//            for (int col = 1; col < cols; col++) {
//                if (matrix[row][0] == 0 || matrix[0][col] == 0) {
//                    matrix[row][col] = 0;
//                }
//            }
//        }

        // Обнуляем первую строку
        if (firstRowZero) {
            for (int col = 0; col < cols; col++) {
                matrix[0][col] = 0;
            }
        }

        // Обнуляем первый столбец
        if (firstColZero) {
            for (int row = 0; row < rows; row++) {
                matrix[row][0] = 0;
            }
        }
    }
}
