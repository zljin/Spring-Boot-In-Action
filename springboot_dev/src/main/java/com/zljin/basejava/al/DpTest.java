package com.zljin.basejava.al;

/**
 * 动态规划
 */
public class DpTest {
    public static void main(String[] args) {
        o1Package();
    }

    /**
     * https://github.com/labuladong/fucking-algorithm/blob/master/%E5%8A%A8%E6%80%81%E8%A7%84%E5%88%92%E7%B3%BB%E5%88%97/%E8%83%8C%E5%8C%85%E9%97%AE%E9%A2%98.md
     * 0 1背包问题
     * 状态和选择
     */
    public static void o1Package() {
        int n = 3;//3个物品
        int w = 4;//背包共装4g
        int[] wt = new int[]{2, 1, 3};
        int[] val = new int[]{4, 2, 3};
        System.out.println(maxValue(n, w, wt, val));
    }

    private static int maxValue(int n, int w, int[] wt, int[] val) {

        int[][] dp = new int[n + 1][w + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= w; j++) {
                if (j - wt[i - 1] < 0) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - wt[i - 1]] + val[i - 1]);
                }
            }
        }
        return dp[n][w];
    }
}
