package com.zljin.basejava.javase;

import com.google.gson.Gson;

import java.util.Comparator;
import java.util.TreeSet;

public class SetCompareTest {
    public static void main(String[] args) {
        TreeSet ts = new TreeSet(new Comparator<Account>() {
            @Override
            public int compare(Account o1, Account o2) {
                return o1.getBalance() - o2.getBalance();
            }
        });
        ts.add(new Account("12345", 20, "李四"));
        ts.add(new Account("12346", 19, "王五"));
        ts.add(new Account("12347", 14, "阿三"));
        ts.add(new Account("12348", 18, "李四"));
        System.out.println(new Gson().toJson(ts));
    }
}
