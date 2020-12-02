package com.yanngyi.leetcode.l727;

import java.util.PriorityQueue;

/**作者：@author LeetCode-Solution
 链接：https://leetcode-cn.com/problems/reorganize-string/solution/zhong-gou-zi-fu-chuan-by-leetcode-solution/
 来源：力扣（LeetCode）
 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。*/
public class ReBuildSolution {
    public String reorganizeString(String s) {
        if (s.length() < 2) {
            return s;
        }
        //声明一个能存储26个长度的数组（0-25）表示（a-z）
        int[] counts = new int[26];
        int maxCount = 0;
        int length = s.length();
        //遍历字符数组，并统计各个字符出现的次数
        for (int i = 0; i < length; i++) {
            char c = s.charAt(i);
            counts[c - 'a']++;
            maxCount = Math.max(maxCount, counts[c - 'a']);
        }
        if (maxCount > (length + 1) / 2) {
            return "";
        }
        // PriorityQueue是基于优先堆的一个无界队列，这个优先队列中的元素可以默认自然排序或者通过提供的Comparator（比较器）在队列实例化的时排序。
        //counts[letter2 - 'a'] - counts[letter1 - 'a'] 按照数组中对应下标的数字的大小进行倒序排列 实时 字母的顺序随着数组中字母的数量变化
        PriorityQueue<Character> queue = new PriorityQueue<>((letter1, letter2) -> counts[letter2 - 'a'] - counts[letter1 - 'a']);
        //将数组中字母数量不为0的字母存入queue
        for (char c = 'a'; c <= 'z'; c++) {
            if (counts[c - 'a'] > 0) {
                queue.offer(c);
            }
        }
        StringBuffer sb = new StringBuffer();
        while (queue.size() > 1) {
            char letter1 = queue.poll();
            char letter2 = queue.poll();
            sb.append(letter1);
            sb.append(letter2);
            int index1 = letter1 - 'a', index2 = letter2 - 'a';
            counts[index1]--;
            counts[index2]--;
            if (counts[index1] > 0) {
                queue.offer(letter1);
            }
            if (counts[index2] > 0) {
                queue.offer(letter2);
            }
        }
        if (queue.size() > 0) {
            sb.append(queue.poll());
        }
        return sb.toString();
    }
}

