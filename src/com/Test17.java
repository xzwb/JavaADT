package com;

public class Test17 {
    public int[] exchange(int[] nums) {
        // 奇数位
        int i = 0;
        // 偶数位
        int j = 0;
        while (i < nums.length) {
            int swap = nums[j];
            nums[j] = nums[i];
            nums[i] = swap;
            i += 2;
            j++;
        }
        return nums;
    }
}
