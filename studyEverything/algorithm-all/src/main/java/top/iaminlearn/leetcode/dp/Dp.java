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
        // ��ĿҪ������ս����dp(amount)
        return dp(coins,amount);
    }

    // ���壺Ҫ�ճ���� n�� ����Ҫdp(coins,n) ��Ӳ��
    static int dp(int[] coins, int amount) {
        // base case
        if (amount == 0) return 0;
        if (amount < 0) return -1;

        int res = Integer.MAX_VALUE;
        for (int coin : coins) {
            // ����������Ľ��
            int subProblem = dp(coins,amount -coin);
            // �������޽�������
            if (subProblem == -1) continue;
            // ����������ѡ�����Ž⣬Ȼ���һ
            res = Math.min(res,subProblem + 1);
        }
        return res == Integer.MAX_VALUE ? -1 : res;
    }

    // ===================== ����¼ ==================

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
        // dp ����ȫ������ʼ��Ϊ����ֵ
        Arrays.fill(memo,-666);
        return dp1(coins,amount);
    }

    int dp1(int[] coins,int amount) {
        // base case
        if (amount == 0) return 0;
        if (amount < 0) return -1;
        // �鱸��¼ ��ֹ�ظ�����
        if (memo[amount] != -666) {
            return memo[amount];
        }
        int res = Integer.MAX_VALUE;
        for (int coin: coins) {
            // ����������Ľ��
            int subProblem = dp1(coins,amount -coin);
            // �������޽�������
            if (subProblem == -1 ) continue;
            // ����������ѡ�����Ž⣬Ȼ���һ
            res = Math.min(res,subProblem + 1);
        }
        memo[amount] = (res == Integer.MAX_VALUE ? -1 : res);
        return memo[amount];
    }

    // ================== dp table �Ե����� ===============
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
        // ���for ѭ���ڱ�������״̬������ȡֵ
        for (int i = 0; i < dp.length; i++) {
            // �ڲ� for ѭ����������ѡ�����Сֵ
            for (int coin : coins) {
                // �������޽⣬����
                if (i - coin < 0) {
                    continue;
                }
                // 1 + dp[i -coin] ��һ ��ָ ��������ֵ�� ��Ǯ�е�һ������ amount Ϊ 3ʱ
                // �������ֵΪ dp[3]  = dp[2] + ��ֵΪ 1 ��Ǯ = dp[2] + 1
                // Ҳ������ dp[3] = dp[1] + ��ֵΪ 2 ��Ǯ = dp[1] + 1
                // �� dp[2] �����Ž�Ϊ 1�� dp[1] ���Ž�ҲΪ 1��
                // ��� amount Ϊ 3 �Ľ�����С��Ǯ�� Ϊ 2�����
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
        // ��ʼ������ dp ��СΪamount + 1��ȫ��Ԫ�س�ʼ��Ϊ -1
        int[] dp = new int[amount + 1];
        Arrays.fill(dp,-1);
        dp[0] = 0; // ���0 �����Ž� dp[0] = 0
        // ���� i�� 1 ѭ���� amount�� ���μ����� 1 ��amount �����Ž�
        for (int i= 1; i<=amount; i++) {
            // ����ÿ����� i�� ʹ�ñ��� j������ֵcoins ����
            for (int j= 0; j< coins.length ; j++) {
                //����С�ڵ��� i����ֵcoins[j] ������ i - coins[j]�����Ž�
                if (coins[j] <= i && dp[i - coins[j]] != -1) {
                    // �����ǰ��δ�������dp[i] �����ڼ�������Ž��
                    if (dp[i] == -1 || dp[i] > dp[i - coins[j]] + 1) {
                        dp[i] = dp[i - coins[j]] + 1; // ����dp[i]
                    }
                }
            }
        }
        return dp[amount]; // ����dp[amount]�����Ž�
    }
}
