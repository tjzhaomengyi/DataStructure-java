package com.book.zuoshen.InterviewGuide.chpt5;

/**
 * @Author: zhaomengyi
 * @Date: 2024/2/16 10:46
 * @Description:字符串两个小技巧：（1）替换字符 （2）格式化
 */
public class StringTrickReplaceAndModify {
    //1、将空格替换为字符串
    public void replace(char[] chars){
        if(chars == null || chars.length == 0){
            return;
        }
        int num = 0;//统计空格个数
        int len = 0;
        for(len = 0; len < chars.length && chars[len] != 0; len++){
            if(chars[len] == ' '){
                num++;
            }
        }
        int j = len + num * 2 - 1; //新字符串的结束位置
        //替换技巧从后往前替换
        for(int i = len - 1; i >= 0; i--){
            if(chars[i] != ' '){
                chars[j--] = chars[i];
            } else {
                chars[j--] = '0';
                chars[j--] = '2';
                chars[j--] = '%';
            }
        }
    }

    //2、格式化字符串，遇到“*”略过，数字字符复制
    public void modify(char[] chars){
        if(chars == null || chars.length == 0){
            return;
        }
        int j = chars.length - 1;
        for(int i = chars.length - 1; i >= 0; i--){
            if(chars[i] != '*'){
                chars[j--] = chars[i];
            }
        }
        for( ; j >= 0 ; ){
            chars[j--] = '*';
        }
    }
}
