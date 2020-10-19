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
//        int[][] matrix = {{-5}};
        System.out.println(findNumberIn2DArray(matrix, -5));
        System.out.println(findNumberIn2DArray(matrix, 20));
    }

    public static boolean findNumberIn2DArray(int[][] matrix, int target) {
        for (int[] nums : matrix) {
            if (nums.length == 0) {
                return false;
            }
            if (target < nums[0]) {
                return false;
            }
            if (target <= nums[nums.length - 1]) {
                if (binarySearch(nums, target)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean binarySearch(int[] nums, int target) {
        int left = 0;
        int right = nums.length;
        int mid = (left + right) / 2;
        while (left <= right) {
            if (target == nums[mid]) {
                return true;
            } else if (target < nums[mid]) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
            mid = (left + right) / 2;
        }
        return false;
    }

}
