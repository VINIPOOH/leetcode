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

    //тут мы ищем ноды для старта проверки
    public boolean isSubtree(TreeNode root, TreeNode subRoot) {
        if (root == null) {
            return false;
        }
        if (root.val == subRoot.val && areIdentical(root, subRoot)) {
            return true;
        }
        return isSubtree(root.left, subRoot) || isSubtree(root.right, subRoot);
    }

    //тут мы проверяем что вырастает ли из заданной ноды нужное дерево
    private boolean areIdentical(TreeNode root, TreeNode subRoot) {
        if (root== null && subRoot == null){
            return true;
        }
        if (root == null || subRoot == null){
            return false;
        }

        if (root.val != subRoot.val){
            return false;
        }
        return areIdentical(root.left, subRoot.left) && areIdentical(root.right, subRoot.right);
    }
}
