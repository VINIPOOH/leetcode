package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class PacificAtlanticWaterFlow417 {

    private static final int[][] DIRECTIONS = {
            { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 }
    };

    /*
Обход клеток острова для Pacific Atlantic Water Flow можно реализовать тремя способами:

1. DFS рекурсивно - (эта реализация)
   - Использует стек вызовов системы
   - Отмечаем visited при входе в функцию
   - Идём "вглубь", пока не упремся

2. DFS через явный стек
   - Ручной стек заменяет рекурсию
   - LIFO: последняя добавленная клетка обрабатывается первой
   - Идём вглубь, логика как у рекурсивного DFS

3. BFS через очередь
   - FIFO: первая добавленная клетка обрабатывается первой
   - Идём "вширь" по уровням
   - Здесь результат одинаков с DFS, так как нам важна только достижимость
*/
    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        int m = heights.length;
        int n = heights[0].length;

        boolean[][] pacific = new boolean[m][n];
        boolean[][] atlantic = new boolean[m][n];

        // Pacific для каждой крайней клетки запускаем поиск в ширину
        for (int i = 0; i < m; i++) {
            dfs(heights, pacific, i, 0);
        }
        for (int j = 0; j < n; j++) {
            dfs(heights, pacific, 0, j);
        }

        // Atlantic
        for (int i = 0; i < m; i++) {
            dfs(heights, atlantic, i, n - 1);
        }
        for (int j = 0; j < n; j++) {
            dfs(heights, atlantic, m - 1, j);
        }

        List<List<Integer>> result = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (pacific[i][j] && atlantic[i][j]) {
                    result.add(Arrays.asList(i, j));
                }
            }
        }

        return result;
    }

    private void dfs(int[][] heights, boolean[][] waterReachable, int row, int col) {
        waterReachable[row][col] = true;

        for (int[] d : DIRECTIONS) {
            int newRow = row + d[0];
            int newCol = col + d[1];

            if (newRow < 0 || newCol < 0 ||
                    newRow >= heights.length || newCol >= heights[0].length) {
                continue;
            }

            if (waterReachable[newRow][newCol]) {
                continue;
            }

            if (heights[newRow][newCol] >= heights[row][col]) {
                dfs(heights, waterReachable, newRow, newCol);
            }
        }
    }
}
