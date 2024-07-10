package com.zljin.basejava.interview;


/**
 * -128 ~ 127 IntegerCache.cache 会复用已有对象，这个区间可用 == 判断
 * 但这个区间之外的所有数据，都会在堆上，并不会复用已有的对象
 *
 * 强制：所有整型包装类对象之间值的比较，全部使用equals()方法！！
 *
 * 不推荐Integer()构造方法，demised
 *
 *
 */
public class IntegerTest {
    public static void main(String[] args) {
        m1();
        m2();
    }

    private static void m1(){
        Integer x = Integer.valueOf(600);
        Integer y = Integer.valueOf(600);
        int z = 600;
        System.out.println(x == y);
        System.out.println(x.equals(y));
        System.out.println(x == z);

        System.out.println("======================");

        Integer a = Integer.valueOf(99);
        Integer b = Integer.valueOf(99);
        System.out.println(a == b);
        System.out.println(a.equals(b));
    }


    /**
     *  * 自动拆装箱的机制：
     *  * Integer 是一个包装类，而 int 是基本数据类型。当把一个 Integer 对象赋值给一个 int 类型的变量时，就会发生自动拆箱
     *  * 自动装箱是指将基本数据类型自动转换为对应的包装类对象
     */
    private static void m2(){
        Integer num = new Integer(10);
        int value = num;  // 这里发生了自动拆箱
        int num2 = 10;
        Integer integerNum = num2;  // 这里发生了自动装箱
        System.out.println(integerNum.equals(value));
    }

}
