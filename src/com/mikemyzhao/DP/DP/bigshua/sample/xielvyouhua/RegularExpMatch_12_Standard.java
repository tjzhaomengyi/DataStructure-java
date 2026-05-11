package com.mikemyzhao.DP.DP.bigshua.sample.xielvyouhua;

public class RegularExpMatch_12_Standard {
    /**
     * 匹配带‘。’ 和 ‘*’的标准写法为，* 可以匹配前一个字符的0个到多个，.只能匹配前一个字符的一个
     */

    public boolean isMatch(String s, String p){
        int m = s.length();
        int n= p.length();

        boolean[][] dp = new boolean[m + 1][n + 1];
        dp[0][0] = true;

        // 如果字符串p为 a*b*的情况，就是下面的特殊情况
        for(int j = 2; j <= n; j++){
            if(p.charAt(j - 1) == '*'){
                dp[0][j] = dp[0][j - 2];
            }
        }

        for(int i = 1; i <= m; i++){
            for(int j = 1; j <= n; j++){
                if(p.charAt(j - 1) == '*'){
                    dp[i][j] = dp[i][j - 2]; // baa  ba* ,dp[3][3] = dp[3][1]
                    if(p.charAt(j - 2) == s.charAt(i - 1) || p.charAt(j - 2) == '.'){
                        dp[i][j] |= dp[i - 1][j]; //现在dp[i][j]表示已经使用了这个a*，这里再“或”上i-1的s，表示这个a*不使用
                    } else {
                        if(p.charAt(j - 1) == s.charAt(i - 1) || p.charAt(j - 1) == '.'){
                            dp[i][j] = dp[i - 1][j - 1];
                        }
                    }
                }
            }
        }
        return dp[m][n];


    }


}
