package org.others;

import com.alibaba.fastjson.JSON;

import java.util.*;

public class LFU {

    public static void main(String[] args) {
        int[][] input = {{2, -788346195}, {2, 663130427}, {2, 199473611},
                {2, -195891979}, {1, 500832003, 16688758}, {2, 500832003},
                {1, 611386314, -165759898}, {2, 468212983}, {2, -271818474},
                {1, 652927731, -348635282}, {1, -238995884, -258671053},
                {2, 500832003}, {2, -147772526}, {1, 476972438, 303030386},
                {2, 611386314}, {2, 500832003}, {1, -122160324, 226250640},
                {2, -613589387}, {1, -278040308, -367610673}, {1, 225799075,
                -205870402}, {1, -146502911, 538248725}, {2, 225799075},
                {1, 188913912, 458810061}, {2, -119477399}, {1, -298402981,
                -545080319}, {1, -212003183, -395967680}, {1, -403828707,
                312998011}, {2, -629547856}, {2, -591793417}, {2, -238995884},
                {2, -221284932}, {2, 612267338}, {1, 196500800, -910183182},
                {1, -642727487, 390702112}, {2, 3788878}, {2, 225799075},
                {1, 473983650, -454872678}, {1, 324437559, -590323082},
                {1, -111589492, -168572335}, {2, -300599720},
                {1, -812965602, -161093786}, {2, -642727487},
                {2, 188913912}, {1, -218604410, -90822424}, {2, -87730129},
                {2, 265165195}, {2, -569793417}, {2, -735605468}, {1, 333199655, 133683700},
                {1, -763325900, -572482758}, {1, 497099029, -624508071}, {2, -315061766}, {2, -642727487}};

        LFU lfu = new LFU();
        int[] ints = lfu.LFU(input, 1000);
        int[] ints1 = lfu.LFU2(input, 1000);
        System.out.println(JSON.toJSONString(ints));
        System.out.println(JSON.toJSONString(ints1));


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


    public int[] LFU2 (int[][] operators, int k) {
        // write code here
        if(operators==null) return new int[] {-1};
        LinkedHashMap<Integer, Integer> map = new LinkedHashMap<Integer, Integer>();
        LinkedHashMap<Integer, Integer> count = new LinkedHashMap<Integer, Integer>();
        ArrayList<Integer> list  = new ArrayList<Integer>();
        for(int i=0;i<operators.length;i++) {
            int op = operators[i][0] , key = operators[i][1];
            if(op==1) {
                if(map.containsKey(key)) {
                    map.put(key, operators[i][2]);
                    count.put(key, count.get(key)+1);
                }else {
                    if(map.size()==k) {
                        int removekey = GetMinKey(count);
                        map.remove(removekey);
                        count.remove(removekey);
                    }
                    map.put(key, operators[i][2]);
                    if(count.containsKey(key)) count.put(key, count.get(key)+1);
                    else count.put(key, 1);
                }
            }else if(op==2) {
                if(map.containsKey(key)) {
                    int val = map.get(key);
                    count.put(key, count.get(key)+1);
                    list.add(val);
                }else {
                    list.add(-1);
                }
            }
        }
        int []A = new int [list.size()];
        for(int i=0;i<list.size();i++) {
            A[i] = list.get(i);
        }
        return A;
    }

    public int GetMinKey(LinkedHashMap<Integer, Integer> count) {
        int minCount = Integer.MAX_VALUE;
        int key = 0;
        for(Map.Entry<Integer, Integer> entry : count.entrySet()) {
            if(entry.getValue()<minCount) {
                minCount = entry.getValue();
                key = entry.getKey();
            }
        }
        return key;
    }

}
