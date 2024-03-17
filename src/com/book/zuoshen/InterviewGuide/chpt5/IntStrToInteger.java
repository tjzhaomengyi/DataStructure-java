package com.book.zuoshen.InterviewGuide.chpt5;

/**
 * @Author: zhaomengyi
 * @Date: 2024/2/14 11:55
 * @Description:
 */
public class IntStrToInteger {
    public boolean isValid(char[] chars){
        if(chars[0] != '-' && (chars[0] < '0' || chars[0] > '9')){
            return false;
        }
        if(chars[0] == '0' && (chars.length == 1 || chars[1] == '0')){
            return false;
        }
        if(chars[0] == '0' && chars.length > 1){
            return false;
        }
        for(int i = 1; i < chars.length; i++){
            if(chars[i] < '0' || chars[i] > '9'){
                return false;
            }
        }
        return true;
    }

    public int convert(String str){
        if(str == null || str.equals("")){
            return 0;
        }
        char[] chas = str.toCharArray();
        if(!isValid(chas)){
            return 0;
        }
        boolean posi = chas[0] == '-' ? false : true;
        int minq = Integer.MIN_VALUE / 10;
        int minr = Integer.MIN_VALUE % 10;
        int res = 0;
        int cur = 0;
        for(int i = posi ? 0 : 1; i < chas.length; i++){
            cur = '0' - chas[i];
            if((res < minq) || (res == minq && cur < minr)){
                return 0;
            }
            res = res * 10 + cur;
        }
        if(posi && res == Integer.MIN_VALUE){
            return 0;
        }
        return posi ? -res : res;
    }
}
