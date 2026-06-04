package org.example;

public class ReorderList143 {

    public class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }
    }

    public void reorderList(ListNode head) {
        //if (head == null || head.next == null) return;
        //эта проверка не обязательна потому что фаст слоу проход защищен вайлом.
        //Реверс лист защищен своим вайлом

        ListNode slow = getMildeNode(head);

        // 2. Разворот второй половины
        ListNode second = reverseList(slow.next);
        slow.next = null; // обрываем первую половину

        // 3. Слияние двух половин
        ListNode first = head;
        while (second != null) {
            ListNode tmp1 = first.next;
            ListNode tmp2 = second.next;

            first.next = second;
            second.next = tmp1;

            first = tmp1;
            second = tmp2;
        }
    }

    // 1. Найти середину списка
    //если размер не парный первая половина всегда больше
    // (a + (b - 1)) / b трюк если через подсчет делать это
    private static ListNode getMildeNode(ListNode head) {
        ListNode slow = head, fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    // Разворот списка in-place
    private ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;

        while (curr != null) {
            ListNode nextTmp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = nextTmp;
        }
        return prev;
    }
}
