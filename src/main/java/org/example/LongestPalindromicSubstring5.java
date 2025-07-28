package org.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LongestPalindromicSubstring5 {

    public static void main(String[] args) {
        LongestPalindromicSubstring5 longestPalindromicSubstring5 = new LongestPalindromicSubstring5();
        System.out.println(longestPalindromicSubstring5.longestPalindrome("babad"));
    }

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
