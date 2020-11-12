package leetcode;

/**
 * 给定不同面额的硬币 coins 和一个总金额 amount。编写一个函数来计算可以凑成总金额所需的最少的硬币个数。如果没有任何一种硬币组合能组成总金额，返回 -1。
 *
 * 你可以认为每种硬币的数量是无限的。
 */
public class Test10 {
    public static void main(String[] args) {
        coinChange(new int[]{1, 2, 5}, 11);
    }

    public static int coinChange(int[] coins, int amount) {
        int[] temp = new int[amount + 1];
        int length = coins.length;
        temp[0] = 0;
        for (int i = 1; i <= amount; i++) {
            temp[i] = Integer.MAX_VALUE;
            for (int j = 0; j < length; j++) {
                if ((i >= coins[j]) && (temp[i - coins[j]] != Integer.MAX_VALUE)) {
                    temp[i] = Math.min(temp[i - coins[j]] + 1, temp[i]);
                }
            }
        }
        if (temp[amount] == Integer.MAX_VALUE) {
            return -1;
        }
        return temp[amount];
    }
}
