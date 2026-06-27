package org.example.desine;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class LRUCache146 {
    private Map<Integer, Integer> cash;

    public LRUCache146(int capacity) {
        cash = new LinkedHashMap<Integer, Integer>(capacity, 0.75F, true) {

            //Этот ремув вызывается после пут. Потому проверка должна быть +1 один. Соответственно больше, а не ==.
            @Override
            protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
                return size() > capacity;
            }
        };
    }

    public int get(int key) {
        return cash.get(key) == null ? -1 : cash.get(key);
    }

    public void put(int key, int value) {
        cash.put(key, value);
    }
}

//classic algorithmic solution мое решение до проверки
//class LRUCache {
//    private Node head;
//    private Node tail;
//    private Map<Integer, Node> cash;
//    private int maxCapacity;
//
//    private static class Node {
//        Node left;
//        Node right;
//        Integer value;
//        Integer key;
//
//    }
//
//    public LRUCache(int capacity) {
//        this.cash = new HashMap<>(capacity);
//        maxCapacity = capacity;
//    }
//
//    public int get(int key) {
//        Node node = cash.get(key);
//        if (node == null) {
//            return -1;
//        }
//
//        moveNodeToStart(node);
//        return node.value;
//    }
//
//    public void put(int key, int value) {
//        Node node = cash.get(key);
//        if (node != null) {
//            node.value = value;
//            moveNodeToStart(node);
//            return;
//        }
//
//        prune();
//        Node newNode = new Node();
//        cash.put(key, newNode);
//        newNode.value = value;
//        newNode.key = key;
//
//        addNewNodeToList(newNode);
//    }
//
//    private void addNewNodeToList(Node newNode) {
//        if (head==null){
//            head = newNode;
//            tail = newNode;
//            return;
//        }
//        newNode.right = head;
//        head.left = newNode;
//        head = newNode;
//    }
//
//    private void prune() {
//        if (maxCapacity == cash.size()) {
//            cash.remove(tail.key);
//            if (head == tail){
//                head = null;
//                tail = null;
//                return;
//            }
//            tail = tail.left;
//            tail.right = null;
//        }
//    }
//
//    private void moveNodeToStart(Node toMove) {
//        if (toMove == head) {
//            return;
//        }
//        toMove.left.right = toMove.right;
//        if (toMove == tail) {
//            tail = toMove.left;
//        } else {
//            toMove.right.left = toMove.left;
//        }
//        toMove.right = head;
//        head.left = toMove;
//        head = toMove;
//        toMove.left = null;
//    }
//}


//каноническое решение
class LRUCache {

    private static class Node {
        int key;
        int value;
        Node prev;
        Node next;
    }

    private final int capacity;
    private final Map<Integer, Node> cache;

    // sentinel nodes (dummy head/tail)
    //теперь не нужны проверки на случаи если они нул. потому что они изначально есть и всегда будут.
    private final Node head;
    private final Node tail;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.cache = new HashMap<>(capacity);

        head = new Node();
        tail = new Node();

        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        Node node = cache.get(key);
        if (node == null) {
            return -1;
        }
        moveToFront(node);
        return node.value;
    }

    public void put(int key, int value) {
        Node node = cache.get(key);

        if (node != null) {
            node.value = value;
            moveToFront(node);
            return;
        }
        if (cache.size() == capacity) {
            evictLRU();
        }

        Node newNode = new Node();
        newNode.key = key;
        newNode.value = value;

        cache.put(key, newNode);
        addToFront(newNode);
    }

    // ---------------- core operations ----------------

    private void moveToFront(Node node) {
        remove(node);
        addToFront(node);
    }

    private void addToFront(Node node) {
        node.prev = head;
        node.next = head.next;

        head.next.prev = node;
        head.next = node;
    }

    private void remove(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    private void evictLRU() {
        Node lru = tail.prev;
        remove(lru);
        cache.remove(lru.key);
    }
}
