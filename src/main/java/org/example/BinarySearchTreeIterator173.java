package org.example;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Set;

public class BinarySearchTreeIterator173 {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    //stack-based inorder, O(h)
    static class BSTIterator {
        private Deque<TreeNode> stack = new ArrayDeque<>();
        private TreeNode curr;

        public BSTIterator(TreeNode root) {
            curr = root;
        }

        public int next() {
            // идём максимально влево
            while (curr != null) {
                stack.push(curr);
                curr = curr.left;
            }

            // берём следующий узел
            TreeNode node = stack.pop();

            // переходим в правое поддерево
            curr = node.right;

            return node.val;
        }

        public boolean hasNext() {
            return curr != null || !stack.isEmpty();
        }
    }


    //Простой но о от н по памяти.
    static class BSTIteratorArray {

        private List<Integer> inorder = new ArrayList<>();
        private int index = 0;

        public BSTIteratorArray(TreeNode root) {
            dfs(root);
        }

        private void dfs(TreeNode node) {
            if (node == null) return;

            dfs(node.left);
            inorder.add(node.val);
            dfs(node.right);
        }

        public int next() {
            return inorder.get(index++);
        }

        public boolean hasNext() {
            return index < inorder.size();
        }
    }
}
