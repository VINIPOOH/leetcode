package org.example;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class MinimumWindowSubstring76 {

    public static void main(String[] args) {
        new MinimumWindowSubstring76().minWindow("ADOBECODEBANC", "ABC");
    }

    //мое первое самостоятельно написаное решение
    public String minWindow(String s, String t) {
        Map<Character, Long> neededChars = t.chars().mapToObj(value -> (char) value).collect(Collectors.groupingBy(c -> c, Collectors.counting()));
        Map<Character, Long> notUsedYetChars = new HashMap<>(neededChars);
        Map<Character, Long> charsInWindow = new HashMap<>();

        int lengthOfPotentialResult = Integer.MAX_VALUE;
        String potentialResult = "";
        int left = 0;
        int right = 0;

        while (right < s.length()) {
            charsInWindow.merge(s.charAt(right), 1L, Long::sum);
            notUsedYetChars.computeIfPresent(s.charAt(right), (_, val) -> --val == 0 ? null : val);
            while (notUsedYetChars.isEmpty()) {
                //на самом деле достаточно и просто ==
                char leftChar = s.charAt(left);
                Long leftCharAmountInWindow = charsInWindow.get(leftChar);
                charsInWindow.compute(leftChar, (_, val) -> --val == 0 ? null : val);
                if (neededChars.containsKey(leftChar) && leftCharAmountInWindow <= neededChars.get(leftChar)) {
                    notUsedYetChars.merge(leftChar, 1L, Long::sum);
                    if (lengthOfPotentialResult > right - left) {
                        lengthOfPotentialResult = right - left;
                        potentialResult = s.substring(left, right + 1);
                    }
                }
                left++;
            }
            right++;
        }
        return potentialResult;
    }

    //мое решение переписанное на массив с трюком
    public String minWindowArrays(String s, String t) {
        int[] neededChars = new int[128];
        int[] notUsedYetChars = new int[128];
        int[] charsInWindow = new int[128];

        // заполняем neededChars
        for (char c : t.toCharArray()) {
            neededChars[c]++;
            notUsedYetChars[c]++;
        }

        // сколько всего символов нужно (аналог "map пустая / не пустая")
        int missing = t.length();

        int lengthOfPotentialResult = Integer.MAX_VALUE;
        String potentialResult = "";
        int left = 0;
        int right = 0;

        while (right < s.length()) {
            char rightChar = s.charAt(right);

            // добавляем в окно
            charsInWindow[rightChar]++;

            // уменьшаем "недостающие", если этот символ ещё был нужен
            if (notUsedYetChars[rightChar] > 0) {
                notUsedYetChars[rightChar]--;
                missing--;
            }

            // аналог notUsedYetChars.isEmpty()
            while (missing == 0) {
                char leftChar = s.charAt(left);

                // если удаление ломает валидность окна
                if (neededChars[leftChar] > 0 &&
                        charsInWindow[leftChar] <= neededChars[leftChar]) {

                    if (lengthOfPotentialResult > right - left) {
                        lengthOfPotentialResult = right - left;
                        potentialResult = s.substring(left, right + 1);
                    }

                    // возвращаем символ в "недостающие"
                    notUsedYetChars[leftChar]++;
                    missing++;
                }

                // уменьшаем окно
                charsInWindow[leftChar]--;
                left++;
            }

            right++;
        }

        return potentialResult;
    }

    //каноничное по мнению ГПТ по мне абсолютно не так явно понятно читаемо. Идея противоположна моей добавлять те которые найдены, а не убирать те которые найдены из notUsedYetChars
    public String minWindowCanon(String s, String t) {
        // сколько нужно каждого символа (как у тебя neededChars)
        Map<Character, Integer> neededChars = t.chars().mapToObj(value -> (char) value).collect(Collectors.groupingBy(c -> c, Collectors.collectingAndThen(
                Collectors.counting(),
                Long::intValue
        )));
        // сколько сейчас в окне
        Map<Character, Integer> charsInWindow = new HashMap<>();

        int required = neededChars.size(); // сколько уникальных символов нужно закрыть
        int formed = 0; // сколько уже закрыто

        int left = 0;
        int right = 0;

        int lengthOfPotentialResult = Integer.MAX_VALUE;
        String potentialResult = "";

        while (right < s.length()) {
            char rightChar = s.charAt(right);

            // добавляем символ в окно
            charsInWindow.merge(rightChar, 1, Integer::sum);

            // если это нужный символ и мы набрали нужное количество
            if (neededChars.containsKey(rightChar)
                    && charsInWindow.get(rightChar).intValue() == neededChars.get(rightChar).intValue()) {
                formed++; // закрыли один символ
            }

            // пока окно валидное (аналог notUsedYetChars.isEmpty())
            while (formed == required) {

                // обновляем ответ
                if (lengthOfPotentialResult > right - left) {
                    lengthOfPotentialResult = right - left;
                    potentialResult = s.substring(left, right + 1);
                }

                char leftChar = s.charAt(left);

                // уменьшаем символ в окне
                charsInWindow.put(leftChar, charsInWindow.get(leftChar) - 1);

                // если это нужный символ и теперь его стало меньше нужного
                if (neededChars.containsKey(leftChar)
                        && charsInWindow.get(leftChar) < neededChars.get(leftChar)) {
                    formed--; // окно стало невалидным
                }

                left++;
            }

            right++;
        }

        return potentialResult;
    }
}
