package com.book.zuoshen.InterviewGuide.chpt4.LISProblems;

import java.util.HashMap;

/**
 * @Author: zhaomengyi
 * @Date: 2024/2/9 14:51
 * @Description:找出最长的连续递增的序列
 */
public class MaxLenContinueLIS {
    public int longestContinueLIS(int[] arr){
        if(arr == null || arr.length == 0){
            return 0;
        }
        int max = 1;
        //使用一个map记录每个值所在最大连续序列的最大长度,key表示这个数，value表示arr[key]所在连续序列的最大长度
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        for(int i = 0; i < arr.length; i++){
            if(!map.containsKey(arr[i])){
                map.put(arr[i], 1);
                if(map.containsKey(arr[i] - 1)){ //看一下有没有比较
                    //当左侧出现过最大长度记录的时候，需要更新最左侧节点的记录长度，还有当前节点的记录长度，如果左右都有中间节点会把两边的信息都联合起来
                    max = Math.max(max, merge(map, arr[i] - 1, arr[i]));
                }
                if(map.containsKey(arr[i] + 1)){
                    max = Math.max(max, merge(map, arr[i], arr[i + 1]));
                }
            }
        }
        return max;
    }

    //保证每次先更新完左侧的最长连续序列长度后，都会在后面检查右侧的时候把arr[i]节点也更新好，
    public int merge(HashMap<Integer, Integer> map, int less, int more){
        int left = less - map.get(less) + 1;
        int right = more + map.get(more) - 1;
        int len = right - left + 1;
        //更新左右节点所在的最大长度
        map.put(left, len);
        map.put(right, len);
        return len;
    }

    //更缩略的写法
    public int longest(int[] arr){
        HashMap<Integer, Integer> map = new HashMap<>();
        int len = 1;
        for(int num : arr){
            if(!map.containsKey(num)){
                map.put(num, 1);
                int preLen = map.getOrDefault(num - 1, 0);
                int postLen = map.getOrDefault(num + 1, 0);
                int all = preLen + postLen;
                map.put(num - preLen, all);
                map.put(num + postLen, all);
                map.put(num, all);//把当前节点也更新一下,【解释，既然每次都连接上了，说明前面都是连完整的，更新当前节点就行了】
                len = Math.max(len, all);
            }
        }
        return len;
    }
}
