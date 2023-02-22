package com.mikemyzhao.DP.DP.bigshua;

/**
 * @Author: zhaomengyi
 * @Date: 2022-04-16 23:00
 * @Description:
 */
public class LC_0091_DecodeWays {
  public static int numDecoding1(String s){
    if(s == null || s.length() == 0){
      return 0;
    }
    char[] str = s.toCharArray();
    return process(str,0);
  }

  //从inedx开始后面还有多少中转化方式，[0..index-1]已经搞定
  public static int process(char[] str, int index){
    if(index == str.length){
      return 1;//已经满了，这是一种方法
    }
    if(str[index] == '0'){
      return 0;
    }
    //技巧:情况1只用这1位
    int ways = process(str, index + 1);
    if(index + 1 == str.length){
      return ways;//这是最后一个了
    }
    int num = (str[index] - '0') * 10 + str[index + 1] - '0';
    if(num < 27){
      ways += process(str, index + 2);
    }
    return ways;
  }

  //这个必须要改成动态规划
  public static int numDecodings(String s){
    if(s == null || s.length() == 0){
      return 0;
    }
    char[] str = s.toCharArray();
    int N = str.length;
    int[] dp = new int[N + 1];
    dp[N] = 1;
    //要通过i + 2或者 i+1 推出i
    for(int i = N - 1; i >= 0; i--){
      if(str[i] != 0){
        dp[i] = dp[i+1];
        if(i + 1 == N){
          continue;
        }
        int num = (str[i] - '0') * 10 + (str[i + 1] - '0');
        if(num < 27){
          dp[i] += dp[i + 2];
        }
      }
    }
    return dp[0];
  }

  public int numDecodings2(String s) {
    if(s == null || s.length() == 0){
      return 0;
    }
    char[] str = s.toCharArray();
    int N = str.length;
    int[] dp = new int[N + 1];
    dp[N] = 1;
    //要通过i + 2或者 i+1 推出i
    for(int i = N - 1; i >= 0; i--){
      //技巧:这里如果等于'0'不做任何处理略过！‘0’字符必须和其他字符组合才行！自己没用的
     if(str[i] != '0') {
        dp[i] = dp[i+1];
        if(i + 1 == N){
          continue;
        }
        int num = (str[i] - '0') * 10 + (str[i + 1] - '0');
        if(num < 27){
          dp[i] += dp[i + 2];
        }
      }
    }
    return dp[0];
  }


}
