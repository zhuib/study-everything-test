package top.iaminlearn.leetcode.fib;

import lombok.extern.slf4j.Slf4j;

/**
 * Date: 2022/3/17 12:22
 */

//@Slf4j
public class Fib {

    public static void main(String[] args) {
//        int result = fib(20);
//        int result = fib1(20);
//        int result = fib2(20);
        int result = fib3(20);
        System.out.println("fib = "+result);
    }

    static int fib(int n) {
        if (n == 1 || n == 2) {
            return 1;
        }
        return fib(n-1) + fib(n-2);
    }


    static int fib1(int n) {
    // 备忘录全初始化为0
        int[] memo = new int[n+1];
        // 进行带备忘录的递归
        return helper(memo,n);
    }

    static int helper(int[] memo, int n) {
        // base case
        if (n==0 || n==1) {
            return n;
        }
        // 排除已经计算过的
        if (memo[n] != 0) return memo[n];
        memo[n] = helper(memo,n-1) + helper(memo,n-2);
        return memo[n];
    }

    static int fib2(int n) {
        if (n==0 ) return 0;
        int [] dp = new int[n +1];
        // base case
        dp[0] = 0; dp[1] = 1;
        for (int i= 2;i <= n; i++) {
            dp[i] = dp[i-1] + dp[i-2];
        }
        return dp[n];
    }

    static int fib3(int n) {
        if (n == 0 || n == 1) {
            // base case
            return n;
        }
        // 分别代表 dp[i-1] 和 dp[i-2]
        int dp_i_1 = 1, dp_i_2 = 0;
        for (int i = 2; i <= n; i++) {
            // dp[i] = dp[i-1] + dp[i-2]
            int dp_i = dp_i_1 + dp_i_2;
            // 滚动更新
            dp_i_2 = dp_i_1;
            dp_i_1 = dp_i;
        }
        return dp_i_1;
    }
}
