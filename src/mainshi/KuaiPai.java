package mainshi;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 比较交换
 */
public class KuaiPai {

    static void kuaiPai(int[] nums, int left, int right) {
        if (left > right) {
            return;
        }
        int i = left;
        int j = right;
        int x = nums[left];
        int temp;
        while (i != j) {
            // 从右向左找
            while (nums[j] >= x && i < j) {
                j--;
            }
            while (nums[i] <= x && i < j) {
                i++;
            }
            if (i < j) {
                temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
            }
        }
        nums[left] = nums[i];
        nums[i] = x;
        kuaiPai(nums, left, i-1);
        kuaiPai(nums, i+1, right);
    }

    public static void main(String[] args) {
        int[] nums = new int[]{5, 8, 7, 9, 3, 65, 45, 15};
        kuaiPai(nums, 0, nums.length-1);
        for (int i : nums) {
            System.out.println(i);
        }
    }
}
