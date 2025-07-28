package org.example;

import java.util.ArrayList;
import java.util.List;

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

    //    public Node cloneGraph(Node node) {
    //        if (node == null) {
    //            return null;
    //        }
    //
    //        Node[] nodesInProcessing = new Node[101];
    //        Queue<Node> stack = new ArrayDeque<>();
    //        Node toReturn = new Node(node.val);
    //        nodesInProcessing[1] = toReturn;
    //        stack.add(node);
    //        while (!stack.isEmpty()) {
    //            Node pivot = stack.remove();
    //            int val = pivot.val;
    //            if (nodesInProcessing[val] == null) {
    //                nodesInProcessing[val] = new Node(val);
    //            }
    //            Node nodToProcess = nodesInProcessing[val];
    //
    //            for (Node current : pivot.neighbors) {
    //                int key = current.val;
    //                Node conectedNode = nodesInProcessing[key];
    //                if (conectedNode == null) {
    //                    conectedNode = new Node(key);
    //                    nodesInProcessing[key] = conectedNode;
    //                    stack.add(current);
    //                }
    //                nodToProcess.neighbors.add(conectedNode);
    //            }
    //        }
    //
    //        return toReturn;
    //    }

    /**
     * Recursive DFS deap first search оптимизирован по памяти, знаем что всего максимум 100 елементов
     *
     * @param node
     * @return
     */

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
}
