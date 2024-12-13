package com.zljin.basejava.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * 哈希
 */
public class Solution001 {
    public static void main(String[] args) {
        int[] nums = new int[]{2, 7, 11, 15};
        int target = 22;
        int[] values = new Solution001().twoSum(nums, target);
        for (int i = 0; i < values.length; i++) {
            System.out.println(values[i]);
        }
    }

    public int[] twoSum(int[] nums, int target) {
        //(value,index)
        Map<Integer, Integer> resultMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (resultMap.containsKey(target - nums[i])) {
                return new int[]{resultMap.get(target - nums[i]), i};
            }
            resultMap.put(nums[i], i);
        }
        return new int[]{};
    }
}
