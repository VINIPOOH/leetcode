package org.example;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Queue;
import java.util.Set;
import java.util.stream.Collectors;

public class WordBreak139 {

    public static void main(String[] args) {
        List<String> wordDict = Arrays.stream(new String[] { "cats", "dog", "sand", "and", "cat", "an" }).collect(Collectors.toList());
        System.out.println(new WordBreak139().wordBreak("catsandogcat", wordDict));
    }

    //каноническое решение
    public boolean wordBreak(String text, List<String> dictionary) {
        Set<String> wordSet = new HashSet<>(dictionary);

        final int textLength = text.length();

        boolean[] dp = new boolean[textLength + 1];
        dp[0] = true;

        for (int endPosition = 1; endPosition <= textLength; endPosition++) {
            for (int startPosition = 0; startPosition < endPosition; startPosition++) {
                if (!dp[startPosition]) {
                    continue;
                }
                String candidateWord = text.substring(startPosition, endPosition);
                if (wordSet.contains(candidateWord)) {
                    dp[endPosition] = true;
                    break;
                }
            }
        }
        return dp[textLength];
    }


    public boolean wordBreakDPOptimized(String s, List<String> wordDict) {
        // Переводим список слов в HashSet для быстрого поиска
        Set<String> dict = new HashSet<>(wordDict);

        // Находим максимальную длину слова в словаре
        int maxLen = getMaxLen(wordDict);

        final int length = s.length();
        // dp[i] = true, если s[0..i) можно разбить на слова из словаря
        boolean[] dp = new boolean[length + 1];
        dp[0] = true; // пустая строка всегда "разбиваемая"

        // Идем по всем позициям в строке s (от 1 до length)
        for (int i = 1; i <= length; i++) {
            // Проверяем только последние maxLen символов (оптимизация)
            for (int len = 1; len <= maxLen && len <= i; len++) {
                // Если подстроку s[0..i-len) можно разбить
                if (!dp[i - len]) {
                    continue;
                }

                // Проверяем, есть ли текущее слово в словаре
                if (dict.contains(s.substring(i - len, i))) {
                    dp[i] = true; // s[0..i) разбивается
                    break; // нашли слово — дальше проверять не нужно
                }
            }
        }//этот цикл можно было бы крутить и по словам из словаря, но это будет быстрее только при очень маленьком словаре с длинными словами.

        // dp[length] = можно ли разбить всю строку s
        return dp[length];
    }

    private static int getMaxLen(List<String> wordDict) {
        int maxLen = 0;
        for (String word : wordDict) {
            maxLen = Math.max(maxLen, word.length());
        }
        return maxLen;
    }

    //
    public boolean wordBreakBFS(String s, List<String> wordDict) {
        Set<String> dict = new HashSet<>(wordDict); // быстрый поиск слов
        int n = s.length();
        // Находим максимальную длину слова в словаре
        int maxLen = getMaxLen(wordDict);

        Queue<Integer> queue = new LinkedList<>(); // индексы, с которых можно начать слово
        boolean[] visited = new boolean[n]; // чтобы не проверять один и тот же индекс дважды

        queue.add(0); // начинаем с позиции 0

        while (!queue.isEmpty()) {
            int start = queue.poll();

            if (visited[start]) {
                continue; // уже проверяли этот индекс
            }
            visited[start] = true;

            // пробуем все слова, которые начинаются в позиции start
            for (int len = 1; len <= maxLen && start + len <= n; len++) {
                int end = start + len;
                String sub = s.substring(start, end);

                if (dict.contains(sub)) {
                    if (end == n) {
                        return true;
                    }
                    queue.add(end);
                }
            }
        }

        return false; // не удалось разбить строку
    }

    //Метод с построением дерева. Сложный, но эфективныйц при огромных словарях
    static class TrieNode {
        TrieNode[] children = new TrieNode[26];
        boolean isWord;
    }

    public boolean wordBreakTrie(String s, List<String> wordDict) {
        TrieNode root = buildTrie(wordDict);

        int n = s.length();
        boolean[] dp = new boolean[n + 1];
        dp[0] = true;

        for (int i = 0; i < n; i++) {
            if (!dp[i]) {
                continue;
            }

            TrieNode node = root;

            // идём вперёд по строке, как по Trie
            for (int j = i; j < n; j++) {
                char c = s.charAt(j);
                node = node.children[c - 'a'];

                if (node == null) {
                    break;
                }

                // нашли слово
                if (node.isWord) {
                    dp[j + 1] = true;
                }
            }
        }

        return dp[n];
    }

    private TrieNode buildTrie(List<String> wordDict) {
        TrieNode root = new TrieNode();

        for (String word : wordDict) {
            TrieNode node = root;
            for (char c : word.toCharArray()) {
                if (node.children[c - 'a'] == null) {
                    node.children[c - 'a'] = new TrieNode();
                }
                node = node.children[c - 'a'];
            }
            node.isWord = true;
        }

        return root;
    }
/// ______________________________________________________///
    //мое первое решение
    /*

    📌 Word Break — моё решение (DFS + memo)
    1️⃣ Идея алгоритма
    Используется backtracking (DFS) по строке
    Строим разбиение слева направо
    Если зашли в тупик — делаем откат (backtrack)
    Запоминаем неудачные позиции, чтобы не проверять их повторно
    2️⃣ Состояния
    roadStatuses[i]:
      1  → позиция достижима
     -1 → из позиции нельзя дойти до конца (тупик)
      0 → ещё не обработана
    3️⃣ Доп. структура
    successfulIndexes — стек пути (индексы, куда удалось дойти)
    4️⃣ Логика
    Начинаем с 0
    Пытаемся найти слово, двигаясь вперёд
    Если нашли:
    помечаем позицию как 1
    идём дальше
    Если не нашли:
    помечаем позицию как -1
    откатываемся назад (берём предыдущий индекс из стека)
    Если стек пуст → решения нет
    5️⃣ Ключевой инсайт

    ❗ Это DFS с мемоизацией неудачных состояний
    (pruning через roadStatuses)

    6️⃣ Сложность
    Без ограничения длины слова:
    O(n³)
    С ограничением maxLen:
    O(n * L²)

    где:

    n — длина строки
    L — максимальная длина слова
    7️⃣ Проблемы решения
    ❌ substring → аллокации и замедление
    ❌ возможен heavy backtracking
    ❌ хуже предсказуемость, чем у DP
    8️⃣ Сравнение с каноном
    Метод	Подход
    DP	bottom-up
    BFS	поиск пути
    Моё решение	DFS + backtracking + memo
    9️⃣ Вывод

    ❗ Рабочее решение, но сложнее и менее эффективно, чем DP
    ❗ Хорошо демонстрирует понимание backtracking + pruning

     */

    public boolean wordBreakMy(String s, List<String> wordDict) {
        Set<String> wordSet = new HashSet<>(wordDict);
        int[] roadStatuses = new int[s.length() + 1];
        roadStatuses[0] = 1;
        LinkedList<Integer> successfulIndexes = new LinkedList<>();
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
}
