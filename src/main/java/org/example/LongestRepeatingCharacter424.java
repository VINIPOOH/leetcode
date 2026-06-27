package org.example;

import java.util.HashMap;
import java.util.Map;

public class LongestRepeatingCharacter424 {
    public static void main(String[] args) {
        new LongestRepeatingCharacter424().characterReplacement("ABAB", 2);
    }

    //каноническое решение.
    //мы ищем самое большое количество одной буквы в рамках окна. maxCount содержит максимум одной буквы который нам удалось достичь в окне
    public int characterReplacementCanon(String s, int k) {
        int[] count = new int[26]; // частота букв
        int maxCount = 0;          // максимальная частота одной буквы которая когда-либо достигалась в окне
        //максимальная длина окна сотвественно растет на итерациях когда удалось вырастить максКоунт. В остальных
        //мы просто сохраняем ширину окна и идем дальше.
        int maxLen = 0;
        int left = 0;

        for (int right = 0; right < s.length(); right++) {
            count[s.charAt(right) - 'A']++;
            maxCount = Math.max(maxCount, count[s.charAt(right) - 'A']);

            // если нужно больше замен, чем k → сдвигаем левый указатель
            if ((right - left + 1) - maxCount > k) {
                count[s.charAt(left) - 'A']--;
                left++;
            }

            maxLen = Math.max(maxLen, right - left + 1);
        }

        return maxLen;
    }


    //с мапой а не масивом
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
            if ((right - left + 1) - maxCount > alouvedAmountReplacement) {
                char leftChar = s.charAt(left);
                amountCharacterInWindow.put(leftChar, amountCharacterInWindow.get(leftChar) - 1);
                left++;
                //maxCount пересчитывать не нужно поскольку он хранит не текущее а максимальнео значение одинаковой буквы в пределах окна которое удалось достичь
            }
            // обновляем максимальную длину окна
            maxWindow = Math.max(maxWindow, right - left + 1);
        }

        return maxWindow;
    }
}
