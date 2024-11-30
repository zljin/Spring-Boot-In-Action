package com.zljin.basejava.javase;


/**
 * 浅拷贝和深拷贝
 *
 * 区别：
 * 1. 浅拷贝是创建多一个对象的引用，但指向还是同一个对象
 * 2. 深拷贝完全新创建一个对象
 *
 * 必须要实现Cloneable接口才能调用clone()方法
 *
 */
public class CloneTest {
    public static void main(String[] args) throws CloneNotSupportedException {
        Emp emp = new Emp("zhansan", 15, "马斯特", "CEO");
        Emp cloneEmp = (Emp) emp.clone();
        System.out.println(emp.getBoss().getTitle());
        System.out.println(cloneEmp.getBoss().getTitle());

        cloneEmp.getBoss().setTitle("CTO");
        System.out.println(emp.getBoss().getTitle());
        System.out.println(cloneEmp.getBoss().getTitle());
    }

    static class Emp implements Cloneable{
        private String empName;
        private Integer age;

        private Boss boss;

        public Emp(String empName, Integer age, String bossName,String title) {
            this.empName = empName;
            this.age = age;
            this.boss = new Boss(bossName,title);
        }

        public String getEmpName() {
            return empName;
        }

        public void setEmpName(String empName) {
            this.empName = empName;
        }

        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }

        public Boss getBoss() {
            return boss;
        }

        public void setBoss(Boss boss) {
            this.boss = boss;
        }

        /**
         * 浅拷贝，重新创建一个对象的引用，指向还是同一个对象
         */
//        @Override
//        protected Object clone() throws CloneNotSupportedException {
//            return super.clone();
//        }

        /**
         * 深拷贝，重新new一个对象
         * @return
         * @throws CloneNotSupportedException
         */
        @Override
        protected Object clone() throws CloneNotSupportedException {
            return new Emp(empName,age, boss.bossName, boss.title);
        }
    }


    static class Boss implements Cloneable{
        private String bossName;
        private String title;

        public Boss() {
        }

        public Boss(String bossName, String title) {
            this.bossName = bossName;
            this.title = title;
        }

        public String getBossName() {
            return bossName;
        }

        public void setBossName(String bossName) {
            this.bossName = bossName;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        @Override
        protected Object clone() throws CloneNotSupportedException {
            return super.clone();
        }
    }
}
