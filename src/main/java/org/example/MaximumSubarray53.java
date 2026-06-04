package org.example;

public class MaximumSubarray53 {

    //main
    public int maxSubArray(int[] nums) {
        return divide(nums, 0, nums.length - 1).maxSum;
    }

    private static class Node {
        int leftSum;   // максимальная сумма, начинающаяся в левом крае сегмента
        int rightSum;  // максимальная сумма, заканчивающаяся в правом крае сегмента
        int maxSum;    // максимальная сумма любого под массива внутри сегмента. Необходима для контроля сквозного сегмента
        int totalSum;  // сумма всего сегмента

        Node(int val) {
            leftSum = rightSum = maxSum = totalSum = val;
        }
    }

    private Node divide(int[] nums, int l, int r) {
        //делим вплоть до 1 елемента
        if (l == r) {
            return new Node(nums[l]);
        }

        int mid = (l + r) / 2;

        Node left = divide(nums, l, mid);
        Node right = divide(nums, mid + 1, r);

        Node res = new Node(0);
        //Считаем общую суму для контроля сквозных сегментов
        res.totalSum = left.totalSum + right.totalSum;
        //Находим максимум с началом с права и началом с лева для новой склейки
        res.leftSum = Math.max(left.leftSum, left.totalSum + right.leftSum);
        res.rightSum = Math.max(right.rightSum, right.totalSum + left.rightSum);
        //Лучший локальный максимум или слева или с права, или посередине.

        res.maxSum = Math.max(
                Math.max(left.maxSum, right.maxSum),
                left.rightSum + right.leftSum
//                Math.max(left.rightSum + right.leftSum, left.totalSum + right.totalSum)
                //totalSum явно не проверяем потому что он уже включен res.leftSum res.rightSum
        );
        return res;
    }

    //Kadane Solution жадный
    public int maxSubArrayKadane(int[] nums) {
        // Инициализация: первый элемент массива
        int maxSoFar = nums[0];   // глобальный максимум
        int maxEndingHere = nums[0]; // локальный максимум, заканчивающийся на текущем элементе

        // Проходим по массиву начиная со второго элемента
        for (int i = 1; i < nums.length; i++) {
            // Локальный максимум: либо текущий элемент сам по себе, либо продолжаем предыдущий под массив
            //если текущий елемент больше сумы значит прошлая часть отрицательная и она мусор можно отбросить
            maxEndingHere = Math.max(nums[i], maxEndingHere + nums[i]);

            // Обновляем глобальный максимум
            maxSoFar = Math.max(maxSoFar, maxEndingHere);
        }

        return maxSoFar;
    }
}
