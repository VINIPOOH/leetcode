package org.example;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

public class ValidParentheses20 {

    //оптимизированое решение
    public boolean isValidSwitch(String s) {
        Stack<Character> stack = new Stack<>();

        for (char c : s.toCharArray()) {
            switch (c) {
                //записываем противоположные дужки, что б упростить потом проверку
                case '(': stack.push(')'); break;
                case '{': stack.push('}'); break;
                case '[': stack.push(']'); break;
                default:
                    // если стек пуст или символ не совпадает с ожидаемым закрывающим
                    if (stack.isEmpty() || stack.pop() != c) {
                        return false;
                    }
            }
        }

        // в конце стек должен быть пустой
        return stack.isEmpty();
    }

    //мое решение
    public boolean isValid(String s) {
        Deque<Character> stack = new ArrayDeque<>();

        for (char c : s.toCharArray()) {
            if (c == '(' || c == '{' || c == '[') {
                stack.push(c);
            } else {
                if (stack.isEmpty()) {
                    return false;
                }
                char top = stack.pop();
                if ((c == ')' && top != '(') ||
                        (c == '}' && top != '{') ||
                        (c == ']' && top != '[')) {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }
}
