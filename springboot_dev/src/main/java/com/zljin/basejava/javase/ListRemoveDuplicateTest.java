package com.zljin.basejava.javase;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 如何去除list中的重复元素，用三种方法
 */
public class ListRemoveDuplicateTest {

    public static void main(String[] args) {
        m1();
        m2();
        m3();
        m4();
    }

    private static void m1(){
        List<Integer> srcList = new ArrayList<>(Arrays.asList(70,70,-1,3,5,7,3,5,7));

        List<Integer> filterList = new ArrayList<>();

        srcList.forEach(e->{
            if(!filterList.contains(e)){
                filterList.add(e);
            }
        });
        System.out.println(filterList);
    }

    private static void m2(){
        List<Integer> srcList = new ArrayList<>(Arrays.asList(70,70,-1,3,5,7,3,5,7));
        List<Integer> orderfilterList = new ArrayList<>(new HashSet<>(srcList));
        List<Integer> disorderfilterList = new ArrayList<>(new LinkedHashSet<>(srcList));
        System.out.println(orderfilterList);
        System.out.println(disorderfilterList);
    }

    private static void m3(){
        List<Integer> srcList = new ArrayList<>(Arrays.asList(70,70,-1,3,5,7,3,5,7));

        List<Integer> filterList = srcList.stream().distinct().collect(Collectors.toList());
        System.out.println(filterList);
    }

    /**
     * 双指针
     */
    private static void m4(){
        List<Integer> srcList = new ArrayList<>(Arrays.asList(70,70,-1,3,5,7,3,5,7));

        List<Integer> filterList = new ArrayList<>(srcList);
        for(Integer e:srcList){
            if(filterList.indexOf(e)!=filterList.lastIndexOf(e)){
                filterList.remove(filterList.lastIndexOf(e));
            }
        }
        System.out.println(filterList);
    }
}
