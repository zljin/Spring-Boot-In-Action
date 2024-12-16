package com.zljin.basejava.leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * 滑动窗口
 *
 * 解题模板：
 * //外层循环扩展右边界，内层循环扩展左边界
 * for (int l = 0, r = 0 ; r < n ; r++) {
 * 	//当前考虑的元素
 * 	while (l <= r && check()) {//区间[left,right]不符合题意
 *         //扩展左边界
 *     }
 *     //区间[left,right]符合题意，统计相关信息
 * }
 */
public class Solution003 {

    public static void main(String[] args) {
        String s = "abcabcbb";
        System.out.println(new Solution003().lengthOfLongestSubstring(s));
    }

    public int lengthOfLongestSubstring(String s) {
        //拆解主String
        char[] charArray = s.toCharArray();
        //结果存放
        int ret = 0;
        //去重
        Set<Character> charSet = new HashSet<>();
        for (int left = 0, right = 0; right < s.length(); right++) {
            char ch = charArray[right];
            while(charSet.contains(ch)){
                charSet.remove(charArray[left]);
                left++;
            }

            charSet.add(ch);
            ret = Math.max(ret,right-left+1);

        }
        return ret;
    }
}
