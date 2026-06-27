package org.example;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CombinationSum39 {

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        Arrays.sort(candidates);

        List<List<Integer>> result = new ArrayList<>();
        List<Integer> currentPath = new ArrayList<>();
        dfs1(candidates, target, 0, currentPath, result);
        return result;
    }

    //классический с циклом
    private static void dfs(int[] candidates,
                            int target,
                            int start,
                            List<Integer> currentPath,
                            List<List<Integer>> result) {

        if (target == 0) {//проверка условия нахождения нужного пути
            result.add(new ArrayList<>(currentPath));
            return; //unnecessary but for clarity step. condition out of recursion
        } else if (target < 0 || start == candidates.length) { //проверка условия бессмысленности дальнейшего поиска пути
            return; //unnecessary but for clarity step. condition out of recursion
        } else {
            for (int i = start; i < candidates.length && (target - candidates[i]) >= 0; i++) {
                int currentCandidate = candidates[i];
                //добавляем текущий елемент в путь поскольку он прошел проверки выше
                currentPath.add(currentCandidate);
                //вычисляем новый таргет для рекурсии
                int newTarget = target - currentCandidate;
                // i, НЕ i+1 — если элемент можно использовать повторно
                dfs(candidates, newTarget, i, currentPath, result);
                //удаляем текущий элемент с пути поскольку с ним проверка закончена и дальше мы будем проверять новый текущий элемент в цикле
                currentPath.removeLast();
            }
        }
    }

    //с ветвлением choose → explore → unchoose” + “skip branch
    private static void dfs1(int[] candidates,
                             int target,
                             int start,
                             List<Integer> currentPath,
                             List<List<Integer>> result) {

        if (target == 0) {//проверка условия нахождения нужного пути
            result.add(new ArrayList<>(currentPath));
            return; //unnecessary but for clarity step. condition out of recursion
        } else if (target < 0 || start == candidates.length) { //проверка условия бессмысленности дальнейшего поиска пути
            return; //unnecessary but for clarity step. condition out of recursion
        } else {
            int currentCandidate = candidates[start];
            int newTarget = target - currentCandidate;
            if (newTarget < 0) { //ранний  выход по отсортированному массиву с обоих веток. дальше элементы точно слишком большие
                return;
            }
            currentPath.add(currentCandidate);
            dfs1(candidates, newTarget, start, currentPath, result);
            currentPath.removeLast();

            //запуск ветки без текущего кандидата, потому таргет не меняем и берем следующего кандидата
            dfs1(candidates, target, start + 1, currentPath, result);
        }
    }

    //со стеком итеративный
    private static void dfs2(int[] candidates,
                             int target,
                             int start,
                             List<List<Integer>> result) {

        ArrayDeque<Integer> stack = new ArrayDeque<>();
        int currentStart = start;
        int remaining = target;

        while (true) {
            // строим путь пока не выполним условие или не закончиться возможность строить путь
            while (currentStart < candidates.length && remaining > 0) {
                int cand = candidates[currentStart];
                if (cand > remaining) {
                    currentStart++;
                    continue; // раннее отсечение
                }
                stack.push(currentStart);        // добавляем индекс кандидата в путь
                remaining -= cand;               // уменьшаем оставшийся target
            }
            // Если нашли путь строим ответ
            if (remaining == 0) {
                createResponsePath(candidates, result, stack);
            }

            //пока все не перебрано на этом этапе у нас всегда есть хоть 1 элемент
            if (stack.isEmpty()) {
                break; // закончились все варианты
            }

            // Откат: убираем последний элемент пути
            //на первый взгляд кажется что 1 выхода не достаточно. Но глубокий выход достигается тем что во внутреннем Вайле не добавляются новые элементы
            //и происходит второй выход.
            int lastIndex = stack.pop();
            remaining += candidates[lastIndex]; // откатываем target
            currentStart = lastIndex + 1;       // переходим к следующему кандидату
        }
    }

    private static void createResponsePath(int[] candidates, List<List<Integer>> result, ArrayDeque<Integer> stack) {
        List<Integer> path = new ArrayList<>();
        for (int idx : stack) {
            path.add(candidates[idx]);
        }
        result.add(path);
    }
}
