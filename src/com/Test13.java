package com;

import java.util.Deque;
import java.util.LinkedList;

public class Test13 {
    public boolean validateStackSequences(int[] pushed, int[] popped) {
        Deque<Integer> deque = new LinkedList<>();
        int k = 0;
        for (int i = 0; i < pushed.length; i++) {
            deque.push(pushed[i]);
            while (!deque.isEmpty() && deque.peek() == popped[k]) {
                deque.pop();
                k++;
            }
        }
        if (k < popped.length) {
            return false;
        } else {
            return true;
        }
    }
}
