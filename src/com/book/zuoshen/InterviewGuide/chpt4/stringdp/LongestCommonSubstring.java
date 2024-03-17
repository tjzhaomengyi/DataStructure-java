package com.book.zuoshen.InterviewGuide.chpt4.stringdp;

/**
 * @Author: zhaomengyi
 * @Date: 2024/2/6 17:27
 * @Description:返回最长公共子串，子数组和子串问题直接反应就是以i结尾得到最大的子串/子数组结果
 */
public class LongestCommonSubstring {
    //最长公共子串中dp[i][j]的含义和最长公共子序列有些不同，表示：必须以str1[i]和str2[j]结尾组成的最长公共子串的长度是多少
    public int[][] getDP(char[] str1, char[] str2){
        int[][] dp = new int[str1.length][str2.length];
        for(int i = 0; i < str1.length; i++){
            if(str1[i] == str2[0]){
                dp[i][0] = 1;
            }
        }
        for(int j = 0; j < str2.length; j++){
            if(str1[0] == str2[j]){
                dp[0][j] = 1;
            }
        }
        for(int i = 1; i < str1.length; i++){
            for(int j = 1; j < str2.length; j++){
                if(str1[i] == str2[j]){
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                }
            }
        }
        return dp;
    }

    //找出最长公共子串
    public String lcst(String str1, String str2){
        if(str1 == null || str2 == null || str1.equals("") || str2.equals("")){
            return "";
        }
        char[] chars1 = str1.toCharArray();
        char[] chars2 = str2.toCharArray();
        int[][] dp = getDP(chars1, chars2);
        int end = 0;
        int max = 0;
        for(int i = 0; i < chars1.length; i++){
            for(int j = 0; j < chars2.length; j++){
                if(dp[i][j] > max){
                    end = i;
                    max = dp[i][j];
                }
            }
        }
        return str1.substring(end - max + 1, end + 1);
    }


    /**
     * 优化版本的dp，这个问题从dp[i][j]明显看出是一个斜线优化
     */
    public String lcst2(String str1, String str2){
        if(str1 == null || str2 == null || str1.equals("") || str2.equals("")){
            return "";
        }
        char[] chs1 = str1.toCharArray();
        char[] chs2 = str2.toCharArray();
        int row = 0;//斜线开始的行
        int col = chs2.length - 1; //斜线开始的列
        int max = 0; //记录最大长度
        int end = 0;//最大长度更新时，记录子串的结尾位置

        while(row < chs1.length){
            int i = row;
            int j = col;
            int len = 0;
            //从(i,j)开始往右下方遍历
            while(i < chs1.length && j < chs2.length){
                if(chs1[i] != chs2[j]){
                    len = 0;
                } else {
                    len++;
                }
                if(len > max){
                    end = i;
                    max = len;
                }
                i++;
                j++;
            }
            if(col > 0){
                col--;
            } else {
                row++;
            }
        }
        return str1.substring(end - max + 1, end + 1);
    }


}
