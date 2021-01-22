package com;


import java.util.ArrayList;
import java.util.List;

public class Test11 {
    public int[] spiralOrder(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return new int[0];
        }
        int row = matrix.length;
        int col = matrix[0].length;
        int sum = row * col;
        int[] nums = new int[sum];
        // 定义边界
        int k = 0;
        int top = 0;
        int under = row - 1;
        int left = 0;
        int right = col - 1;
        while (top <= under && left <= right) {
            for (int i = left; i <= right; i++) {
                nums[k++] = matrix[top][i];
            }
            top++;
            for (int i = top; i <= under; i++) {
                nums[k++] = matrix[i][right];
            }
            right--;
            for (int i = right; i >= left && top <= under; i--) {
                nums[k++] = matrix[under][i];
            }
            under--;
            for (int i = under; i >= top && left <= right; i--) {
                nums[k++] = matrix[i][left];
            }
            left++;
        }
        return nums;
    }
}
