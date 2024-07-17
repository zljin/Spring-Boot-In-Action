package com.zljin.basejava.base;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 强制：使用Arrays.asList()把数字转为集合时，不能使用其修改集合相关的方法
 * 会抛出java.lang.UnsupportedOperationException
 * 可使用 new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5))
 */
public class ArraysListBugTest {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> list2 = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));

        list.add(6);

        list.forEach(System.out::println);
    }
}
