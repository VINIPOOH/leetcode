package org.example;

import java.util.ArrayList;
import java.util.List;

public class WordSearchII212 {

    private static class TrieNode {
        TrieNode[] children = new TrieNode[26];
        //храним строку что б не хранить путь закапывания как нужно было бы делать с флагом.
        // еще можно было бы реализовать дерево ссылками на родителя, что бы собрать слово потом.
        // Еще вариант хранить индекс слова в масиве тут. А если слова такого нет то -1.
        String word;
    }

    public List<String> findWords(char[][] board, String[] words) {

        TrieNode trieRoot = buildTrie(words);

        List<String> foundWords = new ArrayList<>();

        for (int row = 0; row < board.length; row++) {
            for (int column = 0; column < board[0].length; column++) {
                search(board, row, column, trieRoot, foundWords);
            }
        }

        return foundWords;
    }

    private void search(
            char[][] board,
            int row,
            int column,
            TrieNode trieNode,
            List<String> foundWords) {

        if (row < 0 ||
                column < 0 ||
                row >= board.length ||
                column >= board[0].length) {
            return;
        }

        char currentLetter = board[row][column];

        if (currentLetter == '#') {
            return;
        }

        TrieNode nextTrieNode =
                trieNode.children[currentLetter - 'a'];

        if (nextTrieNode == null) {
            return;
        }

        if (nextTrieNode.word != null) {
            foundWords.add(nextTrieNode.word);
            nextTrieNode.word = null;
        }

        board[row][column] = '#';

        search(board, row + 1, column, nextTrieNode, foundWords);
        search(board, row - 1, column, nextTrieNode, foundWords);
        search(board, row, column + 1, nextTrieNode, foundWords);
        search(board, row, column - 1, nextTrieNode, foundWords);

        board[row][column] = currentLetter;
    }

    private TrieNode buildTrie(String[] words) {

        TrieNode trieRoot = new TrieNode();

        for (String word : words) {

            TrieNode currentNode = trieRoot;

            for (char letter : word.toCharArray()) {

                int alphabetIndex = letter - 'a';

                if (currentNode.children[alphabetIndex] == null) {
                    currentNode.children[alphabetIndex] = new TrieNode();
                }

                currentNode = currentNode.children[alphabetIndex];
            }

            currentNode.word = word;
        }

        return trieRoot;
    }
}
