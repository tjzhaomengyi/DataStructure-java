package com.book.zuoshen.InterviewGuide.chpt5;

/**
 * @Author: zhaomengyi
 * @Date: 2024/2/20 10:05
 * @Description:对字符串进行最少分割得到回文
 */
public class CutToPalidrome {
    public int minCut(String str){
        if(str == null || str.equals("")){
            return 0;
        }
        char[] chs = str.toCharArray();
        int len = chs.length;
        int[] dp = new int[len + 1]; //从0开始到len-1为止的最小分割数
        dp[len] = -1;
        boolean[][] p = new boolean[len][len];
        for(int i = len - 1; i >= 0; i--){
            dp[i] = Integer.MAX_VALUE;
            for(int j = i; j < len; j++){
                if(chs[i] == chs[j] && (j - i < 2 || p[i+1][j-1])){ //如果(1)j和i只有1个或者两个字符 或者 (2)i和j内部已经成了回文
                    p[i][j] = true;//先更新表
                    dp[i] = Math.min(dp[i], dp[j + 1] + 1); //dp[j + 1] + 1后面划分出来的再加上当前这组
                }
            }
        }
        return dp[0];
    }

    //这个方法更好理解
    public int minCutByPart(String str){
        if(str == null || str.equals("") || str.length() < 2){
            return 0;
        }
        char[] chs = str.toCharArray();
        int len = str.length();
        boolean[][] map = createCheckMap(chs, len);
        int[] dp = new int[len];
//        dp[len - 1] = 1; //这里起始可以不管，反正下面dp[i]要赋值
        for(int i = len - 1; i >= 0; i--){
            if(map[i][len - 1]){
                dp[i] = 1;
            } else {
                int next = Integer.MAX_VALUE;
                for(int j = i; j < len; j++){
                    if(map[i][j]){
                        next = Math.min(next, dp[j + 1]);
                    }
                }
                dp[i] = next + 1;//前面划分加上当前这组
            }
        }
        return dp[0] - 1;
    }

    public static boolean[][] createCheckMap(char[] str, int N){
        boolean[][] ans = new boolean[N][N];
        //先填写对角线
        for(int i = 0; i < N - 1; i++){
            ans[i][i] = true;
            ans[i][i + 1] = str[i] == str[i + 1];
        }
        ans[N - 1][N - 1] = true;
        //根据表格填写右半区
        for(int i = N - 3; i >= 0; i--){
            for(int j = i + 2; j < N; j++){
                ans[i][j] = str[i] == str[j] && ans[i + 1][j - 1];//修改表格
            }
        }
        return ans;
    }
}
