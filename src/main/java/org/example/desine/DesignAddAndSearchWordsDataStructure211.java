package org.example.desine;

public class DesignAddAndSearchWordsDataStructure211 {

    static class WordDictionary {

        static class Node {
            Node[] leaf = new Node[26];
            boolean isWord;
        }

        private final Node root = new Node();

        public void addWord(String word) {
            Node cur = root;
            for (int i = 0; i < word.length(); i++) {
                int idx = word.charAt(i) - 'a';
                if (cur.leaf[idx] == null) {
                    cur.leaf[idx] = new Node();
                }
                cur = cur.leaf[idx];
            }
            cur.isWord = true;
        }

        public boolean search(String word) {
            return dfs(word, 0, root);
        }

        private boolean dfs(String word, int i, Node node) {
            if (node == null) {
                return false;
            }

            if (i == word.length()) {
                return node.isWord;
            }

            char c = word.charAt(i);

            if (c == '.') {
                for (Node next: node.leaf) {
                    if (dfs(word, i + 1, next)) {
                        return true;
                    }
                }
                return false;
            } else {
                int idx = c - 'a';
                return dfs(word, i + 1, node.leaf[idx]);
            }
        }
    }
}
