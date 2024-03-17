package com.book.zuoshen.InterviewGuide.chpt5;

/**
 * @Author: zhaomengyi
 * @Date: 2024/2/14 12:58
 * @Description:在包含null的字符串数组中找到最左位置
 */
public class NullStrArray {
    //使用二分方法
    public int getIndex(String[] strs, String str){
        if(strs == null || strs.length == 0 || str == null){
            return -1;
        }
        int res = -1;
        int left = 0;
        int right = strs.length - 1;
        int mid = 0;
        int i = 0;
        //三种不同的情况调整left和right指针
        while(left <= right){
            mid = (left + right) / 2;
            if(strs[mid] != null && strs[mid].equals(str)){
                res = mid;
                right = mid - 1;
            } else if(strs[mid] != null){
                if(strs[mid].compareTo(str) < 0){
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            } else { //strs[mid]=null
                i = mid;
                while(strs[i] == null && --i >= left);//如果左半区遍历的时候为null，一直往左走
                if(i < left || strs[i].compareTo(str) < 0){
                    left = mid  + 1; //把左侧
                } else {
                    res = strs[i].equals(str) ? i : res;
                    right = i - 1;
                }
            }
        }
        return res;
    }

}
