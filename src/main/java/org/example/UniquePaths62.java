package org.example;

public class UniquePaths62 {

    //Каноническое ДП
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

    //Решение по формуле комбинаторики просто для общего образования
    public int uniquePathsCombinatorics(int m, int n) {
        // Общее количество шагов: (m-1) вниз + (n-1) вправо
        int totalSteps = m + n - 2;

        // Мы выбираем, в каких шагах будут "вниз" (или "вправо" — не важно)
        // Берём минимум, чтобы меньше крутить цикл
        int k = Math.min(m - 1, n - 1);

        // Используем long, потому что промежуточные значения могут быть больше int
        long result = 1;

        // Считаем биномиальный коэффициент:
        // C(totalSteps, k) = totalSteps! / (k! * (totalSteps - k)!)
        //
        // Но вместо факториалов считаем по частям:
        // C(n, k) = (n-k+1)/1 * (n-k+2)/2 * ... * (n)/k
        //
        // Это позволяет:
        // - не раздувать числа
        // - не использовать BigInteger
        for (int i = 1; i <= k; i++) {
            // На каждом шаге:
            // умножаем на следующий числитель
            result = result * (totalSteps - k + i);

            // и сразу делим на i (часть знаменателя)
            result = result / i;

            // Важно:
            // деление всегда "чистое" (без остатка),
            // потому что это биномиальный коэффициент
        }

        // По условию результат <= 2 * 10^9, значит влезет в int
        return (int) result;
    }
}
