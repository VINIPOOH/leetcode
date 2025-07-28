package org.example;

//You are given two non-empty linked lists representing two non-negative
//integers. The digits are stored in reverse order, and each of their nodes contains a
//single digit. Add the two numbers and return the sum as a linked list.
//
// You may assume the two numbers do not contain any leading zero, except the
//number 0 itself.
//
//
// Example 1:
//
//
//Input: l1 = [2,4,3], l2 = [5,6,4]
//Output: [7,0,8]
//Explanation: 342 + 465 = 807.
//
//
// Example 2:
//
//
//Input: l1 = [0], l2 = [0]
//Output: [0]
//
//
// Example 3:
//
//
//Input: l1 = [9,9,9,9,9,9,9], l2 = [9,9,9,9]
//Output: [8,9,9,9,0,0,0,1]
//
//
//
// Constraints:
//
//
// The number of nodes in each linked list is in the range [1, 100].
// 0 <= Node.val <= 9
// It is guaranteed that the list represents a number that does not have
//leading zeros.
//
//
// Related Topics Linked List Math Recursion 👍 34034 👎 6818

//leetcode submit region begin(Prohibit modification and deletion)

public class AddTwoNumbers2 {

    static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        ListNode toReturn = new ListNode();
        ListNode currentNode = toReturn;
        int overwhelmed = 0;
        do {
            if (l1.next == null) {
                overwhelmed += l1.val;
                processWhenOneListEnded(l2, overwhelmed, currentNode);
                return toReturn;
            } else if (l2.next == null) {
                overwhelmed += l2.val;
                processWhenOneListEnded(l1, overwhelmed, currentNode);
                return toReturn;
            } else {
                int sum = getSum(l1, l2, overwhelmed);
                overwhelmed = processSumNode(sum, currentNode);
                l1 = l1.next;
                l2 = l2.next;
                currentNode = createNewNodStep(currentNode);
            }
        }
        while (true);

    }

    private static void processWhenOneListEnded(ListNode listThatMaySteelHaveElements, int overwhelmed, ListNode currentNode) {
        while (listThatMaySteelHaveElements != null) {
            int sum = listThatMaySteelHaveElements.val + overwhelmed;
            overwhelmed = processSumNode(sum, currentNode);
            if (listThatMaySteelHaveElements.next == null) {
                if (overwhelmed > 0) {
                    currentNode = createNewNodStep(currentNode);
                    currentNode.val = overwhelmed;
                }
                break;
            }
            listThatMaySteelHaveElements = listThatMaySteelHaveElements.next;
            currentNode = createNewNodStep(currentNode);
        }
    }

    private static ListNode createNewNodStep(ListNode currentNode) {
        ListNode newNode = new ListNode();
        currentNode.next = newNode;
        currentNode = newNode;
        return currentNode;
    }

    private static int processSumNode(int sum, ListNode currentNode) {
        currentNode.val = sum % 10;
        return sum / 10;
    }

    private static int getSum(ListNode l1, ListNode l2, int overwhelmed) {
        return l1.val + l2.val + overwhelmed;
    }

}

//leetcode submit region end(Prohibit modification and deletion)

