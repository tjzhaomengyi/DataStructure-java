package com.book.zuoshen.InterviewGuide.chpt5;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @Author: zhaomengyi
 * @Date: 2024/2/19 20:35
 * @Description:这是一个贪心算法的题
 */
public class MakeDictOrderString {
    public class MyComparator implements Comparator<String> {

        @Override
        public int compare(String o1, String o2) {
            return (o1 + o2).compareTo(o2 + o1);
        }
    }

    public String lowestString(String[] strs){
        if(strs == null || strs.length == 0){
            return "";
        }
        //根据新的排序方式排序
        Arrays.sort(strs, new MyComparator());
        String res = "";
        for(int i = 0; i < strs.length; i++){
            res += strs[i];
        }
        return res;
    }
}
