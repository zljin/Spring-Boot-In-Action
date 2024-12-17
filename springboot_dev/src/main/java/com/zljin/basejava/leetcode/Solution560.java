package com.zljin.basejava.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * 子串
 * <p>
 * 前缀和+哈希
 */
public class Solution560 {
    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 3};
        int k = 3;
        System.out.println(new Solution560().subarraySum(nums, k));
    }


    /**
     * pre[j]: 代表前缀和，即0-j的总和
     * <p>
     * pre[j]-k: 由于当前累计和是pre[j],如果pre[j]-k 在map中，则
     * pre[j]-(pre[j]-k) = k
     * <p>
     * pre[j]-k: 对应一个起点
     * <p>
     * key: pre[j]-k value: count
     *
     * @param nums
     * @param k
     * @return 0, 1
     * 1,1
     * 3,1
     * 6,1
     * <p>
     * <p>
     * 3
     */
    public int subarraySum(int[] nums, int k) {
        int sum = 0;
        Map<Integer, Integer> retMap = new HashMap<>();
        retMap.put(0, 1);  //初始化前缀和为0的次数为1
        int pre = 0;
        for (int i = 0; i < nums.length; i++) {
            pre += nums[i];
            if (retMap.containsKey(pre - k)) {
                sum += retMap.get(pre - k);
            }
            retMap.put(pre, retMap.getOrDefault(pre, 0) + 1);
        }
        return sum;
    }
}
