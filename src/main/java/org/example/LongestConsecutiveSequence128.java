package org.example;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class LongestConsecutiveSequence128 {
    public int longestConsecutive(int[] nums) {
        int length = nums.length;
        if (nums.length == 0) {
            return 0;
        }
        HashSet<Integer> hashSet = new HashSet<>(length);
        for (int num : nums) {
            hashSet.add(num);
        }
        int maxSequence = 1;
        int currentSequence = 1;
        for (int i = 0; i < length; i++) {
            int num = nums[i];
            int pivot = num - 1;
            while (hashSet.remove(pivot)) {
                currentSequence++;
                pivot--;
            }
            pivot = num + 1;
            while (hashSet.remove(pivot)) {
                currentSequence++;
                pivot++;
            }
            if (currentSequence > maxSequence) {
                maxSequence = currentSequence;
            }
            currentSequence = 1;
        }
        return maxSequence;
    }

    class UnionFind {
        private int[] parent;
        private int[] size; // размер множества

        public UnionFind(int n) {
            parent = new int[n];
            size = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                size[i] = 1;
            }
        }

        public int find(int x) {
            if (parent[x] != x) {
                parent[x] = find(parent[x]); // path compression
            }
            return parent[x];
        }

        public void union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);
            if (rootX != rootY) {
                parent[rootY] = rootX; // присоединяем одно множество к другому
                size[rootX] += size[rootY]; // обновляем размер множества
            }
        }

        public int getSize(int x) {
            return size[find(x)];
        }
    }

    public int longestConsecutiveUnionFind(int[] nums) {
        if (nums.length == 0) return 0;

        Map<Integer, Integer> map = new HashMap<>(); // число → индекс в UF
        UnionFind uf = new UnionFind(nums.length);

        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            if (map.containsKey(num)) continue; // пропускаем дубликаты
            map.put(num, i);

            // объединяем с соседями
            if (map.containsKey(num - 1)) {
                uf.union(i, map.get(num - 1));
            }
            if (map.containsKey(num + 1)) {
                uf.union(i, map.get(num + 1));
            }
        }

        int maxLen = 0;
        for (int i = 0; i < nums.length; i++) {
            maxLen = Math.max(maxLen, uf.getSize(i));
        }

        return maxLen;
    }

}
