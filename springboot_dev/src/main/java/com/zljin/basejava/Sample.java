package com.zljin.basejava;

import java.util.ArrayList;
import java.util.List;

public class Sample {

    public static void main(String[] args) {
        List<String> alist = new ArrayList<>();
        alist.add("java");
        alist.add("go");
        alist.add("sql");
        alist.remove("go");
        alist.add(0,"kotlin");
        alist.set(0,"kotlin2");
        alist.addAll(alist);
        System.out.println(alist);
    }
}
