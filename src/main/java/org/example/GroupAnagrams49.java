package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//https://leetcode.com/problems/group-anagrams/description/?envType=problem-list-v2&envId=oizxjoit
public class GroupAnagrams49 {

    // сложность Время: O(n * m)
    //Память: O(n * m)
    public static List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();

        for (String word : strs) {
            int[] count = new int[26];  // массив счётчиков букв
            for (char c : word.toCharArray()) {
                count[c - 'a']++;       // увеличиваем счётчик для буквы
            }
            // получаем каноническую форму
            String key = Arrays.toString(count);
            //агрегируем по ключу
            map.computeIfAbsent(key, k -> new ArrayList<>()).add(word);
        }
        //достаем результат агрегации
        return new ArrayList<>(map.values());
    }

    //сложность Время: O(n * m log m)
    //n — количество слов, m — средняя длина слова (сортировка каждого слова)
    //Память: O(n * m) для хранения мапы и списков слов
    public static List<List<String>> groupAnagrams2(String[] strs) {
        // Мапа: ключ — отсортированная строка, значение — список анаграмм
        Map<String, List<String>> map = new HashMap<>();

        for (String word : strs) {
            char[] chars = word.toCharArray();
            Arrays.sort(chars);           // сортируем буквы в слове
            String key = new String(chars); // создаём ключ из отсортированной строки

            // добавляем слово в мапу по ключу
            map.computeIfAbsent(key, k -> new ArrayList<>()).add(word);
        }

        // возвращаем все группы анаграмм
        return new ArrayList<>(map.values());
    }

}
