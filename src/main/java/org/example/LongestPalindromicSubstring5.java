package org.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class LongestPalindromicSubstring5 {

    public static void main(String[] args) {
        LongestPalindromicSubstring5 longestPalindromicSubstring5 = new LongestPalindromicSubstring5();
        System.out.println(longestPalindromicSubstring5.longestPalindrome("babad"));
    }

    //каноническое решение
    public String longestPalindrome1(String s) {
        if (s == null || s.length() < 2) {
            return s;
        }

        //Индекс начала текущего лучшего палиндрома
        int startOfBest = 0;
        int maxLength = 1;

        for (int center = 0; center < s.length(); center++) {

            // 1) Проверяем палиндром нечётной длины (центр = один символ)
            int oddLength = expandAroundCenter(s, center, center);
            if (oddLength > maxLength) {
                maxLength = oddLength;
                startOfBest = center - (oddLength - 1) / 2; //отнимание единицы можно убрать потому что джава дробь отбросит при приведении к инт.
            }

            // 2) Проверяем палиндром чётной длины (центр между символами)
            int evenLength = expandAroundCenter(s, center, center + 1);
            if (evenLength > maxLength) {
                maxLength = evenLength;
                startOfBest = center - (evenLength / 2) + 1;
            }
        }

        return s.substring(startOfBest, startOfBest + maxLength);
    }

    /**
     * Расширяет палиндром от заданного центра и возвращает его длину
     */
    private int expandAroundCenter(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }

        // длина палиндрома после выхода из цикла
        return right - left - 1;
    }

    /// ________________________///
    //Не каноничное кривое решение с идеей что палиндром требует парных букв. И возможно не корректное. Но тесты литкод проходит.
    public String longestPalindrome(String s) {

        //        Set<Character> charactersThatIsThereMoreThenOnce = findCharactersTatInLineMoreThenOnce(s);
        String currentLongestSubStr = s.substring(0, 1);

        Map<Character, List<Integer>> characterListHashMap = getCharacterListHashMap(s);
        characterListHashMap = removeSingleLetters(characterListHashMap);
        int currentLongestStringSize = 1;
        if (characterListHashMap.isEmpty()) {
            return currentLongestSubStr;
        }
        for (Map.Entry<Character, List<Integer>> entry : characterListHashMap.entrySet()) {
            List<Integer> charIndexes = entry.getValue();
            int sizeOfLetterIndexes = charIndexes.size();
            for (int i = 0; i < sizeOfLetterIndexes - 1; i++) {
                loop:
                for (int j = sizeOfLetterIndexes - 1; j > i; j--) {
                    int firstI = charIndexes.get(i) + 1;
                    int secondI = charIndexes.get(j) - 1;
                    int potentialSubStrLength = charIndexes.get(j) - charIndexes.get(i) + 1;
                    if (currentLongestStringSize > potentialSubStrLength) {
                        break;
                    }
                    for (; firstI < secondI; firstI++, secondI--) {
                        if (s.charAt(firstI) != s.charAt(secondI)) {
                            continue loop;
                        }
                    }
                    currentLongestStringSize = potentialSubStrLength;
                    currentLongestSubStr = s.substring(charIndexes.get(i), charIndexes.get(j) + 1);

                }
            }
        }
        return currentLongestSubStr;
    }

    private static Map<Character, List<Integer>> removeSingleLetters(Map<Character, List<Integer>> characterListHashMap) {
        return characterListHashMap.entrySet().stream().filter(characterListEntry -> !characterListEntry.getValue().isEmpty()).collect(Collectors.toMap(
                Map.Entry::getKey, Map.Entry::getValue));
    }

    private static HashMap<Character, List<Integer>> getCharacterListHashMap(String s) {
        HashMap<Character, List<Integer>> charactersIndexes = new LinkedHashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char key = s.charAt(i);
            List<Integer> indexes = charactersIndexes.get(key);
            if (indexes == null) {
                indexes = new ArrayList<>();
            }
            indexes.addLast(i);
            charactersIndexes.put(key, indexes);
        }
        return charactersIndexes;
    }
}
