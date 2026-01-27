package org.example;

public class SubtreeOfAnotherTree572 {

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

    public boolean isSubtree(TreeNode root, TreeNode subRoot) {
        if (root == null) {
            return false;
        }
        if (root.val == subRoot.val && isContainingSubTree(root, subRoot)) {
            return true;
        }
        return isSubtree(root.left, subRoot) || isSubtree(root.right, subRoot);
    }

    private boolean isContainingSubTree(TreeNode root, TreeNode subRoot) {
        if (root== null && subRoot == null){
            return true;
        }
        if (root == null || subRoot == null){
            return false;
        }

        if (root.val != subRoot.val){
            return false;
        }
        return isContainingSubTree(root.left, subRoot.left) && isContainingSubTree(root.right, subRoot.right);
    }
}
