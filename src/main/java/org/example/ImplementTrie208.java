package org.example;

public class ImplementTrie208 {
}


//мое первое решение написал рекурсивно
class Trie {
    Trie[] lists;
    boolean isWord;

    public Trie() {
        lists = new Trie[26];
    }

    public void insert(String word) {
        if (word.isEmpty()) {
            isWord = true;
            return;
        }
        int currentLetter = getCurrentLetterIndex(word);
        String newWord = word.substring(1);
        Trie trie = lists[currentLetter] == null ? new Trie() : lists[currentLetter];
        lists[currentLetter] = trie;
        trie.insert(newWord);
    }

    public boolean search(String word) {
        if (word.isEmpty()) {
            return isWord;
        }
        Trie list = lists[getCurrentLetterIndex(word)];
        if (list==null){
            return false;
        }
        return list.search(word.substring(1));
    }

    public boolean startsWith(String prefix) {
        if (prefix.isEmpty()) {
            return true;
        }
        Trie list = lists[getCurrentLetterIndex(prefix)];
        if (list==null){
            return false;
        }
        return list.startsWith(prefix.substring(1));
    }

    private int getCurrentLetterIndex(String word){
        return word.charAt(0) - 'a';
    }

    // итеративное решение более приемлемое для собеседований
    class Trie1 {
        Trie1[] children = new Trie1[26];
        boolean isWord;

        public void insert(String word) {
            Trie1 node = this;

            for (char c : word.toCharArray()) {
                int i = c - 'a';

                if (node.children[i] == null) {
                    node.children[i] = new Trie1();
                }

                node = node.children[i];
            }

            node.isWord = true;
        }

        public boolean search(String word) {
            Trie1 node = this;

            for (char c : word.toCharArray()) {
                int i = c - 'a';

                if (node.children[i] == null) {
                    return false;
                }

                node = node.children[i];
            }

            return node.isWord;
        }

        public boolean startsWith(String prefix) {
            Trie1 node = this;

            for (char c : prefix.toCharArray()) {
                int i = c - 'a';

                if (node.children[i] == null) {
                    return false;
                }

                node = node.children[i];
            }

            return true;
        }
    }
}
