package org.example;

public class ContainerWithMostWater11 {
    public static void main(String[] args) {
        System.out.println(new ContainerWithMostWater11().maxArea(new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 }));
    }

    //каноническое решение
    public int maxArea(int[] height) {
        int left = 0;
        int right = height.length - 1;
        int maxArea = 0;

        while (left < right) {
            int width = right - left;
            int currentHeight = Math.min(height[left], height[right]);
            int currentArea = width * currentHeight;

            maxArea = Math.max(maxArea, currentArea);

            // двигаем меньшую сторону
            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }

        return maxArea;
    }
}
