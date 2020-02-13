package com.xzwb.test;

import com.xzwb.stack.Stack;

public class StackTest {
    public static void main(String[] args) {
        Stack<String> stringStack = new Stack<>();
        System.out.println(stringStack.pull());
        stringStack.push("elele");
        stringStack.push("hahah");
        System.out.println(stringStack.pull());
        System.out.println(stringStack.pull());
    }
}
