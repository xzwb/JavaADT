package mainshi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * 三数之和
 */
public class Sum {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        // 排序
        Arrays.sort(nums);
        int len = nums.length;
        for (int i = 0; i < len; i++) {
            int l = i + 1;
            int r = len-1;
            if (nums[i] > 0) break;
            if (i > 0 && nums[i] == nums[i-1]) continue;
            while (l < r) {
                int sum = nums[i] + nums[l] + nums[r];
                if (sum == 0) {
                   result.add(Arrays.asList(nums[i], nums[l], nums[r]));
                   while (l < r && nums[l] == nums[l+1]) l++;
                   while (l < r && nums[r] == nums[r-1]) r--;
                   l++;
                   r--;
                }
                else if (sum < 0) {
                    l++;
                } else if (sum > 0) {
                    r--;
                }
            }
        }
        return result;
    }

    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target-nums[i])) {
                return new int[]{map.get(target-nums[i]), i};
            }
            map.put(nums[i], i);
        }
        return new int[0];
    }
}
