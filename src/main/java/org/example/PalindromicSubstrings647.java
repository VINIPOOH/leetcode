package org.example;

public class PalindromicSubstrings647 {

    public static void main(String[] args) {
        System.out.println(new PalindromicSubstrings647().countSubstrings("bbccaacacdbdbcbcbbbcbadcbdddbabaddbcadb"));
    }

    //каноническое решение
    public int countSubstringsCanonical(String s) {
        int count = 0;

        for (int i = 0; i < s.length(); i++) {
            // нечётные палиндромы (центр в i)
            count += expandAroundCenter(s, i, i);
            // чётные палиндромы (центр между i и i+1)
            count += expandAroundCenter(s, i, i + 1);
        }

        return count;
    }

    private int expandAroundCenter(String s, int left, int right) {
        int count = 0;
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            count++;
            left--;
            right++;
        }
        return count;
    }

    /// _______________________________________///
    //my optimization
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
                countToReturn += (countForSameLater * (countForSameLater - 1)) / 2;//Арифметическая прогрессия. Количество под отрезков последовательности
            }
            //мы не заботимся о центре между букв поскольку палиндром с центром между букв всегда имеет дви и более одинаковые буквы в центре которые покрыты
            //внутренним циклом вайл выше.
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

    /// _______________________________________///
    //dynamic programing based solution

    /**
     * В задаче Longest Palindromic Substring DP не даёт асимптотического выигрыша, поскольку каждое состояние dp[i][j] вычисляется один раз
     * и используется не более одного раза (как зависимость для dp[i-1][j+1]).
     * Отсутствует многократное переиспользование состояний, поэтому преимущества динамического программирования проявляются лишь в структурировании решения.
     * <p>
     * Для этой задачи это избыточно.
     */
    public int countSubstringsDP(String s) {
        int n = s.length();
        //x first letter index y last letter index -- true if between them palindrome;
        boolean[][] isPalindrome = new boolean[n][n];

        int count = 0;

        // длина подстроки
        for (int length = 1; length <= n; length++) {
            for (int left = 0; left + length - 1 < n; left++) {
                int right = left + length - 1;
                if (s.charAt(left) == s.charAt(right)) {
                    if (length <= 2) {
                        isPalindrome[left][right] = true;
                    } else {
                        isPalindrome[left][right] = isPalindrome[left + 1][right - 1];
                    }
                }

                if (isPalindrome[left][right]) {
                    count++;
                }
            }
        }
        return count;
    }

    /**solution based on letter pairs for palindrome
     *absolutely bad solution
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
