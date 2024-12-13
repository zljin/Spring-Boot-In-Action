package com.zljin.basejava.leetcode;

/**
 * 链表
 */
public class Solution021 {

    public static void main(String[] args) {

    }

    class ListNode {
        int val;

        ListNode next;
    }


    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode prehead = new ListNode();
        ListNode prev = prehead;

        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                prev.next = l1;
                l1 = l1.next;
            } else {
                prev.next = l2;
                l2 = l2.next;
            }
            prev = prev.next;
        }
        prev.next = l1 == null ? l2 : l1;
        return prev.next;
    }
}




