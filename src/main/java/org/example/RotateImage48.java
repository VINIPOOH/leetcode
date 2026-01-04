package org.example;

import static org.example.utils.MatrixUtils.flipHorizontally;
import static org.example.utils.MatrixUtils.flipVertically;
import static org.example.utils.MatrixUtils.reflectSecondaryDiagonal;
import static org.example.utils.MatrixUtils.transpose;

//https://leetcode.com/problems/rotate-image/?envType=problem-list-v2&envId=oizxjoit
public class RotateImage48 {
    //Layer-by-layer cyclic rotation (4-cycle in-place rotation) поворот на 90 граусов по часовой стрелке
    // для поворота в другую сторону формула будет newRow = n - 1 - col, newCol = row верно?
    public void rotate90(int[][] matrix) {
        for (int col = 0; col < matrix.length / 2; col++) {
            int rov = col;
            // нужно отнять 1 потому что последний элемент строки повернут в процессе вращения первого
            int indexToStop = matrix.length - 1;
            for (; rov < (indexToStop - col); rov++) {
                int currentRov = rov;
                int currentCol = col;
                int insertCol = matrix.length - 1 - currentRov;
                int toIncert = matrix[currentRov][currentCol];
                int toSave;
                for (int i = 0; i < 4; i++) {
                    int insertRov = currentCol;
                    toSave = matrix[insertRov][insertCol];
                    matrix[insertRov][insertCol] = toIncert;
                    toIncert = toSave;
                    currentRov = insertRov;
                    currentCol = insertCol;
                    insertCol = matrix.length - 1 - currentRov;
                }
            }
        }
    }

    //поворот на 180
    public void rotate180(int[][] matrix) {
        int n = matrix.length;
        // проходим верхнюю половину строк
        for (int rov = 0; rov < n / 2; rov++) {
            for (int col = 0; col < n; col++) {
                int oppositeRov = n - 1 - rov;
                int oppositeCol = n - 1 - col;

                int temp = matrix[rov][col];
                matrix[rov][col] = matrix[oppositeRov][oppositeCol];
                matrix[oppositeRov][oppositeCol] = temp;
            }
        }
        // если размер нечётный — обрабатываем центральную строку
        if (n % 2 == 1) {
            int middleRov = n / 2;
            for (int col = 0; col < n / 2; col++) {
                int oppositeCol = n - 1 - col;

                int temp = matrix[middleRov][col];
                matrix[middleRov][col] = matrix[middleRov][oppositeCol];
                matrix[middleRov][oppositeCol] = temp;
            }
        }
    }

    //через транспонирование и отражение
    public void rotate90_2(int[][] matrix) {
        transpose(matrix);
        flipHorizontally(matrix);
    }

    //через транспонирование и отражение
    public void rotate270_2(int[][] matrix) {
        reflectSecondaryDiagonal(matrix);
        flipHorizontally(matrix);
    }

    //поворот на 180 градусов через двойное отражение
    public void rotate180_2(int[][] matrix) {
        flipHorizontally(matrix);
        flipVertically(matrix);
    }
}
