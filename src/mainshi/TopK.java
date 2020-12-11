package mainshi;

import java.util.PriorityQueue;

public class TopK {
//    public int findKthLargest(int[] nums, int k) {
//        quickSort(nums, 0, nums.length-1);
//        return nums[k];
//    }
//
//    void quickSort(int[] nums, int left, int right) {
//        int i = left;
//        int j = right;
//        int x = nums[i];
//        if (i > j) {
//            return;
//        }
//        while (i != j) {
//            while (nums[j] >= x && i > j) {
//                j--;
//            }
//            while (nums[i] <= x && i > j) {
//                i++;
//            }
//            nums[i] = nums[i] + nums[j];
//            nums[j] = nums[i] - nums[j];
//            nums[i] = nums[i] - nums[j];
//        }
//        nums[left] = nums[left] + nums[i];
//        nums[i] = nums[left] - nums[i];
//        nums[left] = nums[left] - nums[i];
//        quickSort(nums, left, i-1);
//        quickSort(nums, i+1, right);
//        return;
//    }

//    public int findKthLargest(int[] nums, int k) {
//        k = nums.length - k;//注意这里的k已经变了
//        int lo = 0, hi = nums.length - 1;
//        while (lo <= hi) {
//            int i = lo;
//            //这里把数组以A[lo]的大小分为两部分，
//            //一部分是小于A[lo]的，一部分是大于A[lo]的
//            // [lo,i]<A[lo]，[i+1,j)>=A[lo]
//            for (int j = lo + 1; j <= hi; j++)
//                if (nums[j] < nums[lo])
//                    swap(nums, j, ++i);
//            swap(nums, lo, i);
//            if (k == i)
//                return nums[i];
//            else if (k < i)
//                hi = i - 1;
//            else
//                lo = i + 1;
//        }
//        return -1;
//    }
//
//    private void swap(int[] nums, int i, int j) {
//        if (i != j) {
//            nums[i] ^= nums[j];
//            nums[j] ^= nums[i];
//            nums[i] ^= nums[j];
//        }
//    }

    public int findKthLargest(int[] nums, int k) {
        final PriorityQueue<Integer> queue = new PriorityQueue<>();
        for (int val : nums) {
            queue.add(val);
            if (queue.size() > k)
                queue.poll();
        }
        return queue.peek();
    }
}
