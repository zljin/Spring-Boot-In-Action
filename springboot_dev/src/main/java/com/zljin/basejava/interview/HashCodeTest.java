package com.zljin.basejava.interview;

import java.util.HashSet;

/**
 * 一般在10W以内不会发生hash冲突
 */
public class HashCodeTest {
    public static void main(String[] args) {
        HashSet<Integer> hashSet = new HashSet<>();
        for (int i = 1; i <= 11 * 10000; i++) {
            int hashcodeValue = new Object().hashCode();
            if(!hashSet.contains(hashcodeValue)){
                hashSet.add(hashcodeValue);
            }else {
                System.out.println("发生hash冲突，在第"+i+"次，值是："+hashcodeValue);
            }
        }
        System.out.println(hashSet.size());
    }
}
