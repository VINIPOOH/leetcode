package org.example;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;

public class CoinChange322 {

    //нет возможности раннего выхода по сравнению с поиском в ширину BFS
    public int coinChange(int[] coins, int targetAmount) {
        //количество шагов для сумы. Посути мапа
        int[] dp = new int[targetAmount + 1];
        Arrays.fill(dp, Integer.MAX_VALUE); // "бесконечность"
        dp[0] = 0;
        for (int i = 1; i <= targetAmount; i++) {
            for (int coin : coins) {
                if (coin <= i) {
                    //если монета меньше и то проверяем что меньше
                    //текущий и тый индекс или если использовать +
                    //текущую монету.
                    dp[i] = Math.min(dp[i], dp[i - coin] + 1);
                }
            }
        }
        return dp[targetAmount] > targetAmount ? -1 : dp[targetAmount];
    }

    //явно выделены слои через два массива я писал
    public int coinChangeBFS1(int[] coins, int targetAmount) {
        Arrays.sort(coins);

        if (targetAmount == 0) {
            return 0;
        }

        int[] amountOfCoins = new int[targetAmount + 1];
        Arrays.fill(amountOfCoins, -1);
        List<Integer> currentLayerSums;
        List<Integer> nextLayerSums = new ArrayList<>();

        nextLayerSums.add(0);
        int currentLayer = 0;

        while (!nextLayerSums.isEmpty()) {
            currentLayerSums = nextLayerSums;
            nextLayerSums = new ArrayList<>();
            currentLayer++;

            for (int sum : currentLayerSums) {
                for (int coin : coins) {
                    long currentSum = (long) coin + sum;
                    //will optimize if coins are sorted
                    if (currentSum > targetAmount) {
                        break;
                    }
                    int currentSumInt = (int) currentSum;
                    if (currentSumInt == targetAmount) {
                        return currentLayer;
                    } else if (amountOfCoins[currentSumInt] == -1) {
                        amountOfCoins[currentSumInt] = currentLayer;
                        nextLayerSums.addLast(currentSumInt);
                    }
                }
            }

        }

        return amountOfCoins[targetAmount];
    }

    public int coinChangeBFS(int[] coins, int targetAmount) {

        // Базовый случай:
        // чтобы набрать сумму 0 — не нужно ни одной монеты
        if (targetAmount == 0) {
            return 0;
        }
        // visited[x] = мы уже знаем минимальное число монет для суммы x
        // (или уже были в этой вершине графа)
        boolean[] visitedAmounts = new boolean[targetAmount + 1];
        // Очередь для BFS.
        // В ней лежат суммы, которые мы можем получить
        Queue<Integer> bfsQueue = new ArrayDeque<>();
        // Стартовая вершина графа — сумма 0
        bfsQueue.offer(0);
        visitedAmounts[0] = true;
        // level = текущее количество монет
        // В BFS это "глубина слоя"
        int coinsUsed = 0;
        // Пока есть суммы, которые можно расширять
        while (!bfsQueue.isEmpty()) {
            // Все элементы текущего слоя
            int nodesInCurrentLayer = bfsQueue.size();
            // Переходим на следующий слой:
            // теперь используем на 1 монету больше
            coinsUsed++;
            // Обрабатываем все суммы,
            // достижимые с coinsUsed - 1 монетами
            for (int i = 0; i < nodesInCurrentLayer; i++) {

                int currentAmount = bfsQueue.poll();
                // Пробуем добавить каждую монету
                for (int coinValue : coins) {
                    int nextAmount = currentAmount + coinValue;
                    // Если мы ровно попали в нужную сумму —
                    // это первый раз (BFS!), значит минимум
                    if (nextAmount == targetAmount) {
                        return coinsUsed;
                    }
                    // Если сумма допустимая и мы там ещё не были —
                    // добавляем в очередь
                    if (nextAmount < targetAmount && !visitedAmounts[nextAmount]) {
                        visitedAmounts[nextAmount] = true;
                        bfsQueue.offer(nextAmount);
                    }
                }
            }
        }
        // Если BFS закончился и мы не дошли до targetAmount —
        // решения не существует
        return -1;
    }

}
