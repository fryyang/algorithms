package com.yanngyi.sxt.sgreed;

import java.util.*;

/**
 * @author yangyi
 */
public class CollectionRecover {
    public static void main(String[] args) {
        List<String> selectList = new ArrayList<>();

        Set<String> allCityCollection = new HashSet<>();

        Map<String, List<String>> initCollection = new HashMap<>();
        List<String> cityCollection1 = new ArrayList<>();
        cityCollection1.add("北京");
        cityCollection1.add("上海");
        cityCollection1.add("天津");
        initCollection.put("k1", cityCollection1);

        List<String> cityCollection2 = new ArrayList<>();
        cityCollection2.add("广州");
        cityCollection2.add("北京");
        cityCollection2.add("深圳");
        initCollection.put("k2", cityCollection2);

        List<String> cityCollection3 = new ArrayList<>();
        cityCollection3.add("成都");
        cityCollection3.add("上海");
        cityCollection3.add("杭州");
        initCollection.put("k3", cityCollection3);

        List<String> cityCollection4 = new ArrayList<>();
        cityCollection4.add("上海");
        cityCollection4.add("天津");
        initCollection.put("k4", cityCollection4);

        List<String> cityCollection5 = new ArrayList<>();
        cityCollection5.add("杭州");
        cityCollection5.add("大连");
        initCollection.put("k5", cityCollection5);

        allCityCollection.addAll(cityCollection1);
        allCityCollection.addAll(cityCollection2);
        allCityCollection.addAll(cityCollection3);
        allCityCollection.addAll(cityCollection4);
        allCityCollection.addAll(cityCollection5);

        //[成都, 上海, 广州, 天津, 大连, 杭州, 北京, 深圳]
        System.out.println(Arrays.toString(allCityCollection.toArray()));

        while (allCityCollection.size() > 0) {
            int maxCount = 0;
            String maxKey = null;
            for (Map.Entry<String, List<String>> entry : initCollection.entrySet()) {

                List<String> value = entry.getValue();
                value.retainAll(allCityCollection);
                if (value.size() > maxCount) {
                    maxKey = entry.getKey();
                }
                maxCount =  Math.max(maxCount, value.size());

            }
            selectList.add(maxKey);
            allCityCollection.removeAll(initCollection.get(maxKey));
            System.out.println(maxKey);
            System.out.println(maxCount);
            System.out.println(Arrays.toString(allCityCollection.toArray()));
        }
        System.out.println(Arrays.toString(allCityCollection.toArray()));
        System.out.println(Arrays.toString(selectList.toArray()));
    }
}
