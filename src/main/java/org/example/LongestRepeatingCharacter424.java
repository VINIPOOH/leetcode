package org.example;

import java.util.HashMap;
import java.util.Map;

public class LongestRepeatingCharacter424 {
    public static void main(String[] args) {
        new LongestRepeatingCharacter424().characterReplacement("ABAB", 2);
    }

    //Sliding Window with Lazy Invariant
    public int characterReplacement(String s, int alouvedAmountReplacement) {
        Map<Character, Integer> amountCharacterInWindow = new HashMap<>(); // количество сущностей в окне
        int maxCount = 0; // максимальное количество одного символа когда-либо бывшее в окне
        int left = 0; //индекс левого края окна
        int maxWindow = 0; //максимальный размер окна которого удалось достичь

        for (int right = 0; right < s.length(); right++) {//за одну итерацию проходим весь массив окном
            char addedChar = s.charAt(right); //добавить новый элемент в структуру
            amountCharacterInWindow.put(addedChar, amountCharacterInWindow.getOrDefault(addedChar, 0) + 1);

            // пересчитываем исторический максимум одинаковых символов
            maxCount = Math.max(maxCount, amountCharacterInWindow.get(addedChar));

            // если нужно больше замен, чем разрешено, сдвигаем левый указатель
            while ((right - left + 1) - maxCount > alouvedAmountReplacement) {
                char leftChar = s.charAt(left);
                amountCharacterInWindow.put(leftChar, amountCharacterInWindow.get(leftChar) - 1);
                left++;
                // Пересчитываем maxCount для нового окна. Ето делать не нужно алгоритм будет работать корректно и без етого
//                maxCount = 0;
//                for (int val : amountCharacterInWindow.values()) {
//                    maxCount = Math.max(maxCount, val);
//                }
            }
            // обновляем максимальную длину окна
            maxWindow = Math.max(maxWindow, right - left + 1);
        }

        return maxWindow;
    }

    //Sliding Window classic
    public int characterReplacement1(String s, int alouvedAmountReplacement) {
        Map<Character, Integer> amountCharacterInWindow = new HashMap<>(); // количество сущностей в окне
        int maxCount = 0; // максимальное количество одного символа в окне
        int left = 0; //индекс левого края окна
        int maxWindow = 0; //максимальный размер окна которого удалось достичь

        for (int right = 0; right < s.length(); right++) {//за одну итерацию проходим весь массив окном
            char addedChar = s.charAt(right); //добавить новый элемент в структуру
            amountCharacterInWindow.put(addedChar, amountCharacterInWindow.getOrDefault(addedChar, 0) + 1);

            // пересчитываем исторический максимум одинаковых символов
            maxCount = Math.max(maxCount, amountCharacterInWindow.get(addedChar));

            // если нужно больше замен, чем разрешено, сдвигаем левый указатель
            while ((right - left + 1) - maxCount > alouvedAmountReplacement) {
                char leftChar = s.charAt(left);
                amountCharacterInWindow.put(leftChar, amountCharacterInWindow.get(leftChar) - 1);
                left++;
                // Пересчитываем maxCount для нового окна
                maxCount = 0;
                for (int val : amountCharacterInWindow.values()) {
                    maxCount = Math.max(maxCount, val);
                }
            }
            // обновляем максимальную длину окна
            maxWindow = Math.max(maxWindow, right - left + 1);
        }
        return maxWindow;
    }

    public static int twoPointer(String s, int k) {
        int currentBreakCounter = k;
        int lengthOfMaxSequence = 0;
        int lengthOfCurrentSequence = 0;
        int slowPointer = 0;
        int fastPointer = 0;
        while (slowPointer < s.length()) {
            char currentCharacter = s.charAt(slowPointer);
            while (currentBreakCounter == 0 && fastPointer < s.length()) {
                while (slowPointer < s.length() && currentCharacter == s.charAt(slowPointer)) {
                    slowPointer++;
                    lengthOfCurrentSequence++;
                }
                fastPointer = slowPointer;
                if (currentCharacter != s.charAt(fastPointer)) {
                    currentBreakCounter--;
                }
                fastPointer++;
                lengthOfCurrentSequence++;
            }
            if (lengthOfMaxSequence < lengthOfCurrentSequence) {
                lengthOfMaxSequence = lengthOfCurrentSequence;
            }
            if (fastPointer == s.length()) {
                return lengthOfMaxSequence;
            }
            lengthOfCurrentSequence = 0;
        }
        return lengthOfMaxSequence;
    }
}
