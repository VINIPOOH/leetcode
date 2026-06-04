package org.example;

public class MaximumProductSubarray152 {

    //каноническое ДП решение
    public int maxProductDP(int[] nums) {
        int max = nums[0];
        int min = nums[0];
        int result = nums[0];

        for (int i = 1; i < nums.length; i++) {
            int cur = nums[i];

            // сохраняем старый max
            int prevMax = max;

            //Проверка макс cur закрывает сразу два сценария. Когда элемент 0 мы отбросим хвост и если прошлый макс отрицательный мы его заменим новым +
            //или текущий элемент текущий максимум, или результат умножения максимума или минимума если меняется знак.
            max = Math.max(cur, Math.max(cur * max, cur * min));
            //Проверка мин cur закрывает сразу два сценария. Когда элемент 0 мы отбросим хвост. Когда предыдущий и мин и макс отрицательные сохранить новый минус.
            //или текущий елемент минимум или прошлый макс если была смена знака или минимум если смены знака не было.
            min = Math.min(cur, Math.min(cur * prevMax, cur * min));

            result = Math.max(result, max);//обновляем текущий лучший максимум
        }

        return result;
    }


    /*
    Разбиваем массив на блоки без нулей
    1 Каждый ноль "сбрасывает" блок, потому что ноль уничтожает произведение.
        *Блок — это последовательность чисел без нулей.
        *Для каждого блока ищем наилучший продукт
    2 Запоминаем первый и последний отрицательный элемент.
        *Если общее произведение блока положительное → берем весь блок.
        *Если произведение отрицательное → можно убрать либо первые элементы до первого минуса, либо последние элементы после последнего минуса, чтобы получить положительное произведение.
    3 Обрабатываем крайние случаи
        *Ноль сам по себе — кандидат на максимум.
        *Блок длиной 1 с минусом — берём единственный элемент.
     */

    //мое решение быстрее каноничного, но много сложнее код
    public int maxProduct(int[] nums) {
        int n = nums.length;
        int maxProd = Integer.MIN_VALUE;

        int blockStart = 0;     // начало текущего блока (после последнего нуля)
        int firstNeg = -1;      // индекс первого минуса в блоке
        int lastNeg = -1;       // индекс последнего минуса в блоке
        int prod = 1;           // произведение элементов текущего блока

        for (int i = 0; i < n; i++) {
            if (nums[i] == 0) {
                // Обработать блок перед нулём (если он не пуст)
                if (i > blockStart) {
                    maxProd = Math.max(maxProd, bestFromBlock(nums, blockStart, i - 1, firstNeg, lastNeg));
                }
                // Ноль сам по себе кандидат на максимум
                maxProd = Math.max(maxProd, 0);

                // Сброс состояния для следующего блока
                blockStart = i + 1;
                prod = 1;
                firstNeg = -1;
                lastNeg = -1;
            } else {
                // Аккумулируем произведение и запоминаем минусы
                prod *= nums[i];
                if (nums[i] < 0) {
                    if (firstNeg == -1) {
                        firstNeg = i;
                    }
                    lastNeg = i;
                }
            }
        }

        // Обработать последний блок (если массив не закончился нулём)
        if (blockStart < n) {
            maxProd = Math.max(maxProd, bestFromBlock(nums, blockStart, n - 1, firstNeg, lastNeg));
        }

        return maxProd;
    }

    // Возвращает наилучший продукт для блока nums[l..r] (гарантированно без нулей внутри).
    private int bestFromBlock(int[] nums, int l, int r, int firstNeg, int lastNeg) {
        // Вычисляем полное произведение блока
        int total = 1;
        for (int i = l; i <= r; i++) {
            total *= nums[i];
        }

        // Если произведение положительное — берём весь блок
        if (total > 0) {
            return total;
        }

        // total <= 0 (в наших блоках total==0 не встречается)
        // Попробуем убрать префикс (включая первый минус), если после этого остаётся непустой кусок
        int leftOption = Integer.MIN_VALUE;
        if (firstNeg != -1 && firstNeg + 1 <= r) {
            int p = 1;
            for (int i = firstNeg + 1; i <= r; i++) {
                p *= nums[i];
            }
            leftOption = p;
        }

        // Попробуем убрать суффикс (включая последний минус), если после этого остаётся непустой кусок
        int rightOption = Integer.MIN_VALUE;
        if (lastNeg != -1 && l <= lastNeg - 1) {
            int p = 1;
            for (int i = l; i <= lastNeg - 1; i++) {
                p *= nums[i];
            }
            rightOption = p;
        }

        // Если ни один вариант не даёт непустого подмассива (блок длины 1), вернём единственный элемент
        if (leftOption == Integer.MIN_VALUE && rightOption == Integer.MIN_VALUE) {
            // блок из одного элемента => l==r
            return nums[l];
        }

        return Math.max(leftOption, rightOption);
    }

