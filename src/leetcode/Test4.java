package leetcode;

import java.util.Arrays;

public class Test4 {
    public static void main(String[] args) {
        int[][] matrix = {
                {1, 4, 7, 11, 15},
                {2, 5, 8, 12, 19},
                {3, 6, 9, 16, 22},
                {10, 13, 14, 17, 24},
                {18, 21, 23, 26, 30}
        };
        System.out.println(findNumberIn2DArray(matrix, 8));
//        System.out.println(findNumberIn2DArray(matrix, 20));
    }

    public static boolean findNumberIn2DArray(int[][] matrix, int target) {
        for (int[] nums : matrix) {
            if (target < nums[0]) {
                return false;
            }
            if (target < nums[nums.length - 1]) {
                return binarySearch(nums, target);
            }
        }
        return false;
    }

    public static boolean binarySearch(int[] nums, int target) {
        int i = 0, j = nums.length;
        int mid = (i + j) / 2;
        while (i <= j) {
            if (target < nums[mid]) {
                j = mid - 1;
            } else if (target > nums[mid]) {
                i = mid + 1;
            }
            if (i == j && target == nums[mid]) {
                return true;
            }
            mid = (i + j) / 2;
        }
        return false;
    }

}
