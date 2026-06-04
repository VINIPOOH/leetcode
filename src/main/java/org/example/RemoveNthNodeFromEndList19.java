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
        // Добавляем фиктивный узел, чтобы упростить удаление головы
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        ListNode fast = dummy;
        ListNode slow = dummy;

        // Сначала сдвигаем fast на n+1 шагов вперед
        for (int i = 0; i <= n; i++) {
            fast = fast.next;
        }

        // Теперь двигаем оба указателя до конца списка
        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }

        // slow.next → узел, который нужно удалить
        slow.next = slow.next.next;

        return dummy.next; // новый head списка
    }
}
