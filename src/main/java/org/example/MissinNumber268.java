package org.example;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class MissinNumber268 {
    public static void main(String[] args) {
        new MissinNumber268().missingNumber(new int[] { 9, 6, 4, 2, 3, 5, 7, 0, 1 });
    }

    //через сет
    public int missingNumber(int[] nums) {
        Set<Integer> numsSet = Arrays.stream(nums).boxed().collect(Collectors.toSet());

        for (int i = 0; i < nums.length; i++) {
            if (!numsSet.contains(i)) {
                return i;
            }
        }
        return nums.length;
    }

    //через отнимание реальной сумы и ожи
    public int missingNumberArithmeticProgression(int[] nums) {
        int n = nums.length;
        // Арифметическая прогрессия:
        // a   - первый член // d   - шаг (разность между соседними членами) // n   - номер члена прогрессии (1,2,3,...)

        // Формула n-го члена: a + (n - 1) * d

        // Формула суммы первых n членов:n/2 * (2*a + (n - 1)*d) или эквивалентно: n/2 * (a + a_n)
        int expectedSum = n * (n + 1) / 2;
        int actualSum = 0;
        for (int num : nums) {
            actualSum += num;
        }
        return expectedSum - actualSum;
    }

    //через бит манипуляции, самый быстрый
    public int missingNumberBitManipulation(int[] nums) {
        int n = nums.length;
        int xor = 0;
        for (int i = 0; i < n; i++) { //нельзя меньше равно потому что нет такого элемента в массиве.
            xor ^= i ^ nums[i]; //одинаковые биты дают 0 разные 1
            //Исключающее или комутативно и ассоциативно. Потому все элементы гасятся с соответствующими им и. И н мы гасим потом отдельно. И остается
            // то число, что без пары
        }
        return xor ^ n;
    }

    /*
    Cyclic Sort / In-place Hashing
Идея: пытаемся поставить каждый элемент на “свой индекс”, если это возможно.
Т.е. число x должно лежать в nums[x], если x < n.
Если x == n (верхняя граница диапазона) → заменяем на -1.
Проходим по массиву:
Если число на своём месте → пропускаем
Если нет → берём текущий элемент, переставляем на правильное место и продолжаем по цепочке (pivot).
В итоге:
Массив превращается в почти “самопозиционированный”:
nums[i] == i для всех чисел, которые есть
-1 для пропавших или чисел n
После этого просто проходим и ищем индекс i, где nums[i] != i → это пропавшее число.
Если такого нет → пропущено n.
     */
    public int missingNumberMy(int[] nums) {
        int n = nums.length;
        // Размещаем числа на их “правильные” позиции
        for (int i = 0; i < n; i++) {
            while (nums[i] < n && nums[i] != i) {
                int correctIndex = nums[i];
                // меняем местами nums[i] и nums[correctIndex]
                int temp = nums[i];
                nums[i] = nums[correctIndex];
                nums[correctIndex] = temp;
            }
        }

        // Находим первый индекс, где число != индекс
        for (int i = 0; i < n; i++) {
            if (nums[i] != i) {
                return i;
            }
        }

        // Если все числа на своих местах, пропущено n
        return n;
    }
}
