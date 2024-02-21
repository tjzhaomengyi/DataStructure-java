package com.book.zuoshen.InterviewGuide.chpt5;

/**
 * @Author: zhaomengyi
 * @Date: 2024/2/18 18:10
 * @Description:添加最小的字符个数，让字符串变成回文
 */
public class AddLessCharToPalindrome {
    //使用动态规划来计算添加多少字符变成回文
    public int[][] getDP(char[] str){
        int[][] dp = new int[str.length][str.length];//dp[i][j]表示str[i..j]添加多少字符可以变成回文字符
        for(int j = 1; j < str.length; j++){
            dp[j - 1][j] = str[j - 1] == str[j] ? 0 : 1;
            for(int i = j - 2; i > -1; i--){
                if(str[i] == str[j]){
                    dp[i][j] = dp[i + 1][j - 1];
                } else {
                    dp[i][j] = Math.min(dp[i + 1][j], dp[i][j - 1] + 1);
                }
            }
        }
        return dp;
    }

    public String getPalindrome1(String str){
        if(str == null || str.length() < 2){
            return str;
        }
        char[] chs = str.toCharArray();
        int[][] dp = getDP(chs);
        char[] res = new char[chs.length + dp[0][chs.length - 1]];
        int i = 0;
        int j = chs.length - 1;
        int resl = 0; //向res中填充用的左指针
        int resr = res.length - 1; //向res填充用的右指针
        while(i <= j){
            if(chs[i] == chs[j]){
                res[resl++] = chs[i++];
                res[resr--] = chs[j--];
            } else if(dp[i][j - 1] < dp[i + 1][j]){//右侧添加字符串少
                res[resl++] = chs[j];//让resl当前位置和j凑成一组对称，i到j-1已经成为回文
                res[resr--] = chs[j--];//右侧回收
            } else {
                res[resl++] = chs[i];//先把chs[i]填充到res[resl]中
                res[resr--] = chs[i++]; //让后让res[r]位置的内容填充上chs[i]的内容
            }
        }
        return String.valueOf(res);
    }
}
