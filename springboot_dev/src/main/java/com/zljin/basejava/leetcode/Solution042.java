package com.zljin.basejava.leetcode;

/**
 * 双指针
 */
public class Solution042 {
    public static void main(String[] args) {
        int[] height = new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        System.out.println(new Solution042().trap(height));
    }


    public int trap(int[] height) {
        int l = height.length;
        // 求出最高点的下标值
        int max = height[0], maxi = 0;
        for (int i = 1; i < l; i++) {
            if (height[i] > max) {
                max = height[i];
                maxi = i;
            }
        }
        int Amount = 0;
        int leftMax = 0;
        // 求出到最高点的左边的雨水
        for (int i = 0; i < maxi; i++) {
            if (height[i] > leftMax) {
                leftMax = height[i];
            } else {
                Amount += leftMax - height[i];
            }
        }
        // 求出到最高点的右边的雨水
        int rightMax = 0;
        for (int i = l - 1; i > maxi; i--) {
            if (height[i] > rightMax) {
                rightMax = height[i];
            } else {
                Amount += rightMax - height[i];
            }
        }
        return Amount;
    }
}
