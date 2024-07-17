package com.zljin.basejava.base;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * BigDecimal的坑，常用于金额的精度运算
 *
 * 1. BigDecimal等值比较请强制使用compareTo(),而不是equals()方法
 * 2. 禁止使用BigDecimal(double)的构造方法，而是用BigDecimal(string) or valueOf() 方法
 * 3. 加减乘除，请用BigDecimal内置方法
 * 4. 推荐用字符串构造方法构造科学计数，不会失真
 */
public class BigDecimalTest {
    public static void main(String[] args) {
        compare0Bug();
        compare1Bug();
        compare2();
        compare3();
        compare4();
        divide1();
        printScientific();
    }


    private static void compare0Bug(){
        double b1 = 0.03;
        double b2 = 0.02;
        System.out.println(b1-b2);
    }
    private static void compare1Bug(){
        BigDecimal b1 = new BigDecimal(0.03);
        BigDecimal b2 = new BigDecimal(0.02);
        System.out.println(b1.subtract(b2));//0.0099999999999999984734433411404097569175064563751220703125
    }

    private static void compare2(){
        BigDecimal b1 = new BigDecimal("0.03");
        BigDecimal b2 = new BigDecimal("0.02");
        System.out.println(b1.subtract(b2));//0.01
    }

    private static void compare3(){
        BigDecimal b1 = BigDecimal.valueOf(0.03);
        BigDecimal b2 = BigDecimal.valueOf(0.02);
        System.out.println(b1.subtract(b2));//0.01
    }

    private static void compare4(){
        BigDecimal b1 = new BigDecimal("0.9");
        BigDecimal b2 = new BigDecimal("0.90");
        BigDecimal b3 = new BigDecimal("0.91");
        BigDecimal b4 = new BigDecimal("0.8");
        System.out.println(b1.compareTo(b2));
        System.out.println(b1.compareTo(b3));
        System.out.println(b1.compareTo(b4));
        System.out.println(b1.equals(b2));
    }

    private static void divide1(){
        BigDecimal b1 = new BigDecimal("2.0");
        BigDecimal b2 = new BigDecimal("3.0");
        System.out.println(b1.divide(b2,2, RoundingMode.HALF_UP));//除法精度四舍五入 0.67
    }

    /**
     * 科学输入法
     */
    private static void printScientific(){
        BigDecimal amount = BigDecimal.valueOf(123456789.123456789);
        System.out.println(amount);
        System.out.println(amount.toString());
        System.out.println(amount.toPlainString());//非科学计数法输出


        //推荐用字符串构造方法构造科学计数，不会失真
        BigDecimal amount2 = new BigDecimal("123456789.123456789");
        System.out.println(amount2);
        System.out.println(amount2.toString());
        System.out.println(amount2.toPlainString());//非科学计数法输出

    }
}
