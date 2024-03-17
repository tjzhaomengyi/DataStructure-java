package com.book.zuoshen.InterviewGuide.chpt4.stringdp;

/**
 * @Author: zhaomengyi
 * @Date: 2024/2/8 14:17
 * @Description:根据给出数字字符串求出可能转出多少种字符串
 */
public class NumToChars {
    public int num(String str){
        if(str == null || str.length() == 0){
            return 0;
        }
        char[] chars = str.toCharArray();
        return process(chars, 0);
    }

    public int process(char[] chs, int i){
        if(i == chs.length){
            return 1;
        }
        if(chs[i] == '0'){
            return 0;
        }
        int res = process(chs, i + 1);
        if(i + 1 < chs.length && (chs[i] - '0') * 10 + chs[i + 1] - '0' < 27){
            res += process(chs, i + 2);
        }
        return res;
    }

    public int numDPBook(String str){
        if(str == null || str.equals("")) {
            return 0;
        }
        char[] chs = str.toCharArray();
        int cur = chs[chs.length - 1] == '0' ? 0 : 1;//最后一位如果是0没有方法数凑出来
        int next = 1;//统计能生成的总数，根据递归我们知道，只要i到了长度N，那么就是一种方法
        int tmp = 0;
        for(int i = chs.length - 2; i >= 0; i--){
            if(chs[i] == '0'){
                next = cur;
                cur = 0;
            } else {
                tmp = cur;
                if((chs[i] - '0') * 10 + chs[i + 1] - '0' < 27){
                    cur += next;
                }
                next = tmp;
            }
        }
        return cur;
    }


    public static int numDP(String str){
        if(str == null || str.equals("") || str.charAt(0) == '0'){
            return 0;
        }

        char[] chs = str.toCharArray();
        int N = chs.length;
        //dp[i]表示到这一位和有多少种表示方法
        int[] dp = new int[N];
        dp[0] = 1;//以i结尾有多少种转换方法
//        dp[1] = (chs[0] - '0') * 10 + chs[1] - '0' < 27 ? 2 : 1;
        for(int i = 1; i < N; i++){
            if(chs[i] == '0'){
                if(chs[i - 1] == '0' || chs[i - 1] > '2' || (i - 2 >= 0 && dp[i - 2] == 0)) {//情况（1）：不合理的条件不能组成数字
                    return 0;
                } else {
                    dp[i] = i - 2 >= 0 ? dp[i - 2] : 1;
                }
            } else {
                dp[i] = dp[i - 1];
                if(chs[i - 1] != '0' && (chs[i - 1] - '0') * 10 + chs[i] - '0' < 27){
                    dp[i] += i - 2 >= 0 ? dp[i - 2] : 1;
                }
            }
        }
        return dp[N - 1];
    }

    public int numDPRightToLeft(String str){
        if(str == null || str.equals("") || str.charAt(0) == '0'){
            return 0;
        }
        //以i结尾可以有多少种方法
        char[] s = str.toCharArray();
        int N = s.length;
        int[] dp = new int[N + 1];
        dp[N] = 1;
        for(int i = N - 1; i >= 0; i--){
            if (s[i] != '0'){
                int ways = dp[i + 1];
                if (i + 1 < str.length() && (s[i] - '0') * 10 + s[i + 1] - '0' < 27){
                    ways += dp[i + 2];
                }
                dp[i] = ways;
            }
        }
        return dp[0];
    }
}
