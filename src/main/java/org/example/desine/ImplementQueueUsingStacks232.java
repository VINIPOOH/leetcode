package org.example.desine;

import java.util.Stack;

public class ImplementQueueUsingStacks232 {

    class MyQueue {
        Stack<Integer> inStack = new Stack<>();
        Stack<Integer> outStack = new Stack<>();

        public void push(int x) {
            inStack.push(x);
        }

        public int pop() {
            moveIfNeeded();
            return outStack.pop();
        }

        public int peek() {
            moveIfNeeded();
            return outStack.peek();
        }

        public boolean empty() {
            return inStack.isEmpty() && outStack.isEmpty();
        }

        private void moveIfNeeded() {
            if (outStack.isEmpty()) {
                while (!inStack.isEmpty()) {
                    outStack.push(inStack.pop());
                }
            }
        }
    }
}
