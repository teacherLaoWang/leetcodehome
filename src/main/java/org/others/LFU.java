package org.others;

import com.alibaba.fastjson.JSON;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class LFU {

    public static void main(String[] args) {
        int[][] input = {{1, 1, 1}, {1, 2, 2}, {1, 3, 2}, {1, 2, 4}, {1, 3, 5}, {2, 2}, {1, 4, 4}, {2, 1}};

        LFU lfu = new LFU();
        int[] ints = lfu.LFU(input, 3);
        System.out.println(JSON.toJSONString(ints));

    }

    public int[] LFU(int[][] operators, int k) {
        int resultSize = 0;
        int index = 0;
        List<Integer> list = new LinkedList<>();
        Map<Integer, Integer> map = new HashMap<>();
        if (operators != null) {
            for (int[] set : operators) {
                if (set[0] == 2) {
                    resultSize++;
                }
            }
        }
        int[] result = new int[resultSize];
        for (int[] set : operators) {
            int key = set[1];
            if (set[0] == 1) {
                if (list.size() <= k) {
                    if (!list.contains(key)) {
                        list.add(key);
                    }
                    map.put(key, set[2]);
                } else {
                    if (list.contains(key)) {
                        list.remove(0);
                    } else {
                        map.remove(list.get(0));
                    }
                    list.add(key);
                    map.put(key, set[2]);
                }
            } else {
                if (list.contains(key)) {
                    list.remove(0);
                    list.add(key);
                    result[index] = map.get(key);
                } else {
                    result[index] = -1;
                }
                index++;
            }
        }

        return result;
    }
}
