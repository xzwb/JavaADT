package com;

import java.util.HashMap;
import java.util.HashSet;

public class Test8 {
    public int singleNumber(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int n : nums) {
            if (map.get(n) == null) {
                map.put(n, 1);
            } else {
                map.put(n, 2);
            }
        }
        for (int n : nums) {
            if (map.get(n) == 1) {
                return n;
            }
        }
        return 1;
    }
}
