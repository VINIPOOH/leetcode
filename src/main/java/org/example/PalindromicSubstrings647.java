package org.example;

public class PalindromicSubstrings647 {

    public static void main(String[] args) {
        System.out.println(new PalindromicSubstrings647().countSubstrings("bbccaacacdbdbcbcbbbcbadcbdddbabaddbcadb"));
    }

    public int countSubstrings(String s) {
        int length = s.length();
        int countToReturn = length;
        int currentProcessIndex = 0;
        while (currentProcessIndex < length - 1) {
            int left = currentProcessIndex;
            int countForSameLater = 1;
            while (currentProcessIndex < length - 1 && s.charAt(currentProcessIndex) == s.charAt(currentProcessIndex + 1)) {
                countForSameLater++;
                currentProcessIndex++;
            }
            if (countForSameLater > 1) {
                countToReturn += (countForSameLater * (countForSameLater - 1)) / 2;
            }
            countToReturn = countToReturn + getPalindromeCount(s, left - 1, currentProcessIndex + 1, length);
            currentProcessIndex++;

        }
        return countToReturn;
    }

    private static int getPalindromeCount(String s, int left, int right, int length) {
        int palindromeCount = 0;
        while (left >= 0 && right < length) {
            if (s.charAt(left) != s.charAt(right)) {
                break;
            }
            left--;
            right++;
            palindromeCount++;
        }
        return palindromeCount;
    }

    /**solution based on letter pairs for palindrome
     *
     */
    //    public int countSubstrings(String s) {
    //
    //        Map<Character, List<Integer>> characterIndexesList = new HashMap<>();
    //        populateMap(s, characterIndexesList);
    //        filterSingleLetters(characterIndexesList);
    //
    //        int amountOfPalindromes = s.length();
    //        for (Map.Entry<Character, List<Integer>> entry : characterIndexesList.entrySet()) {
    //            List<Integer> indexes = entry.getValue();
    //            for (int i = 0; i < indexes.size() - 1; i++) {
    //                nextIndexedLoop:
    //                for (int j = i + 1; j < indexes.size(); j++) {
    //                    int endIndex = indexes.get(j) - 1;
    //                    int startIndex = indexes.get(i) + 1;
    //                    for (; startIndex < endIndex; startIndex++, endIndex--) {
    //                        if (s.charAt(startIndex) != s.charAt(endIndex)) {
    //                            continue nextIndexedLoop;
    //                        }
    //                    }
    //                    amountOfPalindromes++;
    //                }
    //            }
    //        }
    //        return amountOfPalindromes;
    //    }
    //
    //    private void filterSingleLetters(Map<Character, List<Integer>> characterIndexesList) {
    //        characterIndexesList.entrySet().removeIf(entry -> entry.getValue().size() <= 1);
    //    }
    //
    //    private void populateMap(String s, Map<Character, List<Integer>> characterIndexesList) {
    //        for (int i = 0; i < s.length(); i++) {
    //            characterIndexesList.computeIfAbsent(s.charAt(i), character -> new ArrayList<>()).add(i);
    //        }
    //    }

}
