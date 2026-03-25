package org.example;

import java.util.HashMap;
import java.util.Map;

//ошибка с номером
public class LongestSubstringWithoutRepeatingCharacters3 {

    //Канонический вариант. По заявлениям ИИ могут быть задачи где невозможна моя оптимизация обновлять только на событии перехода к новому окну.
    //Пример он так родить и не смог
    public int lengthOfLongestSubstringCanonical(String s) {
        HashMap<Character, Integer> characterToLastIndexMap = new HashMap<>();

        int left = 0;
        int maxSequenceLength = 0;

        for (int i = 0; i < s.length(); i++) {
            char newChar = s.charAt(i);

            if (characterToLastIndexMap.containsKey(newChar)) {
                int lastIndex = characterToLastIndexMap.get(newChar);
                //в случае повтора обновляем лефт. эсли повтор слева от лефт, то окно не сдвинется
                left = Math.max(left, lastIndex + 1);
            }

            // каноническое обновление максимума — на каждом шаге после стабилизации окна
            int currentSequenceLength = i - left + 1;
            maxSequenceLength = Math.max(maxSequenceLength, currentSequenceLength);

            characterToLastIndexMap.put(newChar, i);
        }

        return maxSequenceLength;
    }


    //Оптимизированное мной каноническое решение. Обновляет максимум только при переходе в новое окно и по окончанию прохода.
    public int lengthOfLongestSubstring1(String s) {
        HashMap<Character, Integer> characterToLastIndexMap = new HashMap<>();

        int left = 0; // было: currentSequenceLength
        int maxSequenceLength = 0;

        for (int i = 0; i < s.length(); i++) {
            char newChar = s.charAt(i);

            if (characterToLastIndexMap.containsKey(newChar)) {
                int lastIndex = characterToLastIndexMap.get(newChar);
                //в случае повтора обновляем лефт. эсли повтор слева от лефт, то окно не сдвинется
                int currentSequenceLength = i - left + 1; // теперь считаем тут
                maxSequenceLength = Math.max(maxSequenceLength, currentSequenceLength);
                left = Math.max(left, lastIndex + 1);
            }
            characterToLastIndexMap.put(newChar, i);
        }
        maxSequenceLength = Math.max(maxSequenceLength, s.length() - left);
        return maxSequenceLength;
    }

    public int lengthOfLongestSubstring2(String s) {
        Map<Character, Integer> lastIndex = new HashMap<>();
        int left = 0;
        int maxLength = 0;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (lastIndex.containsKey(c)) {
                left = Math.max(left, lastIndex.get(c) + 1);
            }

            lastIndex.put(c, i);
            maxLength = Math.max(maxLength, i - left + 1);
        }

        return maxLength;
    }

    //Старый не каноничный вариант с массивом и сразу без контейнс проверки. С использованием избыточной текущей длины и не нужной очисткой мапы.
    public int lengthOfLongestSubstringNoArray(String s) {
        HashMap<Character, Integer> characterToLustIndexMap = new HashMap<>();
        int currentSequenceLength = 0;
        int maxSequenceLength = 0;

        for (int i = 0; i < s.length(); i++) {
            Integer endSlideWindowIndex = characterToLustIndexMap.get(s.charAt(i));
            //Если нул элемента нет в мапе можно идти дальше. Растет счетчик
            if (endSlideWindowIndex == null) {
                currentSequenceLength++;
            } else {
//                Элемент есть в мапе - значит двигаем оконо чистим мапу.
                int startSlideWindowIndex = i - currentSequenceLength;
                for (int j = startSlideWindowIndex; j <= endSlideWindowIndex; j++) {
                    characterToLustIndexMap.remove(s.charAt(j));
                }
                maxSequenceLength = Math.max(maxSequenceLength, currentSequenceLength);
                currentSequenceLength = i - endSlideWindowIndex;
            }
            characterToLustIndexMap.put(s.charAt(i), i);
        }
        //Можно было бы без этой проверки. Альтернатива делать обновление макс на каждой итерации цикла.
        maxSequenceLength = Math.max(maxSequenceLength, currentSequenceLength);
        return maxSequenceLength;
    }
}
