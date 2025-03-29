package org.example;

import java.util.HashMap;

public class LongestSubstringWithoutRepeatingCharacters3 {
    public int lengthOfLongestSubstring(String s) {
        char[] inputStringArray = s.toCharArray();
        HashMap<Character, Integer> characterToLustIndexMap = new HashMap<>();
        int currentSequenceLength = 0;
        int maxSequenceLength = 0;

        for (int i = 0; i < inputStringArray.length; i++) {
            if (!characterToLustIndexMap.containsKey(inputStringArray[i])) {
                currentSequenceLength++;
                characterToLustIndexMap.put(inputStringArray[i], i);
                continue;
            }
            Integer indexFromWhichCleanMap = characterToLustIndexMap.get(inputStringArray[i]);
            int indexToWhichCleanIbcluded = i - currentSequenceLength;
            for (int j = indexFromWhichCleanMap; j >= indexToWhichCleanIbcluded; j--) {
                characterToLustIndexMap.remove(inputStringArray[j]);
            }
            if (maxSequenceLength < currentSequenceLength) {
                maxSequenceLength = currentSequenceLength;
            }
            currentSequenceLength = i - indexFromWhichCleanMap;
            characterToLustIndexMap.put(inputStringArray[i], i);
        }
        if (maxSequenceLength < currentSequenceLength) {
            maxSequenceLength = currentSequenceLength;
        }
        return maxSequenceLength;
    }

    public int lengthOfLongestSubstringNoArray(String s) {
        HashMap<Character, Integer> characterToLustIndexMap = new HashMap<>();
        int currentSequenceLength = 0;
        int maxSequenceLength = 0;

        for (int i = 0; i < s.length(); i++) {
            Integer indexFromWhichCleanMap = characterToLustIndexMap.get(s.charAt(i));
            if (indexFromWhichCleanMap == null) {
                currentSequenceLength++;
            } else {
                int indexToWhichCleanIbcluded = i - currentSequenceLength;
                for (int j = indexFromWhichCleanMap; j >= indexToWhichCleanIbcluded; j--) {
                    characterToLustIndexMap.remove(s.charAt(j));
                }
                if (maxSequenceLength < currentSequenceLength) {
                    maxSequenceLength = currentSequenceLength;
                }
                currentSequenceLength = i - indexFromWhichCleanMap;
            }
            characterToLustIndexMap.put(s.charAt(i), i);

        }
        if (maxSequenceLength < currentSequenceLength) {
            maxSequenceLength = currentSequenceLength;
        }
        return maxSequenceLength;
    }
}
