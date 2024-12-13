package com.zljin.basejava.leetcode;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 哈希
 */
public class Solution049 {
    public static void main(String[] args) {
        String[] strs = new String[]{"eat", "tea", "tan", "ate", "nat", "bat"};
        System.out.println(new Solution049().groupAnagrams(strs));
    }

    public List<List<String>> groupAnagrams(String[] strs) {
        return groupAnagrams1(strs);
    }

    /**
     * Hash key值用来去重
     * @param strs
     * @return
     */
    public List<List<String>> groupAnagrams1(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            char[] charArray = str.toCharArray();
            Arrays.sort(charArray);
            String key = new String(charArray);
            List<String> list = map.getOrDefault(key, new ArrayList<>());
            list.add(str);
            map.put(key, list);
        }
        return new ArrayList<>(map.values());
    }

    public List<List<String>> groupAnagrams2(String[] strs) {
        return new ArrayList<>(Arrays.stream(strs).collect
                        (Collectors.groupingBy(s -> Arrays.toString(s.codePoints().sorted().toArray())))
                .values());
    }
}
