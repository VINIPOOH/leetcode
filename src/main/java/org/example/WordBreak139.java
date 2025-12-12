package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.stream.Collectors;

public class WordBreak139 {

    public static void main(String[] args) {
        List<String> wordDict = Arrays.stream(new String[] { "cats","dog","sand","and","cat","an" }).collect(Collectors.toList());
        System.out.println(new WordBreak139().wordBreak("catsandogcat", wordDict));
    }

    public boolean wordBreak(String s, List<String> wordDict) {
        Set<String> wordSet = new HashSet<>(wordDict);
        int[] roadStatuses = new int[s.length() + 1];
        roadStatuses[0] = 1;
        ArrayList<Integer> successfulIndexes = new ArrayList<>();
        successfulIndexes.addFirst(0);
        int currentProcessingIndexInString = 0;

        bigLoop:
        while (roadStatuses[s.length()] != 1) {
            for (int i = currentProcessingIndexInString + 1; i <= s.length(); i++) {
                //now we can use i for both places with no need to +1.
                String substring = s.substring(currentProcessingIndexInString, i);
                if (roadStatuses[i] != -1 && wordSet.contains(substring)) {
                    roadStatuses[i] = 1;
                    successfulIndexes.addFirst(i);
                    currentProcessingIndexInString = i;
                    continue bigLoop;
                }
            }
            roadStatuses[successfulIndexes.removeFirst()] = -1;
            try {
                currentProcessingIndexInString = successfulIndexes.getFirst();
            } catch (NoSuchElementException e) {
                return false;
            }
        }
        return true;
    }

    //    public boolean wordBreak(String s, List<String> wordDict) {
    //        Set<String> wordSet = new HashSet<>(wordDict);
    //        int n = s.length();
    //        boolean[] isPossibleFromPosition = new boolean[n + 1]; // dp[i] — можно ли разбить s[0..i)
    //        isPossibleFromPosition[0] = true; // Пустая строка всегда "разбиваемая"
    //
    //        for (int i = 1; i <= n; i++) {
    //            for (int j = 0; j < i; j++) {
    //                if (isPossibleFromPosition[j] && wordSet.contains(s.substring(j, i))) {
    //                    isPossibleFromPosition[i] = true;
    //                    break; // нашли разбиение — дальше искать не надо
    //                }
    //            }
    //        }
    //
    //        return isPossibleFromPosition[n];
    //    }
}
