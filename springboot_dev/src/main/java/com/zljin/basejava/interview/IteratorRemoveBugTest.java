package com.zljin.basejava.interview;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * 不要在foreach循环里进行元素的add/remove 操作。remove操作请使用iterator的方式，
 * 如果并发操作，需要对iterator对象进行加锁
 */
public class IteratorRemoveBugTest {
    public static void main(String[] args) {
        m1();
    }

    private static void m1(){
        List<Integer> list = new ArrayList<>(Arrays.asList(11,12,13,14,15));

        Iterator<Integer> iterator = list.iterator();
        while (iterator.hasNext()){
            Integer cur = iterator.next();
            if(cur.equals(12)){
                //list.remove(cur);//Exception in thread "main" java.util.ConcurrentModificationException
                iterator.remove();
            }
        }
        list.forEach(System.out::println);
    }
}
