package com;

public class Test7 {
    public int[] singleNumbers(int[] nums) {
        //记录a^b的结果
        int ret = 0;
        for (int n : nums) {
            ret ^= n;
        }
        int div = 1;
        // 选取一个不为0的位将a，b分到不同的组
        while ((div & ret) == 0) {
            div <<= 1;
        }
        int a = 0, b = 0;
        for (int n : nums) {
            if ((div & n) == 0) {
                a ^= n;
            } else {
                b ^= n;
            }
        }

        return new int[]{a, b};
    }
}
