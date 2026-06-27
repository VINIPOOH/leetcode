package org.example;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class CloneGraph133 {

    class Node {
        public int val;
        public List<Node> neighbors;

        public Node() {
            val = 0;
            neighbors = new ArrayList<Node>();
        }

        public Node(int _val) {
            val = _val;
            neighbors = new ArrayList<Node>();
        }

        public Node(int _val, ArrayList<Node> _neighbors) {
            val = _val;
            neighbors = _neighbors;
        }
    }

    /**
     * итеративно в глубину
     * @param node
     * @return
     */

        public Node cloneGraphIterativeDFS(Node node) {
            if (node == null) {
                return null;
            }

            Node[] nodesInProcessing = new Node[101];
            Queue<Node> stack = new ArrayDeque<>();
            Node toReturn = new Node(node.val);
            nodesInProcessing[1] = toReturn;
            stack.add(node);
            while (!stack.isEmpty()) {
                Node pivot = stack.remove();
                int val = pivot.val;
                if (nodesInProcessing[val] == null) {
                    nodesInProcessing[val] = new Node(val);
                }
                Node nodToProcess = nodesInProcessing[val];

                for (Node current : pivot.neighbors) {
                    int key = current.val;
                    Node conectedNode = nodesInProcessing[key];
                    if (conectedNode == null) {
                        conectedNode = new Node(key);
                        nodesInProcessing[key] = conectedNode;
                        stack.add(current);
                    }
                    nodToProcess.neighbors.add(conectedNode);
                }
            }

            return toReturn;
        }

    /**
     * Recursive DFS deap first search оптимизирован по памяти, знаем что всего максимум 100 елементов
     *
     * @param node
     * @return
     */

    //если бы в задаче не было условий что узлов не более 101 и все уникальны то нужно было бы использовать IdentityHashMap. Ключем были бы уже обработаные оригинальные ноды
    //а значением новые ноды (их клоны)
    public Node cloneGraph(Node node) {
        return node == null ? null : processNodesInDeap(node, new Node[101]);
    }

    private Node processNodesInDeap(Node nodeToClone, Node[] nodesInProcessing) {
        int preprocessingValue = nodeToClone.val;
        if (nodesInProcessing[preprocessingValue] != null) {
            return nodesInProcessing[preprocessingValue];
        }

        Node newClone = new Node(preprocessingValue);
        nodesInProcessing[preprocessingValue] = newClone;

        for (Node current : nodeToClone.neighbors) {
            newClone.neighbors.add(processNodesInDeap(current, nodesInProcessing));
        }
        return newClone;
    }


    //Поиск в ширину
    public Node cloneGraphWFS(Node node) {

        if (node == null) {
            return null;
        }

        Map<Node, Node> originalToClone = new HashMap<>();
        Queue<Node> queue = new LinkedList<>();

        Node clonedStart = new Node(node.val);
        originalToClone.put(node, clonedStart);

        queue.offer(node);

        while (!queue.isEmpty()) {

            Node currentOriginal = queue.poll();
            Node currentClone = originalToClone.get(currentOriginal);

            for (Node neighbor : currentOriginal.neighbors) {

                if (!originalToClone.containsKey(neighbor)) {

                    Node clonedNeighbor = new Node(neighbor.val);

                    originalToClone.put(neighbor, clonedNeighbor);
                    queue.offer(neighbor);
                }

                currentClone.neighbors.add(
                        originalToClone.get(neighbor)
                );
            }
        }

        return clonedStart;
    }
}
