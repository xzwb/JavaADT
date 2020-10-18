package leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 官方的解法
 * 用set集合去存数组中的元素遇到不能存的返回 妙啊
 * 遍历数组一遍。使用哈希集合（HashSet），添加元素的时间复杂度为 O(1)，故总的时间复杂度是 O(n)。
 * 空间复杂度：O(n)。不重复的每个元素都可能存入集合，因此占用 O(n) 额外空间。
 * class Solution {
 *     public int findRepeatNumber(int[] nums) {
 *         Set<Integer> set = new HashSet<Integer>();
 *         int repeat = -1;
 *         for (int num : nums) {
 *             if (!set.add(num)) {
 *                 repeat = num;
 *                 break;
 *             }
 *         }
 *         return repeat;
 *     }
 * }
 */
public class Test3 {
    public static void main(String[] args) {
        int[] nums = {0, 1, 2, 3, 4, 5, 1};
        System.out.println(findRepeatNumber(nums));
        int[] nums1 = new int[10];
        nums1[0] = 1;
        nums1[1] = 2;
        nums1[2] = 1;
        System.out.println(findRepeatNumber(nums1));
    }

    public static int findRepeatNumber(int[] nums) {
        System.out.println(nums.length);
        List<Integer> list = new ArrayList<>();
        for (int num : nums) {
            list.add(num);
        }
        Collections.sort(list);
        int length = list.size();
        for (int i = 0; i < length - 1; i++) {
            if (list.get(i).compareTo(list.get(i+1)) == 0) {
                return list.get(i);
            }
        }
        return 0;
    }
}
