package com.zljin.basejava.leetcode;

/**
 * 双指针
 */
public class Solution011 {
    public static void main(String[] args) {
        int[] arr = new int[]{1, 8, 6, 2, 5, 4, 8, 3, 7};
        System.out.println(new Solution011().maxArea(arr));
    }

    /**
     * 一句话概括：我们left++和right--都是为了尝试取到更多的水，如果短的板不动的话，取到的水永远不会比上次多。
     *
     * @param height
     * @return
     */
    public int maxArea(int[] height) {
        int left = 0;
        int right = height.length - 1;

        int result = 0;

        while (left < right) {
            int area = Math.min(height[left], height[right]) * (right - left);
            result = Math.max(result, area);

            if (height[left] < height[right]) {
                ++left;
            } else {
                --right;
            }
        }

        return result;
    }
}
