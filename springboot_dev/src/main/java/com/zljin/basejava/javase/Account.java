package com.zljin.basejava.javase;


public class Account implements Comparable<Account> {
    private String accountNumber;
    private Integer balance;
    private String firstname;

    public Account() {
    }

    public Account(String accountNumber, Integer balance, String firstname) {
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.firstname = firstname;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void addAccount(Integer num) {
        this.setBalance(balance + num);
        System.out.println("name: " + firstname + " amount: " + this.getBalance());
    }
    private static void setMsg(String name) {
        System.out.println("welcome: " + name);
    }

    @Override
    public int compareTo(Account o) {
        int compareBalance = this.balance.compareTo(o.balance);
        return compareBalance == 0 ? this.firstname.compareTo(o.firstname) : compareBalance;
    }
}
