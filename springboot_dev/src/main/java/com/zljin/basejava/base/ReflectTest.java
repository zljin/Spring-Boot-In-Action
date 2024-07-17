package com.zljin.basejava.base;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ReflectTest {
    public static void main(String[] args) throws Exception {

        //1. get class image
        Class<?> accountClass = Class.forName("com.zljin.basejava.base.Account");

        //2. get constructor by image
        Constructor<?> constructor = accountClass.getConstructor(String.class, Integer.class, String.class);

        //3. create obj by constructor
        Object obj = constructor.newInstance("189003", 1000, "乔治");

        //4. get variable by image
        Field firstname = accountClass.getDeclaredField("firstname");
        firstname.setAccessible(true);//暴力访问
        firstname.set(obj, "马卡");


        //5. get method by image
        Method addAccount = accountClass.getMethod("addAccount", Integer.class);
        addAccount.invoke(obj,100);

        //6. get static method by image
        Method setMsg = accountClass.getDeclaredMethod("setMsg", String.class);
        setMsg.setAccessible(true);//暴力访问
        setMsg.invoke(null,"尊贵的会员");

        Account account = (Account) obj;
        System.out.println(account.getFirstname());
    }
}
