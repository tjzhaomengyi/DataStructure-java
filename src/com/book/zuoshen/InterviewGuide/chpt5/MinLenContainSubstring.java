package com.book.zuoshen.InterviewGuide.chpt5;

/**
 * @Author: zhaomengyi
 * @Date: 2024/2/20 09:21
 * @Description:给定字符串str1和str2，求str1的子串中包含有str2所有字符的最小子串长度，可以不连续
 */
public class MinLenContainSubstring {
    public int minLength(String str1, String str2){
        if(str1 == null || str2 == null || str1.length() < str2.length()){
            return 0;
        }
        char[] chs1 = str1.toCharArray();
        char[] chs2 = str2.toCharArray();
        int[] map = new int[256]; //map记录chs1要还chs2的账，如果是负数表示chs1多给了chs2，并且这些负数是可以让left压缩的
        for(int i = 0; i < chs2.length; i++){
            map[chs2[i]]++;
        }
        int left = 0;
        int right = 0;
        int match = chs2.length;
        int minLen = Integer.MAX_VALUE;
        //right一直往右推，left收缩压到最小的范围
        while(right != chs1.length){
            map[chs1[right]]--; //chs1里面的字符开始对map进行还账
            //如果这个值在chs2中match长度减1
            if(map[chs1[right]] >= 0){
                match--;
            }
            //把chs2中所有字符都收集完了
            if(match == 0){
                //把左侧可以收缩的字符删除掉，就是那些map[chs1[left]] < 0的字符
                while(map[chs1[left]] < 0){
                    map[chs1[left]]++; //先退账，把字典的值补回去
                    left++; //左侧边界收
                }
            }
            right++; //重复上述过程
        }
        return minLen == Integer.MAX_VALUE ? 0 : minLen;
    }
}
