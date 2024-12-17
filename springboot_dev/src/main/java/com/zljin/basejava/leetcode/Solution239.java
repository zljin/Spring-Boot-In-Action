package com.zljin.basejava.leetcode;

import java.util.LinkedList;

/**
 * 子串
 *
 * 双队列
 */
public class Solution239 {
    public static void main11(String[] args) {
        LinkedList<Integer> deque = new LinkedList<>();
        deque.add(1);
        deque.add(2);
        deque.add(3);
        deque.add(4);
        System.out.println(deque.getFirst());
        System.out.println(deque.getLast());
        System.out.println(deque.peek());
        System.out.println(deque.peekFirst());
        System.out.println(deque.peekLast());
//        System.out.println(deque.pollFirst());
//        System.out.println(deque.pollLast());
//        System.out.println(deque.removeFirst());
//        System.out.println(deque.removeLast());
        System.out.println(deque);
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1, 3, -1, -3, 5, 3, 6, 7};
        int k = 3;

        int[] ints = new Solution239().maxSlidingWindow(nums, k);
        for(int in:ints){
            System.out.print(in+" ");
        }
        System.out.println();
    }


    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums.length == 0 || k == 0) {
            return new int[0];
        }

        int[] res = new int[nums.length - k + 1];

        //从大到小
        LinkedList<Integer> queue = new LinkedList<>();

        for (int i = 0; i < nums.length; i++) {

            int cur = nums[i];
            //保证从小到大，如果前面数小则需要依次弹出，直到满足要求
            while (!queue.isEmpty() && cur >= nums[queue.getLast()]) {
                queue.removeLast();
            }

            //添加当前对应数组的下标
            queue.addLast(i);

            //判断当前队列中的队首是否有效
            if (queue.getFirst() <= i - k) {
                queue.removeFirst();
            }

            //当窗口长度为K时，保存当前窗口中的最大值
            if (i + 1 >= k) {
                res[i+1-k] = nums[queue.getFirst()];
            }
        }


        return res;
    }
}
