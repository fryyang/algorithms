package com.yanngyi.leetcode.l727;

import java.util.Arrays;
import java.util.PriorityQueue;

public class ReBuildingSolutionTest {
    public static void main(String[] args) {
        String s = "eiqruiqetuoiqerotiioret";
        int [] counts = new int[26];
        char[] chars = s.toCharArray();
        int maxCount = 0;
        for (char c : chars) {
            counts[c - 'a']++;
            maxCount = Math.max(maxCount, counts[c -'a']);
        }
        System.out.println(Arrays.toString(counts));
        if (maxCount > (s.length() + 1) / 2) {
            System.out.println("无法处理");
        }
        PriorityQueue<Character> queue = new PriorityQueue<>((c1, c2) -> counts[c2- 'a'] - counts[c1 - 'a']);
        for (int i= 0; i< counts.length; i++) {
            if (counts[i] > 0) {
                queue.offer((char) ('a' + i));
            }
        }
        StringBuilder sb = new StringBuilder();
        System.out.println(Arrays.toString(queue.toArray()));
        while (!queue.isEmpty()) {
            Character poll1 = null;
            Character poll2 = null;
            if (queue.size() == 1) {
                poll1 = queue.poll();
                int i = counts[poll1 - 'a']--;
                if (i != 0) {
                    queue.offer(poll1);
                }
            } else {
                poll1 = queue.poll();
                int i = counts[poll1 - 'a']--;
                if (i != 0) {
                    queue.offer(poll1);
                }
                poll2 = queue.poll();
                int i1 = counts[poll2 - 'a']--;
                if (i1 != 0) {
                    queue.offer(poll2);
                }
            }
            System.out.println(Arrays.toString(queue.toArray()));
            sb.append(poll1);
            if (poll2 != null) {
                sb.append(poll2);
            }
        }
        System.out.println(sb);
    }
}
