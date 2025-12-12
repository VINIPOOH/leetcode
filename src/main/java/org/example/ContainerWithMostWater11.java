package org.example;

public class ContainerWithMostWater11 {
    public static void main(String[] args) {
        System.out.println(new ContainerWithMostWater11().maxArea(new int[] { 1,2,3,4,5,6,7,8,9,10 }));
    }

    public int maxArea(int[] height) {
        int currentRightIndex = height.length - 1;
        int currentLeftIndex = 0;
        int currentMaxArea = 0;
        int width = currentRightIndex - currentLeftIndex;
        while (width>2) {
            int currentMinHeight = Math.min(height[currentRightIndex], height[currentLeftIndex]);
            currentMaxArea = Math.max(currentMaxArea, currentMinHeight * (currentRightIndex - currentLeftIndex));
            int requiredHeight = (int) Math.ceil((double) currentMaxArea / ((double)width-1));
            while (width>2 && height[currentLeftIndex] <= requiredHeight) {
                currentLeftIndex++;
                width--;
                requiredHeight = (int) Math.ceil((double) currentMaxArea / ((double)width-1));
            }
            while (width>2 && height[currentRightIndex] <= requiredHeight) {
                currentRightIndex--;
                width--;
                requiredHeight = (int) Math.ceil((double) currentMaxArea / ((double)width-1));
            }
        }

        while (currentRightIndex > currentLeftIndex) {
            int currentMinHeight = Math.min(height[currentRightIndex], height[currentLeftIndex]);
            currentMaxArea = Math.max(currentMaxArea, currentMinHeight * (currentRightIndex - currentLeftIndex));
            while (currentLeftIndex < currentRightIndex && height[currentLeftIndex] <= currentMinHeight) {
                currentLeftIndex++;
                width--;
            }
            while (currentLeftIndex < currentRightIndex && height[currentRightIndex] <= currentMinHeight) {
                currentRightIndex--;
                width--;
            }
        }
        return currentMaxArea;
    }

//    public int maxArea(int[] height) {
//        int currentRightIndex = height.length - 1;
//        int currentLeftIndex = 0;
//        int currentMaxArea = 0;
//
//        while (currentRightIndex > currentLeftIndex) {
//            int width = currentRightIndex - currentLeftIndex;
//            int currentMinHeight = Math.min(height[currentLeftIndex], height[currentRightIndex]);
//            currentMaxArea = Math.max(currentMaxArea, currentMinHeight * width);
//
//            // Вычисляем требуемую минимальную высоту, чтобы получить хотя бы такую же площадь на следующем шаге
//            int requiredHeight = (int) Math.ceil((double) currentMaxArea / (width - 1));
//
//            if (height[currentLeftIndex] < height[currentRightIndex]) {
//                do {
//                    currentLeftIndex++;
//                    width = currentRightIndex - currentLeftIndex;
//                    requiredHeight = width > 0 ? (int) Math.ceil((double) currentMaxArea / width) : Integer.MAX_VALUE;
//                } while (currentLeftIndex < currentRightIndex && height[currentLeftIndex] < requiredHeight);
//            } else {
//                do {
//                    currentRightIndex--;
//                    width = currentRightIndex - currentLeftIndex;
//                    requiredHeight = width > 0 ? (int) Math.ceil((double) currentMaxArea / width) : Integer.MAX_VALUE;
//                } while (currentLeftIndex < currentRightIndex && height[currentRightIndex] < requiredHeight);
//            }
//        }
//
//        return currentMaxArea;
//    }
}
