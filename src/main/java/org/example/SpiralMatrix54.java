package org.example;

import java.util.ArrayList;
import java.util.List;

public class SpiralMatrix54 {

    public List<Integer> spiralOrder(int[][] matrix) {
        int rovTop = 0;
        int rovBotom = matrix.length - 1;
        int colLeft = 0;
        int colRight = matrix[0].length - 1;
        ArrayList<Integer> toReturn = new ArrayList<>();

        while (rovTop <= rovBotom && colLeft <= colRight) {
            for (int col = colLeft; col <= colRight; col++) {
                toReturn.add(matrix[rovTop][col]);
            }
            rovTop++;
            for (int rov = rovTop; rov <= rovBotom; rov++) {
                toReturn.add(matrix[rov][colRight]);
            }
            colRight--;

            if (rovTop <= rovBotom) {
                for (int col = colRight; col >= colLeft; col--) {
                    toReturn.add(matrix[rovBotom][col]);
                }
                rovBotom--;
            }

            if (colLeft <= colRight)  {
                for (int rov = rovBotom; rov >= rovTop; rov--) {
                    toReturn.add(matrix[rov][colLeft]);
                }
                colLeft++;
            }
        }
        return toReturn;
    }

    //теоретически позволяет удобно делать более сложные структуры поворотов по матрице
    public List<Integer> spiralOrderOneCycle(int[][] matrix) {
        List<Integer> spiral = new ArrayList<>();
        if (matrix.length == 0) return spiral;

        int rows = matrix.length;
        int cols = matrix[0].length;

        // Границы слоя
        int topBoundary = 0;
        int bottomBoundary = rows - 1;
        int leftBoundary = 0;
        int rightBoundary = cols - 1;

        // Текущая позиция
        int row = 0;
        int col = 0;

        // Направление движения: 0=вправо, 1=вниз, 2=влево, 3=вверх
        int direction = 0;

        while (spiral.size() < rows * cols) {
            spiral.add(matrix[row][col]);

            switch (direction) {
                case 0: // идём вправо
                    if (col == rightBoundary) {
                        direction = 1;  // поворот вниз
                        topBoundary++;   // сдвигаем верхнюю границу
                        row++;           // следующий элемент вниз
                    } else {
                        col++;
                    }
                    break;
                case 1: // идём вниз
                    if (row == bottomBoundary) {
                        direction = 2;  // поворот влево
                        rightBoundary--; // сдвигаем правую границу
                        col--;           // следующий элемент влево
                    } else {
                        row++;
                    }
                    break;
                case 2: // идём влево
                    if (col == leftBoundary) {
                        direction = 3;  // поворот вверх
                        bottomBoundary--; // сдвигаем нижнюю границу
                        row--;            // следующий элемент вверх
                    } else {
                        col--;
                    }
                    break;
                case 3: // идём вверх
                    if (row == topBoundary) {
                        direction = 0;  // поворот вправо
                        leftBoundary++;  // сдвигаем левую границу
                        col++;           // следующий элемент вправо
                    } else {
                        row--;
                    }
                    break;
            }
        }

        return spiral;
    }
}
