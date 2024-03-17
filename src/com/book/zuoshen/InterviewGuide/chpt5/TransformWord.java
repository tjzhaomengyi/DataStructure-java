package com.book.zuoshen.InterviewGuide.chpt5;

/**
 * @Author: zhaomengyi
 * @Date: 2024/2/14 11:29
 * @Description:
 */
public class TransformWord {
    public boolean isTransformer(String str1, String str2){
        if(str1 == null || str2 == null || str1.length() != str2.length() || str1.equals(str2)){
            return false;
        }
        char[] chs1 = str1.toCharArray();
        char[] chs2 = str2.toCharArray();
        int[] map = new int[256];
        for(int i = 0; i < chs1.length; i++){
            map[chs1[i]]++;
        }
        for(int i = 0; i < chs2.length; i++){
            if(map[chs2[i]]-- < 0){
                return false;
            }
        }
        return true;
    }
}
