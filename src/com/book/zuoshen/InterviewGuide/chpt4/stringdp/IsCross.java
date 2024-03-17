package com.book.zuoshen.InterviewGuide.chpt4.stringdp;

/**
 * @Author: zhaomengyi
 * @Date: 2024/2/7 18:09
 * @Description:判断一个字符串是否由两个字符串交互组成
 */
public class IsCross {
    public boolean isCross(String str1, String str2, String aim){
        if(str1 == null || str2 == null || aim == null){
            return false;
        }
        char[] ch1 = str1.toCharArray();
        char[] ch2 = str2.toCharArray();
        char[] chaim = aim.toCharArray();
        if(chaim.length != ch1.length + ch2.length){
            return false;
        }
        boolean[][] dp = new boolean[ch1.length + 1][ch2.length + 1];//长度
        dp[0][0] = true;//没有字符串表示可以交互而成
        for(int i = 1; i < ch1.length + 1; i++){
            if(chaim[i - 1] != ch1[i - 1]){
                break;
            }
            dp[i][0] = true;
        }
        for(int j = 1; j < ch2.length + 1; j++){
            if(chaim[j - 1] != ch2[j - 1]){
                break;
            }
            dp[0][j] = true;
        }

        for(int i = 1; i < ch1.length + 1; i++){
            for(int j = 1; j < ch2.length + 1; j++){
                //如果当前dp[i-1][j]为true，说明从ch1[0..i-2]和ch2[0..j-1]可以交互组成aim[0..i+j-2],如果aim[i+j-1]==ch1[i-1]即可实现i+j长度的交互
                //如果当前dp[i][j - 1]为true，说明从ch1[0..i-1]和ch2[0..j-2]可以交互组成aim[0..i+j-2],如果aim[i+j-1] == ch2[j-1]即可实现i+j长度的交互
                if(( dp[i - 1][j] && chaim[i + j - 1] == ch1[i - 1] ) ||
                        ( dp[i][j] && chaim[i + j - 1] == ch2[j - 1] )
                ){
                   dp[i][j] = true;
                }
            }
        }
        return dp[ch1.length][ch2.length];
    }

}
