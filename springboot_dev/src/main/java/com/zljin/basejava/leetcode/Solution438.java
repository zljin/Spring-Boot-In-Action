package com.zljin.basejava.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 滑动窗口
 */
public class Solution438 {
    public static void main(String[] args) {
        String s = "cbaebabacd";
        String p = "abc";
        System.out.println(new Solution438().findAnagrams(s, p));
    }

    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> ans = new ArrayList<>();
        if (s.length() < p.length()) {
            return ans;
        }
        //建立两个数组存放字符串中字母出现的词频，并以此作为标准比较
        int[] sCount = new int[26];
        int[] pCount = new int[26];

        for (int i = 0; i < p.length(); i++) {
            sCount[s.charAt(i) - 'a']++; //记录s中前pLen个字母的词频
            pCount[s.charAt(i) - 'a']++;
        }

        //判断放置处是否有异位词
        if (Arrays.equals(sCount, pCount)) {
            ans.add(0);
        }


        for (int i = 0; i < s.length() - p.length(); i++) { //i是滑动前的首位
            sCount[s.charAt(i) - 'a']--;//将滑动前首位的词频删去
            sCount[s.charAt(i + p.length()) - 'a']++;//增加滑动后最后一位的词频

            if (Arrays.equals(sCount, pCount)) {
                ans.add(i + 1);
            }
        }
        return ans;
    }
}
