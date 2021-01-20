package com;

public class Test5 {
    public int numWays(int n) {
        int sum = 0, a = 1, b = 1;
        for (int i = 0; i < n; i++) {
            sum = (a + b) % 1000000007;
            a = b;
            b = sum;
        }
        return a;
    }
}
