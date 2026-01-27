package org.example;

public class ReverseLinkedList206 {

    public class ListNode {
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

    public ListNode reverseList(ListNode head) {
        //(tmpHead) (next) ()
        //(next) (tmpHead)
        if (head == null) {
            return head;
        }
        ListNode tmpHead = head;
        ListNode tmpNext = head.next;
        head.next = null;
        while (tmpNext != null) {
            ListNode newTmpNext = tmpNext.next;
            tmpNext.next = tmpHead;
            tmpHead = tmpNext;
            tmpNext = newTmpNext;
        }

        return tmpHead;
    }

    public ListNode reverseList1(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;

        while (curr != null) {
            ListNode nextTemp = curr.next; // сохраняем ссылку на следующий
            curr.next = prev;              // переворачиваем текущий узел
            prev = curr;                   // сдвигаем prev
            curr = nextTemp;               // сдвигаем curr
        }

        return prev; // prev — новый head
    }

    public ListNode reverseListRecursion(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode newHead = reverseListRecursion(head.next); // переворачиваем хвост
        head.next.next = head; // текущий узел ставим после следующего
        head.next = null;      // разрываем старую ссылку

        return newHead; // возвращаем новый head всей перевёрнутой части
    }
}
