package com.zljin.basejava.leetcode;

import java.util.*;
/**
 * 哈希
 */
public class Solution128 {
    public static void main(String[] args) {
        int[] nums = new int[]{100, 4, 200, 1, 3, 4, 2};
        System.out.println(new Solution128().longestConsecutive(nums));
    }

    /**
     * tip: 用哈希表查找这个数前面一个数是否存在，即num-1在序列中是否存在。存在那这个数肯定不是开头，直接跳过。
     *
     * @param nums
     * @return
     */
    public int longestConsecutive(int[] nums) {
        Set<Integer> numSet = new HashSet<>();

        for (int num : nums) {
            numSet.add(num);
        }

        int longest = 0;

        for (int num : numSet) {
            if (!numSet.contains(num - 1)) {
                int curNum = num;
                int curCal = 1;
                while (numSet.contains(curNum + 1)) {
                    curNum = curNum + 1;
                    curCal += 1;
                }
                longest = Math.max(longest, curCal);
            }
        }
        return longest;
    }
}
