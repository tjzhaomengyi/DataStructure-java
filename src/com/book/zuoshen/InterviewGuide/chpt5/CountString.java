package com.book.zuoshen.InterviewGuide.chpt5;

/**
 * @Author: zhaomengyi
 * @Date: 2024/2/14 12:12
 * @Description:字符串统计
 */
public class CountString {
    public String getCountString(String str){
        if (str == null || str.equals("")) {
            return "";
        }
        char[] chs = str.toCharArray();
        String res = String.valueOf(chs[0]);
        int num = 1;
        for(int i = 1; i < chs.length; i++){
            if(chs[i] != chs[i - 1]){
                res = concat(res, String.valueOf(num), String.valueOf(chs[i]));
                num = 1;
            } else {
                num++;
            }
        }
        return concat(res, String.valueOf(num), "");
    }

    public String concat(String s1, String s2, String s3){
        return s1 + "_" + s2 + (s3.equals("") ? s3 : "_" + s3);
    }
}
