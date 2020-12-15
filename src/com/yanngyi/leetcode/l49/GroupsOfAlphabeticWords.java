package com.yanngyi.leetcode.l49;

import java.util.*;

/**
 * 字母异位词分组
 * 难度：中等
 *
 * 给定一个字符串数组，将字母异位词组合在一起。字母异位词指字母相同，但排列不同的字符串。
 *
 * 示例:
 *
 * 输入: ["eat", "tea", "tan", "ate", "nat", "bat"]
 * 输出:
 * [
 *   ["ate","eat","tea"],
 *   ["nat","tan"],
 *   ["bat"]
 * ]
 * 说明：
 *
 * 所有输入均为小写字母。
 * 不考虑答案输出的顺序。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/group-anagrams
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author yangyi
 */
public class GroupsOfAlphabeticWords {

    public List<List<String>> groupAnagramsSelf(String[] strs) {
        //作为返回结果
        List<List<String>> resultLists = new ArrayList<>();
        List<String> paramList = new ArrayList<>(Arrays.asList(strs));
        List<String> paramListNew = new ArrayList<>(Arrays.asList(strs));
        while (paramList.size() > 0) {
            //存放符合条件的一个集合
            List<String> resultList = new ArrayList<>();
            paramListNew.forEach(
                    c -> {
                        //将字母全排列，并判断在paramList是否有对应的字母，如果有则将其移除，并加入resultList中，并且index--
                        List<String> rangeResult = new ArrayList<>();
                        rangeResult.forEach(
                                d -> {
                                    if (paramList.contains(d)) {
                                        paramList.remove(d);
                                        resultList.add(d);
                                    }
                                }
                        );
                        resultLists.add(resultList);
                    }
            );
        }

        return resultLists;
    }

    /**
     * 前言
     * 两个字符串互为字母异位词，当且仅当两个字符串包含的字母相同。同一组字母异位词中的字符串具备相同点，可以使用相同点作为一组字母异位词的标志，使用哈希表存储每一组字母异位词，哈希表的键为一组字母异位词的标志，哈希表的值为一组字母异位词列表。
     *
     * 遍历每个字符串，对于每个字符串，得到该字符串所在的一组字母异位词的标志，将当前字符串加入该组字母异位词的列表中。遍历全部字符串之后，哈希表中的每个键值对即为一组字母异位词。
     *
     * 以下的两种方法分别使用排序和计数作为哈希表的键。
     *
     * 方法一：排序
     * 由于互为字母异位词的两个字符串包含的字母相同，因此对两个字符串分别进行排序之后得到的字符串一定是相同的，故可以将排序之后的字符串作为哈希表的键。
     *
     * 复杂度分析
     *
     * 时间复杂度：O(nklogk)，其中 n 是strs 中的字符串的数量，k 是 strs 中的字符串的的最大长度。需要遍历 n 个字符串，对于每个字符串，需要 O(klogk) 的时间进行排序以及 O(1) 的时间更新哈希表，因此总时间复杂度是 O(nklogk)。
     *
     * 空间复杂度：O(nk)，其中 n 是 strs 中的字符串的数量，k 是 strs 中的字符串的的最大长度。需要用哈希表存储全部字符串。
     *
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/group-anagrams/solution/zi-mu-yi-wei-ci-fen-zu-by-leetcode-solut-gyoc/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param strs
     * @return
     */
    public static List<List<String>> groupAnagramsMethodFirst(String[] strs) {

        List<List<String>> resultLists = new ArrayList<>();
        Map<String, List<String>> map = new HashMap<>(16);
        for (String s : strs) {
            char[] array = s.toCharArray();
            Arrays.sort(array);
            String string = new String(array);
            List<String> list = map.getOrDefault(string, new ArrayList<>());
            list.add(s);
            map.put(string, list);
//            List<String> list = map.get(string);
//            if (list != null) {
//                list.add(s);
//            } else {
//                list = new ArrayList<>();
//                list.add(s);
//                map.put(string, list);
//            }
        }
//        map.forEach( (k, v) -> {
//            resultLists.add(v);
//        });
        resultLists = new ArrayList<>(map.values());
        return resultLists;
    }

    /**
     * 方法二：计数
     * 由于互为字母异位词的两个字符串包含的字母相同，因此两个字符串中的相同字母出现的次数一定是相同的，故可以将每个字母出现的次数使用字符串表示，作为哈希表的键。
     *
     * 由于字符串只包含小写字母，因此对于每个字符串，可以使用长度为 26 的数组记录每个字母出现的次数。在使用数组作为哈希表的键时，不同语言的支持程度不同，需要保持注意。
     *
     * 复杂度分析
     *
     * 时间复杂度：O(n(k+∣Σ∣))，其中 n 是 strs 中的字符串的数量，k 是 strs 中的字符串的的最大长度，Σ 是字符集，在本题中字符集为所有小写字母，∣Σ∣=26。需要遍历 nn 个字符串，对于每个字符串，需要 O(k) 的时间计算每个字母出现的次数，O(∣Σ∣) 的时间生成哈希表的键，以及 O(1) 的时间更新哈希表，因此总时间复杂度是 O(n(k+|\Sigma|))O(n(k+∣Σ∣))。
     *
     * 空间复杂度：O(n(k+∣Σ∣))，其中 n 是 strs 中的字符串的数量，k 是 strs 中的字符串的最大长度，Σ 是字符集，在本题中字符集为所有小写字母，∣Σ∣=26。需要用哈希表存储全部字符串，而记录每个字符串中每个字母出现次数的数组需要的空间为 O(∣Σ∣)，在渐进意义下小于 O(n(k+∣Σ∣))，可以忽略不计.
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/group-anagrams/solution/zi-mu-yi-wei-ci-fen-zu-by-leetcode-solut-gyoc/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param strs
     * @return
     */
    public static List<List<String>> groupAnagramsMethodSecond(String[] strs) {
        Map<String, List<String>> map = new HashMap<String, List<String>>();
        for (String str : strs) {
            int[] counts = new int[26];
            int length = str.length();
            for (int i = 0; i < length; i++) {
                counts[str.charAt(i) - 'a']++;
            }
            // 将每个出现次数大于 0 的字母和出现次数按顺序拼接成字符串，作为哈希表的键
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < 26; i++) {
                if (counts[i] != 0) {
                    sb.append((char) ('a' + i));
                    sb.append(counts[i]);
                }
            }
            String key = sb.toString();
            List<String> list = map.getOrDefault(key, new ArrayList<String>());
            list.add(str);
            map.put(key, list);
        }
        return new ArrayList<List<String>>(map.values());
    }

    public static void main(String[] args) {
       String [] s = {"eat","tea","tan","ate","nat","bat"};
        List<List<String>> lists = groupAnagramsMethodSecond(s);
        System.out.println(lists);
    }
}
