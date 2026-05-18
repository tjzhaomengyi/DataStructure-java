package com.niuke.BM101.cachedesign;

import java.util.*;

/**
 * 使用三个HashMap，一个统计key-value，一个统计key-freq，一个统计freq-keys，
 * 再使用一个变量记录当前最少的使用次数 minFreq，如果有删除、修改、添加就把minFreq进行修改
 * 同时变更三个HashMap里面的记录内容
 */

public class LFU {
    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     *
     * lfu design
     * @param operators int整型二维数组 ops
     * @param k int整型 the k
     * @return int整型一维数组
     */
    public int[] LFU (int[][] operators, int k) {
        // write code here
        LFUCache lfu = new LFUCache(k);
        int len = 0;
        for(int i = 0; i < operators.length; i++){
            if(operators[i][0] == 2){
                len++;
            }
        }
        int[] res = new int[len];
        len = 0;
        for(int i = 0; i < operators.length; i++){
            if(operators[i][0] == 1){
                lfu.put(operators[i][1], operators[i][2]);
            } else {
                res[len++] = lfu.get(operators[i][1]);
            }
        }
        return res;
    }

    public class LFUCache {
        HashMap<Integer, Integer> keyToVal;
        HashMap<Integer, Integer> keyToFreq;
        HashMap<Integer, LinkedHashSet<Integer>> freqToKeys;
        int minFreq; //记录最小频次
        int cap;

        public LFUCache(int cap){
            keyToVal = new HashMap<>();
            keyToFreq = new HashMap<>();
            freqToKeys = new HashMap<>();
            this.cap = cap;
            this.minFreq = 0;
        }

        public int get(int key){
            if(!keyToVal.containsKey(key)){
                return -1;
            }
            increaseFreq(key);
            return keyToVal.get(key);
        }

        public void put(int key, int val){
            if(this.cap <= 0) return;
            if(keyToVal.containsKey(key)){
                keyToVal.put(key, val);
                increaseFreq(key);
                return;
            }
            if(this.cap <= keyToVal.size()){
                removeMinFreqKey();
            }
            keyToVal.put(key, val);
            keyToFreq.put(key, 1);
            //插入FK表
            freqToKeys.putIfAbsent(1, new LinkedHashSet<>());
            freqToKeys.get(1).add(key);
            this.minFreq = 1;
        }
        //找到最小freq的key列表进行删除
        private void removeMinFreqKey() {
            LinkedHashSet<Integer> keyList = freqToKeys.get(this.minFreq);
            int deletedKey = keyList.iterator().next();
            keyList.remove(deletedKey);
            if(keyList.isEmpty()){
                freqToKeys.remove(this.minFreq);
            }
            keyToVal.remove(deletedKey);
            keyToVal.remove(deletedKey);
        }
        private void increaseFreq(int key){
            int freq = keyToFreq.get(key);
            keyToFreq.put(key, freq + 1);
            freqToKeys.get(freq).remove(key);
            //将key添加到freq+1的freqtoKey的map中
            freqToKeys.putIfAbsent(freq + 1, new LinkedHashSet<>());
            freqToKeys.get(freq + 1).add(key);
            //处理一下前任freq
            if(freqToKeys.get(freq).isEmpty()){
                freqToKeys.remove(freq);
                //再跟新频率数
                if(this.minFreq == freq) this.minFreq++;
            }
        }
    }
}
