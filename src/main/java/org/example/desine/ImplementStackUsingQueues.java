package org.example.desine;

import java.util.LinkedList;
import java.util.Queue;

public class ImplementStackUsingQueues {

    // вставка О(н) достать О(1)
    class MyStack {

        private Queue<Integer> queue = new LinkedList<>();

        public void push(int x) {
            queue.offer(x);

            int size = queue.size();

            //Крутим н - 1 раз. Потому что новый элемент уже на месте его крутить не нужно.
            for (int i = 0; i < size - 1; i++) {
                queue.offer(queue.poll());
            }
        }

        public int pop() {
            return queue.poll();
        }

        public int top() {
            return queue.peek();
        }

        public boolean empty() {
            return queue.isEmpty();
        }
    }

    //вставка зха о(1) достать О(н)
    class MyStack1 {

        Queue<Integer> mainQueue = new LinkedList<>();
        Queue<Integer> helperQueue = new LinkedList<>();

        public void push(int value) {
            mainQueue.offer(value);
        }

        public int pop() {
            while (mainQueue.size() > 1) {
                helperQueue.offer(mainQueue.poll());
            }

            int topElement = mainQueue.poll();

            Queue<Integer> temp = mainQueue;
            mainQueue = helperQueue;
            helperQueue = temp;

            return topElement;
        }

        public int top() {
            while (mainQueue.size() > 1) {
                helperQueue.offer(mainQueue.poll());
            }

            int topElement = mainQueue.peek();
            helperQueue.offer(mainQueue.poll());

            Queue<Integer> temp = mainQueue;
            mainQueue = helperQueue;
            helperQueue = temp;

            return topElement;
        }

        public boolean empty() {
            return mainQueue.isEmpty();
        }
    }
}
