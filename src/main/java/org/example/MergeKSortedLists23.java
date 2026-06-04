package org.example;

import java.util.Comparator;
import java.util.PriorityQueue;

public class MergeKSortedLists23 {
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

    //мин куча
    public ListNode mergeKListsMinHeap(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }

        PriorityQueue<ListNode> heap = new PriorityQueue<>(
                Comparator.comparingInt(a -> a.val)
        );

        // кладем первые элементы
        for (ListNode node : lists) {
            if (node != null) {
                heap.offer(node);//Тут конкретно правильнее было бы по моему мнению использовать add. но оставил офер как напоминание про метод для очередей
                //с ограниченным буфером
            }
        }

        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;

        while (!heap.isEmpty()) {
            ListNode minNode = heap.poll();
            tail.next = minNode;
            tail = tail.next;//двигаем хвост на теперь уже новый хвост

            if (minNode.next != null) {
                heap.offer(minNode.next);
                //Тут конкретно правильнее было бы по моему мнению использовать add. но оставил офер как напоминание про метод для очередей
                //с ограниченным буфером
            }
        }

        return dummy.next;
    }

    //Divide and conquer - сливаем попарно пока листы не закончатся. Сложность N log k - к количеству списков. н общее количество элементов.
    //На практике быстрее мин кучи хотя и сложность таже
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }

        int interval = 1;

        while (interval < lists.length) {
            for (int i = 0; i + interval < lists.length; i += interval * 2) {
                lists[i] = mergeTwoLists(lists[i], lists[i + interval]);
            }
            interval <<= 1;
        }

        return lists[0];
    }

    private ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;

        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                tail.next = l1;
                l1 = l1.next;
            } else {
                tail.next = l2;
                l2 = l2.next;
            }
            tail = tail.next;
        }

        // добавляем остаток
        if (l1 != null) {
            tail.next = l1;
        } else {
            tail.next = l2;
        }

        return dummy.next;
    }
}
