package org.example;

public class RemoveNthNodeFromEndList19 {

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

    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode firstDummy = new ListNode(1, head);
        ListNode pointerBeforPotentialNodeToremoov = firstDummy;
        ListNode currentPointer = head;
        for (int i = 1; i < n; i++) {
            currentPointer = currentPointer.next;
        }
        while (currentPointer.next != null) {
            currentPointer = currentPointer.next;
            pointerBeforPotentialNodeToremoov = pointerBeforPotentialNodeToremoov.next;
        }
        pointerBeforPotentialNodeToremoov.next = pointerBeforPotentialNodeToremoov.next.next;
        return firstDummy.next;
    }
}
