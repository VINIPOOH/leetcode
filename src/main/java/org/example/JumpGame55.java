package org.example;

import java.util.ArrayList;
import java.util.List;

public class JumpGame55 {
    public boolean canJump(int[] nums) {
        int currentAchievementPoint = nums.length - 1;
        for (int i = nums.length - 2; i >= 0; i--) {
            if (nums[i] >= currentAchievementPoint - i) {
                currentAchievementPoint = i;
            }
        }
        return currentAchievementPoint == 0;
    }


    //движемся с начала, менее интуитивный алгоритм. По сути BFS поиск в ширину
    public boolean canJumpFromStart(int[] nums) {
        int farthestAchivableIndex = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i > farthestAchivableIndex) {
                return false; // застряли
            }
            farthestAchivableIndex = Math.max(farthestAchivableIndex, i + nums[i]);
        }
        return true;
    }

    //минимальное количество прыжков если конец может быть не достижим
    public int jumpUniversal(int[] nums) {
        int jumps = 0;
        int currentEnd = 0;
        int farthestAchivableIndex = 0;

        for (int i = 0; i < nums.length - 1; i++) {
            farthestAchivableIndex = Math.max(farthestAchivableIndex, i + nums[i]);

            //проверяем дошли ли мы до конца текущего уровня
            //в рамках нашего поиска в ширину BFS
            if (i == currentEnd) {
                jumps++;//увеличиваем счетчик уровня (количество прыжков
                if (farthestAchivableIndex <= i) return -1; // если конец не достижим
                //обновляем край текущего уровня (дистанция покрыта текущим прыжком)
                currentEnd = farthestAchivableIndex;
            }
        }

        return jumps;
    }

    //минимальное количество прыжков
    public int jump(int[] nums) {
        int jumps = 0;
        int currentEnd = 0;
        int farthestAchivableIndex = 0;

        for (int i = 0; i < nums.length - 1; i++) {
            farthestAchivableIndex = Math.max(farthestAchivableIndex, i + nums[i]);

            if (i == currentEnd) {
                jumps++;
                currentEnd = farthestAchivableIndex;
            }
        }

        return jumps;
    }

    //минимальный путь прыжков
    public List<Integer> jumpRoute(int[] nums) {
        List<Integer> route = new ArrayList<>();
        if (nums.length == 1) {
            route.add(0);
            return route;
        }
        int currentEnd = 0;
        int farthestAchivableIndex = 0;
        int jumpFrom = 0;

        route.add(0); // старт

        for (int i = 0; i < nums.length - 1; i++) {
            //если с текущего итого можно прыгнуть дальше текущего максимума
            //добавляем как новый кандидат на следующий прыжок
            if (i + nums[i] > farthestAchivableIndex) {
                farthestAchivableIndex = i + nums[i];
                jumpFrom = i;
            }
            if (i == currentEnd) {
                if (farthestAchivableIndex <= i) {
                    // недостижимо
                    return null;
                }
                //обновляем край текущего слоя поиска в ширину BFS
                currentEnd = farthestAchivableIndex;
                route.add(jumpFrom);
            }
        }
        route.add(nums.length - 1); // финиш
        return route;
    }

}
