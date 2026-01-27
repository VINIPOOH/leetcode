package org.example;

import java.util.ArrayDeque;
import java.util.Queue;

public class NumberOfIslands200 {

    public int numIslands(char[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int islands = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '1') {
                    islands++;
                    dfs(grid, i, j);
                }
            }
        }
        return islands;
    }

    private void dfs(char[][] grid, int i, int j) {
        // границы и вода
        if (i < 0 || j < 0 || i >= grid.length || j >= grid[0].length) {
            return;
        }
        if (grid[i][j] != '1') {
            return;
        }

        // помечаем как посещённую
        grid[i][j] = '0';

        // расширяемся
        dfs(grid, i + 1, j);
        dfs(grid, i - 1, j);
        dfs(grid, i, j + 1);
        dfs(grid, i, j - 1);
    }

    public int numIslandsBFS(char[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;

        int islandsCount = 0;

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {

                // Если нашли землю — это новый остров
                if (grid[row][col] == '1') {
                    islandsCount++;
                    floodFillBfs(grid, row, col);
                }
            }
        }

        return islandsCount;
    }

    private void floodFillBfs(char[][] grid, int startRow, int startCol) {
        int rows = grid.length;
        int cols = grid[0].length;

        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(new int[] { startRow, startCol });

        // Помечаем стартовую клетку как посещённую
        grid[startRow][startCol] = '0';

        // Направления: вверх, вниз, влево, вправо
        int[][] directions = {
                { 1, 0 },
                { -1, 0 },
                { 0, 1 },
                { 0, -1 }
        };

        while (!queue.isEmpty()) {
            int[] cell = queue.poll();
            int currentRow = cell[0];
            int currentCol = cell[1];

            for (int[] direction : directions) {
                int newRow = currentRow + direction[0];
                int newCol = currentCol + direction[1];

                // Проверяем границы и что это ещё земля
                if (newRow >= 0 && newRow < rows &&
                        newCol >= 0 && newCol < cols &&
                        grid[newRow][newCol] == '1') {

                    // Помечаем как посещённую
                    grid[newRow][newCol] = '0';
                    queue.add(new int[] { newRow, newCol });
                }
            }
        }
    }

    //Избыточно для данной задачи, нужно для понимания самого метода
    public int numIslandsUnion(char[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }

        int rows = grid.length;
        int cols = grid[0].length;
        UnionFind unionFind = new UnionFind(rows * cols, cols);

        // Инициализируем все "1" как отдельные острова
        for (int rov = 0; rov < rows; rov++) {
            for (int col = 0; col < cols; col++) {
                if (grid[rov][col] == '1') {
                    int id = unionFind.countId(rov, col);
                    unionFind.setParent(id);
                }
            }
        }

        // Проходим по всем клеткам и объединяем соседние "1"
        int[][] directions = { { -1, 0 }, { 0, -1 } }; // проверяем только верх и левый
        for (int rov = 0; rov < rows; rov++) {
            for (int colum = 0; colum < cols; colum++) {
                if (grid[rov][colum] == '1') {
                    int id = unionFind.countId(rov, colum);
                    for (int[] dir : directions) {
                        int nextRove = rov + dir[0];
                        int nextColum = colum + dir[1];
                        if (nextRove >= 0 && nextColum >= 0 && grid[nextRove][nextColum] == '1') {
                            int neighborId = unionFind.countId(nextRove, nextColum);
                            unionFind.union(id, neighborId);
                        }
                    }
                }
            }
        }

        return unionFind.getCount();
    }

    // Класс Union-Find
    // Класс Union-Find с ранком
    class UnionFind {
        private int[] parent;
        //Сдесь ета оптимизация с ранк абсолютно бессмысленна
        //ее использовать есть смысл исключительно когда в операции поиска корня
        //мы не можем использовать сворачивание пути. иначе это не дает никакого выиграша
        //ибо все деревья практически плоские или плоские
        private int[] rank; // добавляем для балансировки деревьев
        private int count;
        private int numCols;

        public UnionFind(int size, int numCols) {
            this.numCols = numCols;
            parent = new int[size];
            rank = new int[size]; // инициализация ранка
            count = 0;
            for (int i = 0; i < size; i++) {
                parent[i] = -1; // -1 означает, что здесь вода или ещё не инициализировано
                rank[i] = 0;    // начальный ранг = 0
            }
        }

        public int countId(int rov, int col) {
            return rov * numCols + col;
        }

        // Создать новый остров
        public void setParent(int id) {
            if (parent[id] == -1) {
                parent[id] = id;
                rank[id] = 0; // ранг корня = 0
                count++;
            }
        }

        // Найти корень сжатие пути
        public int find(int id) {
            if (parent[id] != id) {
                parent[id] = find(parent[id]); // path compression
            }
            return parent[id];
        }

        // Объединить два острова с балансировкой по ранку
        public void union(int id1, int id2) {
            int root1 = find(id1);
            int root2 = find(id2);

            if (root1 != root2) {
                // присоединяем меньшее дерево к большему
                if (rank[root1] < rank[root2]) {
                    parent[root1] = root2;
                } else if (rank[root1] > rank[root2]) {
                    parent[root2] = root1;
                } else {
                    parent[root2] = root1;
                    rank[root1]++; // увеличиваем ранг если они равны
                }
                count--; // уменьшили количество отдельных островов
            }
        }

        public int getCount() {
            return count;
        }
    }
}
