package com.mikemyzhao.DPAndRecursion_5.DP.bigshua.sample.xielvyouhua;


/**
 * @Author: zhaomengyi
 * @Date: 2022-03-24 8:55
 * @Description:
 */
public class RegularExpMatch_12 {
  //基础有效性
  public static boolean isValid(char[] s, char[] e){
    //s中不能有.和*
    for(int i = 0; i < s.length; i++){
      if(s[i] == '*' || s[i] == '.'){
        return false;
      }
    }
    //*的特殊情况开头不可以是，也不能连着
    for(int i = 0; i < e.length; i++){
      if(e[i] == '*' && (i == 0 || e[i - 1] == '*')){
        return false;
      }
    }
    return true;
  }

  //尝试版本,迭代
  public static boolean isMatch(String str, String exp){
    if(str == null || exp == null){
      return false;
    }
    char[] s = str.toCharArray();
    char[] e = exp.toCharArray();
    return isValid(s, e) && process(s, e, 0,0);
  }

  //str[si...]能不能被exp[ei...]配出来
  public static boolean process(char[] s, char[] e,int si, int ei){
    if(ei == e.length){
      return si == s.length;
    }
    //【以i+1位置结尾】todo:考虑ei的下一个位置是不是*,所以每次匹配是考虑ei,ei+1这两个位置，都考虑完了还不满足可以考虑ei+2对si进行匹配就是最后的返回结果的意思
    if(ei + 1 == e.length || e[ei + 1] != '*'){//exp的ei + 1表达式结束，或者ei+1为*
      //如果ei+1不是* 这里要求s[si]要和e[ei]可以配上，如果等于终值位置就配不上了~，表达式和ei都有位置
      //要不咱俩一致，要不是混子，并且后续还都得一样！
      return si != s.length && (e[ei] == s[si] || e[ei] == '.') && process(s, e, si + 1, ei + 1);
    }
    //ei+1是*
    while(si != s.length && (e[ei] == s[si] || e[ei] == '.')){
      if(process(s, e, si, ei + 2)){
        return true;//后面都成立这个才成立
      }
      si++;
    }
    //如果si结尾了
    //aaaaa
    //c*
    return process(s, e, si, ei + 2);
  }

  //记忆化搜索 + 斜率优化
  public static boolean isMatch2(String str, String exp){
    if(str == null || exp == null){
      return false;
    }
    char[] s = str.toCharArray();
    char[] e = exp.toCharArray();
    if(!isValid(s, e)){
      return false;
    }
    //dp = -1表示算过false，0没算过，1true
    int[][] dp = new int[s.length + 1][e.length + 1];//因为要用到i+1
    return isValid(s, e) && process2(s, e, 0, 0, dp);
  }

  public static boolean process2(char[] s,char[] e,int si, int ei, int[][] dp){
    if(dp[si][ei] != 0){
      return dp[si][ei] == 1;
    }
    boolean ans = false;
    //不断考察ei位置
    if(ei == e.length){
      ans = si == s.length;
    }else{
      if(ei + 1 == e.length || e[ei + 1] != '*'){
        ans = si != s.length && (e[ei] == s[si] || e[ei] == '.') && process2(s, e, si + 1, ei + 1, dp);
      }else{
        if(si == s.length){
          ans = process2(s,e,si,ei+2,dp);
        }else{
          if(s[si] != e[ei] && e[ei] != '.'){//把while优化掉了
            ans = process2(s, e, si, ei + 2, dp);
          } else {//斜率优化
            ans = process2(s, e, si, ei + 2, dp) || process2(s, e, si + 1, ei, dp);
          }
        }
      }
    }
    dp[si][ei] = ans ? 1 : -1;
    return ans;
  }
}