    //Мой метод оптимизированный вместе гпт. На тесте литкод примерно то же самое, но упростили и еще оптимизировали
    //block-based divide-and-conquer for maximum product subarray
    /*
     * Максимальное произведение подмассива (Maximum Product Subarray) – блоковый подход
     *
     * Идея:
     * 1. Разбиваем массив на блоки без нулей (Zero-Split Segments), так как ноль обнуляет произведение.
     * 2. Для каждого блока:
     *    - Считаем произведение всего блока (total) один раз.
     *    - Считаем количество отрицательных чисел в блоке.
     *    - Если минусов чётное число → весь блок даёт максимальное произведение.
     *    - Если минусов нечётное число → убираем либо первый, либо последний минус (Trimming Negative Prefix/Suffix),
     *      чтобы получить максимальное положительное произведение.
     *      **Оптимизация:** мы не умножаем элементы блока дважды для каждого варианта, а берём уже вычисленное total
     *      и делим его на произведение префикса (до первого минуса) или суффикса (после последнего минуса).
     *      Это снижает количество умножений и ускоряет выполнение.
     * 3. Для подсчёта произведений блока используем рекурсивное попарное умножение (Pairwise Multiplication),
     *    чтобы снизить количество последовательных умножений и ускорить вычисление на длинных блоках.
     * 4. Ноль сам по себе всегда кандидат на максимум.
     *
     * Особенности реализации:
     * - total хранится в long, чтобы избежать переполнения при больших блоках.
     * - Блоки длиной 1 корректно обрабатываются (если нечётное число минусов → просто берём единственный элемент).
     * - Деление total / productSegment(...) позволяет быстро исключить префикс/суффикс при нечётных минусах.
     *
     * Преимущества:
     * - Быстрее стандартного DP с сохранением максимума и минимума на каждом шаге для длинных блоков.
     * - Уменьшает количество умножений внутри блока с O(n) до O(log n) через попарное умножение.
     *
     * Недостатки:
     * - Немного сложнее для понимания по сравнению с каноническим DP.
     * - На коротких блоках выигрыш по скорости минимален, преимущество проявляется на длинных сегментах.
     */
    public int maxProductMyOptimized(int[] nums) {
        int n = nums.length;
        int maxProd = Integer.MIN_VALUE;

        int blockStart = 0;

        while (blockStart < n) {
            if (nums[blockStart] == 0) {
                maxProd = Math.max(maxProd, 0); // ноль сам по себе
                blockStart++;
                continue;
            }

            // найти конец блока без нулей
            int blockEnd = blockStart;
            int firstNeg = -1, lastNeg = -1, negCount = 0;

            while (blockEnd < n && nums[blockEnd] != 0) {
                if (nums[blockEnd] < 0) {
                    if (firstNeg == -1) firstNeg = blockEnd;
                    lastNeg = blockEnd;
                    negCount++;
                }
                blockEnd++;
            }

            long total = productSegment(nums, blockStart, blockEnd - 1);

            long blockMax;
            if (negCount % 2 == 0) {
                blockMax = total;
            } else {
                long leftProd = (firstNeg + 1 <= blockEnd - 1) ?
                                total / productSegment(nums, blockStart, firstNeg) : Integer.MIN_VALUE;
                long rightProd = (lastNeg - 1 >= blockStart) ?
                                 total / productSegment(nums, lastNeg, blockEnd - 1) : Integer.MIN_VALUE;

                // Если оба варианта невозможны (блок длины 1) — берём единственный элемент
                if (leftProd == Integer.MIN_VALUE && rightProd == Integer.MIN_VALUE) {
                    blockMax = nums[blockStart];
                } else {
                    blockMax = Math.max(leftProd, rightProd);
                }
            }

            maxProd = (int)Math.max(maxProd, blockMax);
            blockStart = blockEnd;
        }

        return maxProd;
    }

    private long productSegment(int[] nums, int l, int r) {
        if (l > r) return 1;  // пустой блок
        if (l == r) return nums[l];
        int mid = l + (r - l) / 2;
        return productSegment(nums, l, mid) * productSegment(nums, mid + 1, r);
    }
}
