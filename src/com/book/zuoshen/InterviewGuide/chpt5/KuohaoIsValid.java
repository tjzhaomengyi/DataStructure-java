package com.book.zuoshen.InterviewGuide.chpt5;

/**
 * @Author: zhaomengyi
 * @Date: 2024/2/19 10:12
 * @Description:1、判断括号的有效性，2、返回最长的有效括号子串
 */
public class KuohaoIsValid {
    public boolean isValid(String str){
        if(str == null || str.length() == 0 || str.equals("")){
            return false;
        }
        char[] chs = str.toCharArray();
        int status = 0;
        for(int i = 0; i < chs.length; i++){
            if(chs[i] != '(' && chs[i] != ')'){
                return false;
            }
            if(chs[i] == ')' && --status < 0){
                return false;
            }
            if(chs[i] == '('){
                status++;
            }
        }
        return status == 0;
    }
    public int maxLength(String str){
        if(str == null || str.equals("")){
            return 0;
        }
        char[] chs = str.toCharArray();
        int[] dp = new int[chs.length];
        int pre = 0;
        int res = 0;
        for(int i = 1; i < chs.length; i++){
            if(chs[i] == ')'){ //当前字符是）有可能组成新的一组括号组
                pre = i - dp[i - 1] - 1; //找到上一个左括号或者有括号，判断这个位置的字符能不能和当前的chs[i]=')'组成新的括号组
                if(pre >= 0 && chs[pre] == '('){
                    dp[i] = dp[i - 1] + 2 + (pre > 0 ? dp[pre - 1] : 0); //()(())前面括号的情况，看一下pre前面是否有完整的括号组
                }
            }
            res = Math.max(res, dp[i]);//调整最大长度
        }
        return res;
    }
}
