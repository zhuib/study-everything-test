package top.iaminlearn.leetcode.dp;

import org.junit.Test;

import java.util.Arrays;

/**
 * Date: 2022/3/17 14:13
 */
public class Dp {

    public static void main(String[] args) {
        int[] coins = {1,2,5};
        int amount = 11;
        int coinChange = coinChange2(coins, amount);
        System.out.println(coinChange);
    }

    static int coinChange(int[] coins, int amount) {
        // 题目要求的最终结果是dp(amount)
        return dp(coins,amount);
    }

    // 定义：要凑出金额 n， 至少要dp(coins,n) 个硬币
    static int dp(int[] coins, int amount) {
        // base case
        if (amount == 0) return 0;
        if (amount < 0) return -1;

        int res = Integer.MAX_VALUE;
        for (int coin : coins) {
            // 计算子问题的结果
            int subProblem = dp(coins,amount -coin);
            // 子问题无解则跳过
            if (subProblem == -1) continue;
            // 在子问题中选择最优解，然后加一
            res = Math.min(res,subProblem + 1);
        }
        return res == Integer.MAX_VALUE ? -1 : res;
    }

    // ===================== 备忘录 ==================

    @Test
    public void memoDp() {
        int[] coins = {1,2,5};
        int amount = 11;
//        int coinChange = coinChange(coins, amount);
        int coinChange = coinChange1(coins, amount);
        System.out.println(coinChange);
    }
    int[] memo;
    int coinChange1(int[] coins, int amount) {
        memo = new int[amount +1];
        // dp 数组全部都初始化为特殊值
        Arrays.fill(memo,-666);
        return dp1(coins,amount);
    }

    int dp1(int[] coins,int amount) {
        // base case
        if (amount == 0) return 0;
        if (amount < 0) return -1;
        // 查备忘录 防止重复计算
        if (memo[amount] != -666) {
            return memo[amount];
        }
        int res = Integer.MAX_VALUE;
        for (int coin: coins) {
            // 计算子问题的结果
            int subProblem = dp1(coins,amount -coin);
            // 子问题无解则跳过
            if (subProblem == -1 ) continue;
            // 在子问题中选择最优解，然后加一
            res = Math.min(res,subProblem + 1);
        }
        memo[amount] = (res == Integer.MAX_VALUE ? -1 : res);
        return memo[amount];
    }

    // ================== dp table 自底向上 ===============
    @Test
    public void dpTable() {
        int[] coins = {1,2,5};
        int amount = 11;
        int coinChange = coinChange2(coins, amount);
        System.out.println(coinChange);
    }
    static int coinChange2(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        Arrays.fill(dp,amount + 1);

        // base case
        dp[0] = 0;
        // 外层for 循环在遍历所有状态的所有取值
        for (int i = 0; i < dp.length; i++) {
            // 内层 for 循环在求所有选择的最小值
            for (int coin : coins) {
                // 子问题无解，跳过
                if (i - coin < 0) {
                    continue;
                }
                // 1 + dp[i -coin] 加一 是指 单独的面值即 零钱中的一个，如 amount 为 3时
                // 如果是面值为 dp[3]  = dp[2] + 面值为 1 零钱 = dp[2] + 1
                // 也可以是 dp[3] = dp[1] + 面值为 2 零钱 = dp[1] + 1
                // 而 dp[2] 的最优解为 1； dp[1] 最优解也为 1；
                // 因此 amount 为 3 的金额的最小零钱数 为 2个组成
                dp[i] = Math.min(dp[i], 1 + dp[i -coin]);
            }
        }
        return (dp[amount] == amount + 1) ? -1 : dp[amount];
    }

    // =============== dp table ======================

    @Test
    public void dp() {
        int[] coins = {1,2,5};
        int amount = 11;
        System.out.println(coinChange3(coins, amount));
    }

    int coinChange3(int[] coins, int amount) {
        // 初始化数组 dp 大小为amount + 1，全部元素初始化为 -1
        int[] dp = new int[amount + 1];
        Arrays.fill(dp,-1);
        dp[0] = 0; // 金额0 的最优解 dp[0] = 0
        // 变量 i从 1 循环至 amount， 依次计算金额 1 至amount 的最优解
        for (int i= 1; i<=amount; i++) {
            // 对于每个金额 i， 使用变量 j遍历面值coins 数组
            for (int j= 0; j< coins.length ; j++) {
                //所有小于等于 i的面值coins[j] 如果金额 i - coins[j]有最优解
                if (coins[j] <= i && dp[i - coins[j]] != -1) {
                    // 如果当前金额还未计算或者dp[i] 比正在计算的最优解大
                    if (dp[i] == -1 || dp[i] > dp[i - coins[j]] + 1) {
                        dp[i] = dp[i - coins[j]] + 1; // 更新dp[i]
                    }
                }
            }
        }
        return dp[amount]; // 返回dp[amount]的最优解
    }
}
